/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.memory.dao;

import com.memory.bean.DuplaBean;
import com.memory.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Caiquekola
 */
public class DuplaDAO {

    public int cadastroERetornoID(DuplaBean dupla) {
        Connection con = ConnectionFactory.getConnection(); //conecta com o banco de dados
        PreparedStatement stmt = null; //Declara comando para o banco de dados
        ResultSet rs = null;
        int id = 1;

        try { //Prepara as instruções do banco de dados
            stmt = con.prepareStatement("INSERT INTO memoria.dupla (pontos,sequencia) VALUES (?,?)");

            stmt.setInt(1, 0);
            stmt.setString(2, "0");
            stmt.executeUpdate();

            stmt = con.prepareStatement("SELECT * FROM memoria.dupla order by DESC");
            rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_dupla");
            }

        } catch (SQLException ex) { //Trata erro de manipulação no banco de dados

            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs); //fecha a conexão com o banco de dados
        }

        return id;
    }
}
