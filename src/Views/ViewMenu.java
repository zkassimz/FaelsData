package Views;

import Dao.SQLManager;
import Utils.Env;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ViewMenu extends javax.swing.JFrame {

    Env env;
    SQLManager sql;

    public ViewMenu() {
        sql = new SQLManager();
        env = new Env(sql.getSession().getUsuario());
        initComponents();
        centralizarTela();
        jlp_menssagem.setVisible(false);
        load();
    }

    public void centralizarTela() {
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
    }

    public void load() {
        jlb_usuario_atual.setText(env.getUsuario());
        jlb_nivel_acesso.setText(env.getAcesso());
        jlb_tipo_acesso.setText(env.getTipo_acesso());
    }

    public boolean nivelDeAcesso(String btn) {
        boolean acesso = false;
        if ((btn.equals("btn1")
                || btn.equals("btn3")
                || btn.equals("btn4")
                || btn.equals("btn6"))
                && Integer.parseInt(env.getAcesso()) <= 5) {
            acesso = true;
        }
        if ((btn.equals("btn2")
                || btn.equals("btn5"))
                && Integer.parseInt(env.getAcesso()) <= 1) {
            acesso = true;
        }
        return acesso;
    }

    public void router(String btn, String route) {
        if (nivelDeAcesso(btn)) {
            switch (btn) {
                case "btn1" -> {
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            ViewCadastro cadastro = new ViewCadastro();
                            cadastro.setVisible(true);
                            timer.cancel();
                            dispose();
                        }
                    }, 100, 10);
                }
                case "btn2" -> {

                }
                case "btn3" ->
                    System.out.println(route);
                case "btn4" -> {
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            ViewContribuintes contrib;
                            try {
                                contrib = new ViewContribuintes();
                                contrib.setVisible(true);
                            } catch (SQLException ex) {
                                Logger.getLogger(ViewMenu.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            timer.cancel();
                            dispose();
                        }
                    }, 100, 10);
                }
                case "btn5" ->
                    System.out.println(route);
                case "btn6" ->
                    System.out.println(route);
            }
        } else {
            System.out.println("Acesso negado");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btn_minimizar = new javax.swing.JLabel();
        btn_fechar = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JPanelLinhaSuperior = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btn_cofre = new javax.swing.JLabel();
        btn_comprovantes = new javax.swing.JLabel();
        btn_pagamento = new javax.swing.JLabel();
        btn_cadastro = new javax.swing.JLabel();
        btn_contribuintes = new javax.swing.JLabel();
        btn_dashboard = new javax.swing.JLabel();
        JPanelLinhaInferior = new javax.swing.JPanel();
        jlb_usuario_atual = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jlb_tipo_acesso = new javax.swing.JLabel();
        jlb_nivel_acesso = new javax.swing.JLabel();
        jlp_menssagem = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jLabel7.setFont(new java.awt.Font("Poppins", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel3.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Aparência");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(10, 188, 96));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jPanel1.add(btn_minimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 14, -1, -1));

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
        jPanel1.add(btn_fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(855, 13, -1, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/logo_fael.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 40));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Poppins", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Menu");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, -1, 40));

        jPanel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 40));

        JPanelLinhaSuperior.setBackground(new java.awt.Color(222, 222, 222));

        javax.swing.GroupLayout JPanelLinhaSuperiorLayout = new javax.swing.GroupLayout(JPanelLinhaSuperior);
        JPanelLinhaSuperior.setLayout(JPanelLinhaSuperiorLayout);
        JPanelLinhaSuperiorLayout.setHorizontalGroup(
            JPanelLinhaSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        JPanelLinhaSuperiorLayout.setVerticalGroup(
            JPanelLinhaSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanel2.add(JPanelLinhaSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 873, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_cofre.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buttons/cofre.png"))); // NOI18N
        btn_cofre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cofreMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cofreMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cofreMouseExited(evt);
            }
        });
        jPanel3.add(btn_cofre, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, -1, -1));

        btn_comprovantes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buttons/comprovantes.png"))); // NOI18N
        btn_comprovantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_comprovantesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_comprovantesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_comprovantesMouseExited(evt);
            }
        });
        jPanel3.add(btn_comprovantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, -1, -1));

        btn_pagamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buttons/pagamento.png"))); // NOI18N
        btn_pagamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_pagamentoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_pagamentoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_pagamentoMouseExited(evt);
            }
        });
        jPanel3.add(btn_pagamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 290, -1, -1));

        btn_cadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buttons/cadastro.png"))); // NOI18N
        btn_cadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cadastroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cadastroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cadastroMouseExited(evt);
            }
        });
        jPanel3.add(btn_cadastro, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        btn_contribuintes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buttons/contribuintes.png"))); // NOI18N
        btn_contribuintes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_contribuintesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_contribuintesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_contribuintesMouseExited(evt);
            }
        });
        jPanel3.add(btn_contribuintes, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, -1, -1));

        btn_dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buttons/dashboard.png"))); // NOI18N
        btn_dashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_dashboardMouseExited(evt);
            }
        });
        jPanel3.add(btn_dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, -1, -1));

        JPanelLinhaInferior.setBackground(new java.awt.Color(222, 222, 222));
        JPanelLinhaInferior.setPreferredSize(new java.awt.Dimension(878, 1));

        javax.swing.GroupLayout JPanelLinhaInferiorLayout = new javax.swing.GroupLayout(JPanelLinhaInferior);
        JPanelLinhaInferior.setLayout(JPanelLinhaInferiorLayout);
        JPanelLinhaInferiorLayout.setHorizontalGroup(
            JPanelLinhaInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        JPanelLinhaInferiorLayout.setVerticalGroup(
            JPanelLinhaInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanel3.add(JPanelLinhaInferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 495, 880, 1));

        jlb_usuario_atual.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jlb_usuario_atual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(jlb_usuario_atual, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 500, 100, 30));

        jLabel14.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Usuário: ");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 50, 30));

        jLabel15.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Nível de Acesso:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 90, 30));

        jlb_tipo_acesso.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jlb_tipo_acesso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(jlb_tipo_acesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, 190, 30));

        jlb_nivel_acesso.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jlb_nivel_acesso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jPanel3.add(jlb_nivel_acesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, 30, 30));

        jlp_menssagem.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/error_25px.png"))); // NOI18N

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel2.setText("Acesso Negado!");

        javax.swing.GroupLayout jlp_menssagemLayout = new javax.swing.GroupLayout(jlp_menssagem);
        jlp_menssagem.setLayout(jlp_menssagemLayout);
        jlp_menssagemLayout.setHorizontalGroup(
            jlp_menssagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jlp_menssagemLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(213, Short.MAX_VALUE))
        );
        jlp_menssagemLayout.setVerticalGroup(
            jlp_menssagemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        jPanel3.add(jlp_menssagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 500, 390, 30));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 880, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_cadastroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cadastroMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_cadastroMouseEntered

    private void btn_cadastroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cadastroMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_cadastroMouseExited

    private void btn_cofreMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cofreMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_cofreMouseEntered

    private void btn_cofreMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cofreMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_cofreMouseExited

    private void btn_comprovantesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_comprovantesMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_comprovantesMouseEntered

    private void btn_comprovantesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_comprovantesMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_comprovantesMouseExited

    private void btn_contribuintesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_contribuintesMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_contribuintesMouseEntered

    private void btn_contribuintesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_contribuintesMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_contribuintesMouseExited

    private void btn_dashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_dashboardMouseEntered

    private void btn_dashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_dashboardMouseExited

    private void btn_pagamentoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pagamentoMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_pagamentoMouseEntered

    private void btn_pagamentoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pagamentoMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_pagamentoMouseExited

    private void btn_minimizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minimizarMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_minimizarMouseEntered

    private void btn_minimizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minimizarMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_minimizarMouseExited

    private void btn_fecharMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fecharMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_fecharMouseEntered

    private void btn_fecharMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fecharMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_fecharMouseExited

    private void btn_minimizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_minimizarMouseClicked
        setExtendedState(ICONIFIED);
    }//GEN-LAST:event_btn_minimizarMouseClicked

    private void btn_fecharMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fecharMouseClicked
        System.exit(0);
    }//GEN-LAST:event_btn_fecharMouseClicked

    private void btn_cadastroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cadastroMouseClicked
        router("btn1", "cadastro");
    }//GEN-LAST:event_btn_cadastroMouseClicked

    private void btn_cofreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cofreMouseClicked
        router("btn2", "cofre");
    }//GEN-LAST:event_btn_cofreMouseClicked

    private void btn_comprovantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_comprovantesMouseClicked
        router("btn3", "comprovantes");
    }//GEN-LAST:event_btn_comprovantesMouseClicked

    private void btn_contribuintesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_contribuintesMouseClicked
        router("btn4", "contribuintes");
    }//GEN-LAST:event_btn_contribuintesMouseClicked

    private void btn_dashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_dashboardMouseClicked
        router("btn5", "dashboard");
    }//GEN-LAST:event_btn_dashboardMouseClicked

    private void btn_pagamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_pagamentoMouseClicked
        router("btn6", "pagamentos");
    }//GEN-LAST:event_btn_pagamentoMouseClicked

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(ViewMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new ViewMenu().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelLinhaInferior;
    private javax.swing.JPanel JPanelLinhaSuperior;
    private javax.swing.JLabel btn_cadastro;
    private javax.swing.JLabel btn_cofre;
    private javax.swing.JLabel btn_comprovantes;
    private javax.swing.JLabel btn_contribuintes;
    private javax.swing.JLabel btn_dashboard;
    private javax.swing.JLabel btn_fechar;
    private javax.swing.JLabel btn_minimizar;
    private javax.swing.JLabel btn_pagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jlb_nivel_acesso;
    private javax.swing.JLabel jlb_tipo_acesso;
    private javax.swing.JLabel jlb_usuario_atual;
    private javax.swing.JPanel jlp_menssagem;
    // End of variables declaration//GEN-END:variables
}
