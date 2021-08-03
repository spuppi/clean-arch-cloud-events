package com.spuppi.cleanarchevents.cleanarchcloudevents.usecases.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    private String nome;
    private String cpf;
}
