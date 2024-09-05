/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.memory.bean;

/**
 *
 * @author Caiquekola
 */
public class DuplaBean {
    //CREATE TABLE dupla (
//    id_dupla INT AUTO_INCREMENT PRIMARY KEY,
//    nickname VARCHAR(50) NOT NULL,
//    pontos INTEGER NOT NULL,
//    sequencia VARCHAR(255) NOT NULL,
//    nivel INTEGER NOT NULL
//);
    
    private int id_dupla;
    private int pontos;
    private String sequencia;

    public DuplaBean(int id_dupla, String nickname, int pontos, String sequencia) {
        this.id_dupla = id_dupla;
        this.pontos = pontos;
        this.sequencia = sequencia;
    }

    public DuplaBean(String nickname, int pontos, String sequencia) {
        this.pontos = pontos;
        this.sequencia = sequencia;
    }

    public DuplaBean(int pontos, String sequencia) {
        this.pontos = pontos;
        this.sequencia = sequencia;
    }

  
    
    
    
    

    public int getId_dupla() {
        return id_dupla;
    }

    

    public int getPontos() {
        return pontos;
    }

    public String getSequencia() {
        return sequencia;
    }
    
    
    
    
}
