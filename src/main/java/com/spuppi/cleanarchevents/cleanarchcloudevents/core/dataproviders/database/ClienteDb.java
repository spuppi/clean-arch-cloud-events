package com.spuppi.cleanarchevents.cleanarchcloudevents.core.dataproviders.database;

import com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.dataproviders.database.DatabaseClientePort;
import org.springframework.stereotype.Component;

@Component
public class ClienteDb implements DatabaseClientePort {

//    @Autowired
//    private Tbaz111_Cliente tbaz111;

    @Override
    public void salvarCliente(String transactionId, String nome, String cpf) {

//        Tbaz111Model cliente = Tbaz111Model.builder()
//                .transactionId(transactionId)
//                .nome(nome)
//                .cpf(cpf)
//                .build();

        System.out.println("--------SALVAR CLIENTE---------");
        System.out.println("transactionId: " + transactionId + "nome: " + nome + "cpf: " + cpf);

        //tbaz111.save(cliente);
    }
}
