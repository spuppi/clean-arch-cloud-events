package com.spuppi.cleanarchevents.cleanarchcloudevents.usecases.uccadastraraverbacao;

import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.annotation.UseCase;
import com.spuppi.cleanarchevents.cleanarchcloudevents.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoEventRequest;
import com.spuppi.cleanarchevents.cleanarchcloudevents.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoEventResponse;
import com.spuppi.cleanarchevents.cleanarchcloudevents.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoPort;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@UseCase(name = "uccadastraraverbacao", eventRequest = "com.spuppi.cleanarchevents.cleanarchcloudevents.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoEventRequest")
public class EfetuarAverbacaoImpl implements EfetuarAverbacaoPort {

    @Async
    @EventListener
    @Override
    public EfetuarAverbacaoEventResponse execute(EfetuarAverbacaoEventRequest request) {

        System.out.println("Ouviu evento");
        System.out.println(request);

        EfetuarAverbacaoEventResponse response = EfetuarAverbacaoEventResponse.builder().build();

        return response;
    }
}
