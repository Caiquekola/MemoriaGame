package com.memory.dao;

import com.memory.bean.Jogador;
import com.memory.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JogadorDAO {

    //create
    public int cadastro(Jogador jogador) {
        Connection con = ConnectionFactory.getConnection(); //conecta com o banco de dados
        PreparedStatement stmt = null; //Declara comando para o banco de dados
        int sucess = 0;

        try { //Prepara as instruções do banco de dados
            stmt = con.prepareStatement("INSERT INTO memoria.jogador (nickname,email,senha) VALUES (?,?,?)");

            stmt.setString(1, jogador.getNickname());
            stmt.setString(2, jogador.getEmail());
            stmt.setString(3, jogador.getSenha());
            stmt.executeUpdate();

        } catch (SQLException ex) { //Trata erro de manipulação no banco de dados
            sucess = -1;
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt); //fecha a conexão com o banco de dados
        }

        return sucess;
    }

    //read
    public Jogador login(String nickname, String senha) {
        PreparedStatement stmt = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet rs = null;
        Jogador jogador = new Jogador(-1, null, null, null);
        try {
            String sql = "SELECT * FROM memoria.jogador WHERE nickname = ? AND senha = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nickname);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                jogador = new Jogador(rs.getInt("id"), rs.getString("nickname"), rs.getString("senha"), rs.getString("email"));
                System.out.println("Login com sucesso");
                System.out.println("Seu ID" + jogador.getId());
            } else {
                System.out.println("Nao possui conta!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return jogador;
    }

//    public void create(Jogador u) { //Adapta Usuario para o banco de dados
//        Connection con = ConnectionFactory.getConnection(); //conecta com o banco de dados
//        PreparedStatement stmt = null; //Declara comando para o banco de dados
//
//        try { //Prepara as instruções do banco de dados
//            stmt = con.prepareStatement("INSERT INTO memoria.jogador (nickname,senha,email,pontuacao_total) VALUES (?,?,?,?)");
//            
//            stmt.setString(1, u.getNickname());
//            stmt.setString(2, u.getSenha());
//            stmt.setString(3, u.getEmail());
//            stmt.setInt(4, u.getPontuacao_total());
//            stmt.executeUpdate();
//            
//        } catch (SQLException ex) { //Trata erro de manipulação no banco de dados
//            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            ConnectionFactory.closeConnection(con, stmt); //fecha a conexão com o banco de dados
//        }
//
//    }
    public List<Jogador> read() { //Retorna uma lista com todos os jogadores
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Jogador> jogadores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * from memoria.jogador WHERE ORDER BY id"); //Filtro para seleção de somente jogador

            rs = stmt.executeQuery();

            while (rs.next()) {
                Jogador jogador = new Jogador(rs.getString("nickname"), rs.getString("senha"), rs.getString("email"), rs.getInt("pontuacao_total"));
                jogadores.add(jogador);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return jogadores;

    }

    public List<Jogador> readAllRank() { //Retorna uma lista com todos os jogadores ordenados por rank
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Jogador> jogadores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * from memoria.jogador ORDER BY pontuacao_total DESC"); //Filtro para seleção de somente jogador

            rs = stmt.executeQuery();

            while (rs.next()) {
                Jogador jogador = new Jogador(rs.getString("nickname"), rs.getString("senha"), rs.getString("email"), rs.getInt("pontuacao_total"));
                jogadores.add(jogador);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return jogadores;

    }

    public List<Jogador> readRankTop3() { //Retorna uma lista com todos os jogadores
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Jogador> jogadores = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * from memoria.jogador ORDER BY pontuacao_total DESC limit 3"); //Filtro para seleção de somente jogador

            rs = stmt.executeQuery();

            while (rs.next()) {
                Jogador jogador = new Jogador(rs.getString("nickname"), rs.getString("senha"), rs.getString("email"), rs.getInt("pontuacao_total"));
                jogadores.add(jogador);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return jogadores;

    }

    //Dado um id encontrar seu record e retornar os pontos baseado no modo de dificuldade
    public int recordeJogadorDificuldade(Jogador jogador, int nivel) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int record = 0;

        try {
            stmt = con.prepareStatement("SELECT max(pontos) as pontos FROM memoria.jogo WHERE id_player1 = ? and nivel = ?");
            stmt.setInt(1, jogador.getId());
            stmt.setInt(2, nivel);
            rs = stmt.executeQuery();

            if (rs.next()) {
                record = rs.getInt("pontos");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return record;

    }

    //Teste para ver se o usuário e email já existe, utilizado na criação de contas
    public int readByNickname(String nickname) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int resultado = 1;
        //0 para jogador já existente
        //1 para jogador inexistente

        try {
            stmt = con.prepareStatement("SELECT * from memoria.jogador WHERE nickname = ?");
            stmt.setString(1, nickname);
            rs = stmt.executeQuery();

            if (rs.next()) {
                resultado = 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return resultado;

    }

    //Teste para ver se o usuário e email já existe, utilizado na criação de contas
    public int readByEmail(String email) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int resultado = 1;
        //0 para jogador já existente
        //1 para jogador inexistente

        try {
            stmt = con.prepareStatement("SELECT * from memoria.jogador WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                resultado = 0;
            }

        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return resultado;

    }

//    public void updateAccount(Jogador a){ //Atualizar a senha do Usuario
//        Connection con = ConnectionFactory.getConnection();
//        PreparedStatement stmt = null;
//        String sql = ("UPDATE memoria.jogador SET senha = ? WHERE email = ? and nickname = ? ;");
//        
//        try {
//            
//            stmt = con.prepareStatement(sql);
//            stmt.setString(1, a.getSenha());
//            stmt.setString(2, a.getEmail());
//            stmt.setString(3, a.getNickname());
//            
//            stmt.executeUpdate();
//            
//        } catch (SQLException ex) {
//            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }finally{
//            ConnectionFactory.closeConnection(con,stmt);
//        }
//        
//    }
    //Resetar Score do jogdor
    public void resetScore(Jogador jogador) {
        //DELETE FROM jogo WHERE id_player1 = id_jogador;
        //DELETE FROM dupla WHERE id_dupla IN (SELECT id_dupla FROM jogo WHERE id_player1 = id_jogador);
        //DELETE FROM jogador_conquista WHERE jogador_id = id_jogador;
        //DELETE FROM jogador WHERE id = id_jogador;
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            /* Subconsulta Interna (Subquery): Calcula as pontuações máximas de todos os jogadores e conta quantas são maiores 
                 do que a pontuação do jogador em questão.
               Subconsulta Externa: Calcula a pontuação máxima do jogador específico e usa a subconsulta para calcular o ranking. */

            stmt = con.prepareStatement("DELETE FROM jogo WHERE id_player1 = ?;");
            stmt.setInt(1, jogador.getId());
            stmt.executeUpdate();
            stmt = con.prepareStatement("DELETE FROM dupla WHERE id_dupla IN (SELECT id_dupla FROM jogo WHERE id_player1 = ?);");
            stmt.setInt(1, jogador.getId());
            stmt.executeUpdate();
            stmt = con.prepareStatement("DELETE FROM jogador_conquista WHERE jogador_id = ?;");
            stmt.setInt(1, jogador.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    //Utilizado para o usuário excluir sua conta
    public void deletarConta(Jogador jogador) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        try {
            //DeletarConta
            resetScore(jogador);
            stmt = con.prepareStatement("DELETE FROM jogador WHERE id = ?;");
            stmt.setInt(1, jogador.getId());
            stmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    //Posicao No rank do jogador
    public int posicaoRank(Jogador j) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int resultado = -1;
        try {
            /* Subconsulta Interna (Subquery): Calcula as pontuações máximas de todos os jogadores e conta quantas são maiores 
                 do que a pontuação do jogador em questão.
               Subconsulta Externa: Calcula a pontuação máxima do jogador específico e usa a subconsulta para calcular o ranking. */

            stmt = con.prepareStatement("SELECT jogador_id, nickname, pontos, "
                    + "(SELECT COUNT(*) + 1 FROM (SELECT j.id AS jogador_id, MAX(g.pontos) AS pontos FROM jogador j JOIN jogo g ON j.id = g.id_player1 GROUP BY j.id) "
                    + "AS Subquery WHERE Subquery.pontos > RankedPlayers.pontos) AS ranking FROM (SELECT j.id AS jogador_id, j.nickname, MAX(g.pontos) "
                    + "AS pontos FROM jogador j JOIN jogo g ON j.id = g.id_player1 GROUP BY j.id, j.nickname) AS RankedPlayers WHERE jogador_id = ?;");
            stmt.setInt(1, j.getId());
            rs = stmt.executeQuery();

            if (rs.next()) {
                //Pego seu ranking
                resultado = rs.getInt("ranking");
            }

        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return resultado;
    }

    public void verificarEAdicionarConquista(Jogador j, int conquistaId) {
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            // Verifica se o jogador já possui a conquista
            String queryVerificarConquista = "SELECT COUNT(*) FROM jogador_conquista WHERE jogador_id = ? AND conquista_id = ?";
            stmt = con.prepareStatement(queryVerificarConquista);
            stmt.setInt(1, j.getId());
            stmt.setInt(2, conquistaId);
            rs = stmt.executeQuery();

            if (rs.next() && rs.getInt(1) == 0) {
                // O jogador não possui a conquista, então adicione
                String queryAdicionarConquista = "INSERT INTO jogador_conquista (jogador_id, conquista_id) VALUES (?, ?)";
                stmt = con.prepareStatement(queryAdicionarConquista);
                stmt.setInt(1, j.getId());
                stmt.setInt(2, conquistaId);
                stmt.executeUpdate();
                System.out.println("Conquista adicionada: "+conquistaId);
            }

        } catch (SQLException ex) {
            Logger.getLogger(JogadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
