package entidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import entidades.ResultadoChaveValor;



public class ControladorDados {
    private static final String DIRETORIO_DEFAULT = "./baseDados/";

    public static String ler(String nomeArquivo) {
        String caminhoCompleto = DIRETORIO_DEFAULT + nomeArquivo + ".txt";
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

    public static ResultadoChaveValor separarChaveValor(String input){
        Pattern pattern = Pattern.compile("([^:]+):\\s*(.*)");
        Matcher matcher = pattern.matcher(input);
        ArrayList<String> chaves = new ArrayList<String>();
        ArrayList<String> valores = new ArrayList<String>();
        while (matcher.find()) {
            chaves.add(matcher.group(1).trim());
            valores.add(matcher.group(2).trim());
        }

        return new ResultadoChaveValor(chaves, valores);
    }
}
