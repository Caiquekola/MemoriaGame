
package com.memory.bean;

public class Jogo {
    private int id;
    private String data; //'2024-09-01'
    private int pontos;
    private String sequencia;
    private int nivel;
    private int id_player;
    private int id_dupla;
    
//Dupla, solo

    public Jogo(int pontos, String sequencia, int nivel, int id_player) {
        this.pontos = pontos;
        this.sequencia = sequencia;
        this.nivel = nivel;
        this.id_player = id_player;
    }

    public Jogo(int pontos, String sequencia, int nivel, int id_player, int id_dupla) {
        this.pontos = pontos;
        this.sequencia = sequencia;
        this.nivel = nivel;
        this.id_player = id_player;
        this.id_dupla = id_dupla;
    }

    public Jogo(int id, String data, int pontos, String sequencia, int nivel, int id_player, int id_dupla) {
        this.id = id;
        this.data = data;
        this.pontos = pontos;
        this.sequencia = sequencia;
        this.nivel = nivel;
        this.id_player = id_player;
        this.id_dupla = id_dupla;
    }

    
    
    
    

    public int getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public int getPontos() {
        return pontos;
    }

    public String getSequencia() {
        return sequencia;
    }

    public int getNivel() {
        return nivel;
    }

    
    
    
}
