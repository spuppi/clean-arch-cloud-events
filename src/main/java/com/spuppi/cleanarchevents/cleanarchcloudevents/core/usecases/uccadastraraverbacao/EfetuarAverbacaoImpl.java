package com.spuppi.cleanarchevents.cleanarchcloudevents.core.usecases.uccadastraraverbacao;

import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.annotation.UseCase;
import com.spuppi.cleanarchevents.cleanarchcloudevents.application.configuration.usecaseevent.annotation.UseCaseInit;
import com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.dataproviders.database.DatabaseClientePort;
import com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoEventRequest;
import com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.usecases.ucefetuaraverbacao.EfetuarAverbacaoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@UseCase(name = "uccadastraraverbacao")
public class EfetuarAverbacaoImpl implements EfetuarAverbacaoPort{

    @Autowired
    private DatabaseClientePort clienteDb;

    @UseCaseInit
    @Async
    @EventListener
    @Override
    public String execute(EfetuarAverbacaoEventRequest efetuarAverbacaoEventRequest) {

        System.out.println("iniciar use case");

        String transactionId = UUID.randomUUID().toString();
        efetuarAverbacaoEventRequest.setTransactionId(transactionId);

        clienteDb.salvarCliente(efetuarAverbacaoEventRequest.getTransactionId(), efetuarAverbacaoEventRequest.getNome(), efetuarAverbacaoEventRequest.getCpf());

        return "Dentro do usecase e processado corretamente!!!";
    }
}
