package Utils;

public class Env {

    private String usuario;
    private String acesso;
    private String UsuarioSQl;
    private String SenhaSQL;
    private String DriverSQL;
    private String UrlSQL;
    private String tipo_acesso;

    public Env() {
        initMySQL();
    }

    public Env(Usuario user) {
        initMySQL();
        initUser(user);
        System.out.println("THIS.USUARIO: " + this.usuario);
    }

    private void initMySQL() {
        UsuarioSQl = "root";
        this.SenhaSQL = "megagengar";
        this.DriverSQL = "com.mysql.jdbc.Driver";
        this.UrlSQL = "jdbc:mysql://localhost:3306/sindicato";
    }

    private void initUser(Usuario usuario) {
        this.usuario = usuario.getUsuario();
        this.acesso = usuario.getNivel_Acesso();
        this.tipo_acesso = usuario.getTipo_Usuario();
    }

    public String getUsuario() {
        return usuario;
    }

    public String getAcesso() {
        return acesso;
    }

    public String getUsuarioSQl() {
        return UsuarioSQl;
    }

    public String getSenhaSQL() {
        return SenhaSQL;
    }

    public String getDriverSQL() {
        return DriverSQL;
    }

    public String getUrlSQL() {
        return UrlSQL;
    }

    public String getTipo_acesso() {
        return tipo_acesso;
    }

}
