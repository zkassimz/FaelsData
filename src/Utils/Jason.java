package Utils;

import java.util.ArrayList;

public class Jason {

    private String messagem;
    private String cod;
    private ArrayList<Contribuinte> response;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    private Usuario usuario;

    public Jason() {
        this.response = new ArrayList();
    }
    
    public String getMessagem() {
        return messagem;
    }

    public void setMessagem(String messagem) {
        this.messagem = messagem;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public ArrayList<Contribuinte> getResponse() {
        return response;
    }

    public void setResponse(ArrayList<Contribuinte> response) {
        this.response = response;
    }

}
