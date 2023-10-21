package entidades;

public class Endereco {
    private String rua;
    private String numero;
    private String cep;
    private String cidade;
    private String pais;
    private String bairro;

    public Endereco(String rua, String numero, String cep, String cidade, String pais, String bairro) {
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.cidade = cidade;
        this.pais = pais;
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    
    public String paraString() {
        return  "\nRua:" + rua +
                "\nNumero:" + numero +
                "\nCEP:" + cep +
                "\nCidade:" + cidade +
                "\nPais:" + pais +
                "\nBairro:" + bairro;
    }
}
