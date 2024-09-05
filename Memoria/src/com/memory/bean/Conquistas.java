/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.memory.bean;

/**
 *
 * @author Caiquekola
 */
public class Conquistas {
//    CREATE TABLE conquista (
//    id INT AUTO_INCREMENT PRIMARY KEY,
//    nome VARCHAR(100) NOT NULL,
//    descricao TEXT, -- Descrição da conquista
//    pontuacao_necessaria INTEGER NOT NULL
//);
    private int id;
    private String nome;
    private String descricao;
    private int pontuacao_necessaria;

    public Conquistas(String nome, String descricao, int pontuacao_necessaria) {
        this.nome = nome;
        this.descricao = descricao;
        this.pontuacao_necessaria = pontuacao_necessaria;
    }

    public Conquistas(int id, String nome, String descricao, int pontuacao_necessaria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.pontuacao_necessaria = pontuacao_necessaria;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getPontuacao_necessaria() {
        return pontuacao_necessaria;
    }
    
    
}
