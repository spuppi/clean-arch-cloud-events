package com.spuppi.cleanarchevents.cleanarchcloudevents.core.dataproviders.database.repositories;

import com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.dataproviders.database.SimulacaoPersistenciaPort;
import com.spuppi.cleanarchevents.cleanarchcloudevents.core.dataproviders.database.model.Tbaz111Model;
import org.springframework.stereotype.Component;

@Component
public class SimulacaoPersistencia implements SimulacaoPersistenciaPort {

    @Override
    public void salvarCliente(Tbaz111Model model) {
        System.out.println("Salvar cliente dentro da simulacao");
        System.out.println(model);
    }
}
