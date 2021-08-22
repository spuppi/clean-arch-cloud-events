package com.spuppi.cleanarchevents.cleanarchcloudevents.application.entrypoints.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.annotation.UseCase;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.annotation.UseCaseInit;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.enums.EventStatus;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.model.UseCaseEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;

@Api(value = "Swagger2DemoRestControllerJsonEvent")
@RestController(value = "/v1/event")
public class UseCaseControllerRest {

    private static final String CORE_USECASES = "com.spuppi.cleanarchevents.cleanarchcloudevents.core.usecases";

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation(value = "Criar um use case event json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Evento criado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/usecaseevent", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<UseCaseEvent> useCaseEventJson(
            @RequestBody UseCaseEvent useCaseEvent) {

        System.out.println(useCaseEvent.getPayload());

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(UseCase.class));
        String scanPath = CORE_USECASES;
        Object eventProcessed = null;
        try {
            for (BeanDefinition beanDef : provider.findCandidateComponents(scanPath)) {
                Class<?> useCaseClass = Class.forName(beanDef.getBeanClassName());
                String useCase = useCaseClass.getAnnotation(UseCase.class).name();
                if (useCase.equalsIgnoreCase(useCaseEvent.getHeaders().get("usecase"))) {
                    Method[] methods = useCaseClass.getMethods();
                    for(Method method : methods) {
                        if(method.isAnnotationPresent(UseCaseInit.class)){
                            Class<?> eventType = method.getParameterTypes()[0];
                            try {
                                eventPublisher.publishEvent(objectMapper.readValue(String.valueOf(useCaseEvent.getPayload()), eventType));
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

        return ResponseEntity.ok(useCaseEvent);
    }
}
