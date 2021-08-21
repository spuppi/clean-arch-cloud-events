package com.spuppi.cleanarchevents.cleanarchcloudevents.application.entrypoints.serverless.aws;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.annotation.UseCase;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.enums.EventStatus;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.enums.EventType;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.model.UseCaseEvent;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class UseCaseEventFunctionAdapterAws implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent>{

    //curl https://pvid8uk8vi.execute-api.us-east-1.amazonaws.com/api/usecaseeventfunctionadapteraws -H "Content-Type: text/plain" -d "{\"cpf\": \"123123\", \"nome\": \"asdad\", \"tipoContrato\": 1, \"transactionId\": \"12311\"}"

    static AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
            "com.spuppi.cleanarchevents.cleanarchcloudevents.core");

    public UseCaseEventFunctionAdapterAws() {
        ctx.getAutowireCapableBeanFactory().autowireBean(this);
    }

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {

        ObjectMapper objectMapper = new ObjectMapper();

        context.getLogger().log("Java HTTP trigger processed a request.");
        context.getLogger().log(String.valueOf(event));

        UseCaseEvent useCaseEvent = new UseCaseEvent();
        useCaseEvent.setPayload(String.valueOf(event.getBody()));
        useCaseEvent.setEventType(EventType.AWS_API_GATEWAY);
        useCaseEvent.getHeaders().put("usecase","uccadastraraverbacao");

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(UseCase.class));
        String scanPath = "com.spuppi.cleanarchevents.cleanarchcloudevents.core.usecases";
        Object eventProcessed = null;
        try {
            for (BeanDefinition beanDef : provider.findCandidateComponents(scanPath)) {
                Class<?> useCaseClass = Class.forName(beanDef.getBeanClassName());
                String useCase = useCaseClass.getAnnotation(UseCase.class).name();
                Class<?> eventType = Class.forName(Class.forName(beanDef.getBeanClassName()).getAnnotation(UseCase.class).eventType());
                if (useCase.equalsIgnoreCase(useCaseEvent.getHeaders().get("usecase"))) {
                    Method[] methods = useCaseClass.getMethods();
                    for(Method method : methods) {
                        if(method.getName().equalsIgnoreCase("execute")) {
                            try {
                                context.getLogger().log(String.valueOf(objectMapper.readValue(event.getBody(), eventType)));
                                Object instanceInvoke = useCaseClass.newInstance();
                                ctx.getAutowireCapableBeanFactory().autowireBean(instanceInvoke);
                                eventProcessed = method.invoke(instanceInvoke, objectMapper.readValue(event.getBody(), eventType));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    useCaseEvent.setStatus(EventStatus.CREATED);
                }else{
                    useCaseEvent.setStatus(EventStatus.NOT_FOUND);
                }
                break;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        APIGatewayProxyResponseEvent responseEvent = new APIGatewayProxyResponseEvent();
        responseEvent.setBody("Certo!!: \n" + String.valueOf(eventProcessed));
        responseEvent.setStatusCode(200);
        Map<String, String> headers = new HashMap<>();
        headers.put("X-Powered-By", "AWS Lambda & Serverless");
        headers.put("Content-Type", "application/json");
        responseEvent.setHeaders(headers);
        return responseEvent;
    }

}