package com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.usecases.ucefetuaraverbacao;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public interface EfetuarAverbacaoPort extends Consumer<EfetuarAverbacaoEventRequest> {

    void execute(EfetuarAverbacaoEventRequest efetuarAverbacaoEventRequest);
}
