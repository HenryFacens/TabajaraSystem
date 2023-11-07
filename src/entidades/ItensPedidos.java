package entidades;

public class ItensPedidos {
    private int qntItens;
    private String nomeProduto;
    private int valorUnitario;
    private double valorTotal;


    public ItensPedidos(int qntItens, String nomeProduto, int valorUnitario, double valorTotal) {
        this.qntItens = qntItens;
        this.nomeProduto = nomeProduto;
        this.valorUnitario = valorUnitario;
        this.valorTotal = valorTotal;
    }

    public int getQntItens() {
        return qntItens;
    }

    public void setQntItens(int qntItens) {
        this.qntItens = qntItens;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(int valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    
}
