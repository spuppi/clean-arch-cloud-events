package com.spuppi.cleanarchevents.cleanarchcloudevents.contracts.usecases.ucefetuaraverbacao;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EfetuarAverbacaoRequest{

    private String transactionId;
    private String nome;
    private String cpf;
    private Byte tipoContrato;
}
