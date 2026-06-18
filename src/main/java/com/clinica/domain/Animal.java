package com.clinica.domain;

public class Animal {

    private final String nome;
    private final String especie;
    private final boolean adotado;

    public Animal(String nome, String especie, boolean adotado) {
        this.nome = nome;
        this.especie = especie;
        this.adotado = adotado;
    }

    public String getNome() {
        return nome;
    }

    public String getEspecie() {
        return especie;
    }

    public boolean isAdotado() {
        return adotado;
    }
}
