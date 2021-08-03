package com.spuppi.cleanarchevents.cleanarchcloudevents.contracts.usecases.ucefetuaraverbacao;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EfetuarAverbacaoEventRequest {

    private String nome;
    private String cpf;
    private Byte tipoContrato;
}
