
package com.memory.views;


import com.memory.bean.Jogador;
import com.memory.control.LoginController;
import javax.swing.JOptionPane;
import com.memory.views.MenuInicial;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JTextField;

public class Login extends javax.swing.JFrame {

    
    public Login() {
        initComponents();
        nickname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });

        senha.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });
        //Definição do ícone/logo
        
       
    }
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nickname = new javax.swing.JTextField();
        senha = new javax.swing.JPasswordField();
        wpp = new javax.swing.JLabel();
        entrar = new javax.swing.JButton();
        cadastrar = new javax.swing.JButton();
        voltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nickname.setToolTipText("Nickname");
        nickname.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        nickname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nicknameActionPerformed(evt);
            }
        });
        getContentPane().add(nickname, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 320, 30));

        senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                senhaActionPerformed(evt);
            }
        });
        getContentPane().add(senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 320, 30));

        wpp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/memory/resources/Login.png"))); // NOI18N
        getContentPane().add(wpp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        entrar.setText("Entrar");
        entrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entrarActionPerformed(evt);
            }
        });
        getContentPane().add(entrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 430, 170, 50));

        cadastrar.setText("Criar conta");
        cadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cadastrarMouseEntered(evt);
            }
        });
        cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastrarActionPerformed(evt);
            }
        });
        getContentPane().add(cadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 490, 160, 40));

        voltar.setText("Voltar");
        voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                voltarActionPerformed(evt);
            }
        });
        getContentPane().add(voltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 490, 170, 40));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void login(){
        
        //Testar todos os campos preenchidos
        if (nickname.getText().isEmpty() || senha.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Preencha todos os campos", JOptionPane.HEIGHT);
        }
        //Testar se existe usuário e ao mesmo tempo se a senha está correta?
        else {
            try {
                LoginController login = new LoginController();

                Jogador jogador = login.loginUsuario(this);
                
                switch (jogador.getId()) {
                    case -1:
                         JOptionPane.showMessageDialog(this, "ERRO! Usuário ou senha incorretos!", "Erro de Login!", JOptionPane.YES_OPTION);
                        break;
                    default:
                        this.dispose();
                        Menu menu = new Menu(jogador);
                        menu.setVisible(true);
                        break;
                }
            } catch (SQLException sql) {
                // Tratamento de exceção
            }
        }
    }
    
    private void cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastrarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        Cadastro cadastro = new Cadastro();
        cadastro.setVisible(true);
    }//GEN-LAST:event_cadastrarActionPerformed

    private void nicknameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nicknameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nicknameActionPerformed

    private void entrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entrarActionPerformed
        // TODO add your handling code here:
        login();
        
        
        
    }//GEN-LAST:event_entrarActionPerformed

    private void voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_voltarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        MenuInicial tl = new MenuInicial();
        tl.setVisible(true);
    }//GEN-LAST:event_voltarActionPerformed

    private void senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_senhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_senhaActionPerformed

    private void cadastrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrarMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_cadastrarMouseEntered

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
        
        
    }
    
    private void jogar(){
        if(senha.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Insira um nickname");
            senha.requestFocus();
        }else{
            //código sql conection
        }
    }

    public JTextField getNickname() {
        return nickname;
    }

    public JTextField getSenha() {
        return senha;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cadastrar;
    private javax.swing.JButton entrar;
    private javax.swing.JTextField nickname;
    private javax.swing.JPasswordField senha;
    private javax.swing.JButton voltar;
    private javax.swing.JLabel wpp;
    // End of variables declaration//GEN-END:variables
}
