package com.memory.control;

import com.memory.bean.Jogador;
import com.memory.dao.JogadorDAO;
import com.memory.views.Login;
import java.sql.SQLException;



public class LoginController {
    
      public Jogador loginUsuario(Login view) throws SQLException{
          // INT porque retorna seu ID de usuário
          System.out.println(view.getNickname().getText());
          
          JogadorDAO login = new JogadorDAO();         
          
          Jogador loginSucessful =  login.login(view.getNickname().getText(), view.getSenha().getText());
          return loginSucessful;
      }
      
      
//    public int loginUsuario(Login view) throws SQLException{
//        LoginDAO login = new LoginDAO();
//        System.out.println(login.login((view.getjTextField1().getText()), view.getjPasswordField1().getText()));
//        return login.login((view.getjTextField1().getText()), view.getjPasswordField1().getText());
//    }
//    public Professor nomeProfessor(Login view) throws SQLException{
//        ProfessorDAO pdao = new ProfessorDAO();
//        Professor p = new Professor((view.getjTextField1().getText()), view.getjPasswordField1().getText());
//        return pdao.readUnit(p);
//    }
//    public Aluno nomeAluno(Login view) throws SQLException{
//        AlunoDAO adao = new AlunoDAO();
//        Aluno a = new Aluno((view.getjTextField1().getText()), view.getjPasswordField1().getText());
//        return adao.read(a);
//    }
}
