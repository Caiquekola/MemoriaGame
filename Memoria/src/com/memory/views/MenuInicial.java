package com.memory.views;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Caiquekola
 */
public class MenuInicial extends javax.swing.JFrame {

    /**
     * Creates new form HomeScreen
     */
    
    JLabel wpp = new javax.swing.JLabel();
       

       

        
    public MenuInicial() {
        initComponents();
//        setIconImage(new ImageIcon("Solo.png").getImage());
//        wpp.setIcon(new javax.swing.ImageIcon(getClass().getResource("Solo.png"))); // NOI18N
//        getContentPane().add(wpp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wppp = new javax.swing.JLabel();
        sair = new javax.swing.JButton();
        jogar = new javax.swing.JButton();
        creditos1 = new javax.swing.JButton();
        ranking = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        wppp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/memory/resources/MenuInicial.png"))); // NOI18N
        getContentPane().add(wppp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        sair.setText("jButton1");
        sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairActionPerformed(evt);
            }
        });
        getContentPane().add(sair, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 510, 200, 60));

        jogar.setText("jButton1");
        jogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jogarActionPerformed(evt);
            }
        });
        getContentPane().add(jogar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 300, 200, 50));

        creditos1.setText("jButton1");
        creditos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditos1ActionPerformed(evt);
            }
        });
        getContentPane().add(creditos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 440, 200, 50));

        ranking.setText("jButton1");
        ranking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rankingActionPerformed(evt);
            }
        });
        getContentPane().add(ranking, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 370, 200, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_sairActionPerformed

    private void jogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jogarActionPerformed
        // TODO add your handling code here:
        Login login = new Login();
        login.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jogarActionPerformed

    private void creditos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditos1ActionPerformed
        // TODO add your handling code here:
        Creditos creditos = new Creditos();
        creditos.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_creditos1ActionPerformed

    private void rankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rankingActionPerformed
        // TODO add your handling code here:
        Ranking rank = new Ranking();
        rank.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_rankingActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(MenuInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton creditos1;
    private javax.swing.JButton jogar;
    private javax.swing.JButton ranking;
    private javax.swing.JButton sair;
    private javax.swing.JLabel wppp;
    // End of variables declaration//GEN-END:variables
}
