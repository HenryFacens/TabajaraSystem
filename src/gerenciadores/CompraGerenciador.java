package gerenciadores;
import entidades.Cliente;
import entidades.Compra;
import entidades.SalvarDados;


public class CompraGerenciador {

    private Compra compra;

    public CompraGerenciador(Cliente cliente, String carrinho) {
        this.compra = new Compra(cliente, carrinho);
        salvarCompra(compra);
    }

    public void salvarCompra(Compra compra) {
        String dadosCompra = compra.paraString();
        System.out.println(dadosCompra);
        SalvarDados.salvar(dadosCompra, "compras");
    }

}
