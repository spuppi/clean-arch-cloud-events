package com.spuppi.cleanarchevents.cleanarchcloudevents.application.entrypoints.serverless.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.annotation.UseCase;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.enums.EventStatus;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.model.UseCaseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Map;

public class EventFunctionsAws implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context) {

        objectMapper = new ObjectMapper();

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(UseCase.class));
        String scanPath = "com.spuppi.cleanarchevents.cleanarchcloudevents.core.usecases";
        String useCase = null;
        Class<?> eventType = null;

        context.getLogger().log("apiGatewayProxyRequestEvent: " + apiGatewayProxyRequestEvent);
        context.getLogger().log("apiGatewayProxyRequestEvent.getBody(): " + apiGatewayProxyRequestEvent.getBody());

        UseCaseEvent event = null;
        try {
            event = objectMapper.readValue(apiGatewayProxyRequestEvent.getBody(), UseCaseEvent.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

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
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(event.getStatus().ordinal())
                .withHeaders(event.getHeaders())
                .withBody(String.valueOf(event.getPayload()));
    }
}
