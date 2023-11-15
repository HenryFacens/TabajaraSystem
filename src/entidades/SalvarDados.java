package entidades;

import java.io.BufferedWriter;

import java.io.FileWriter;
import java.io.IOException;

import java.util.List;


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
    public static void limparArquivo(String tipo) {
        try {
            String nomeArquivo = DEFAULT_DIRETORIO + tipo + ".txt";
            FileWriter fw = new FileWriter(nomeArquivo, false);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}