package Dao;

import Utils.Contribuinte;
import Utils.Env;
import Utils.Jason;
import Utils.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SQLManager {

    private final String Url;
    private final String Usuario;
    private final String Senha;
    private Connection conn = null;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private ArrayList<Contribuinte> contribuintes;
    Env env;

    public SQLManager() {
        env = new Env();
        this.Senha = env.getSenhaSQL();
        this.Url = env.getUrlSQL();
        System.out.println("URL " + this.Url);
        this.Usuario = env.getUsuarioSQl();
        contribuintes = new ArrayList();
    }

    public Connection connect() {
        Connection connector = null;
        try {
            connector = DriverManager.getConnection(Url + "?user=" + this.Usuario + "&password=" + this.Senha);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return connector;
    }

    public Jason CadastroContrinuinte(Contribuinte contribuinte) {
        Jason jason = new Jason();
        jason = this.BuscaContribuinteExistente(contribuinte.getCpf());
        if (jason.getCod().equals("sucess")) {
            String SQL = "INSERT INTO ENTIDADES"
                    + "(nome, endereco, cidade, pai, mae,naturalidade, estado_civil,empregador, RG,ssp,"
                    + "exp_rg, alfabetizado, uf, number_house, apelido, bairro, loctrab, nacionalidade,"
                    + "profissao,cpf,CTPS, exp_ctps,titulo, cep, data_nasc, telefone, sexo) values "
                    + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            conn = this.connect();
            try {
                pstmt = conn.prepareStatement(SQL);
                pstmt.setString(1, contribuinte.getNome());
                pstmt.setString(2, contribuinte.getRua());
                pstmt.setString(3, contribuinte.getCidade());
                pstmt.setString(4, contribuinte.getNomePai());
                pstmt.setString(5, contribuinte.getNomeMae());
                pstmt.setString(6, contribuinte.getNaturalidade());
                pstmt.setString(7, contribuinte.getEstado_Civil());
                pstmt.setString(8, contribuinte.getEmpregador());
                pstmt.setString(9, contribuinte.getRg());
                pstmt.setString(10, contribuinte.getOrgao_Expedidor());
                pstmt.setString(11, contribuinte.getData_Expedicao_Rg());
                pstmt.setString(12, contribuinte.getAlfabetizacao());
                pstmt.setString(13, contribuinte.getUF());
                pstmt.setString(14, contribuinte.getNumero_Casa());
                pstmt.setString(15, contribuinte.getApelido());
                pstmt.setString(16, contribuinte.getBairro());
                pstmt.setString(17, contribuinte.getLocal_Trabalho());
                pstmt.setString(18, contribuinte.getNacionalidade());
                pstmt.setString(19, contribuinte.getProfissao());
                pstmt.setString(20, contribuinte.getCpf());
                pstmt.setString(21, contribuinte.getCtps());
                pstmt.setString(22, contribuinte.getData_Expedicao_Ctps());
                pstmt.setString(23, contribuinte.getTitulo_Eleitor());
                pstmt.setString(24, contribuinte.getCep());
                pstmt.setString(25, contribuinte.getNascimento());
                pstmt.setString(26, contribuinte.getTelefone());
                pstmt.setString(27, contribuinte.getSexo());
                pstmt.execute();
                pstmt.close();
                jason.setCod("sucess");
                jason.setMessagem("Usuário Cadastrado com sucesso!");
            } catch (SQLException e) {
                jason.setCod("err");
                jason.setMessagem("Falha ao cadastrar o usuário!");
                System.out.println("Erro: " + e);
            }
        }
        return jason;
    }

    public Jason BuscaContribuinteExistente(String cpf) {
        String SQL = "SELECT ID FROM ENTIDADES WHERE CPF = ?";
        conn = this.connect();
        Jason jason = new Jason();
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, cpf);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                jason.setCod("warn");
                jason.setMessagem("O usuário já está cadastrado.");
            } else {
                jason.setCod("sucess");
                jason.setMessagem("Nenhum registro encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Erro " + e);
            jason.setCod("err");
            jason.setMessagem(e.toString());
        }
        return jason;
    }

    public Jason BuscarContribuintes() {
        String SQL = "SELECT ID, NOME FROM ENTIDADES";
        Jason jason = new Jason();
        conn = this.connect();
        try {
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            Contribuinte contrib;
            contribuintes.clear();
            while (rs.next()) {
                contrib = new Contribuinte();
                contrib.setID(rs.getInt("id"));
                contrib.setNome(rs.getString("nome"));
                contribuintes.add(contrib);
            }
            if (contribuintes.isEmpty()) {
                jason.setCod("warn");
                jason.setMessagem("Nenhum contribuinte encontrado!");
                jason.setResponse(contribuintes);
            } else {
                jason.setCod("sucess");
                jason.setMessagem("Contribuintes Encontrados!");
                jason.setResponse(contribuintes);
            }
        } catch (SQLException e) {
            System.out.println("Erro Like: " + e);
            jason.setCod("err");
            jason.setMessagem(e.toString());
            jason.setResponse(contribuintes);
        }
        return jason;
    }

    public Jason BuscarContribuintes(String nome) {
        String SQL = "SELECT ID, NOME FROM ENTIDADES WHERE NOME LIKE ?";
        conn = this.connect();
        Jason jason = new Jason();
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, "%" + nome + "%");
            rs = pstmt.executeQuery();
            Contribuinte contrib;
            contribuintes.clear();
            while (rs.next()) {
                contrib = new Contribuinte();
                contrib.setNome(rs.getString("nome"));
                contrib.setID(rs.getInt("id"));
                contribuintes.add(contrib);
            }
            if (contribuintes.isEmpty()) {
                jason.setCod("warn");
                jason.setMessagem("Nenhum contribuinte encontrado!");
                jason.setResponse(null);
            } else {
                jason.setCod("sucess");
                jason.setMessagem("Contribuintes Encontrados!");
                jason.setResponse(contribuintes);
            }
        } catch (SQLException e) {
            System.out.println("Erro Like: " + e);
            jason.setCod("err");
            jason.setMessagem(e.toString());
            jason.setResponse(null);
        }
        return jason;
    }

    public Jason BuscarContribuinte(String ID) {
        String SQL = "SELECT *  FROM ENTIDADES WHERE ID = ?";
        conn = this.connect();
        Jason jason = new Jason();
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, ID);
            rs = pstmt.executeQuery();
            Contribuinte contribuinte;
            contribuintes.clear();
            while (rs.next()) {
                contribuinte = new Contribuinte();
                contribuinte.setNome(rs.getString("nome"));
                contribuinte.setID(rs.getInt("id"));
                contribuinte.setNomeMae(rs.getString("mae"));
                contribuinte.setNomePai(rs.getString("pai"));
                contribuinte.setSexo(rs.getString("sexo"));
                contribuinte.setData_Expedicao_Ctps(rs.getString("exp_ctps"));
                contribuinte.setRg(rs.getString("RG"));
                contribuinte.setCpf(rs.getString("cpf"));
                contribuinte.setNascimento(rs.getString("data_nasc"));
                contribuinte.setOrgao_Expedidor(rs.getString("ssp"));
                contribuinte.setRua(rs.getString("endereco"));
                contribuinte.setNumero_Casa(rs.getString("number_house"));
                contribuinte.setBairro(rs.getString("bairro"));
                contribuinte.setCidade(rs.getString("cidade"));
                contribuinte.setNaturalidade(rs.getString("naturalidade"));
                contribuinte.setUF(rs.getString("uf"));
                contribuinte.setEstado_Civil(rs.getString("estado_civil"));
                contribuinte.setAlfabetizacao(rs.getString("alfabetizado"));
                contribuinte.setNacionalidade(rs.getString("nacionalidade"));
                contribuinte.setEmpregador(rs.getString("empregador"));
                contribuinte.setProfissao(rs.getString("profissao"));
                contribuinte.setLocal_Trabalho(rs.getString("loctrab"));
                contribuinte.setApelido(rs.getString("apelido"));
                contribuinte.setData_Expedicao_Ctps(rs.getString("exp_ctps"));
                contribuinte.setCtps(rs.getString("CTPS"));
                contribuinte.setTitulo_Eleitor(rs.getString("titulo"));
                contribuinte.setTelefone(rs.getString("telefone"));
                contribuinte.setCep(rs.getString("cep"));
                contribuintes.add(contribuinte);
            }
            if (contribuintes.isEmpty()) {
                jason.setCod("warn");
                jason.setMessagem("Nenhum contribuinte encontrado!");
                jason.setResponse(null);
            } else {
                jason.setCod("sucess");
                jason.setMessagem("Contribuintes Encontrados!");
                jason.setResponse(contribuintes);
            }
        } catch (SQLException e) {
            System.out.println("Erro Like: " + e);
            jason.setCod("err");
            jason.setMessagem(e.toString());
            jason.setResponse(contribuintes);
        }
        return jason;
    }

    public Jason Login(String usuario, String senha) {
        String SQL = "SELECT * FROM USUARIOS WHERE USUARIO = ? AND SENHA = ?";
        conn = this.connect();
        Jason jason = new Jason();
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, usuario);
            pstmt.setString(2, senha);
            rs = pstmt.executeQuery();
            Usuario user = new Usuario();
            user.setID(0);
            while (rs.next()) {
                user.setID(rs.getInt("id"));
                user.setUsuario(rs.getString("usuario"));
                user.setNivel_Acesso(Integer.toString(rs.getInt("acesso")));
                user.setTipo_Usuario(rs.getString("tipo_acesso"));
            }

            if (user.getID() == 0) {
                jason.setCod("warn");
                jason.setMessagem("Login inválido");
                jason.setResponse(null);
            } else {
                System.out.println("ID: " + user.getID());
                System.out.println("Nome: " + user.getUsuario());
                System.out.println("Nível de Acesso: " + user.getNivel_Acesso());
                System.out.println("Tipo de Usuário: " + user.getTipo_Usuario());
                jason.setCod("sucess");
                jason.setMessagem("Login realizado com sucesso");
                jason.setResponse(null);
                jason.setUsuario(user);
                Session(Integer.toString(user.getID()));
            }
        } catch (SQLException e) {
            System.out.println("Erro Like: " + e);
            jason.setCod("err");
            jason.setMessagem(e.toString());
            jason.setResponse(null);
        }
        return jason;
    }

    private void Session(String id) {
        String SQL = "UPDATE SESSIONS SET ID_USUARIO = ? WHERE ID_SESSION = 1";
        try {
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro " + e);
        }
    }

    public Jason getSession() {
        Jason jason = new Jason();
        String SubSQL = "(SELECT ID_USUARIO FROM SESSIONS WHERE ID_SESSION = 1)";
        String SQL = "SELECT * FROM USUARIOS WHERE ID = " + SubSQL;
        Usuario user = new Usuario();
        conn = this.connect();
        try {
            pstmt = conn.prepareStatement(SQL);
            rs = pstmt.executeQuery();
            user.setID(0);
            while (rs.next()) {
                user.setID(rs.getInt("id"));
                user.setUsuario(rs.getString("usuario"));
                user.setNivel_Acesso(Integer.toString(rs.getInt("acesso")));
                user.setTipo_Usuario(rs.getString("tipo_acesso"));
            }
            if (user.getID() != 0) {
                jason.setUsuario(user);
            }
        } catch (SQLException e) {
            System.out.println("Erro Like: " + e);
            jason.setCod("err");
            jason.setMessagem(e.toString());
            jason.setResponse(null);
        }
        return jason;
    }
}
