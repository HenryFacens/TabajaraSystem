package gerenciadores;
import entidades.Cliente;
import java.util.ArrayList;
import java.util.List;
public class ClienteGerenciador {

    private Cliente cliente;
    private List<Cliente> lista = new ArrayList<>();


    public void adicionarCliente(Cliente cliente){
        lista.add(cliente);

    }
    public void removerCliente(String documento){
        lista.removeIf(cliente -> cliente.getDocumento().equals(documento));
    }
    public void alterarCliente(){

    }
    public Cliente buscarClienteNome(String nome){
        return lista.stream().filter(cliente -> cliente.getNome()
                .equals(nome))
                .findFirst()
                .orElse(null);
    }
}
