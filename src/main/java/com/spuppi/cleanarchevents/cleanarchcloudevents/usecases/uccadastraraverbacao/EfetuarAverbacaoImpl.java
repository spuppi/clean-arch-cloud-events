package com.spuppi.cleanarchevents.cleanarchcloudevents.usecases.uccadastraraverbacao;

import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.annotation.UseCase;
import com.spuppi.cleanarchevents.cleanarchcloudevents.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoPort;
import com.spuppi.cleanarchevents.cleanarchcloudevents.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@UseCase(name = "uccadastraraverbacao",
        eventType = "com.spuppi.cleanarchevents.cleanarchcloudevents.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoRequest")
public class EfetuarAverbacaoImpl implements EfetuarAverbacaoPort {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Async
    @EventListener
    @Override
    public void execute(EfetuarAverbacaoRequest request) {

        String transactionId = UUID.randomUUID().toString();
        request.setTransactionId(transactionId);

        System.out.println(request);
    }
}
