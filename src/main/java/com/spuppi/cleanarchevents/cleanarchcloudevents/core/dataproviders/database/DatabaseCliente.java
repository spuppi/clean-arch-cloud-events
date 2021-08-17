package com.spuppi.cleanarchevents.cleanarchcloudevents.core.dataproviders.database;

import com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.dataproviders.database.DatabaseClientePort;
import com.spuppi.cleanarchevents.cleanarchcloudevents.core.dataproviders.database.model.Tbaz111Model;
import com.spuppi.cleanarchevents.cleanarchcloudevents.core.dataproviders.database.repositories.Tbaz111_Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseCliente implements DatabaseClientePort {

    @Autowired
    private Tbaz111_Cliente tbaz111;

    @Override
    public void salvarCliente(String transactionId, String nome, String cpf) {

        Tbaz111Model cliente = Tbaz111Model.builder()
                .transactionId(transactionId)
                .nome(nome)
                .cpf(cpf)
                .build();

        System.out.println("--------SALVAR CLIENTE---------");
        System.out.println(cliente);

        //tbaz111.save(cliente);
    }
}
