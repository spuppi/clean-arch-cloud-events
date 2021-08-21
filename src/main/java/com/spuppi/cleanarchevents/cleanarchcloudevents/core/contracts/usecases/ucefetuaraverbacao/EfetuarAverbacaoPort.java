package com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.usecases.ucefetuaraverbacao;

import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Component
public interface EfetuarAverbacaoPort{

    String execute(EfetuarAverbacaoEventRequest efetuarAverbacaoEventRequest);
}
