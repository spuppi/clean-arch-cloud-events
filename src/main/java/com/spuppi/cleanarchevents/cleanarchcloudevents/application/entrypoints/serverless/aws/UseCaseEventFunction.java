package com.spuppi.cleanarchevents.cleanarchcloudevents.application.entrypoints.serverless.aws;

import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.model.UseCaseEvent;
import org.springframework.context.event.EventListener;

import java.util.function.Consumer;

public class UseCaseEventFunction implements Consumer<UseCaseEvent> {

//    @Autowired
//    private ObjectMapper objectMapper;

    @Override
    public void accept(UseCaseEvent event) {

        System.out.println("Evento recebido");
        System.out.println(event);

//        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
//        provider.addIncludeFilter(new AnnotationTypeFilter(UseCase.class));
//        String scanPath = "com.spuppi.cleanarchevents.cleanarchcloudevents.core.usecases";
//        String useCase = null;
//        Class<?> eventType = null;
//        try {
//            for (BeanDefinition beanDef : provider.findCandidateComponents(scanPath)) {
//                useCase = Class.forName(beanDef.getBeanClassName()).getAnnotation(UseCase.class).name();
//                eventType = Class.forName(Class.forName(beanDef.getBeanClassName()).getAnnotation(UseCase.class).eventType());
//                if (useCase.equalsIgnoreCase(event.getHeaders().get("usecase"))) {
//                    eventPublisher.publishEvent(objectMapper.convertValue(event.getPayload(), eventType));
//                    event.setStatus(EventStatus.CREATED);
//                }else{
//                    event.setStatus(EventStatus.NOT_FOUND);
//                }
//                break;
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
