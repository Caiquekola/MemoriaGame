package com.memory.bean;

public class Jogador { //Superclasse abstrata

    private int id;
    private String nickname;
    private String senha;
    private String email;
    private int pontuacao_total;

    public Jogador(int id, String nickname, String senha, String email) {
        this.id = id;
        this.nickname = nickname;
        this.senha = senha;
        this.email = email;
        this.pontuacao_total = 0;
    }
    
    public Jogador(String nickname, String senha, String email, int pontuacao) {
        this.nickname = nickname;
        this.senha = senha;
        this.email = email;
        this.pontuacao_total = pontuacao;
    }

    public Jogador(String nickname, String senha, String email) {
        this.nickname = nickname;
        this.senha = senha;
        this.email = email;
        this.pontuacao_total = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPontuacao_total() {
        return pontuacao_total;
    }

    public void setPontuacao_total(int pontuacao_total) {
        this.pontuacao_total = pontuacao_total;
    }

}
