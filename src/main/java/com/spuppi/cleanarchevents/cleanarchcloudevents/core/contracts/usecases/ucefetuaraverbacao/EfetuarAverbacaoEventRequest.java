package com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.usecases.ucefetuaraverbacao;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EfetuarAverbacaoEventRequest {

    private String transactionId;
    private String nome;
    private String cpf;
    private Byte tipoContrato;
}
