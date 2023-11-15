import entidades.Cliente;
import entidades.Compra;
import entidades.ControladorDados;
import entidades.Endereco;

import entidades.PessoaFisica;
import entidades.PessoaJuridica;
import entidades.Produto;
import entidades.ProdutoPerecivel;
import entidades.ResultadoChaveValor;
import entidades.SalvarDados;
import gerenciadores.ClienteGerenciador;
import gerenciadores.CompraGerenciador;
import gerenciadores.ProdutoGerenciador;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import entidades.DataLoader;
public class MenuSistema {
    public static void main(String[] args) {
        DataLoader.carregarClientes();
        DataLoader.carregarProdutos();
        DataLoader.carregarCompras();

        while (true) {
            String input = JOptionPane.showInputDialog(null,
                    "Escolha uma opção:\n"
                            + "1. Cadastros de Clientes\n"
                            + "2. Deletar cliente pelo CPF ou CNPJ\n"
                            + "3. Deletar cliente pelo nome\n"
                            + "4. Cadastro de Produtos\n"
                            + "5. Efetuação de uma compra\n"
                            + "6. Atualização da situação de pagamento de uma compra\n"
                            + "7. Relatórios\n"
                            + "8. Sair",
                    "Menu",
                    JOptionPane.PLAIN_MESSAGE);

            if (input == null || input.isEmpty()) {
                continue;
            }

            int escolha = Integer.parseInt(input);

            switch (escolha) {
                case 1:
                    ClienteGerenciador gerenciador  = new ClienteGerenciador();
                    String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
                    String bairro = JOptionPane.showInputDialog("Digite o Bairro do Cliente:");
                    String rua = JOptionPane.showInputDialog("Digite o nome da rua do cliente:");
                    String numero = JOptionPane.showInputDialog("Digite o número da residência do cliente:");
                    String cep = JOptionPane.showInputDialog("Digite o CEP do Cliente:");
                    String cidade = JOptionPane.showInputDialog("Digite a cidade do Cliente:");
                    String pais = JOptionPane.showInputDialog("Digite o Pais do Cliente:");
                    Endereco endereco = new Endereco(rua,numero,cep,cidade,pais,bairro);

                    String[] options = {"Pessoa Fisíca","Pessoa Juridica"};
                    int tipoCliente = JOptionPane.showOptionDialog(null, "Escolha o tipo de cliente:",
                            "Tipo de Cliente", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

                    switch (tipoCliente) {
                        case 0:
                            Date dataCadastro = new Date();
                            String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");
                            int qntMaxParcelas = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade máxima de parcelas:"));

                            PessoaFisica pessoaFisica = new PessoaFisica(nome, endereco, dataCadastro, qntMaxParcelas, cpf);

                            SalvarDados.salvar(pessoaFisica.paraString(), "cliente");

                            gerenciador.adicionarCliente(pessoaFisica);

                            JOptionPane.showMessageDialog(null, "Cliente Pessoa Física adicionado com sucesso!");

                            break;
                        case 1:
                            String cnpj = JOptionPane.showInputDialog("Digite o CNPJ da empresa:");

                            String razaoSocial = JOptionPane.showInputDialog("Digite a razão social da empresa:");

                            int prazoMaximo = Integer.parseInt(JOptionPane.showInputDialog("Digite o prazo máximo para a empresa:"));

                            Date dataCadastroPJ = new Date(); // Supondo que a data de cadastro é a data atual
                            // if (new PessoaJuridica("", null, null, cnpj, "", 0).validarCPNJ(cnpj)) { // Validando o CNPJ
                            PessoaJuridica pessoaJuridica = new PessoaJuridica(nome, endereco, dataCadastroPJ, cnpj, razaoSocial, prazoMaximo);

                            SalvarDados.salvar(pessoaJuridica.paraString(), "cliente");

                            gerenciador.adicionarCliente(pessoaJuridica);

                            JOptionPane.showMessageDialog(null, "Cliente Pessoa Jurídica adicionado com sucesso!");
                            // } else {
                            //     JOptionPane.showMessageDialog(null, "CNPJ inválido!");
                            // }
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida!");
                            break;
                    }

                    // Lógica para cadastros de clientes
                    break;

                case 2:
                    String cpfOuCnpj = JOptionPane.showInputDialog("Digite o cpf ou cnpj:");
                    List<Object> objs = Cliente.getClassesInstanciadas();
                    for (Object obj : objs){
                        if (obj instanceof PessoaFisica) {
                            PessoaFisica pessoa = (PessoaFisica) obj;
                                if (pessoa.getCpf().equals(cpfOuCnpj)){
                                    objs.remove(obj);
                                    break;
                                }
                        } else{
                            PessoaJuridica pessoa = (PessoaJuridica) obj;
                            if (pessoa.getCnpj().equals(cpfOuCnpj)){
                                    objs.remove(obj);
                                    break;
                            }
                        }
                    }

                    Cliente.setClassesInstanciadas(objs);
                    SalvarDados.reescreverLista(Cliente.getClassesInstanciadas(), "cliente");
                    break;

                case 3:
                    // Lógica para deletar cliente pelo nome
                    String nomeCliente = JOptionPane.showInputDialog("Digite o nome do cliente:");
                    List<Object> clienteObjs = Cliente.getClassesInstanciadas();
                    for (Object obj : clienteObjs){
                        if (obj instanceof PessoaFisica) {
                            PessoaFisica pessoa = (PessoaFisica) obj;
                                if (pessoa.getNome().equals(nomeCliente)){
                                    clienteObjs.remove(obj);
                                    break;
                                }
                        } else{
                            PessoaJuridica pessoa = (PessoaJuridica) obj;
                            if (pessoa.getNome().equals(nomeCliente)){
                                    clienteObjs.remove(obj);
                                    break;
                            }
                        }
                    }

                    Cliente.setClassesInstanciadas(clienteObjs);
                    SalvarDados.reescreverLista(Cliente.getClassesInstanciadas(), "cliente");
                    break;

                case 4:
                    ProdutoGerenciador produto_gerenciador = new ProdutoGerenciador();
                    String nome_produto = JOptionPane.showInputDialog("Digite o nome do produto:");
                    Integer codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto:"));
                    Double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do produto:"));
                    String descricao = JOptionPane.showInputDialog("Digite uma breve descricao do produto:");
                    Integer quantidade = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade do produto:"));

                    String[] options_produto = {"Produto não Perecível", "Produto Perecível"};
                    int tipoProduto = JOptionPane.showOptionDialog(null, "Escolha o tipo de produto:",
                        "Tipo de produto", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options_produto, options_produto[0]
                    );

                    switch (tipoProduto){
                        case 0:
                            Produto produto = new Produto(valor, nome_produto, codigo, descricao, quantidade);
                            SalvarDados.salvar(produto.paraString(), "produto");
                            produto_gerenciador.adicionarProduto(produto);
                            break;

                        case 1:
                            String dataValidadeStr = JOptionPane.showInputDialog("Digite a data de validade do produto (no formato dd/MM/yyyy)");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date data_validade = dateFormat.parse(dataValidadeStr);
                                ProdutoPerecivel produto_perecivel = new ProdutoPerecivel(valor, nome_produto, codigo, descricao, data_validade, quantidade);
                                SalvarDados.salvar(produto_perecivel.paraString(), "produto");
                                produto_gerenciador.adicionarProduto(produto_perecivel);
                            } catch (ParseException e) {
                                JOptionPane.showMessageDialog(null, "A data inserida não está no formato correto (dd/MM/yyyy)");
                            }
                            break;

                            default:
                            JOptionPane.showMessageDialog(null, "Opção inválida!");
                            break;
                        }
                        JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
                        // lógica para cadastro de produtos
                        break;
                        
                    case 5:
                        List<Object> listaProduto = Produto.getClassesInstanciadas();
                        List<Object> clientes = Cliente.getClassesInstanciadas();
                    
                    boolean cliente_encontrado = false;

                    String[] options_compra = {"Pessoa Fisíca", "Pessoa Juridica"};

                    int tipoCliente_compra = JOptionPane.showOptionDialog(null, "Escolha o tipo de cliente:",
                            "Tipo de Cliente", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, options_compra, options_compra[0]
                    );

                    Cliente cliente_compra = null;

                    if (tipoCliente_compra == 0) {
                        String cpf = JOptionPane.showInputDialog("Digite o CPF do cliente:");
                        for (Object obj : clientes) {
                            if (obj instanceof PessoaFisica) {
                                PessoaFisica cliente = (PessoaFisica) obj;
                                if (cliente.getDocumento().equals(cpf)) {
                                    cliente_encontrado = true;
                                    cliente_compra = cliente;
                                    break;
                                }
                            }
                        }

                    } else if (tipoCliente_compra == 1) {
                        String cnpj = JOptionPane.showInputDialog("Digite o CNPJ do cliente:");
                        for (Object obj : clientes) {
                            if (obj instanceof PessoaJuridica) {
                                PessoaJuridica cliente = (PessoaJuridica) obj;
                                if (cliente.getDocumento().equals(cnpj)) {
                                    cliente_encontrado = true;
                                    cliente_compra = cliente;
                                    break;
                                }
                            }
                        }
                    }

                    if (!cliente_encontrado) {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                    } else {

                        List<Object> carrinho = new ArrayList<>();
                        String carrinhoCompras = "";
                        StringBuilder resumoCompra = new StringBuilder();

                        boolean continuarComprando = true;
                        double total = 0;
                        while (continuarComprando && !listaProduto.isEmpty()) { 
                            int i = 0;
                            String[] produtosArray = new String[listaProduto.size()];
                            for (Object obj: listaProduto){
                                if (obj instanceof ProdutoPerecivel){
                                    ProdutoPerecivel objProd  = (ProdutoPerecivel) obj; 
                                    produtosArray[i] = objProd.paraString();
                                }
                                else{
                                    Produto objProd  = (Produto) obj; 
                                    produtosArray[i] = objProd.paraString();
                                }
                                i++;
                            }

                            JComboBox<String> comboBox = new JComboBox<>(produtosArray);

                            int response = JOptionPane.showConfirmDialog(null, comboBox, "Selecione um produto",
                                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                            int qtd = 0;
                            if (response == JOptionPane.OK_OPTION) {
                                int indexSelecionado = comboBox.getSelectedIndex();
                                Object produtoSelecionado = listaProduto.get(indexSelecionado);
                                carrinho.add(produtoSelecionado);
                                if (produtoSelecionado instanceof ProdutoPerecivel){
                                    ProdutoPerecivel prod = (ProdutoPerecivel) produtoSelecionado;
                                    qtd = prod.getQuantidade();
                                }else{
                                    Produto prod = (Produto) produtoSelecionado;
                                    qtd = prod.getQuantidade();
                                }
                                Integer quantidadePedida = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade do produto (disponível: " + qtd + ")"));
                                if (quantidadePedida > qtd){
                                    JOptionPane.showMessageDialog(null, "Opção inválida!");
                                    break;
                                }
                                listaProduto.remove(indexSelecionado);

                                if (produtoSelecionado instanceof ProdutoPerecivel){
                                    ProdutoPerecivel prod = (ProdutoPerecivel) produtoSelecionado;
                                    prod.setQuantidade(qtd-quantidadePedida);
                                    listaProduto.add(prod);
                                    resumoCompra.append(prod.paraStringCompra()).append("\n");
                                    total += prod.getPreco() * quantidadePedida;

                                }else{
                                    Produto prod = (Produto) produtoSelecionado;
                                    prod.setQuantidade(qtd-quantidadePedida);
                                    listaProduto.add(prod);
                                    resumoCompra.append(prod.paraStringCompra()).append("\n");
                                    total += prod.getPreco() * quantidadePedida;
                                }
                                resumoCompra.append("Quantidade: ").append(quantidadePedida).append("\n");
                            }
                            SalvarDados.reescreverLista(listaProduto, "produto");
                            
                            int continuar = JOptionPane.showConfirmDialog(null, "Deseja adicionar mais produtos ao carrinho?",
                            "Continuar comprando", JOptionPane.YES_NO_OPTION);
                            
                            continuarComprando = (continuar == JOptionPane.YES_OPTION);
                        }

                        resumoCompra.append("Total a pagar: ").append(total);
                        
                        JOptionPane.showMessageDialog(null, resumoCompra.toString(), "Finalizar Compra", JOptionPane.INFORMATION_MESSAGE);
                        
                        carrinhoCompras = resumoCompra.toString();
                        System.out.println(carrinhoCompras);
                        CompraGerenciador compra = new CompraGerenciador(cliente_compra, carrinhoCompras);

                        SalvarDados.limparArquivo("produto");
                        
                        for (Object produto : listaProduto) {
                            if (produto instanceof ProdutoPerecivel) {
                                ProdutoPerecivel prodPer = (ProdutoPerecivel) produto;
                                SalvarDados.salvar(prodPer.paraString(), "produto");
                            } else {
                                Produto prodPer = (Produto) produto;
                                SalvarDados.salvar(prodPer.paraString(), "produto");
                            }
                        }
                    }


                    // Lógica para efetuação de uma compra
                    Produto.setClassesInstanciadas(listaProduto);
                    break;
                case 6:
                Integer identificacao = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo:"));
                List<Object> compras = Compra.getClassesInstanciadas();
                boolean encontrou = false;
                
                for (Object obj : compras) {
                    if (obj instanceof Compra) {
                        Compra compra = (Compra) obj;
                        if (compra.getId().equals(identificacao)) {
                            compra.setPago(true);
                            encontrou = true;
                            break;
                        }
                    }
                }
                
                if (encontrou) {
                    JOptionPane.showMessageDialog(null, "Compra com ID " + identificacao + " marcada como paga.");
                
                    StringBuilder dadosParaSalvar = new StringBuilder();
                    for (Object obj : compras) {
                        if (obj instanceof Compra) {
                            dadosParaSalvar.append(((Compra) obj).paraString()).append("\n");
                        }
                    }
                    SalvarDados.limparArquivo("compras");
                    SalvarDados.salvar(dadosParaSalvar.toString(), "compras");
                } else {
                    JOptionPane.showMessageDialog(null, "Compra com ID " + identificacao + " não encontrada.");
                }
                break;
                case 7:
                    relatorios();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void relatorios() {
        String input = JOptionPane.showInputDialog(null,
                "Escolha uma opção de relatório:\n"
                        + "0. Voltar ao menu inicial\n"
                        + "(a) Relação de todos os Clientes que possuem o nome iniciado por uma determinada sequência de caracteres\n"
                        + "(b) Relação de todos os Produtos\n"
                        + "(c) Busca de um produto pelo nome\n"
                        + "(d) Relação de produtos que são perecíveis e que estão com a data de validade vencida\n"
                        + "(e) Relação de todas as compras\n"
                        + "(f) Busca de uma compra pelo número\n"
                        + "(g) Relação de todas as compras que não foram pagas ainda\n"
                        + "(h) Relação das 10 últimas compras pagas\n"
                        + "(i) Apresentação das informações da compra mais cara\n"
                        + "(j) Apresentação das informações da compra mais barata\n"
                        + "(k) Relação do valor total de compras feitas em cada mês nos últimos 12 meses.",
                "Relatórios",
                JOptionPane.PLAIN_MESSAGE);

        if (input == null || input.isEmpty()) {
            return;
        }

        boolean produtoEncontrado;
        switch (input) {
            case "a":
                String client_input = JOptionPane.showInputDialog("Digite a inicial do cliente a ser buscado: ");
                List<Object> clieteObjs = Cliente.getClassesInstanciadas();
                for(Object obj: clieteObjs){
                    if(obj instanceof PessoaFisica){
                        PessoaFisica cliete = (PessoaFisica) obj;
                        if(cliete.getNome().contains(client_input)){
                            JOptionPane.showMessageDialog(null, cliete.getEndereco().paraString());
                            break;
                        } else {
                            JOptionPane.showMessageDialog(null, "Nenhum cliente encontrado!");
                            break;
                        }
                    }
                }

                break;
                case "b":
                List<Object> produtosb = Produto.getClassesInstanciadas();
                StringBuilder listaProdutos = new StringBuilder("Lista de Produtos:\n");
            
                for (Object obj : produtosb) {
                    if (obj instanceof Produto) {
                        Produto produto = (Produto) obj;
                        listaProdutos.append(produto.paraString()).append("\n");
                    }
                }
            
                JOptionPane.showMessageDialog(null, listaProdutos.toString());
                break;
            
            case "c":
                    String nomeBuscado = JOptionPane.showInputDialog("Digite o nome do produto:");
                    List<Object> produtos = Produto.getClassesInstanciadas();
                    boolean produtoEncontrados = false;
                    
                    for (Object obj : produtos) {
                        if (obj instanceof Produto) {
                            Produto produto = (Produto) obj;
                            if (produto.getNome().equalsIgnoreCase(nomeBuscado)) {
                                JOptionPane.showMessageDialog(null, "Produto encontrado: " + produto.paraString());
                                produtoEncontrados = true;
                                break;
                            }
                        }
                    }
                    
                    if (!produtoEncontrados) {
                        JOptionPane.showMessageDialog(null, "Produto não encontrado.");
                    }

                // Lógica para busca de um produto pelo nome
                break;
            case "d":
                // Lógica para relação de produtos perecíveis com data de validade vencida
                break;
            case "e":
                // Lógica para relação de todas as compras
                break;
            case "f":
                String compra_identificador = JOptionPane.showInputDialog("Digite o numero da compra a ser buscada:");
                String leitura6 = ControladorDados.ler("compra");
                ResultadoChaveValor resultado6 = ControladorDados.separarChaveValor(leitura6);
                if(resultado6.getValores().contains(compra_identificador)){
                    JOptionPane.showMessageDialog(null, resultado6.getValores());
                }
                else {
                    JOptionPane.showMessageDialog(null, "Compra não encontrada");
                }
                break;
            case "g":
                List<Object> comprasg = Compra.getClassesInstanciadas();
                StringBuilder comprasNaoPagas = new StringBuilder("Compras Não Pagas:\n");

                for (Object obj : comprasg) {
                    if (obj instanceof Compra) {
                        Compra compra = (Compra) obj;
                        if (!compra.getPago()) {
                            comprasNaoPagas.append(compra.paraString()).append("\n");
                        }
                    }
                }

                if (comprasNaoPagas.length() > "Compras Não Pagas:\n".length()) {
                    JOptionPane.showMessageDialog(null, comprasNaoPagas.toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Todas as compras estão pagas.");
                }
                break;
            case "h":
                List<Compra> compras = Compra.getClassesInstanciadas()
                                            .stream()
                                            .filter(obj -> obj instanceof Compra)
                                            .map(obj -> (Compra) obj)
                                            .filter(Compra::getPago)
                                            .sorted(Comparator.comparing(Compra::getData).reversed())
                                            .limit(10) 
                                            .collect(Collectors.toList());

                StringBuilder ultimasCompras = new StringBuilder("Últimas 10 Compras Pagas:\n");

                for (Compra compra : compras) {
                    ultimasCompras.append(compra.paraString()).append("\n");
                }

                JOptionPane.showMessageDialog(null, ultimasCompras.toString());
                break;
            case "i":
                // Lógica para apresentação da compra mais cara
                break;
            case "j":
                // Lógica para apresentação da compra mais barata
                break;
            case "k":
                // Lógica para relação do valor total de compras feitas nos últimos 12 meses
                break;
            case "0":
                return; // Retorna para o menu principal
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
