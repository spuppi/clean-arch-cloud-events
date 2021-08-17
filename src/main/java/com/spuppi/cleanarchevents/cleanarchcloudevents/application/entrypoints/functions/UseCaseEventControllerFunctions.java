package com.spuppi.cleanarchevents.cleanarchcloudevents.application.entrypoints.functions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.annotation.UseCase;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.enums.EventStatus;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.model.UseCaseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.function.Consumer;

public class UseCaseEventControllerFunctions implements Consumer<UseCaseEvent> {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void accept(UseCaseEvent event) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(UseCase.class));
        String scanPath = "com.spuppi.cleanarchevents.cleanarchcloudevents.core.usecases";
        String useCase = null;
        Class<?> eventType = null;
        try {
            for (BeanDefinition beanDef : provider.findCandidateComponents(scanPath)) {
                useCase = Class.forName(beanDef.getBeanClassName()).getAnnotation(UseCase.class).name();
                eventType = Class.forName(Class.forName(beanDef.getBeanClassName()).getAnnotation(UseCase.class).eventType());
                if (useCase.equalsIgnoreCase(event.getHeaders().get("usecase"))) {
                    eventPublisher.publishEvent(objectMapper.convertValue(event.getPayload(), eventType));
                    event.setStatus(EventStatus.CREATED);
                }else{
                    event.setStatus(EventStatus.NOT_FOUND);
                }
                break;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
