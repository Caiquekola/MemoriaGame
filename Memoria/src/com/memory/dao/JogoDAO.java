package com.memory.dao;

import com.memory.bean.Jogador;
import com.memory.bean.Jogo;
import com.memory.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;

/**
 *
 * @author Caiquekola
 */
public class JogoDAO {

    public Jogo fimDeJogoSolo(Jogo jogo, Jogador jogador) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO memoria.jogo (pontos, sequencia, nivel, id_player1) values (?,?,?,?)");

            stmt.setInt(1, jogo.getPontos());
            stmt.setString(2, jogo.getSequencia());
            stmt.setInt(3, jogo.getNivel());
            stmt.setInt(4, jogador.getId());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

        return jogo;
    }

    public int getTotalPartidasJogadas() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int total = 0;

        try {
            stmt = con.prepareStatement("SELECT COUNT(*) FROM jogo");
            rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return total;
    }

    public String getUsuarioComMaisPartidas() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String usuario = "N/A";

        try {
            stmt = con.prepareStatement(
                    "SELECT jogador.nickname, COUNT(*) AS total_partidas "
                    + "FROM jogo "
                    + "JOIN jogador ON jogador.id = jogo.id_player1 "
                    + "GROUP BY jogador.id "
                    + "ORDER BY total_partidas DESC "
                    + "LIMIT 1");
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = rs.getString("nickname");
            }
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return usuario;
    }

    public int getMaiorPontuacaoInfinito() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int maiorPontuacao = 0;

        try {
            stmt = con.prepareStatement("SELECT MAX(pontos) FROM jogo WHERE nivel = 2"); // Considerando nível 2 como infinito
            rs = stmt.executeQuery();

            if (rs.next()) {
                maiorPontuacao = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return maiorPontuacao;
    }

    public double getMediaPontuacaoModoInfinito() {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        double mediaPontuacao = 0;

        try {
            stmt = con.prepareStatement("SELECT AVG(pontos) FROM jogo WHERE nivel = 2"); // Considerando nível 2 como infinito
            rs = stmt.executeQuery();

            if (rs.next()) {
                mediaPontuacao = rs.getDouble(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(JogoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return mediaPontuacao;
    }
}
