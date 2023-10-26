package entidades;

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

    public static List<Integer> contarRepetido(String valor, ArrayList<String> lista) {
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).equals(valor)) {
                indices.add(i);
            }
        }
        return indices;
    }

    public static void carregarClientes(){
        String input = ControladorDados.ler("cliente");
        ResultadoChaveValor resultado = ControladorDados.separarChaveValor(input);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        ArrayList<String> valores = resultado.getValores();
        List<Integer> idxsCliente = contarRepetido("end", valores);
        System.out.println(valores);
        for (int idx : idxsCliente){
            Date cadastro = null;
            String nome = valores.get(1);
            String rua = valores.get(2); 
            String numero = valores.get(3);
            String cep = valores.get(4); 
            String cidade = valores.get(5); 
            String pais = valores.get(6); 
            String bairro = valores.get(7);
            Endereco endereco = new Endereco(rua,numero,cep,cidade,pais,bairro);
            try {
                // Parse the date string into a Date object
                cadastro = sdf.parse(valores.get(8));
            } catch (ParseException e) {
                e.printStackTrace();
            } //

            if (valores.get(0).equals("class entidades.PessoaJuridica")){
                String cnpj = valores.get(9);
                String razaoSocial = valores.get(10);
                int prazoMaximo =  Integer.parseInt(valores.get(11));
                PessoaJuridica pessoaJuridica = new PessoaJuridica(nome, endereco, cadastro, cnpj, razaoSocial, prazoMaximo);
            }else{
                String cpf = valores.get(9);
                int qntMaxParcelas = Integer.parseInt(valores.get(10));       
                PessoaFisica pessoaFisica = new PessoaFisica(nome, endereco, cadastro, qntMaxParcelas, cpf);
            }
        }
        
    }
}
