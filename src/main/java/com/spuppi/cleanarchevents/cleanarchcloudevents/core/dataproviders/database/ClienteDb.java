package com.spuppi.cleanarchevents.cleanarchcloudevents.core.dataproviders.database;

import com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.dataproviders.database.DatabaseClientePort;
import com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.dataproviders.database.SimulacaoPersistenciaPort;
import com.spuppi.cleanarchevents.cleanarchcloudevents.core.dataproviders.database.model.Tbaz111Model;
import com.spuppi.cleanarchevents.cleanarchcloudevents.core.dataproviders.database.repositories.Tbaz111_Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteDb implements DatabaseClientePort {

//    @Autowired
//    private Tbaz111_Cliente tbaz111;

    @Autowired
    private SimulacaoPersistenciaPort simulacao;

    //IMPLEMENTAR BANCO DE DADOS DYNAMODB

    @Override
    public void salvarCliente(String transactionId, String nome, String cpf) {

        Tbaz111Model cliente = Tbaz111Model.builder()
                .transactionId(transactionId)
                .nome(nome)
                .cpf(cpf)
                .build();

        System.out.println("--------SALVAR CLIENTE---------");
        System.out.println("transactionId: " + transactionId + "nome: " + nome + "cpf: " + cpf);

        simulacao.salvarCliente(cliente);

//        tbaz111.save(cliente);
    }
}
