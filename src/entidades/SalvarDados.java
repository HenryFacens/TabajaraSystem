package entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SalvarDados<T> {

    private static final String DEFAULT_DIRETORIO = "./baseDados/";

    public void salvar(String dado, String arquivo) {
        String caminhoCompleto = DEFAULT_DIRETORIO + arquivo + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoCompleto, true))) {
            writer.write(dado);
            writer.newLine();
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
}
