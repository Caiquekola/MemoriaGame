
package com.memory.dao;

import com.memory.connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Caiquekola
 */
public class LoginDAO {
    //login com nickname e senha
    public int login(String nickname, String senha) {
        PreparedStatement stmt = null;
        Connection con = ConnectionFactory.getConnection();
        ResultSet rs = null;
        int id = -1;
        try {
            String sql = "SELECT * FROM memory.jogador WHERE nickname = ? AND senha = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nickname);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                System.out.println("Login com sucesso");
                id = rs.getInt("id");
                
            } else {
                System.out.println("Nao possui conta!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);

        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return id;
    }
}
