package entidades;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import entidades.ControladorDados;
import entidades.ResultadoChaveValor;

import entidades.PessoaFisica;
import entidades.PessoaJuridica;

import entidades.Endereco;

public class DataLoader {

    private static List<Produto> produtos = new ArrayList<>();


    public static List<List<Integer>> contarRepetido(String valor, ArrayList<String> lista) {
        List<List<Integer>> allLists = new ArrayList<>();
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            newList.add(i);
            if (lista.get(i).equals(valor)) {
                allLists.add(newList);
                newList = new ArrayList<>();
            }
        }
        return allLists;
    }

    public static void carregarClientes(){
        String input = ControladorDados.ler("cliente");
        ResultadoChaveValor resultado = ControladorDados.separarChaveValor(input);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        ArrayList<String> valores = resultado.getValores();
        
        for (List<Integer> idxs : contarRepetido("end", valores)){
            Date cadastro = null;
            String nome = valores.get(idxs.get(1));
            String rua = valores.get(idxs.get(2)); 
            String numero = valores.get(idxs.get(3));
            String cep = valores.get(idxs.get(4)); 
            String cidade = valores.get(idxs.get(5)); 
            String pais = valores.get(idxs.get(6)); 
            String bairro = valores.get(idxs.get(7));
            Endereco endereco = new Endereco(rua,numero,cep,cidade,pais,bairro);
            try {
                // Parse the date string into a Date object
                cadastro = sdf.parse(valores.get(idxs.get(8)));
            } catch (ParseException e) {
                e.printStackTrace();
            } //

            if (valores.get(idxs.get(0)).equals("class entidades.PessoaJuridica")){
                String cnpj = valores.get(idxs.get(9));
                String razaoSocial = valores.get(idxs.get(10));
                int prazoMaximo =  Integer.parseInt(valores.get(idxs.get(11)));
                PessoaJuridica pessoaJuridica = new PessoaJuridica(nome, endereco, cadastro, cnpj, razaoSocial, prazoMaximo);
            }else{
                String cpf = valores.get(idxs.get(9));
                int qntMaxParcelas = Integer.parseInt(valores.get(idxs.get(10)));       
                PessoaFisica pessoaFisica = new PessoaFisica(nome, endereco, cadastro, qntMaxParcelas, cpf);
            }
        }
    }

    public static void carregarProdutos(){
        produtos.clear(); // Limpe a lista antes de carregar novos produtos
    
        String input = ControladorDados.ler("produto");
        ResultadoChaveValor resultado = ControladorDados.separarChaveValor(input);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        ArrayList<String> valores = resultado.getValores();
    
        for (List<Integer> idxs : contarRepetido("end", valores)){
            String nome = valores.get(idxs.get(1));
            int codigoProduto = Integer.parseInt(valores.get(idxs.get(2)));
            double valorProduto = Double.parseDouble(valores.get(idxs.get(3)));
            String descricaoProduto = valores.get(idxs.get(4));
    
            if (valores.get(idxs.get(0)).equals("class entidades.Produto")){
                Produto produto = new Produto(valorProduto, nome, codigoProduto, descricaoProduto);
                produtos.add(produto); // Adicione o produto à lista
            }else{
                try {
                    Date dataValidade = sdf.parse(valores.get(idxs.get(5)));
                    ProdutoPerecivel produtoPerecivel = new ProdutoPerecivel(valorProduto, nome, codigoProduto, descricaoProduto, dataValidade);
                    produtos.add(produtoPerecivel); // Adicione o produto perecível à lista
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static List<Produto> getProdutos(){
        return produtos;
    }


}