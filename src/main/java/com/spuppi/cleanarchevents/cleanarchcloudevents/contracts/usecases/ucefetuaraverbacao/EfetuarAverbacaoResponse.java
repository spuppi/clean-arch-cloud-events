package com.spuppi.cleanarchevents.cleanarchcloudevents.contracts.usecases.ucefetuaraverbacao;

import com.spuppi.cleanarchevents.cleanarchcloudevents.usecases.entities.Cliente;
import com.spuppi.cleanarchevents.cleanarchcloudevents.usecases.entities.Contrato;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EfetuarAverbacaoResponse {

    private String transactionId;
    private Cliente cliente;
    private Contrato contrato;
}
