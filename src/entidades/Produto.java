package entidades;

public class Produto {

    private double valorProduto;
    private String nome;
    private int codigoProduto;
    private String descricaoProduto;

    public Produto(double valorProduto, String nome, int codigoProduto, String descricaoProduto) {
        this.valorProduto = valorProduto;
        this.nome = nome;
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public String paraString(){
        return "Tipo de Produto:" + this.getClass() + "\nValor do Produto:" + valorProduto + "\nNome:" + nome + "\nCódigo Produto:" + codigoProduto + "\nDescrição:" + descricaoProduto + "\nend:end" + "\n\n";
    }
}
