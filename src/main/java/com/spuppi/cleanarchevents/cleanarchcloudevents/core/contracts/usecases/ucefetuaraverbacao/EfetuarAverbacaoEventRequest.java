package com.spuppi.cleanarchevents.cleanarchcloudevents.core.contracts.usecases.ucefetuaraverbacao;

public class EfetuarAverbacaoEventRequest {

    private String transactionId;
    private String nome;
    private String cpf;
    private Byte tipoContrato;

    public EfetuarAverbacaoEventRequest() {}

    public EfetuarAverbacaoEventRequest(String transactionId, String nome, String cpf, Byte tipoContrato) {
        this.transactionId = transactionId;
        this.nome = nome;
        this.cpf = cpf;
        this.tipoContrato = tipoContrato;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Byte getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(Byte tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    @Override
    public String toString() {
        return "EfetuarAverbacaoEventRequest{" +
                "transactionId='" + transactionId + '\'' +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", tipoContrato=" + tipoContrato +
                '}';
    }
}
