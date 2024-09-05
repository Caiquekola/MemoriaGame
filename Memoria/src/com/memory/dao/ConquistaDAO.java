package com.memory.dao;

import com.memory.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class ConquistaDAO {

    public void verificarEAdicionarConquistaHacker(int jogadorId) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Verificar se o jogador possui todas as conquistas exceto "Hacker"
            String sql = "SELECT COUNT(*) AS total_conquistas " +
                         "FROM conquista " +
                         "WHERE id NOT IN (SELECT conquista_id FROM jogador_conquista WHERE jogador_id = ?)";

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, jogadorId);
            rs = stmt.executeQuery();

            int totalConquistas = 0;
            if (rs.next()) {
                totalConquistas = rs.getInt("total_conquistas");
            }

            // Verificar se o total de conquistas que o jogador não possui é 0
            if (totalConquistas == 0) {
                // Adicionar a conquista "Hacker"
                String sqlAddConquista = "INSERT INTO jogador_conquista (jogador_id, conquista_id) " +
                                         "SELECT ?, id FROM conquista WHERE nome = 'Hacker'";

                stmt = con.prepareStatement(sqlAddConquista);
                stmt.setInt(1, jogadorId);
                stmt.executeUpdate();
            }

        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    
    /**
     * Verifica se o jogador possui uma conquista específica.
     * 
     * @param jogadorId O ID do jogador.
     * @param idConquista O ID da conquista.
     * @return Verdadeiro se o jogador possui a conquista, falso caso contrário.
     */
    public  boolean possuiConquista(int jogadorId, int idConquista) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean possuiConquista = false;

        try {
            // Consulta para verificar se o jogador possui a conquista especificada
            String sql = "SELECT COUNT(*) AS count " +
                         "FROM jogador_conquista " +
                         "WHERE jogador_id = ? AND conquista_id = ?";

            stmt = con.prepareStatement(sql);
            stmt.setInt(1, jogadorId);
            stmt.setInt(2, idConquista);
            rs = stmt.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("count");
                possuiConquista = (count > 0);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return possuiConquista;
    }
    
    
    
}
    
    

