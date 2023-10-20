package entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SalvarDados<T> {

    private static final String DEFAULT_DIRETORIO = "./baseDados/";

    public void salvar(String dado, String arquivo){
        String caminhoCompleto = DEFAULT_DIRETORIO + arquivo + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoCompleto))) {
            writer.write(dado.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String ler(String nomeArquivo) {
        String caminhoCompleto = DEFAULT_DIRETORIO + nomeArquivo + ".txt";
        StringBuilder conteudo = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoCompleto))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return conteudo.toString();
    }

//    public class Repository<T> {
//        private String arquivoTexto = "src/baseDados/"; // Nome do arquivo para persistência de dados
//
//        public Repository(String way) {
//            arquivoTexto += way;
//        }
//
//        // Outros métodos relacionados a clientes, como buscar por CPF, CNPJ, etc.
//
//        public List<T> carregarDados() {
//            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(arquivoTexto))) {
//                return (List<T>) input.readObject();
//            } catch (IOException | ClassNotFoundException e) {
//                // Tratar exceções de leitura ou de classe não encontrada
//                return null;
//            }
//        }
//
//        public void salvarDados(List<T> clientes) {
//            try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(arquivoTexto))) {
//                output.writeObject(clientes);
//            } catch (IOException e) {
//                // Tratar exceções de escrita
//            }
//        }
//    }

}
