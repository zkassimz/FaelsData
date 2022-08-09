package Utils;

public class Usuario extends Pessoa {

    private String Nivel_Acesso;
    private String Usuario;
    private String Senha;
    private String Tipo_Usuario;

    public String getTipo_Usuario() {
        return Tipo_Usuario;
    }

    public void setTipo_Usuario(String Tipo_Usuario) {
        this.Tipo_Usuario = Tipo_Usuario;
    }

    public String getNivel_Acesso() {
        return Nivel_Acesso;
    }

    public void setNivel_Acesso(String Nivel_Acesso) {
        this.Nivel_Acesso = Nivel_Acesso;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }
}
