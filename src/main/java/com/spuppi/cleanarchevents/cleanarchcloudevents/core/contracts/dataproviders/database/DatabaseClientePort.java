package com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.dataproviders.database;

import org.springframework.stereotype.Component;

@Component
public interface DatabaseClientePort {

    void salvarCliente(String tranasctionId, String nome, String cpf);
}
