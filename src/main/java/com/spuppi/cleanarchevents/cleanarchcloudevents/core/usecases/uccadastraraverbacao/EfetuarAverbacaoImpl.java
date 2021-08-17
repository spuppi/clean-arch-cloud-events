package com.spuppi.cleanarchevents.cleanarchcloudevents.core.usecases.uccadastraraverbacao;

import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.annotation.UseCase;
import com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.dataproviders.database.DatabaseClientePort;
import com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoEventRequest;
import com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@UseCase(name = "uccadastraraverbacao",
        eventType = "com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoEventRequest")
public class EfetuarAverbacaoImpl implements EfetuarAverbacaoPort{

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private DatabaseClientePort clienteDb;

    @Override
    public void accept(EfetuarAverbacaoEventRequest efetuarAverbacaoEventRequest) {
        eventPublisher.publishEvent(efetuarAverbacaoEventRequest);
    }

    @Async
    @EventListener
    @Override
    public void execute(EfetuarAverbacaoEventRequest efetuarAverbacaoEventRequest) {

        String transactionId = UUID.randomUUID().toString();
        efetuarAverbacaoEventRequest.setTransactionId(transactionId);

        clienteDb.salvarCliente(efetuarAverbacaoEventRequest.getTransactionId(), efetuarAverbacaoEventRequest.getNome(), efetuarAverbacaoEventRequest.getCpf());

        System.out.println(efetuarAverbacaoEventRequest);
    }
}
