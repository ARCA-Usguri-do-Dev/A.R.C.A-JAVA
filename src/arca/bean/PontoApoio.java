package arca.bean;

public class PontoApoio {
    //atributos
    private String pontoNome;
    private String descricaoPonto;
    private String telefonePonto;
    private String capacidade;

    //contrutores
    public PontoApoio(){}

    //setter/getter

    public String getTelefonePonto() {
        return telefonePonto;
    }

    public void setTelefonePonto(String telefonePont) {
        this.telefonePonto = telefonePont;
    }

    public String getDescricaoPonto() {
        return descricaoPonto;
    }

    public void setDescricaoPonto(String descricaoPonto) {
        this.descricaoPonto = descricaoPonto;
    }

    public String getPontoNome() {
        return pontoNome;
    }

    public void setPontoNome(String pontoNome) {
        this.pontoNome = pontoNome;
    }

    public String getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(String capacidade) {
        this.capacidade = capacidade;
    }


    public String status(){
        return "Pendente";
    }

}
