import entidades.Cliente;
import entidades.Endereco;

import entidades.PessoaFisica;
import entidades.PessoaJuridica;
import entidades.Produto;
import entidades.ProdutoPerecivel;
import entidades.SalvarDados;
import gerenciadores.ClienteGerenciador;
import gerenciadores.ProdutoGerenciador;

import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import entidades.ControladorDados;
import entidades.ResultadoChaveValor;
import entidades.DataLoader;
public class MenuSistema {
    public static void main(String[] args) {
        DataLoader.carregarClientes();
        DataLoader.carregarProdutos();

        // for (Object obj : Cliente.getClassesInstanciadas()) {
        //     if (obj instanceof PessoaFisica) {
        //         PessoaFisica pessoaFisica = (PessoaFisica) obj;
        //         System.out.println(pessoaFisica.paraString());
        //     } else if (obj instanceof PessoaJuridica) {
        //         PessoaJuridica pessoaJuridica = (PessoaJuridica) obj;
        //         System.out.println(pessoaJuridica.paraString());
        //     }
        // }
        while (true) {
            System.out.println(Cliente.getClassesInstanciadas());
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

                            SalvarDados<PessoaFisica> salvar_cliente_fisico = new SalvarDados<>();

                            salvar_cliente_fisico.salvar(pessoaFisica.paraString(), "cliente");

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
                            SalvarDados<PessoaJuridica> salvar_cliente_juridico = new SalvarDados<>();

                            salvar_cliente_juridico.salvar(pessoaJuridica.paraString(), "cliente");

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
                    // Lógica para deletar cliente pelo CPF ou CNPJ
                    break;
                case 3:
                    // Lógica para deletar cliente pelo nome
                    break;
                case 4:
                    ProdutoGerenciador produto_gerenciador = new ProdutoGerenciador();
                    String nome_produto = JOptionPane.showInputDialog("Digite o nome do produto:");
                    Integer codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do produto:"));
                    Double valor = Double.parseDouble(JOptionPane.showInputDialog("Digite o valor do produto:"));
                    String descricao = JOptionPane.showInputDialog("Digite uma breve descricao do produto:");

                    String[] options_produto = {"Produto não Perecível", "Produto Perecível"};
                    int tipoProduto = JOptionPane.showOptionDialog(null, "Escolha o tipo de produto:",
                        "Tipo de produto", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, options_produto, options_produto[0]
                    );
                    
                    switch (tipoProduto){
                        case 0:
                            Produto produto = new Produto(valor, nome_produto, codigo, descricao);
                            SalvarDados<Produto> salvar_produto = new SalvarDados<>();
                            salvar_produto.salvar(produto.paraString(), "produto");
                            produto_gerenciador.adicionarProduto(produto);
                            JOptionPane.showMessageDialog(null, "Produto não Perecível adicionado com sucesso!");
                            break;

                        case 1:
                            String dataValidadeStr = JOptionPane.showInputDialog("Digite a data de validade do produto (no formato dd/MM/yyyy)");
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            try {
                                Date data_validade = dateFormat.parse(dataValidadeStr);
                                ProdutoPerecivel produto_perecivel = new ProdutoPerecivel(valor, nome_produto, codigo, descricao, data_validade);
                                SalvarDados<ProdutoPerecivel> salvar_produto_perecivel = new SalvarDados<>();
                                salvar_produto_perecivel.salvar(produto_perecivel.paraString(), "produto");
                                produto_gerenciador.adicionarProduto(produto_perecivel);
                                JOptionPane.showMessageDialog(null, "Produto Perecível adicionado com sucesso!");
                            } catch (ParseException e) {
                                JOptionPane.showMessageDialog(null, "A data inserida não está no formato correto (dd/MM/yyyy)");
                            }
                            break;

                        default:
                            JOptionPane.showMessageDialog(null, "Opção inválida!");
                            break;
                    }

                    // lógica para cadastro de produtos
                    break;
                case 5:
                    //String nomeProduto = JOptionPane.showInputDialog("Digite o nome do produto:");
                    System.out.println(Produto.getClassesInstanciadas());
                    // Lógica para efetuação de uma compra
                    break;
                case 6:
                    // Lógica para atualização da situação de pagamento de uma compra
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

        switch (input) {
            case "a":
                // Lógica para relação de todos os clientes com nome iniciado por uma determinada sequência de caracteres
                break;
            case "b":
                // Lógica para relação de todos os produtos
                break;
            case "c":
                // Lógica para busca de um produto pelo nome
                break;
            case "d":
                // Lógica para relação de produtos perecíveis com data de validade vencida
                break;
            case "e":
                // Lógica para relação de todas as compras
                break;
            case "f":
                // Lógica para busca de uma compra pelo número
                break;
            case "g":
                // Lógica para relação de todas as compras não pagas
                break;
            case "h":
                // Lógica para relação das 10 últimas compras pagas
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
