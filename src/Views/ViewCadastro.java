package Views;

import Dao.SQLManager;
import Utils.Contribuinte;
import Utils.Env;
import Utils.Jason;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public final class ViewCadastro extends javax.swing.JFrame {

    private Env env;
    private SQLManager sql;
    private String Jtxt[];
    private Jason jason;

    public ViewCadastro() {
        sql = new SQLManager();
        env = new Env(sql.getSession().getUsuario());
        initComponents();
        centralizarTela();
        loadElements();
    }

    public void centralizarTela() {
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
    }

    public void loadElements() {
        jlb_usuario_atual.setText(env.getUsuario());
        jlb_nivel_acesso.setText(env.getAcesso());
        jlb_tipo_acesso.setText(env.getTipo_acesso());
        jlb_usuario_atual1.setText(env.getUsuario());
        jlb_nivel_acesso1.setText(env.getAcesso());
        jlb_tipo_acesso1.setText(env.getTipo_acesso());
        jlp_pagina_2.setVisible(false);
    }

    public void cadastrarContribuintes() {
        int cont = 0;
        Jason jason = new Jason();
        Jtxt = new String[]{
            jtxt_nome_contribuinte.getText(),
            jtxt_nome_mae.getText(),
            jtxt_nome_pai.getText(),
            jtxt_sexo.getText(),
            jtxt_expedicao.getText(),
            jtxt_rg.getText(),
            jtxt_cpf.getText(),
            jtxt_nascimento.getText(),
            jtxt_expedicao.getText(),
            jtxt_rua.getText(),
            jtxt_numero_casa.getText(),
            jtxt_bairro.getText(),
            jtxt_cidade.getText(),
            jtxt_naturalidade.getText(),
            jtxt_uf.getText(),
            jtxt_estado_civil.getText(),
            jtxt_alfabetizacao.getText(),
            jtxt_nacionalidade.getText(),
            jtxt_empregador.getText(),
            jtxt_profissao.getText(),
            jtxt_local_trabalho.getText(),
            jtxt_apelido.getText(),
            jtxt_expedicao_ctps.getText(),
            jtxt_ctps.getText(),
            jtxt_titulo.getText(),
            jtxt_telefone.getText(),
            jtxt_cep.getText()
        };

        for (String field : Jtxt) {
            if (field.equals("")) {
                cont++;
            }
        }
        if (cont == 0) {
            Contribuinte contribuinte = new Contribuinte();
            contribuinte.setNome(jtxt_nome_contribuinte.getText());
            contribuinte.setNomeMae(jtxt_nome_mae.getText());
            contribuinte.setNomePai(jtxt_nome_pai.getText());
            contribuinte.setSexo(jtxt_sexo.getText());
            contribuinte.setData_Expedicao_Ctps(jtxt_expedicao.getText());
            contribuinte.setRg(jtxt_rg.getText());
            contribuinte.setCpf(jtxt_cpf.getText());
            contribuinte.setNascimento(jtxt_nascimento.getText());
            contribuinte.setOrgao_Expedidor(jtxt_expedicao.getText());
            contribuinte.setRua(jtxt_rua.getText());
            contribuinte.setNumero_Casa(jtxt_numero_casa.getText());
            contribuinte.setBairro(jtxt_bairro.getText());
            contribuinte.setCidade(jtxt_cidade.getText());
            contribuinte.setNaturalidade(jtxt_naturalidade.getText());
            contribuinte.setUF(jtxt_uf.getText());
            contribuinte.setEstado_Civil(jtxt_estado_civil.getText());
            contribuinte.setAlfabetizacao(jtxt_alfabetizacao.getText());
            contribuinte.setNacionalidade(jtxt_nacionalidade.getText());
            contribuinte.setEmpregador(jtxt_empregador.getText());
            contribuinte.setProfissao(jtxt_profissao.getText());
            contribuinte.setLocal_Trabalho(jtxt_local_trabalho.getText());
            contribuinte.setApelido(jtxt_apelido.getText());
            contribuinte.setData_Expedicao_Ctps(jtxt_expedicao_ctps.getText());
            contribuinte.setCtps(jtxt_ctps.getText());
            contribuinte.setTitulo_Eleitor(jtxt_titulo.getText());
            contribuinte.setTelefone(jtxt_telefone.getText());
            contribuinte.setCep(jtxt_cep.getText());
            SQLManager sql = new SQLManager();
            jason = sql.CadastroContrinuinte(contribuinte);
        } else {
            jason.setMessagem("Todos os campos devem estar preenchidos");
            jason.setCod("warn");
        }
        alert(jason);
    }

    public void alert(Jason jason) {
        Icon icon = null;
        switch (jason.getCod()) {
            case "err" -> icon = new ImageIcon(getClass().getResource("/imagens/icons/cancel_25ox.png"));
            case "warn" -> icon = new ImageIcon(getClass().getResource("/imagens/icons/error_25px.png"));
            case "sucess" -> icon = new ImageIcon(getClass().getResource("/imagens/icons/ok_25px.png"));
        }
        jlb_icon.setIcon(icon);
        jlb_notificacao.setText(jason.getMessagem());
        jpl_notificacao.setVisible(true);
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                jpl_notificacao.setVisible(false);
                timer.cancel();
            }
        }, 3000, 1500);
    }

    public void resetarCampos() {
        jtxt_nome_contribuinte.setText("");
        jtxt_nome_mae.setText("");
        jtxt_nome_pai.setText("");
        jtxt_sexo.setText("");
        jtxt_expedicao.setText("");
        jtxt_rg.setText("");
        jtxt_cpf.setText("");
        jtxt_nascimento.setText("");
        jtxt_expedidor.setText("");
        jtxt_rua.setText("");
        jtxt_numero_casa.setText("");
        jtxt_bairro.setText("");
        jtxt_cidade.setText("");
        jtxt_naturalidade.setText("");
        jtxt_uf.setText("");
        jtxt_estado_civil.setText("");
        jtxt_alfabetizacao.setText("");
        jtxt_nacionalidade.setText("");
        jtxt_empregador.setText("");
        jtxt_profissao.setText("");
        jtxt_local_trabalho.setText("");
        jtxt_apelido.setText("");
        jtxt_expedicao_ctps.setText("");
        jtxt_ctps.setText("");
        jtxt_titulo.setText("");
        jtxt_telefone.setText("");
        jtxt_cep.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btn_minimizar = new javax.swing.JLabel();
        btn_fechar = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JPanelLinhaSuperior = new javax.swing.JPanel();
        jlp_pagina_1 = new javax.swing.JPanel();
        JPanelLinhaInferior = new javax.swing.JPanel();
        jtxt_nome_contribuinte = new javax.swing.JTextField();
        jtxt_nome_mae = new javax.swing.JTextField();
        jtxt_nome_pai = new javax.swing.JTextField();
        jtxt_sexo = new javax.swing.JTextField();
        jtxt_expedicao = new javax.swing.JFormattedTextField();
        jtxt_rg = new javax.swing.JFormattedTextField();
        jtxt_cpf = new javax.swing.JFormattedTextField();
        jtxt_nascimento = new javax.swing.JFormattedTextField();
        jtxt_expedidor = new javax.swing.JTextField();
        jtxt_rua = new javax.swing.JTextField();
        jtxt_numero_casa = new javax.swing.JFormattedTextField();
        jtxt_bairro = new javax.swing.JTextField();
        jtxt_cidade = new javax.swing.JTextField();
        jtxt_naturalidade = new javax.swing.JTextField();
        jtxt_uf = new javax.swing.JFormattedTextField();
        jlb_usuario_atual = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jlb_tipo_acesso = new javax.swing.JLabel();
        jlb_nivel_acesso = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jlb_cpf = new javax.swing.JLabel();
        jlb_nascimento = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jlb_rg = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jlb_rua = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jlb_numero_rua = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jlb_bairro = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jlb_cidade = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jlb_naturalidade = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jlb_nome_mae = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jlb_nome_pai = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jlb_uf = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jlb_orgao_expedidor = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jlb_apelido = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jlb_data_expedicao = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        btn_change_tab_green_2 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jlp_pagina_2 = new javax.swing.JPanel();
        JPanelLinhaInferior1 = new javax.swing.JPanel();
        jtxt_estado_civil = new javax.swing.JTextField();
        jtxt_alfabetizacao = new javax.swing.JTextField();
        jtxt_nacionalidade = new javax.swing.JTextField();
        jtxt_empregador = new javax.swing.JTextField();
        jtxt_profissao = new javax.swing.JTextField();
        jtxt_local_trabalho = new javax.swing.JTextField();
        jtxt_apelido = new javax.swing.JTextField();
        jtxt_expedicao_ctps = new javax.swing.JFormattedTextField();
        jtxt_ctps = new javax.swing.JFormattedTextField();
        jtxt_titulo = new javax.swing.JFormattedTextField();
        jtxt_telefone = new javax.swing.JFormattedTextField();
        jtxt_cep = new javax.swing.JFormattedTextField();
        jlb_usuario_atual1 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jlb_tipo_acesso1 = new javax.swing.JLabel();
        jlb_nivel_acesso1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jlb_nome_mae1 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jlb_nome_pai1 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        btn_chance_tab_green_1 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jlb_nome_pai2 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        btn_reiniciar = new javax.swing.JLabel();
        btn_cadastrar = new javax.swing.JLabel();
        jpl_notificacao = new javax.swing.JPanel();
        jlb_icon = new javax.swing.JLabel();
        jlb_notificacao = new javax.swing.JLabel();

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
        jLabel5.setText("Cadastro");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, -1, 40));

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

        jlp_pagina_1.setBackground(new java.awt.Color(255, 255, 255));
        jlp_pagina_1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jlp_pagina_1.add(JPanelLinhaInferior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 495, 880, 1));

        jtxt_nome_contribuinte.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_nome_contribuinte.setBorder(null);
        jlp_pagina_1.add(jtxt_nome_contribuinte, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 54, 430, 30));

        jtxt_nome_mae.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_nome_mae.setBorder(null);
        jlp_pagina_1.add(jtxt_nome_mae, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 116, 430, 30));

        jtxt_nome_pai.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_nome_pai.setBorder(null);
        jlp_pagina_1.add(jtxt_nome_pai, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 178, 430, 30));

        jtxt_sexo.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_sexo.setBorder(null);
        jlp_pagina_1.add(jtxt_sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 135, 30));

        jtxt_expedicao.setBorder(null);
        try {
            jtxt_expedicao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("**/**/****")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtxt_expedicao.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlp_pagina_1.add(jtxt_expedicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 303, 100, 30));

        jtxt_rg.setBorder(null);
        try {
            jtxt_rg.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("**.***.***-**")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtxt_rg.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_rg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jtxt_rgMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jtxt_rgMouseExited(evt);
            }
        });
        jlp_pagina_1.add(jtxt_rg, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 302, 110, 30));

        jtxt_cpf.setBorder(null);
        try {
            jtxt_cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("***.***.***-**")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtxt_cpf.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlp_pagina_1.add(jtxt_cpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 241, 130, 30));

        jtxt_nascimento.setBorder(null);
        try {
            jtxt_nascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("**/**/****")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtxt_nascimento.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlp_pagina_1.add(jtxt_nascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(555, 241, 100, 30));

        jtxt_expedidor.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_expedidor.setBorder(null);
        jlp_pagina_1.add(jtxt_expedidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 303, 135, 30));

        jtxt_rua.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_rua.setBorder(null);
        jlp_pagina_1.add(jtxt_rua, new org.netbeans.lib.awtextra.AbsoluteConstraints(285, 365, 212, 30));

        jtxt_numero_casa.setBorder(null);
        try {
            jtxt_numero_casa.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("***")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtxt_numero_casa.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlp_pagina_1.add(jtxt_numero_casa, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 365, 30, 30));

        jtxt_bairro.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_bairro.setBorder(null);
        jlp_pagina_1.add(jtxt_bairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 365, 135, 30));

        jtxt_cidade.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_cidade.setBorder(null);
        jlp_pagina_1.add(jtxt_cidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 426, 170, 30));

        jtxt_naturalidade.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_naturalidade.setBorder(null);
        jlp_pagina_1.add(jtxt_naturalidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(428, 427, 170, 30));

        jtxt_uf.setBorder(null);
        try {
            jtxt_uf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("**")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtxt_uf.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlp_pagina_1.add(jtxt_uf, new org.netbeans.lib.awtextra.AbsoluteConstraints(624, 426, 30, 30));

        jlb_usuario_atual.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jlb_usuario_atual.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlp_pagina_1.add(jlb_usuario_atual, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 500, 100, 30));

        jLabel14.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Usuário: ");
        jlp_pagina_1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 50, 30));

        jLabel15.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Nível de Acesso:");
        jlp_pagina_1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 90, 30));

        jlb_tipo_acesso.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jlb_tipo_acesso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlp_pagina_1.add(jlb_tipo_acesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, 190, 30));

        jlb_nivel_acesso.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jlb_nivel_acesso.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlp_pagina_1.add(jlb_nivel_acesso, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, 30, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/Big Input.png"))); // NOI18N
        jlp_pagina_1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 47, -1, -1));

        jLabel1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel1.setText("Nome do Contribuinte");
        jlp_pagina_1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_pequeno_1.png"))); // NOI18N
        jlp_pagina_1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 295, -1, -1));

        jlb_cpf.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_cpf.setText("CPF");
        jlp_pagina_1.add(jlb_cpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 216, -1, -1));

        jlb_nascimento.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_nascimento.setText("Nascimento");
        jlp_pagina_1.add(jlb_nascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(542, 216, -1, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_pequeno_1.png"))); // NOI18N
        jlp_pagina_1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 295, -1, -1));

        jlb_rg.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_rg.setText("RG");
        jlp_pagina_1.add(jlb_rg, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 278, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_medio_2.png"))); // NOI18N
        jlp_pagina_1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 233, -1, -1));

        jlb_rua.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_rua.setText("Rua");
        jlp_pagina_1.add(jlb_rua, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 340, -1, -1));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_medio_1.png"))); // NOI18N
        jlp_pagina_1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 357, -1, -1));

        jlb_numero_rua.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_numero_rua.setText("Nº");
        jlp_pagina_1.add(jlb_numero_rua, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 340, -1, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_pequeno_2.png"))); // NOI18N
        jlp_pagina_1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(615, 419, -1, -1));

        jlb_bairro.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_bairro.setText("Bairro");
        jlp_pagina_1.add(jlb_bairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(512, 340, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_pequeno_1.png"))); // NOI18N
        jlp_pagina_1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(511, 357, -1, -1));

        jlb_cidade.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_cidade.setText("Cidade");
        jlp_pagina_1.add(jlb_cidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 402, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_medio_3.png"))); // NOI18N
        jlp_pagina_1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(219, 419, -1, -1));

        jlb_naturalidade.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_naturalidade.setText("Naturalidade");
        jlp_pagina_1.add(jlb_naturalidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 400, -1, -1));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_medio_3.png"))); // NOI18N
        jlp_pagina_1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 419, -1, -1));

        jlb_nome_mae.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_nome_mae.setText("Nome da Mãe");
        jlp_pagina_1.add(jlb_nome_mae, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 92, -1, -1));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/Big Input.png"))); // NOI18N
        jlp_pagina_1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 109, -1, -1));

        jlb_nome_pai.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_nome_pai.setText("Nome do Pai");
        jlp_pagina_1.add(jlb_nome_pai, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 154, -1, -1));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/Big Input.png"))); // NOI18N
        jlp_pagina_1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 171, -1, -1));

        jlb_uf.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_uf.setText("UF");
        jlp_pagina_1.add(jlb_uf, new org.netbeans.lib.awtextra.AbsoluteConstraints(618, 400, -1, -1));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_pequeno_2.png"))); // NOI18N
        jlp_pagina_1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 357, -1, -1));

        jlb_orgao_expedidor.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_orgao_expedidor.setText("Orgão Expedidor");
        jlp_pagina_1.add(jlb_orgao_expedidor, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 278, -1, -1));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_pequeno_1.png"))); // NOI18N
        jlp_pagina_1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 233, -1, -1));

        jlb_apelido.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_apelido.setText("Sexo");
        jlp_pagina_1.add(jlb_apelido, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 216, -1, -1));

        jLabel63.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_pequeno_1.png"))); // NOI18N
        jlp_pagina_1.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 233, -1, -1));

        jlb_data_expedicao.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_data_expedicao.setText("Data de Expedição");
        jlp_pagina_1.add(jlb_data_expedicao, new org.netbeans.lib.awtextra.AbsoluteConstraints(541, 278, -1, -1));

        jLabel65.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_medio_2.png"))); // NOI18N
        jlp_pagina_1.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 295, -1, -1));

        btn_change_tab_green_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/tab_verde_2.png"))); // NOI18N
        btn_change_tab_green_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_change_tab_green_2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_change_tab_green_2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_change_tab_green_2MouseExited(evt);
            }
        });
        jlp_pagina_1.add(btn_change_tab_green_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 500, -1, -1));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/tab_cinza_1.png"))); // NOI18N
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel16MouseExited(evt);
            }
        });
        jlp_pagina_1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 500, -1, -1));

        jPanel2.add(jlp_pagina_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 880, 530));

        jlp_pagina_2.setBackground(new java.awt.Color(255, 255, 255));
        jlp_pagina_2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JPanelLinhaInferior1.setBackground(new java.awt.Color(222, 222, 222));
        JPanelLinhaInferior1.setPreferredSize(new java.awt.Dimension(878, 1));

        javax.swing.GroupLayout JPanelLinhaInferior1Layout = new javax.swing.GroupLayout(JPanelLinhaInferior1);
        JPanelLinhaInferior1.setLayout(JPanelLinhaInferior1Layout);
        JPanelLinhaInferior1Layout.setHorizontalGroup(
            JPanelLinhaInferior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        JPanelLinhaInferior1Layout.setVerticalGroup(
            JPanelLinhaInferior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jlp_pagina_2.add(JPanelLinhaInferior1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 495, 880, 1));

        jtxt_estado_civil.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_estado_civil.setBorder(null);
        jlp_pagina_2.add(jtxt_estado_civil, new org.netbeans.lib.awtextra.AbsoluteConstraints(525, 84, 134, 30));

        jtxt_alfabetizacao.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_alfabetizacao.setBorder(null);
        jlp_pagina_2.add(jtxt_alfabetizacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 85, 108, 30));

        jtxt_nacionalidade.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_nacionalidade.setBorder(null);
        jlp_pagina_2.add(jtxt_nacionalidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 332, 134, 30));

        jtxt_empregador.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_empregador.setBorder(null);
        jlp_pagina_2.add(jtxt_empregador, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 270, 235, 30));

        jtxt_profissao.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_profissao.setBorder(null);
        jlp_pagina_2.add(jtxt_profissao, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 270, 168, 30));

        jtxt_local_trabalho.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_local_trabalho.setBorder(null);
        jlp_pagina_2.add(jtxt_local_trabalho, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 208, 420, 30));

        jtxt_apelido.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_apelido.setBorder(null);
        jlp_pagina_2.add(jtxt_apelido, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 85, 132, 30));

        jtxt_expedicao_ctps.setBorder(null);
        try {
            jtxt_expedicao_ctps.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("**/**/****")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtxt_expedicao_ctps.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_expedicao_ctps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_expedicao_ctpsActionPerformed(evt);
            }
        });
        jlp_pagina_2.add(jtxt_expedicao_ctps, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 335, 120, 25));

        jtxt_ctps.setBorder(null);
        try {
            jtxt_ctps.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("******* ***-*")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtxt_ctps.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_ctps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_ctpsActionPerformed(evt);
            }
        });
        jlp_pagina_2.add(jtxt_ctps, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 335, 100, 25));

        jtxt_titulo.setBorder(null);
        try {
            jtxt_titulo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("****.****.****")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtxt_titulo.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_titulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtxt_tituloActionPerformed(evt);
            }
        });
        jlp_pagina_2.add(jtxt_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(524, 152, 134, 20));

        jtxt_telefone.setBorder(null);
        try {
            jtxt_telefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(**) * ****-****")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtxt_telefone.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlp_pagina_2.add(jtxt_telefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 152, 134, 20));

        jtxt_cep.setBorder(null);
        try {
            jtxt_cep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("**.***-***")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jtxt_cep.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlp_pagina_2.add(jtxt_cep, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 146, 104, 30));

        jlb_usuario_atual1.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jlb_usuario_atual1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlp_pagina_2.add(jlb_usuario_atual1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 500, 100, 30));

        jLabel20.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel20.setText("Usuário: ");
        jlp_pagina_2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 50, 30));

        jLabel22.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setText("Nível de Acesso:");
        jlp_pagina_2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 90, 30));

        jlb_tipo_acesso1.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jlb_tipo_acesso1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlp_pagina_2.add(jlb_tipo_acesso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, 190, 30));

        jlb_nivel_acesso1.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jlb_nivel_acesso1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlp_pagina_2.add(jlb_nivel_acesso1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, 30, 30));

        jLabel9.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel9.setText("Apelido");
        jlp_pagina_2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, -1, -1));

        jlb_nome_mae1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_nome_mae1.setText("Local de Trabalho");
        jlp_pagina_2.add(jlb_nome_mae1, new org.netbeans.lib.awtextra.AbsoluteConstraints(221, 184, -1, -1));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/Big Input.png"))); // NOI18N
        jlp_pagina_2.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(216, 201, -1, -1));

        jlb_nome_pai1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_nome_pai1.setText("Profissão");
        jlp_pagina_2.add(jlb_nome_pai1, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 246, -1, -1));

        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_pequeno_1.png"))); // NOI18N
        jlp_pagina_2.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 77, -1, -1));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/tab_cinza_2.png"))); // NOI18N
        jlp_pagina_2.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(845, 500, -1, -1));

        btn_chance_tab_green_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/tab_verde_1.png"))); // NOI18N
        btn_chance_tab_green_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_chance_tab_green_1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_chance_tab_green_1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_chance_tab_green_1MouseExited(evt);
            }
        });
        jlp_pagina_2.add(btn_chance_tab_green_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(815, 500, -1, -1));

        jLabel44.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel44.setText("Alfabetização");
        jlp_pagina_2.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 60, -1, -1));

        jLabel68.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_medio_2.png"))); // NOI18N
        jlp_pagina_2.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(381, 77, -1, -1));

        jLabel45.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel45.setText("Estado Civil");
        jlp_pagina_2.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 60, -1, -1));

        jLabel69.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_pequeno_1.png"))); // NOI18N
        jlp_pagina_2.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(514, 77, 150, -1));

        jLabel66.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_medio_3.png"))); // NOI18N
        jlp_pagina_2.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 263, -1, -1));

        jlb_nome_pai2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jlb_nome_pai2.setText("Empregador");
        jlp_pagina_2.add(jlb_nome_pai2, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 246, -1, -1));

        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_medio_4.png"))); // NOI18N
        jlp_pagina_2.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 263, -1, -1));

        jLabel24.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel24.setText("Nacionalidade");
        jlp_pagina_2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(222, 308, -1, -1));

        jLabel46.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel46.setText("CTPS");
        jlp_pagina_2.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 308, -1, -1));

        jLabel72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_medio_2.png"))); // NOI18N
        jlp_pagina_2.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 325, -1, -1));

        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_pequeno_1.png"))); // NOI18N
        jlp_pagina_2.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 325, -1, -1));

        jLabel47.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel47.setText("Data de Expedição");
        jlp_pagina_2.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(516, 308, -1, -1));

        jLabel74.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_pequeno_1.png"))); // NOI18N
        jlp_pagina_2.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(514, 325, 150, -1));

        jLabel26.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel26.setText("CEP");
        jlp_pagina_2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 122, -1, -1));

        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_medio_2.png"))); // NOI18N
        jlp_pagina_2.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(218, 139, -1, -1));

        jLabel76.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_pequeno_1.png"))); // NOI18N
        jlp_pagina_2.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 139, -1, -1));

        jLabel48.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel48.setText("Telefone");
        jlp_pagina_2.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 122, -1, -1));

        jLabel49.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel49.setText("Título de Eleitor");
        jlp_pagina_2.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 122, -1, -1));

        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/input_pequeno_1.png"))); // NOI18N
        jlp_pagina_2.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(514, 139, 150, -1));

        btn_reiniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buttons/reiniciar.png"))); // NOI18N
        btn_reiniciar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_reiniciarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_reiniciarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_reiniciarMouseExited(evt);
            }
        });
        jlp_pagina_2.add(btn_reiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 420, -1, -1));

        btn_cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buttons/cadastrar.png"))); // NOI18N
        btn_cadastrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cadastrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cadastrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_cadastrarMouseExited(evt);
            }
        });
        jlp_pagina_2.add(btn_cadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 420, -1, -1));

        jpl_notificacao.setBackground(new java.awt.Color(255, 255, 255));
        jpl_notificacao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb_icon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jpl_notificacao.add(jlb_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, -2, 23, 30));

        jlb_notificacao.setFont(new java.awt.Font("Poppins", 1, 10)); // NOI18N
        jpl_notificacao.add(jlb_notificacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 1, 250, 28));

        jlp_pagina_2.add(jpl_notificacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 500, 290, 28));

        jPanel2.add(jlp_pagina_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 880, 530));

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
        ViewMenu viewMenu = new ViewMenu();
        viewMenu.setVisible(true);
        dispose();
    }//GEN-LAST:event_btn_fecharMouseClicked

    private void jLabel16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_jLabel16MouseEntered

    private void jLabel16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_jLabel16MouseExited

    private void btn_change_tab_green_2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_change_tab_green_2MouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_change_tab_green_2MouseEntered

    private void btn_change_tab_green_2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_change_tab_green_2MouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_change_tab_green_2MouseExited

    private void btn_chance_tab_green_1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_chance_tab_green_1MouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_chance_tab_green_1MouseEntered

    private void btn_chance_tab_green_1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_chance_tab_green_1MouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_chance_tab_green_1MouseExited

    private void btn_chance_tab_green_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_chance_tab_green_1MouseClicked
        jlp_pagina_1.setVisible(true);
        jlp_pagina_2.setVisible(false);
    }//GEN-LAST:event_btn_chance_tab_green_1MouseClicked

    private void btn_change_tab_green_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_change_tab_green_2MouseClicked
        jlp_pagina_1.setVisible(false);
        jlp_pagina_2.setVisible(true);
    }//GEN-LAST:event_btn_change_tab_green_2MouseClicked

    private void btn_reiniciarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reiniciarMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_reiniciarMouseEntered

    private void btn_reiniciarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reiniciarMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_reiniciarMouseExited

    private void btn_reiniciarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_reiniciarMouseClicked
        resetarCampos();
    }//GEN-LAST:event_btn_reiniciarMouseClicked

    private void btn_cadastrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cadastrarMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_cadastrarMouseEntered

    private void btn_cadastrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cadastrarMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_cadastrarMouseExited

    private void btn_cadastrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cadastrarMouseClicked
        cadastrarContribuintes();
    }//GEN-LAST:event_btn_cadastrarMouseClicked

    private void jtxt_rgMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxt_rgMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_jtxt_rgMouseEntered

    private void jtxt_rgMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxt_rgMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_jtxt_rgMouseExited

    private void jtxt_tituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_tituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_tituloActionPerformed

    private void jtxt_ctpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_ctpsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_ctpsActionPerformed

    private void jtxt_expedicao_ctpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtxt_expedicao_ctpsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_expedicao_ctpsActionPerformed

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
            java.util.logging.Logger.getLogger(ViewCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewCadastro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            new ViewCadastro().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelLinhaInferior;
    private javax.swing.JPanel JPanelLinhaInferior1;
    private javax.swing.JPanel JPanelLinhaSuperior;
    private javax.swing.JLabel btn_cadastrar;
    private javax.swing.JLabel btn_chance_tab_green_1;
    private javax.swing.JLabel btn_change_tab_green_2;
    private javax.swing.JLabel btn_fechar;
    private javax.swing.JLabel btn_minimizar;
    private javax.swing.JLabel btn_reiniciar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlb_apelido;
    private javax.swing.JLabel jlb_bairro;
    private javax.swing.JLabel jlb_cidade;
    private javax.swing.JLabel jlb_cpf;
    private javax.swing.JLabel jlb_data_expedicao;
    private javax.swing.JLabel jlb_icon;
    private javax.swing.JLabel jlb_nascimento;
    private javax.swing.JLabel jlb_naturalidade;
    private javax.swing.JLabel jlb_nivel_acesso;
    private javax.swing.JLabel jlb_nivel_acesso1;
    private javax.swing.JLabel jlb_nome_mae;
    private javax.swing.JLabel jlb_nome_mae1;
    private javax.swing.JLabel jlb_nome_pai;
    private javax.swing.JLabel jlb_nome_pai1;
    private javax.swing.JLabel jlb_nome_pai2;
    private javax.swing.JLabel jlb_notificacao;
    private javax.swing.JLabel jlb_numero_rua;
    private javax.swing.JLabel jlb_orgao_expedidor;
    private javax.swing.JLabel jlb_rg;
    private javax.swing.JLabel jlb_rua;
    private javax.swing.JLabel jlb_tipo_acesso;
    private javax.swing.JLabel jlb_tipo_acesso1;
    private javax.swing.JLabel jlb_uf;
    private javax.swing.JLabel jlb_usuario_atual;
    private javax.swing.JLabel jlb_usuario_atual1;
    private javax.swing.JPanel jlp_pagina_1;
    private javax.swing.JPanel jlp_pagina_2;
    private javax.swing.JPanel jpl_notificacao;
    private javax.swing.JTextField jtxt_alfabetizacao;
    private javax.swing.JTextField jtxt_apelido;
    private javax.swing.JTextField jtxt_bairro;
    private javax.swing.JFormattedTextField jtxt_cep;
    private javax.swing.JTextField jtxt_cidade;
    private javax.swing.JFormattedTextField jtxt_cpf;
    private javax.swing.JFormattedTextField jtxt_ctps;
    private javax.swing.JTextField jtxt_empregador;
    private javax.swing.JTextField jtxt_estado_civil;
    private javax.swing.JFormattedTextField jtxt_expedicao;
    private javax.swing.JFormattedTextField jtxt_expedicao_ctps;
    private javax.swing.JTextField jtxt_expedidor;
    private javax.swing.JTextField jtxt_local_trabalho;
    private javax.swing.JTextField jtxt_nacionalidade;
    private javax.swing.JFormattedTextField jtxt_nascimento;
    private javax.swing.JTextField jtxt_naturalidade;
    private javax.swing.JTextField jtxt_nome_contribuinte;
    private javax.swing.JTextField jtxt_nome_mae;
    private javax.swing.JTextField jtxt_nome_pai;
    private javax.swing.JFormattedTextField jtxt_numero_casa;
    private javax.swing.JTextField jtxt_profissao;
    private javax.swing.JFormattedTextField jtxt_rg;
    private javax.swing.JTextField jtxt_rua;
    private javax.swing.JTextField jtxt_sexo;
    private javax.swing.JFormattedTextField jtxt_telefone;
    private javax.swing.JFormattedTextField jtxt_titulo;
    private javax.swing.JFormattedTextField jtxt_uf;
    // End of variables declaration//GEN-END:variables
}
