package com.spuppi.cleanarchevents.cleanarchcloudevents.application.entrypoints.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.annotation.UseCase;
import com.spuppi.cleanarchevents.cleanarchcloudevents.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoEventRequest;
import com.spuppi.cleanarchevents.cleanarchcloudevents.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoEventResponse;
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
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;

@Api(value = "Swagger2DemoRestController")
@RestController(value = "/v1")
public class AverbacaoControllerRest {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private ObjectMapper objectMapper;

    @ApiOperation(value = "Efetuar uma averbacao")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Averbacao efetuada com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/averbacao", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<EfetuarAverbacaoEventResponse> efetuarAverbacao(
            @RequestHeader String usecase,
            @RequestBody EfetuarAverbacaoEventRequest requestEvent){

        System.out.println("Requeste recebido");
        System.out.println(requestEvent);
        System.out.println("Gerar evento");

        eventPublisher.publishEvent(requestEvent);

        EfetuarAverbacaoEventResponse response = EfetuarAverbacaoEventResponse.builder().build();

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Efetuar uma averbacao json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Averbacao efetuada com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/averbacaojson", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<EfetuarAverbacaoEventResponse> efetuarAverbacaoJson(
            @RequestHeader String usecase,
            @RequestBody String jsonRequestEvent) throws JsonProcessingException {

        System.out.println("Requeste recebido");
        System.out.println(jsonRequestEvent);
        System.out.println("Gerar evento");

        eventPublisher.publishEvent(objectMapper.readValue(jsonRequestEvent, EfetuarAverbacaoEventRequest.class));

        EfetuarAverbacaoEventResponse response = EfetuarAverbacaoEventResponse.builder().build();

        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Efetuar uma averbacao json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Averbacao efetuada com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @RequestMapping(value = "/averbacaobuscarevento", method = RequestMethod.POST, produces="application/json")
    public ResponseEntity<EfetuarAverbacaoEventResponse> efetuarAverbacaoBuscarJson(
            @RequestHeader String usecase,
            @RequestBody String jsonRequestEvent) throws JsonProcessingException, ClassNotFoundException {

        System.out.println("Requeste recebido");
        System.out.println(jsonRequestEvent);
        System.out.println("Gerar evento");

        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(UseCase.class));
        String scanPath = "com.spuppi.cleanarchevents.cleanarchcloudevents.usecases";
        String useCase = null;
        Class<?> clazzEvent = null;

        for (BeanDefinition beanDef : provider.findCandidateComponents(scanPath)) {
            useCase = Class.forName(beanDef.getBeanClassName()).getAnnotation(UseCase.class).name();
            clazzEvent = Class.forName(Class.forName(beanDef.getBeanClassName()).getAnnotation(UseCase.class).eventRequest());
            if(useCase.equalsIgnoreCase(usecase)){
                eventPublisher.publishEvent(objectMapper.readValue(jsonRequestEvent, clazzEvent));
            }
            break;
        }

        EfetuarAverbacaoEventResponse response = EfetuarAverbacaoEventResponse.builder().build();

        return ResponseEntity.ok(response);
    }
}
