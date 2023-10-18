import javax.swing.JOptionPane;

public class MenuSistema {

    public static void main(String[] args) {
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

                    // Lógica para cadastros de clientes
                    break;
                case 2:
                    // Lógica para deletar cliente pelo CPF ou CNPJ
                    break;
                case 3:
                    // Lógica para deletar cliente pelo nome
                    break;
                case 4:
                    // Lógica para cadastro de produtos
                    break;
                case 5:
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
            case "0":
                return; // Retorna para o menu principal
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
            default:
                JOptionPane.showMessageDialog(null, "Opção inválida!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
