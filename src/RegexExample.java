import entidades.Cliente;
import entidades.Endereco;
import entidades.PessoaFisica;
import entidades.PessoaJuridica;
import entidades.SalvarDados;
import gerenciadores.ClienteGerenciador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.plaf.TreeUI;
import javax.xml.crypto.Data;


public class RegexExample {
    public void main(String[] args) {
        String input = SalvarDados.ler("cliente");
        Pattern pattern = Pattern.compile("([^:]+):\\s*(.*)");
        Matcher matcher = pattern.matcher(input);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
        boolean fisica = false;

        String nome = null;
        String rua = null;
        String numero = null;
        String cep = null;
        String cidade = null;
        String pais = null;
        String bairro = null;
        Date cadastro = null;
        String cpf = null;
        String cnpj = null;
        String razaoSocial = null;
        int prazoMaximo = -1;
        int qntMaxParcelas = -1;
        Endereco endereco = null;
        boolean pessoaFisicaPronta = false;
        boolean pessoaJuridicaPronta = false;
        boolean enderecoPronto = false;

        while (matcher.find()) {
            if (enderecoPronto){
                // System.out.println(nome + "," + qntMaxParcelas + "," + cpf + "," + rua + "," + numero + "," + cep + "," + cidade + "," + pais + "," + bairro + "," +  cnpj + "," + razaoSocial + "," + prazoMaximo);
                endereco = new Endereco(rua,numero,cep,cidade,pais,bairro);
                enderecoPronto = false;
            }

            if (pessoaFisicaPronta){
                // System.out.println(nome + "," + qntMaxParcelas + "," + cpf + "," + rua + "," + numero + "," + cep + "," + cidade + "," + pais + "," + bairro + "," +  cnpj + "," + razaoSocial + "," + prazoMaximo);
                PessoaFisica pessoaFisica = new PessoaFisica(nome, endereco, cadastro, qntMaxParcelas, cpf);
                pessoaFisicaPronta = false;

            }
            if (pessoaJuridicaPronta){
                // System.out.println(nome + "," + qntMaxParcelas + "," + cpf + "," + rua + "," + numero + "," + cep + "," + cidade + "," + pais + "," + bairro + "," +  cnpj + "," + razaoSocial + "," + prazoMaximo);                
                PessoaJuridica pessoaJuridica = new PessoaJuridica(nome, endereco, cadastro, cnpj, razaoSocial, prazoMaximo);
                pessoaJuridicaPronta = false;
            }
            // System.out.println(nome + "," + qntMaxParcelas + "," + cpf + "," + rua + "," + numero + "," + cep + "," + cidade + "," + pais + "," + bairro + "," +  cnpj + "," + razaoSocial + "," + prazoMaximo);

            String key = matcher.group(1).trim();
            String value = matcher.group(2).trim();

            if (key.equals("Tipo")){
                switch (value){
                    case "class entidades.PessoaFisica":
                        fisica = true;
                        break;

                    case "class entidades.PessoaJuridica":
                        fisica = false;
                        break;
                }

            }else{
                switch (key){
                    case "Nome":
                        // System.out.println(key + ": " + value);

                        nome = value;
                        break;
                    case "Rua": //Endereço;\n\t
                        // System.out.println(key + ": " + value);

                        rua = value;
                        break;

                    case "Numero":
                        // System.out.println(key + ": " + value);
                        numero = value;
                        break;

                    case "CEP":
                        // System.out.println(key + ": " + value);
                        cep = value;
                        break;

                    case "Cidade":
                        // System.out.println(key + ": " + value);
                        cidade = value;
                        break;

                    case "Pais":
                        // System.out.println(key + ": " + value);
                        pais = value;
                        break;

                    case "Bairro":
                        // System.out.println(key + ": " + value);
                        bairro = value;
                        enderecoPronto = true;
                        break;

                    case "Cadastro":
                        // System.out.println(key + ": " + value);
                        try {
                            // Parse the date string into a Date object
                            cadastro = sdf.parse(value);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        break;
                   }

                if (fisica){
                    switch (key){
                        case "CPF":
                            // System.out.println(key + ": " + value);
                            cpf = value;
                            break;

                        case "Maximas Parcelas":
                            // System.out.println(key + ": " + value);
                            qntMaxParcelas = Integer.parseInt(value);
                            pessoaFisicaPronta = true;
                            break;
                   }

                }else{
                    switch (key){
                        case "CNPJ":
                            // System.out.println(key + ": " + value);
                            cnpj = value;
                            break;

                        case "Razão Social":
                            // System.out.println(key + ": " + value);
                            razaoSocial = value;
                            break;

                        case "Prazo Máximo":
                        prazoMaximo = Integer.parseInt(value);
                        pessoaJuridicaPronta = true;
                        break;
                    }
                }
            }
        }
    }
}