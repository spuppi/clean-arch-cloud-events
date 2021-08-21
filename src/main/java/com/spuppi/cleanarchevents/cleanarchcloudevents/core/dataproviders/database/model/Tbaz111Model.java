package com.spuppi.cleanarchevents.cleanarchcloudevents.core.dataproviders.database.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Builder
@Entity
public class Tbaz111Model {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String transactionId;
    private String nome;
    private String cpf;
}
