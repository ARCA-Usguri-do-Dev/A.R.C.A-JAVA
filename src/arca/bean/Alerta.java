package arca.bean;

public class Alerta {
    //atriibutos
    private String alerta;
    private String descricao;
    private String dtEmissao;
    private String idNivel;
    private String idProblema;

    //construtores

    public Alerta() {
    }

    public Alerta(String alerta, String descricao, String dtEmissao, String idNivel, String idProblema) {
        this.alerta = alerta;
        this.descricao = descricao;
        this.dtEmissao = dtEmissao;
        this.idNivel = idNivel;
        this.idProblema = idProblema;
    }

    //setter/ getter

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDtEmissao() {
        return dtEmissao;
    }

    public void setDtEmissao(String dtEmissao) {
        this.dtEmissao = dtEmissao;
    }

    public String getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(String idNivel) {
        this.idNivel = idNivel;
    }

    public String getIdProblema() {
        return idProblema;
    }

    public void setIdProblema(String idProblema) {
        this.idProblema = idProblema;
    }
}
