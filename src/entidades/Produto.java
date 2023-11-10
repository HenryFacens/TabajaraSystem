package entidades;

import java.util.ArrayList;
import java.util.List;

public class Produto {

    protected static List<Object> classesInstanciadas = new ArrayList<>();
    private double valorProduto;
    private String nome;
    private int codigoProduto;
    private String descricaoProduto;

    public Produto(double valorProduto, String nome, int codigoProduto, String descricaoProduto) {
        this.valorProduto = valorProduto;
        this.nome = nome;
        this.codigoProduto = codigoProduto;
        this.descricaoProduto = descricaoProduto;
        if (!(this instanceof ProdutoPerecivel)){
            Produto.classesInstanciadas.add(this);
        }
    }
    
    public static List<Object> getClassesInstanciadas() {
        return classesInstanciadas;
    }

    public static void setClassesInstanciadas(List<Object> novasClassesInstanciadas){
        classesInstanciadas =  novasClassesInstanciadas;
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

    public float getPreco(){
        return (float) this.valorProduto;
    }

    public String paraString(){
        return "Tipo:" + this.getClass() + "\nNome:" + nome + "\nCódigo Produto:" + codigoProduto + "\nValor do Produto:" + valorProduto  + "\nDescrição:" + descricaoProduto + "\nend:end" + "\n\n";
    }
    public String paraStringCompra(){
        return "Nome:" + nome + "\nValor do Produto:" + valorProduto  + "\nDescrição:" + descricaoProduto;
    }
}
