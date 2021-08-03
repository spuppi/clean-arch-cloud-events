package com.spuppi.cleanarchevents.cleanarchcloudevents.contracts.usecases.ucefetuaraverbacao;

import org.springframework.stereotype.Component;

@Component
public interface EfetuarAverbacaoPort {

    void execute(EfetuarAverbacaoRequest request);
}
