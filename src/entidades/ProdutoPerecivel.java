package entidades;

import java.util.Date;

public class ProdutoPerecivel extends Produto{

    private Date dataValidade;


    public ProdutoPerecivel(double valorProduto, String nome, int codigoProduto, String descricaoProduto, Date dataValidade) {
        super(valorProduto, nome, codigoProduto, descricaoProduto);
        this.dataValidade = dataValidade;
        Produto.classesInstanciadas.add(this);
    }

    public boolean estaVencido(){
        Date dataAtual = new Date();

        return this.dataValidade.before(dataAtual);
    }
    @Override
    public String paraString(){
        return "Tipo: " + this.getClass() + "\nNome:" + getNome() + "\nCódigo Produto:" + getCodigoProduto() + "\nDescrição:" + getDescricaoProduto() + "\nPerecível:" + dataValidade + "\nend:end" + "\n\n";
    }
}
