
package com.memory.control;

import com.memory.bean.Jogador;
import com.memory.dao.JogadorDAO;
import com.memory.views.Cadastro;
import java.sql.SQLException;

public class SignUpController {
    public int signUpUsuario(Cadastro view) throws SQLException{
          // INT porque retorna seu ID de usuário
          JogadorDAO signUp = new JogadorDAO();
          
          Jogador jogador = new Jogador(view.getNickname().getText(),view.getSenha1().getText(),view.getEmail().getText());
          
          int loginSucessful =  signUp.cadastro(jogador);
          return loginSucessful;
     }
   
    public int emailUtilizado(Cadastro view) throws SQLException{
        
        JogadorDAO signUp = new JogadorDAO();
          
        Jogador jogador = new Jogador(view.getNickname().getText(),view.getSenha1().getText(),view.getEmail().getText());
        
        int emailUtilizado = signUp.readByEmail(jogador.getEmail());
        // 0 Existe, 1 Inexiste
        return emailUtilizado;
        
        
    }
    
    public int nicknameUtilizado(Cadastro view) throws SQLException{
        
        JogadorDAO signUp = new JogadorDAO();
          
        Jogador jogador = new Jogador(view.getNickname().getText(),view.getSenha1().getText(),view.getEmail().getText());
        
        int nick = signUp.readByNickname(jogador.getNickname());
        // 0 Existe, 1 Inexiste
        return nick;
        
        
    }
    
}
