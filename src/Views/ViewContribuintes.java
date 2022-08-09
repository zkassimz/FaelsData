package Views;

import Dao.SQLManager;
import Utils.Contribuinte;
import Utils.Env;
import Utils.Jason;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;

public final class ViewContribuintes extends javax.swing.JFrame {

    private int maximo = 0;
    private int pagina = 1;
    private int ctrl1, ctrl2;
    private final int minimo = 1;
    private Env env;
    private Jason jason;
    private ArrayList<Contribuinte> contribuintes;
    private ArrayList<Integer> coordenadas;
    private SQLManager sql;
    private JPanel[] Jpanel;
    private JLabel[] Jlabel;
    private Contribuinte contrib;

    private void paginacao() {
        if (maximo == minimo) {
            btn_ante_aba.setVisible(false);
            btn_prox_aba.setVisible(false);
            System.out.println("Condição 1");
        } else if (pagina == minimo) {
            btn_ante_aba.setVisible(false);
            btn_prox_aba.setVisible(true);
            System.out.println("Condição 2");
        } else if (pagina < maximo) {
            btn_ante_aba.setVisible(true);
            btn_prox_aba.setVisible(true);
            System.out.println("Condição 3");
        } else {
            btn_ante_aba.setVisible(true);
            btn_prox_aba.setVisible(false);
            System.out.println("Condição 4");
        }

    }

    private void MostrarAbas(int status) {
        jlp_info.setVisible(false);
        jpl_dados.setVisible(false);
        jlp_load.setVisible(true);
        System.out.println("Mostrar Abas");
        mudarPagina(status);
        paginacao();
        resetarPaineis();
        iniciarTela();
    }

    private void mudarPagina(int status) {
        switch (status) {
            case 0 -> {
                if (pagina + 1 <= maximo) {
                    this.ctrl1 += 2;
                    this.ctrl2 += 2;
                    this.pagina++;
                    System.out.println("Somei");
                }
            }

            case 1 -> {
                if (pagina - 1 >= minimo) {
                    this.ctrl1 -= 2;
                    this.ctrl2 -= 2;
                    this.pagina--;
                }
            }
        }
        paginacao();
    }

    public ViewContribuintes() throws SQLException {
        initComponents();
        IniciarVariaveis();
        Draw();
        loadElements();
        iniciarTela();
        paginacao();
    }

    private void ResetarPaginacao() {
        this.ctrl1 = 0;
        this.ctrl2 = 1;
        this.pagina = 1;
    }

    private void IniciarVariaveis() {
        sql = new SQLManager();
        this.ctrl1 = 0;
        this.ctrl2 = 1;
        env = new Env(sql.getSession().getUsuario());
        contrib = new Contribuinte();
        jason = new Jason();
        coordenadas = new ArrayList();
        jason = sql.BuscarContribuintes();
        contribuintes = jason.getResponse();
    }

    private void Draw() {
        Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dw = getSize();
        setLocation((ds.width - dw.width) / 2, (ds.height - dw.height) / 2);
    }

    private void recarregarTelaInicial() {
        jlp_info.setVisible(false);
        contribuintes = sql.BuscarContribuintes().getResponse();
        jtxt_nome_like.setText("");
        iniciarTela();
        paginacao();
    }

