package com.spuppi.cleanarchevents.cleanarchcloudevents.application.entrypoints.functions;

import com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoEventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public class EfetuarAverbacaoFunction implements Consumer<EfetuarAverbacaoEventRequest> {

    //curl localhost:8080/springFunctionEvent -H "Content-Type: text/plain" -d "{\"cpf\": \"123123\", \"nome\": \"asdad\", \"tipoContrato\": 1, \"transactionId\": \"12311\"}"

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void accept(EfetuarAverbacaoEventRequest efetuarAverbacaoEventRequest) {
        eventPublisher.publishEvent(efetuarAverbacaoEventRequest);
    }
}