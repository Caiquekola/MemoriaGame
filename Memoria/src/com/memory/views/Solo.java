package com.memory.views;

import com.memory.bean.Jogador;
import com.memory.bean.Jogo;
import com.memory.dao.JogoDAO;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Random;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author Caiquekola
 */
public class Solo extends javax.swing.JFrame {

    private Jogador jogador;
    private LinkedList<Integer> seqCorreta = new LinkedList<>();
    private LinkedList<Integer> seqUsuario = new LinkedList<>();
    private boolean isPlaying = false; // Flag para controle do estado do jogo
    private int acertos = 0; // Contador de acertos
    private int nivel = 0; //Fcil 10, Médio 15, Dificil 20, Infinito
    
    private final int PONTOSFACIL = 8;
    private final int PONTOSMEDIO = 13;
    private final int PONTOSDIFICIL = 15;
    
    
    
    
    
    JButton voltar = new JButton("Voltar");
    
    private JLabel bt1;
    private JLabel bt2;
    private JLabel bt3;
    private JLabel bt4;
   
    
    public Solo(Jogador jogador, int nivel) {
        this.jogador = jogador;
        this.nivel = nivel;
        initComponents();
        initializeGame();
    }

    public Solo() {
        this.jogador = null;
        initComponents();
        initializeGame();
    }

    
    
    
    
    
    private void initializeGame() {
        
        JPanel panel = new JPanel();
        panel.setLayout(null); // Usando layout absoluto
        
        
        // Configuração dos labels fora do initComponents()
        bt1 = new JLabel();
        bt2 = new JLabel();
        bt3 = new JLabel();
        bt4 = new JLabel();
        

        
        // Configurações visuais dos labels
        configureLabel(bt1, Color.GREEN, 110, 140, 160, 150);
        configureLabel(bt2, Color.RED, 280, 140, 160, 150);
        configureLabel(bt3, Color.YELLOW, 110, 310, 160, 150);
        configureLabel(bt4, Color.BLUE, 280, 310, 160, 150);
        
        // Configura o contador de acertos
        contadorAcertos = new JLabel("0");
        contadorAcertos.setFont(new Font("Arial", Font.BOLD, 40));
        contadorAcertos.setBounds(650, 100, 150, 30);
        
        // Configura os botões
        JButton comecar = new JButton("Começar");
        comecar.setBackground(new Color(153, 153, 0));
        comecar.setFont(new Font("Century Gothic", Font.BOLD, 24));
        comecar.setForeground(new Color(51, 204, 255));
        comecar.setBounds(160, 500, 240, 60);
        comecar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comecar.setEnabled(false);
                comecar.setVisible(false);
                gameStart();;
            }
        });

        voltar.setBounds(600, 510, 140, 40);
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                voltarActionPerformed(e);
            }
        });
        
        
        // Adiciona os componentes ao painel
        getContentPane().add(bt1);
        getContentPane().add(bt2);
        getContentPane().add(bt3);
        getContentPane().add(bt4);
        getContentPane().add(comecar);
        getContentPane().add(voltar);
        getContentPane().add(contadorAcertos);

        
        // Define o layout absoluto para posicionar os componentes
        getContentPane().setLayout(null);
    }
    
    private void voltarActionPerformed(ActionEvent e) {
        this.dispose();
        Menu menu = new Menu(jogador);
        menu.setVisible(true);
    }
    
    private void configureLabel(JLabel label, Color color, int x, int y, int width, int height) {
        label.setOpaque(true);
        label.setBackground(color);
        label.setBorder(new TransparentBorder(1, Color.BLACK)); // Borda com centro transparente
        label.setBounds(x, y, width, height);
        label.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelMouseClicked(evt);
            }
        });
    }

    private class TransparentBorder extends AbstractBorder {
        private final int thickness;
        private final Color color;

        public TransparentBorder(int thickness, Color color) {
            this.thickness = thickness;
            this.color = color;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(color);
            g2d.setStroke(new BasicStroke(thickness));
            g2d.drawRect(x, y, width - 1, height - 1);
            g2d.dispose();
        }
    }

    public LinkedList<Integer> addSequenciaCorreta(LinkedList<Integer> lista) {
        int novoValor = new Random().nextInt(4) + 1;
        lista.add(novoValor);
        System.out.println("Sequência Correta: " + lista);
        return lista;
    }

    public void gameStart() {
        isPlaying = true; // Marca o jogo como iniciado
        addSequenciaCorreta(seqCorreta);
        playSequence();
    }

    private void playSequence() {
        Timer timer = new Timer(1000, new ActionListener() {
            private int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < seqCorreta.size()) {
                    int valor = seqCorreta.get(index);
                    highlightBlock(valor);
                    index++;
                } else {
                    ((Timer) e.getSource()).stop();
                    enableUserInput();
                }
            }
        });
        timer.start();
    }

    private void highlightBlock(int bloco) {
        JLabel label = getBlockLabel(bloco);
        if (label != null) {
            Color originalColor = label.getBackground();
            label.setBackground(Color.DARK_GRAY);
            Timer timer = new Timer(300, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    label.setBackground(originalColor);
                }
            });
            timer.setRepeats(false); // Não repetir o evento
            timer.start();
        }
    }

    private JLabel getBlockLabel(int bloco) {
        switch (bloco) {
            case 1:
                return bt1;
            case 2:
                return bt2;
            case 3:
                return bt3;
            case 4:
                return bt4;
            default:
                return null;
        }
    }

    private void setBlockColor(Color color) {
        bt1.setBackground(color);
        bt2.setBackground(color);
        bt3.setBackground(color);
        bt4.setBackground(color);
    }

    private void enableUserInput() {
        bt1.setEnabled(true);
        bt2.setEnabled(true);
        bt3.setEnabled(true);
        bt4.setEnabled(true);
    }

    private void disableUserInput() {
        bt1.setEnabled(false);
        bt2.setEnabled(false);
        bt3.setEnabled(false);
        bt4.setEnabled(false);
    }

    private void handleUserInput(int bloco) {
        if (isPlaying) {
            seqUsuario.add(bloco);
            System.out.println("Tentativa: " + bloco); // Adiciona um print para cada tentativa
            if (seqUsuario.equals(seqCorreta)) {
                JOptionPane.showMessageDialog(this, "Acertou a sequência!", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                acertos++; // Incrementa o contador de acertos
                contadorAcertos.setText(String.valueOf(acertos));
                seqUsuario.clear();
                
                
                addSequenciaCorreta(seqCorreta); // Adiciona novo valor à sequência correta
                //Nivel fácil
                if(acertos==PONTOSFACIL&&nivel==0){
                    vitoria();
                }else if(acertos==PONTOSMEDIO&&nivel==1){
                    vitoria();
                }else if(acertos==PONTOSDIFICIL&&nivel==2){
                    vitoria();
                }
                playSequence(); // Reproduz a sequência completa novamente
            } else if (seqUsuario.size() == seqCorreta.size()) {
                gameOver(); 

                // Altera para a tela de Game Over
            }
        }
    }
    
    private void vitoria(){
        isPlaying = false; // Marca o jogo como terminado
        disableUserInput();
        
        Jogo jogo = new Jogo(acertos,
                            seqCorreta.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining()),
                            nivel, jogador.getId());
        System.out.println("JOGADOR ID"+jogador.getId());
        JogoDAO jogoDao = new JogoDAO();
        jogoDao.fimDeJogoSolo(jogo,jogador);
        
        VitoriaSolo go = new VitoriaSolo(jogador,jogo);
        go.setVisible(true);
        this.dispose();
        
        System.out.println("Game Over! Sequência correta: " + seqCorreta);
    }
    
    
    

    private void labelMouseClicked(java.awt.event.MouseEvent evt) {
        JLabel clickedLabel = (JLabel) evt.getSource();
        int bloco = getBlockNumber(clickedLabel);
        handleUserInput(bloco);
    }

    private int getBlockNumber(JLabel label) {
        if (label == bt1) return 1;
        if (label == bt2) return 2;
        if (label == bt3) return 3;
        if (label == bt4) return 4;
        return -1;
    }

    private void gameOver() {
        isPlaying = false; // Marca o jogo como terminado
        disableUserInput();
        
        Jogo jogo = new Jogo(acertos,
                            seqCorreta.stream()
                            .map(String::valueOf)
                            .collect(Collectors.joining()),
                            nivel, jogador.getId());
        System.out.println("JOGADOR ID"+jogador.getId());
        JogoDAO jogoDao = new JogoDAO();
        jogoDao.fimDeJogoSolo(jogo,jogador);
        
        GameOverSolo go = new GameOverSolo(jogador,jogo);
        go.setVisible(true);
        this.dispose();
        
        System.out.println("Game Over! Sequência correta: " + seqCorreta);
        
    }
    
    

   
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        contadorAcertos = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(671, 671, 671)
                .addComponent(contadorAcertos)
                .addGap(170, 170, 170))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(contadorAcertos)
                .addGap(357, 357, 357))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Solo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Solo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Solo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Solo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Solo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel contadorAcertos;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