    private void mostrarPerfil(int tab) {
        String id = getID(tab);
        jason = sql.BuscarContribuinte(id);
        jtxt_nome_contribuinte.setText(jason.getResponse().get(0).getNome());
        jtxt_nome_mae.setText(jason.getResponse().get(0).getNomeMae());
        jtxt_nome_pai.setText(jason.getResponse().get(0).getNomePai());
        jtxt_sexo.setText(jason.getResponse().get(0).getSexo());
        jtxt_expedicao.setText(jason.getResponse().get(0).getData_Expedicao_Rg());
        jtxt_rg.setText(jason.getResponse().get(0).getRg());
        jtxt_cpf.setText(jason.getResponse().get(0).getCpf());
        jtxt_nascimento.setText(jason.getResponse().get(0).getNascimento());
        jtxt_expedidor.setText(jason.getResponse().get(0).getOrgao_Expedidor());
        jtxt_rua.setText(jason.getResponse().get(0).getRua());
        jtxt_numero_casa.setText(jason.getResponse().get(0).getNumero_Casa());
        jtxt_bairro.setText(jason.getResponse().get(0).getBairro());
        jtxt_cidade.setText(jason.getResponse().get(0).getCidade());
        jtxt_naturalidade.setText(jason.getResponse().get(0).getNaturalidade());
        jtxt_uf.setText(jason.getResponse().get(0).getUF());
        jtxt_estado_civil.setText(jason.getResponse().get(0).getEstado_Civil());
        jtxt_alfabetizacao.setText(jason.getResponse().get(0).getAlfabetizacao());
        jtxt_nacionalidade.setText(jason.getResponse().get(0).getNacionalidade());
        jtxt_empregador.setText(jason.getResponse().get(0).getEmpregador());
        jtxt_profissao.setText(jason.getResponse().get(0).getProfissao());
        jtxt_local_trabalho.setText(jason.getResponse().get(0).getLocal_Trabalho());
        jtxt_apelido.setText(jason.getResponse().get(0).getApelido());
        jtxt_expedicao_ctps.setText(jason.getResponse().get(0).getData_Expedicao_Ctps());
        jtxt_ctps.setText(jason.getResponse().get(0).getCtps());
        jtxt_titulo.setText(jason.getResponse().get(0).getTitulo_Eleitor());
        jtxt_telefone.setText(jason.getResponse().get(0).getTelefone());
        jtxt_cep.setText(jason.getResponse().get(0).getCep());
        jlp_pagina_3.setVisible(false);
        jlp_pagina_1.setVisible(true);
    }

    private String getID(int tab) {
        String ID = "";
        switch (tab) {
            case 1 ->
                ID = jlb_1.getText().substring(0, pagina);
            case 2 ->
                ID = jlb_2.getText().substring(0, pagina);
            case 3 ->
                ID = jlb_3.getText().substring(0, pagina);
            case 4 ->
                ID = jlb_4.getText().substring(0, pagina);
            case 5 ->
                ID = jlb_5.getText().substring(0, pagina);
            case 6 ->
                ID = jlb_6.getText().substring(0, pagina);
            case 7 ->
                ID = jlb_7.getText().substring(0, pagina);
            case 8 ->
                ID = jlb_8.getText().substring(0, pagina);
            case 9 ->
                ID = jlb_9.getText().substring(0, pagina);
            case 10 -> {
                if (pagina == 1) {
                    ID = jlb_10.getText().substring(0, pagina + 1);
                } else {
                    ID = jlb_10.getText().substring(0, pagina);
                }
            }

        }
        return ID;
    }

