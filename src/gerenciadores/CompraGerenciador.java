package gerenciadores;
import java.util.List;

import entidades.Cliente;
import entidades.Compra;
import entidades.SalvarDados;


public class CompraGerenciador {

    private Compra compra;
    
    public CompraGerenciador(Cliente cliente, String carrinho) {
        Integer indentificador = gerarId();
        this.compra = new Compra(cliente, carrinho, indentificador);
        
        salvarCompra(compra);
    }

    public void salvarCompra(Compra compra) {
        String dadosCompra = compra.paraString();
        System.out.println(dadosCompra);
        SalvarDados.salvar(dadosCompra, "compras");
    }

    public static Integer gerarId() {
        Integer id = 0;
        List<Object> compras = Compra.getClassesInstanciadas();
        if (compras.size() > 0){
            Compra ultimaCompra =  (Compra) compras.getLast();
            id = ultimaCompra.getId();
            id++;
        } 
        return id;
    }

}
