/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import Dao.SQLManager;
import Utils.Jason;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author jgpar
 */
public class ViewLogin extends javax.swing.JFrame {

    private final SQLManager sql;
    private Jason jason;

    public ViewLogin() {
        initComponents();
        sql = new SQLManager();

        Draw();
    }

    private void Draw() {
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
        jpl_notificacao2.setVisible(false);
        jpl_notificacao1.setVisible(false);
        jpl_notificacao.setVisible(false);
    }

    private void alert(Jason jason) {
        switch (jason.getCod()) {
            case "err" -> {
                jpl_notificacao2.setVisible(true);
                jpl_notificacao1.setVisible(false);
                jpl_notificacao.setVisible(false);
            }
            case "sucess" -> {
                jpl_notificacao2.setVisible(false);
                jpl_notificacao1.setVisible(true);
                jpl_notificacao.setVisible(false);
            }
            case "warn" -> {
                jpl_notificacao2.setVisible(false);
                jpl_notificacao1.setVisible(false);
                jpl_notificacao.setVisible(true);
            }
        }
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                jpl_notificacao2.setVisible(false);
                jpl_notificacao1.setVisible(false);
                jpl_notificacao.setVisible(false);
                timer.cancel();
            }
        }, 3000, 1500);
    }

    private void Login() {
        jason = new Jason();
        String usuario = jtxt_usuario.getText().trim();
        var senha = String.valueOf(jtxt_senha.getPassword());
        jason = sql.Login(usuario, senha);
        alert(jason);
        if (jason.getCod().equals("sucess")) {
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    ViewMenu viewMenu = new ViewMenu();
                    viewMenu.setVisible(true);
                    timer.cancel();
                    dispose();
                }
            }, 1000, 1000);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jpl_notificacao1 = new javax.swing.JPanel();
        jlb_icon1 = new javax.swing.JLabel();
        jlb_notificacao1 = new javax.swing.JLabel();
        jpl_notificacao2 = new javax.swing.JPanel();
        jlb_icon2 = new javax.swing.JLabel();
        jlb_notificacao2 = new javax.swing.JLabel();
        jpl_notificacao = new javax.swing.JPanel();
        jlb_icon = new javax.swing.JLabel();
        jlb_notificacao = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btn_minimizar = new javax.swing.JLabel();
        btn_fechar = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtxt_usuario = new javax.swing.JTextField();
        jtxt_senha = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_login = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jpl_notificacao1.setBackground(new java.awt.Color(255, 255, 255));
        jpl_notificacao1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_icon1.setBackground(new java.awt.Color(255, 255, 255));
        jlb_icon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/ok_25px.png"))); // NOI18N
        jpl_notificacao1.add(jlb_icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, -1, 30));

        jlb_notificacao1.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jlb_notificacao1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb_notificacao1.setText("Login realizado com sucesso");
        jpl_notificacao1.add(jlb_notificacao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 200, 30));

        jPanel1.add(jpl_notificacao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 340, 30));

        jpl_notificacao2.setBackground(new java.awt.Color(255, 255, 255));
        jpl_notificacao2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_icon2.setBackground(new java.awt.Color(255, 255, 255));
        jlb_icon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/cancel_25px.png"))); // NOI18N
        jpl_notificacao2.add(jlb_icon2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, -1, 30));

        jlb_notificacao2.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jlb_notificacao2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jpl_notificacao2.add(jlb_notificacao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 0, 270, 30));

        jPanel1.add(jpl_notificacao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 340, 30));

        jpl_notificacao.setBackground(new java.awt.Color(255, 255, 255));
        jpl_notificacao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_icon.setBackground(new java.awt.Color(255, 255, 255));
        jlb_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/error_25px.png"))); // NOI18N
        jpl_notificacao.add(jlb_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 22, 30));

        jlb_notificacao.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        jlb_notificacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb_notificacao.setText("Login Inválido");
        jpl_notificacao.add(jlb_notificacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 100, 30));

        jPanel1.add(jpl_notificacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 340, 30));

        jPanel2.setBackground(new java.awt.Color(10, 188, 96));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_minimizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_minimizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/minimizar_branco.png"))); // NOI18N
        btn_minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_minimizarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_minimizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_minimizarMouseExited(evt);
            }
        });
        jPanel2.add(btn_minimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 13, -1, -1));

        btn_fechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_fechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/fechar_branco.png"))); // NOI18N
        btn_fechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_fecharMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_fecharMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_fecharMouseExited(evt);
            }
        });
        jPanel2.add(btn_fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(313, 12, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/logo_fael.png"))); // NOI18N
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 340, 40));

        jtxt_usuario.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_usuario.setToolTipText("");
        jtxt_usuario.setBorder(null);
        jPanel1.add(jtxt_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 126, 170, 30));

        jtxt_senha.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_senha.setBorder(null);
        jPanel1.add(jtxt_senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 202, 170, 30));

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel2.setText("Usuário");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 94, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_medio_3.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 119, -1, -1));

        jLabel4.setFont(new java.awt.Font("Poppins", 0, 13)); // NOI18N
        jLabel4.setText("Senha");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_medio_3.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 195, -1, -1));

        btn_login.setFont(new java.awt.Font("Poppins", 1, 16)); // NOI18N
        btn_login.setForeground(new java.awt.Color(255, 255, 255));
        btn_login.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buttons/btn_login.png"))); // NOI18N
        btn_login.setText("Login");
        btn_login.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_loginMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_loginMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_loginMouseExited(evt);
            }
        });
        jPanel1.add(btn_login, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_minimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minimizarMouseClicked
        setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btn_minimizarMouseClicked

    private void btn_minimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minimizarMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_minimizarMouseEntered

    private void btn_minimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minimizarMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_minimizarMouseExited

    private void btn_fecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fecharMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btn_fecharMouseClicked

    private void btn_fecharMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fecharMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_fecharMouseEntered

    private void btn_fecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fecharMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_fecharMouseExited

    private void btn_loginMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_loginMouseEntered

    private void btn_loginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_loginMouseExited

    private void btn_loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_loginMouseClicked
        Login();
    }//GEN-LAST:event_btn_loginMouseClicked

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
            java.util.logging.Logger.getLogger(ViewLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_fechar;
    private javax.swing.JLabel btn_login;
    private javax.swing.JLabel btn_minimizar;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlb_icon;
    private javax.swing.JLabel jlb_icon1;
    private javax.swing.JLabel jlb_icon2;
    private javax.swing.JLabel jlb_notificacao;
    private javax.swing.JLabel jlb_notificacao1;
    private javax.swing.JLabel jlb_notificacao2;
    private javax.swing.JPanel jpl_notificacao;
    private javax.swing.JPanel jpl_notificacao1;
    private javax.swing.JPanel jpl_notificacao2;
    private javax.swing.JPasswordField jtxt_senha;
    private javax.swing.JTextField jtxt_usuario;
    // End of variables declaration//GEN-END:variables
}