    private void iniciarTela() {
        contribuintes = jason.getResponse();
        jtxt_nome_like.setText("");
        if (contribuintes.isEmpty()) {
            maximo = 0;
            jlp_load.setVisible(false);
            jpl_dados.setVisible(false);
            jlp_info.setVisible(true);
            paginacao();
        } else {
            jpl_dados.setVisible(false);
            jlp_load.setVisible(true);
            Coordenadas();
            ListarContribuintes();
            Timer timer = new Timer();
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    jlp_load.setVisible(false);
                    jlp_info.setVisible(false);
                    jpl_dados.setVisible(true);
                    timer.cancel();
                }
            }, 550, 550);
        }
    }

    private void loadElements() {
        jlb_usuario_atual.setText(env.getUsuario());
        jlb_nivel_acesso.setText(env.getAcesso());
        jlb_tipo_acesso.setText(env.getTipo_acesso());
        jlb_usuario_atual1.setText(env.getUsuario());
        jlb_nivel_acesso1.setText(env.getAcesso());
        jlb_tipo_acesso1.setText(env.getTipo_acesso());
        jlb_usuario_atual2.setText(env.getUsuario());
        jlb_nivel_acesso2.setText(env.getAcesso());
        jlb_tipo_acesso2.setText(env.getTipo_acesso());
        jlp_pagina_2.setVisible(false);
        jlp_pagina_1.setVisible(false);
        resetarPaineis();
        jlp_load.setVisible(false);
        jlp_info.setVisible(false);
        setVisible(false);
    }

    private void Coordenadas() {
        coordenadas = new ArrayList();
        int length = contribuintes.size();
        System.out.println("LENGTH: " + length);
        int inicio = 0;
        int fim = 0;
        int aux = 0;
        if (length >= 1) {
            if (length > 10) {
                for (int i = 0; i < length; i++) {
                    aux++;
                    if (aux == 10) {
                        aux = 0;
                        coordenadas.add(inicio);
                        fim = inicio + 9;
                        inicio = fim + 1;
                        coordenadas.add(fim);
                    } else {
                        fim = aux;
                    }
                }
                if (aux > 0) {
                    coordenadas.add(inicio);
                    coordenadas.add(inicio + fim - 1);
                }
            } else {
                coordenadas.add(inicio);
                coordenadas.add(length - 1);
            }
        }
        this.maximo = coordenadas.size() / 2;
    }

    private void resetarPaineis() {
        Jpanel = new JPanel[]{jlp_1, jlp_2, jlp_3, jlp_4, jlp_5, jlp_6, jlp_7, jlp_8, jlp_9, jlp_10};
        Jlabel = new JLabel[]{jlb_1, jlb_2, jlb_3, jlb_4, jlb_5, jlb_6, jlb_7, jlb_8, jlb_9, jlb_10};
        for (int i = 0; i < Jpanel.length; i++) {
            Jpanel[i].setVisible(false);
            Jlabel[i].setText("");
        }
    }

    private void BuscarContribuinte(String nome) {
        if (!nome.equals("")) {
            ResetarPaginacao();
            Coordenadas();
            contribuintes = new ArrayList();
            jpl_dados.setVisible(false);
            jlp_info.setVisible(false);
            jlp_load.setVisible(true);
            resetarPaineis();
            jason = sql.BuscarContribuintes(nome);
            contribuintes = jason.getResponse();
            iniciarTela();
            paginacao();
        }
    }

    private void ListarContribuintes() {
        int index;
        if (jason.getCod().equals("sucess")) {
            for (int i = coordenadas.get(ctrl1); i <= coordenadas.get(ctrl2); i++) {
                contrib = contribuintes.get(i);
                if (pagina > 1) {
                    index = i - (10 * (pagina - 1));
                } else {
                    index = i;
                }
                Jlabel[index].setText(Integer.toString(contrib.getID()) + " - " + contrib.getNome());
                Jpanel[index].setVisible(true);
            }
        } else if (jason.getCod().equals("warn")) {
            jpl_dados.setVisible(false);
            jlp_info.setVisible(true);
        }

    }

    private void alert(Jason jason) {
        Icon icon = null;
        switch (jason.getCod()) {
            case "err" ->
                icon = new ImageIcon(getClass().getResource("/imagens/icons/cancel_25ox.png"));
            case "warn" ->
                icon = new ImageIcon(getClass().getResource("/imagens/icons/error_25px.png"));
            case "sucess" ->
                icon = new ImageIcon(getClass().getResource("/imagens/icons/ok_25px.png"));
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        rSTableMetroBeanInfo1 = new rojerusan.RSTableMetroBeanInfo();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btn_minimizar = new javax.swing.JLabel();
        btn_fechar = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JPanelLinhaSuperior = new javax.swing.JPanel();
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
        btn_fechar_perfil = new javax.swing.JLabel();
        jpl_notificacao = new javax.swing.JPanel();
        jlb_icon = new javax.swing.JLabel();
        jlb_notificacao = new javax.swing.JLabel();
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
        btn_fechar_perfil1 = new javax.swing.JLabel();
        jlp_pagina_3 = new javax.swing.JPanel();
        jtxt_nome_like = new javax.swing.JTextField();
        jpl_dados = new javax.swing.JPanel();
        jlp_1 = new javax.swing.JPanel();
        jlb_1 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jlp_2 = new javax.swing.JPanel();
        jlb_2 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jlp_3 = new javax.swing.JPanel();
        jlb_3 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jlp_4 = new javax.swing.JPanel();
        jlb_4 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jlp_5 = new javax.swing.JPanel();
        jlb_5 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jlp_6 = new javax.swing.JPanel();
        jlb_6 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jlp_7 = new javax.swing.JPanel();
        jlb_7 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jlp_8 = new javax.swing.JPanel();
        jlb_8 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jlp_9 = new javax.swing.JPanel();
        jlb_9 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jlp_10 = new javax.swing.JPanel();
        jlb_10 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        btn_prox_aba = new javax.swing.JLabel();
        btn_ante_aba = new javax.swing.JLabel();
        btn_buscar_contrib = new javax.swing.JLabel();
        jlp_info = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jlp_load = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        JPanelLinhaInferior2 = new javax.swing.JPanel();
        jlb_usuario_atual2 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jlb_tipo_acesso2 = new javax.swing.JLabel();
        jlb_nivel_acesso2 = new javax.swing.JLabel();
        jpl_notificacao1 = new javax.swing.JPanel();
        jlb_icon1 = new javax.swing.JLabel();
        jlb_notificacao1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btn_recarregar = new javax.swing.JLabel();

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
        jLabel5.setText("Contribuintes");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, -1, 40));

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
        jlp_pagina_2.add(jlb_nome_pai2, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 246, 220, -1));

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

        btn_fechar_perfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/fechar_cinza.png"))); // NOI18N
        btn_fechar_perfil.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_fechar_perfilMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_fechar_perfilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_fechar_perfilMouseExited(evt);
            }
        });
        jlp_pagina_2.add(btn_fechar_perfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, -1, -1));

        jpl_notificacao.setBackground(new java.awt.Color(255, 255, 255));
        jpl_notificacao.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_icon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb_icon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jpl_notificacao.add(jlb_icon, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, -2, 23, 30));

        jlb_notificacao.setFont(new java.awt.Font("Poppins", 1, 10)); // NOI18N
        jpl_notificacao.add(jlb_notificacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 1, 250, 28));

        jlp_pagina_2.add(jpl_notificacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 500, 290, 28));

        jPanel2.add(jlp_pagina_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 880, 530));

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

        btn_fechar_perfil1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/fechar_cinza.png"))); // NOI18N
        btn_fechar_perfil1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_fechar_perfil1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_fechar_perfil1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_fechar_perfil1MouseExited(evt);
            }
        });
        jlp_pagina_1.add(btn_fechar_perfil1, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, -1, -1));

        jPanel2.add(jlp_pagina_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 880, 530));

        jlp_pagina_3.setBackground(new java.awt.Color(255, 255, 255));
        jlp_pagina_3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jtxt_nome_like.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jtxt_nome_like.setBorder(null);
        jtxt_nome_like.setVerifyInputWhenFocusTarget(false);
        jtxt_nome_like.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jtxt_nome_likeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jtxt_nome_likeMouseExited(evt);
            }
        });
        jlp_pagina_3.add(jtxt_nome_like, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 18, 390, 30));

        jpl_dados.setBackground(new java.awt.Color(255, 255, 255));
        jpl_dados.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlp_1.setBackground(new java.awt.Color(255, 255, 255));
        jlp_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlp_1MouseClicked(evt);
            }
        });
        jlp_1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_1.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jlb_1.setForeground(new java.awt.Color(255, 255, 255));
        jlb_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlb_1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlb_1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlb_1MouseExited(evt);
            }
        });
        jlp_1.add(jlb_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 0, 420, 40));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/painel.png"))); // NOI18N
        jLabel34.setText("jLabel34");
        jlp_1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -3, 450, -1));

        jpl_dados.add(jlp_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 1, 445, 40));

        jlp_2.setBackground(new java.awt.Color(255, 255, 255));
        jlp_2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_2.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jlb_2.setForeground(new java.awt.Color(255, 255, 255));
        jlb_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlb_2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlb_2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlb_2MouseExited(evt);
            }
        });
        jlp_2.add(jlb_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 0, 420, 40));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/painel.png"))); // NOI18N
        jLabel38.setText("jLabel34");
        jlp_2.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -3, 450, -1));

        jpl_dados.add(jlp_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 45, 445, 40));

        jlp_3.setBackground(new java.awt.Color(255, 255, 255));
        jlp_3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_3.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jlb_3.setForeground(new java.awt.Color(255, 255, 255));
        jlb_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlb_3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlb_3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlb_3MouseExited(evt);
            }
        });
        jlp_3.add(jlb_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 0, 420, 40));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/painel.png"))); // NOI18N
        jLabel39.setText("jLabel34");
        jlp_3.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -3, 450, -1));

        jpl_dados.add(jlp_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 89, 445, 40));

        jlp_4.setBackground(new java.awt.Color(255, 255, 255));
        jlp_4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_4.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jlb_4.setForeground(new java.awt.Color(255, 255, 255));
        jlb_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlb_4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlb_4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlb_4MouseExited(evt);
            }
        });
        jlp_4.add(jlb_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 0, 420, 40));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/painel.png"))); // NOI18N
        jLabel40.setText("jLabel34");
        jlp_4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -3, 450, -1));

        jpl_dados.add(jlp_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 133, 445, 40));

        jlp_5.setBackground(new java.awt.Color(255, 255, 255));
        jlp_5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_5.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jlb_5.setForeground(new java.awt.Color(255, 255, 255));
        jlb_5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlb_5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlb_5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlb_5MouseExited(evt);
            }
        });
        jlp_5.add(jlb_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 0, 420, 40));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/painel.png"))); // NOI18N
        jLabel42.setText("jLabel34");
        jlp_5.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -3, 450, -1));

        jpl_dados.add(jlp_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 177, 445, 40));

        jlp_6.setBackground(new java.awt.Color(255, 255, 255));
        jlp_6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_6.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jlb_6.setForeground(new java.awt.Color(255, 255, 255));
        jlb_6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlb_6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlb_6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlb_6MouseExited(evt);
            }
        });
        jlp_6.add(jlb_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 0, 420, 40));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/painel.png"))); // NOI18N
        jLabel43.setText("jLabel34");
        jlp_6.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -3, 450, -1));

        jpl_dados.add(jlp_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 221, 445, 40));

        jlp_7.setBackground(new java.awt.Color(255, 255, 255));
        jlp_7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_7.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jlb_7.setForeground(new java.awt.Color(255, 255, 255));
        jlb_7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlb_7MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlb_7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlb_7MouseExited(evt);
            }
        });
        jlp_7.add(jlb_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 0, 420, 40));

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/painel.png"))); // NOI18N
        jLabel50.setText("jLabel34");
        jlp_7.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -3, 450, -1));

        jpl_dados.add(jlp_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 265, 445, 40));

        jlp_8.setBackground(new java.awt.Color(255, 255, 255));
        jlp_8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_8.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jlb_8.setForeground(new java.awt.Color(255, 255, 255));
        jlb_8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlb_8MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlb_8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlb_8MouseExited(evt);
            }
        });
        jlp_8.add(jlb_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 0, 420, 40));

        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/painel.png"))); // NOI18N
        jLabel51.setText("jLabel34");
        jlp_8.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -3, 450, -1));

        jpl_dados.add(jlp_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 309, 445, 40));

        jlp_9.setBackground(new java.awt.Color(255, 255, 255));
        jlp_9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_9.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jlb_9.setForeground(new java.awt.Color(255, 255, 255));
        jlb_9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlb_9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlb_9MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlb_9MouseExited(evt);
            }
        });
        jlp_9.add(jlb_9, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 0, 420, 40));

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/painel.png"))); // NOI18N
        jLabel52.setText("jLabel34");
        jlp_9.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -3, 450, -1));

        jpl_dados.add(jlp_9, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 353, 445, 40));

        jlp_10.setBackground(new java.awt.Color(255, 255, 255));
        jlp_10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_10.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jlb_10.setForeground(new java.awt.Color(255, 255, 255));
        jlb_10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlb_10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlb_10MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlb_10MouseExited(evt);
            }
        });
        jlp_10.add(jlb_10, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 0, 420, 40));

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/painel.png"))); // NOI18N
        jLabel53.setText("jLabel34");
        jlp_10.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(-2, -3, 450, -1));

        jpl_dados.add(jlp_10, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 397, 445, 40));

        btn_prox_aba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buttons/btn_prox_azul.png"))); // NOI18N
        btn_prox_aba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_prox_abaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_prox_abaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_prox_abaMouseExited(evt);
            }
        });
        jpl_dados.add(btn_prox_aba, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 220, -1, -1));

        btn_ante_aba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/buttons/btn_ante_azul.png"))); // NOI18N
        btn_ante_aba.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ante_abaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ante_abaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_ante_abaMouseExited(evt);
            }
        });
        jpl_dados.add(btn_ante_aba, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 180, -1, -1));

        jlp_pagina_3.add(jpl_dados, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 55, 820, 440));

        btn_buscar_contrib.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/search_25px.png"))); // NOI18N
        btn_buscar_contrib.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_buscar_contribMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_buscar_contribMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_buscar_contribMouseExited(evt);
            }
        });
        jlp_pagina_3.add(btn_buscar_contrib, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 20, -1, -1));

        jlp_info.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(10, 188, 96));
        jLabel11.setText("Ops!! Nenhum contribuinte foi encontrado...");

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/search_96px.png"))); // NOI18N

        javax.swing.GroupLayout jlp_infoLayout = new javax.swing.GroupLayout(jlp_info);
        jlp_info.setLayout(jlp_infoLayout);
        jlp_infoLayout.setHorizontalGroup(
            jlp_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jlp_infoLayout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addGroup(jlp_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jlp_infoLayout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(343, 343, 343))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jlp_infoLayout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(151, 151, 151))))
        );
        jlp_infoLayout.setVerticalGroup(
            jlp_infoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jlp_infoLayout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel32)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addContainerGap(153, Short.MAX_VALUE))
        );

        jlp_pagina_3.add(jlp_info, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 55, 820, 440));

        jlp_load.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setBackground(new java.awt.Color(255, 255, 255));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/Spinner-1s-144px.gif"))); // NOI18N

        javax.swing.GroupLayout jlp_loadLayout = new javax.swing.GroupLayout(jlp_load);
        jlp_load.setLayout(jlp_loadLayout);
        jlp_loadLayout.setHorizontalGroup(
            jlp_loadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jlp_loadLayout.createSequentialGroup()
                .addContainerGap(346, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addGap(330, 330, 330))
        );
        jlp_loadLayout.setVerticalGroup(
            jlp_loadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jlp_loadLayout.createSequentialGroup()
                .addGap(130, 130, 130)
                .addComponent(jLabel18)
                .addContainerGap(166, Short.MAX_VALUE))
        );

        jlp_pagina_3.add(jlp_load, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 55, 820, 440));

        JPanelLinhaInferior2.setBackground(new java.awt.Color(222, 222, 222));
        JPanelLinhaInferior2.setPreferredSize(new java.awt.Dimension(878, 1));

        javax.swing.GroupLayout JPanelLinhaInferior2Layout = new javax.swing.GroupLayout(JPanelLinhaInferior2);
        JPanelLinhaInferior2.setLayout(JPanelLinhaInferior2Layout);
        JPanelLinhaInferior2Layout.setHorizontalGroup(
            JPanelLinhaInferior2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 880, Short.MAX_VALUE)
        );
        JPanelLinhaInferior2Layout.setVerticalGroup(
            JPanelLinhaInferior2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jlp_pagina_3.add(JPanelLinhaInferior2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 495, 880, 1));

        jlb_usuario_atual2.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jlb_usuario_atual2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlp_pagina_3.add(jlb_usuario_atual2, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 500, 100, 30));

        jLabel28.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Usuário: ");
        jlp_pagina_3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 50, 30));

        jLabel30.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setText("Nível de Acesso:");
        jlp_pagina_3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 500, 90, 30));

        jlb_tipo_acesso2.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jlb_tipo_acesso2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlp_pagina_3.add(jlb_tipo_acesso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 500, 190, 30));

        jlb_nivel_acesso2.setFont(new java.awt.Font("Poppins", 1, 9)); // NOI18N
        jlb_nivel_acesso2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jlp_pagina_3.add(jlb_nivel_acesso2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 500, 30, 30));

        jpl_notificacao1.setBackground(new java.awt.Color(255, 255, 255));
        jpl_notificacao1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlb_icon1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlb_icon1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jpl_notificacao1.add(jlb_icon1, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, -2, 23, 30));

        jlb_notificacao1.setFont(new java.awt.Font("Poppins", 1, 10)); // NOI18N
        jpl_notificacao1.add(jlb_notificacao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 1, 250, 28));

        jlp_pagina_3.add(jpl_notificacao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 500, 290, 28));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/inputs/Big Input.png"))); // NOI18N
        jlp_pagina_3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        btn_recarregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_recarregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/icons/sync_32px.png"))); // NOI18N
        btn_recarregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_recarregarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_recarregarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_recarregarMouseExited(evt);
            }
        });
        jlp_pagina_3.add(btn_recarregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 0, -1, 50));

        jPanel2.add(jlp_pagina_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 880, 530));

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

    private void btn_buscar_contribMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_buscar_contribMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_buscar_contribMouseEntered

    private void btn_buscar_contribMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_buscar_contribMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_buscar_contribMouseExited

    private void btn_buscar_contribMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_buscar_contribMouseClicked
        BuscarContribuinte(jtxt_nome_like.getText().trim());
    }//GEN-LAST:event_btn_buscar_contribMouseClicked

    private void btn_recarregarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_recarregarMouseEntered
        this.setCursor(Cursor.HAND_CURSOR);
    }//GEN-LAST:event_btn_recarregarMouseEntered

    private void btn_recarregarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_recarregarMouseExited
        this.setCursor(Cursor.DEFAULT_CURSOR);
    }//GEN-LAST:event_btn_recarregarMouseExited

    private void btn_recarregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_recarregarMouseClicked
        recarregarTelaInicial();
    }//GEN-LAST:event_btn_recarregarMouseClicked

    private void jtxt_nome_likeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxt_nome_likeMouseEntered

    }//GEN-LAST:event_jtxt_nome_likeMouseEntered

    private void jtxt_nome_likeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtxt_nome_likeMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jtxt_nome_likeMouseExited

    private void btn_prox_abaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_prox_abaMouseClicked
        MostrarAbas(0);
    }//GEN-LAST:event_btn_prox_abaMouseClicked

    private void btn_ante_abaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ante_abaMouseClicked
        MostrarAbas(1);
    }//GEN-LAST:event_btn_ante_abaMouseClicked

    private void jlp_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlp_1MouseClicked
        mostrarPerfil(1);
    }//GEN-LAST:event_jlp_1MouseClicked

    private void jlb_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_1MouseClicked
        mostrarPerfil(1);
    }//GEN-LAST:event_jlb_1MouseClicked

    private void jlb_1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_1MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jlb_1MouseEntered

    private void jlb_1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_1MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jlb_1MouseExited

    private void btn_fechar_perfilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fechar_perfilMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_fechar_perfilMouseEntered

    private void btn_fechar_perfilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fechar_perfilMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_fechar_perfilMouseExited

    private void btn_fechar_perfilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fechar_perfilMouseClicked
        jlp_pagina_1.setVisible(false);
        jlp_pagina_2.setVisible(false);
        jlp_pagina_3.setVisible(true);
        recarregarTelaInicial();
    }//GEN-LAST:event_btn_fechar_perfilMouseClicked

    private void btn_fechar_perfil1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fechar_perfil1MouseClicked
        jlp_pagina_1.setVisible(false);
        jlp_pagina_2.setVisible(false);
        jlp_pagina_3.setVisible(true);
        recarregarTelaInicial();
    }//GEN-LAST:event_btn_fechar_perfil1MouseClicked

    private void btn_fechar_perfil1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fechar_perfil1MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_fechar_perfil1MouseEntered

    private void btn_fechar_perfil1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_fechar_perfil1MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_fechar_perfil1MouseExited

    private void jlb_2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_2MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jlb_2MouseEntered

    private void jlb_2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_2MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jlb_2MouseExited

    private void jlb_3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_3MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jlb_3MouseEntered

    private void jlb_2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_2MouseClicked
        mostrarPerfil(2);
    }//GEN-LAST:event_jlb_2MouseClicked

    private void jlb_3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_3MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jlb_3MouseExited

    private void jlb_3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_3MouseClicked
        mostrarPerfil(3);
    }//GEN-LAST:event_jlb_3MouseClicked

    private void jlb_4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_4MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jlb_4MouseEntered

    private void jlb_4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_4MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jlb_4MouseExited

    private void jlb_4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_4MouseClicked
        mostrarPerfil(4);
    }//GEN-LAST:event_jlb_4MouseClicked

    private void jlb_7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_7MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jlb_7MouseEntered

    private void jlb_5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_5MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jlb_5MouseEntered

    private void jlb_5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_5MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jlb_5MouseExited

    private void jlb_5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_5MouseClicked
        mostrarPerfil(5);
    }//GEN-LAST:event_jlb_5MouseClicked

    private void jlb_6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_6MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jlb_6MouseEntered

    private void jlb_6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_6MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jlb_6MouseExited

    private void jlb_6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_6MouseClicked
        mostrarPerfil(6);
    }//GEN-LAST:event_jlb_6MouseClicked

    private void jlb_7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_7MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jlb_7MouseExited

    private void jlb_7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_7MouseClicked
        mostrarPerfil(7);
    }//GEN-LAST:event_jlb_7MouseClicked

    private void jlb_8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_8MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jlb_8MouseEntered

    private void jlb_8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_8MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jlb_8MouseExited

    private void jlb_8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_8MouseClicked
        mostrarPerfil(8);
    }//GEN-LAST:event_jlb_8MouseClicked

    private void jlb_9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_9MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jlb_9MouseEntered

    private void jlb_9MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_9MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jlb_9MouseExited

    private void jlb_9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_9MouseClicked
        mostrarPerfil(9);
    }//GEN-LAST:event_jlb_9MouseClicked

    private void jlb_10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_10MouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jlb_10MouseEntered

    private void jlb_10MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_10MouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_jlb_10MouseExited

    private void jlb_10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlb_10MouseClicked
        mostrarPerfil(10);
    }//GEN-LAST:event_jlb_10MouseClicked

    private void btn_ante_abaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ante_abaMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_ante_abaMouseEntered

    private void btn_ante_abaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ante_abaMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_ante_abaMouseExited

    private void btn_prox_abaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_prox_abaMouseEntered
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_prox_abaMouseEntered

    private void btn_prox_abaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_prox_abaMouseExited
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_btn_prox_abaMouseExited

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewContribuintes.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new ViewContribuintes().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ViewContribuintes.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelLinhaInferior;
    private javax.swing.JPanel JPanelLinhaInferior1;
    private javax.swing.JPanel JPanelLinhaInferior2;
    private javax.swing.JPanel JPanelLinhaSuperior;
    private javax.swing.JLabel btn_ante_aba;
    private javax.swing.JLabel btn_buscar_contrib;
    private javax.swing.JLabel btn_chance_tab_green_1;
    private javax.swing.JLabel btn_change_tab_green_2;
    private javax.swing.JLabel btn_fechar;
    private javax.swing.JLabel btn_fechar_perfil;
    private javax.swing.JLabel btn_fechar_perfil1;
    private javax.swing.JLabel btn_minimizar;
    private javax.swing.JLabel btn_prox_aba;
    private javax.swing.JLabel btn_recarregar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel jlb_1;
    private javax.swing.JLabel jlb_10;
    private javax.swing.JLabel jlb_2;
    private javax.swing.JLabel jlb_3;
    private javax.swing.JLabel jlb_4;
    private javax.swing.JLabel jlb_5;
    private javax.swing.JLabel jlb_6;
    private javax.swing.JLabel jlb_7;
    private javax.swing.JLabel jlb_8;
    private javax.swing.JLabel jlb_9;
    private javax.swing.JLabel jlb_apelido;
    private javax.swing.JLabel jlb_bairro;
    private javax.swing.JLabel jlb_cidade;
    private javax.swing.JLabel jlb_cpf;
    private javax.swing.JLabel jlb_data_expedicao;
    private javax.swing.JLabel jlb_icon;
    private javax.swing.JLabel jlb_icon1;
    private javax.swing.JLabel jlb_nascimento;
    private javax.swing.JLabel jlb_naturalidade;
    private javax.swing.JLabel jlb_nivel_acesso;
    private javax.swing.JLabel jlb_nivel_acesso1;
    private javax.swing.JLabel jlb_nivel_acesso2;
    private javax.swing.JLabel jlb_nome_mae;
    private javax.swing.JLabel jlb_nome_mae1;
    private javax.swing.JLabel jlb_nome_pai;
    private javax.swing.JLabel jlb_nome_pai1;
    private javax.swing.JLabel jlb_nome_pai2;
    private javax.swing.JLabel jlb_notificacao;
    private javax.swing.JLabel jlb_notificacao1;
    private javax.swing.JLabel jlb_numero_rua;
    private javax.swing.JLabel jlb_orgao_expedidor;
    private javax.swing.JLabel jlb_rg;
    private javax.swing.JLabel jlb_rua;
    private javax.swing.JLabel jlb_tipo_acesso;
    private javax.swing.JLabel jlb_tipo_acesso1;
    private javax.swing.JLabel jlb_tipo_acesso2;
    private javax.swing.JLabel jlb_uf;
    private javax.swing.JLabel jlb_usuario_atual;
    private javax.swing.JLabel jlb_usuario_atual1;
    private javax.swing.JLabel jlb_usuario_atual2;
    private javax.swing.JPanel jlp_1;
    private javax.swing.JPanel jlp_10;
    private javax.swing.JPanel jlp_2;
    private javax.swing.JPanel jlp_3;
    private javax.swing.JPanel jlp_4;
    private javax.swing.JPanel jlp_5;
    private javax.swing.JPanel jlp_6;
    private javax.swing.JPanel jlp_7;
    private javax.swing.JPanel jlp_8;
    private javax.swing.JPanel jlp_9;
    private javax.swing.JPanel jlp_info;
    private javax.swing.JPanel jlp_load;
    private javax.swing.JPanel jlp_pagina_1;
    private javax.swing.JPanel jlp_pagina_2;
    private javax.swing.JPanel jlp_pagina_3;
    private javax.swing.JPanel jpl_dados;
    private javax.swing.JPanel jpl_notificacao;
    private javax.swing.JPanel jpl_notificacao1;
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
    private javax.swing.JTextField jtxt_nome_like;
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
    private rojerusan.RSTableMetroBeanInfo rSTableMetroBeanInfo1;
    // End of variables declaration//GEN-END:variables
}
