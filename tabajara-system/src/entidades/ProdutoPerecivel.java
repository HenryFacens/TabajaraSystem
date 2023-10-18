package entidades;

import java.util.Date;

public class ProdutoPerecivel extends Produto{

    private Date dataValidade;


    public ProdutoPerecivel(double valorProduto, String nome, int codigoProduto, String descricaoProduto, Date dataValidade) {
        super(valorProduto, nome, codigoProduto, descricaoProduto);
        this.dataValidade = dataValidade;
    }

    public boolean estaVencido(){
        Date dataAtual = new Date();

        return this.dataValidade.before(dataAtual);
    }
    @Override
    public String paraString(){
        return "Produto Perecivel: " + dataValidade;
    }
}
