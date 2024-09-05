
package com.memory.bean;


public class Jogo_Jogador {
    private int jogo_id;
    private int jogador_id;
    private int pontos;

    //Update dos pontos
    public Jogo_Jogador(int jogo_id, int jogador_id, int pontos) {
        this.jogo_id = jogo_id;
        this.jogador_id = jogador_id;
        this.pontos = pontos;
    }

    //Criacao da relacao
    public Jogo_Jogador(int jogo_id, int jogador_id) {
        this.jogo_id = jogo_id;
        this.jogador_id = jogador_id;
    }

    public int getJogo_id() {
        return jogo_id;
    }

    public int getJogador_id() {
        return jogador_id;
    }

    public int getPontos() {
        return pontos;
    }
    
    
    
    
    
    
}
