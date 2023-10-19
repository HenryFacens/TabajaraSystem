package gerenciadores;
import entidades.Produto;
import java.util.ArrayList;
import java.util.List;

/* 
 Produto{
    valor = double
    nome = String
    codigo = int
    descricao = String
 }
 */
public class ProdutoGerenciador {

    private Produto produto;
    private List<Produto> lista = new ArrayList<>();

    public void adicionarProduto(Produto produto){
        lista.add(produto);
    }
}
