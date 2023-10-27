package entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import entidades.Endereco;
import entidades.PessoaFisica;
import entidades.PessoaJuridica;

public class SalvarDados {

    private static final String DEFAULT_DIRETORIO = "./baseDados/";

    public static void salvar(String dado, String arquivo) {
        String caminhoCompleto = DEFAULT_DIRETORIO + arquivo + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoCompleto, true))) {
            writer.write(dado);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void apagarDados(String arquivo) {
        String caminhoCompleto = DEFAULT_DIRETORIO + arquivo + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoCompleto))) {
            writer.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void reescreverLista(List<Object> objetos, String arquivo){
        apagarDados(arquivo);

        for (Object obj : objetos) {
            if (obj instanceof PessoaFisica) {
                PessoaFisica pessoaFisica = (PessoaFisica) obj;
                SalvarDados.salvar(pessoaFisica.paraString(), arquivo);
            } else if (obj instanceof PessoaJuridica) {
                PessoaJuridica pessoaJuridica = (PessoaJuridica) obj;
                SalvarDados.salvar(pessoaJuridica.paraString(), arquivo);
            } else if (obj instanceof Produto && !(obj instanceof ProdutoPerecivel)){
                Produto produto = (Produto) obj;
                SalvarDados.salvar(produto.paraString(), arquivo);
            } else if (obj instanceof ProdutoPerecivel){
                ProdutoPerecivel produtoPerecivel = (ProdutoPerecivel) obj;
                SalvarDados.salvar(produtoPerecivel.paraString(), arquivo);
            }
        }
    }
}