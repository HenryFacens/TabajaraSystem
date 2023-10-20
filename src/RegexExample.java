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
    private static boolean fisica;
    public static void main(String[] args) {
        System.out.println(Cliente.getClassesInstanciadas());

        String input = SalvarDados.ler("cliente");
        Pattern pattern = Pattern.compile("([^:]+):\\s*(.*)");
        Matcher matcher = pattern.matcher(input);
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);

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

        while (matcher.find()) {
            if (bairro != null){
                System.out.println(nome + "," + qntMaxParcelas + "," + cpf + "," + rua + "," + numero + "," + cep + "," + cidade + "," + pais + "," + bairro + "," +  cnpj + "," + razaoSocial + "," + prazoMaximo);
                endereco = new Endereco(rua,numero,cep,cidade,pais,bairro);
                // rua = null;
                // numero = null;
                // cep = null;
                // cidade = null;
                // pais = null;
                // bairro = null;
            }
            else if (qntMaxParcelas >= 0){
                System.out.println(nome + "," + qntMaxParcelas + "," + cpf + "," + rua + "," + numero + "," + cep + "," + cidade + "," + pais + "," + bairro + "," +  cnpj + "," + razaoSocial + "," + prazoMaximo);
                
                PessoaFisica pessoaFisica = new PessoaFisica(nome, endereco, cadastro, qntMaxParcelas, cpf);

                // nome = null;
                // endereco = null;
                // cadastro = null;
                // cpf = null;
                // qntMaxParcelas = -1;
            }

            else if (prazoMaximo >= 0){
                System.out.println(nome + "," + qntMaxParcelas + "," + cpf + "," + rua + "," + numero + "," + cep + "," + cidade + "," + pais + "," + bairro + "," +  cnpj + "," + razaoSocial + "," + prazoMaximo);

                System.out.println(prazoMaximo);

                PessoaJuridica pessoaJuridica = new PessoaJuridica(nome, endereco, cadastro, cnpj, razaoSocial, prazoMaximo);

                // nome = null;
                // endereco = null;
                // cadastro = null;
                // cnpj = null;
                // razaoSocial = null;
                // prazoMaximo = -1;
            }
            System.out.println(nome + "," + qntMaxParcelas + "," + cpf + "," + rua + "," + numero + "," + cep + "," + cidade + "," + pais + "," + bairro + "," +  cnpj + "," + razaoSocial + "," + prazoMaximo);

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
                // System.out.println(key + ": " + value);
                switch (key){
                    case "Nome":
                        nome = value;
                        break;

                    case "Endereço;\n\trua":
                        rua = value;
                        break;

                    case "CEP":
                        cep = value;
                        break;

                    case "Cidade":
                        cidade = value;
                        break;

                    case "Pais":
                        pais = value;
                        break;

                    case "Bairro":
                        bairro = value;
                        break;

                    case "Cadastro":
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
                        case "cpf":
                            cpf = value;
                            break;

                        case "Maximas Parcelas":
                            qntMaxParcelas = Integer.parseInt(value);
                            break;                    
                   }
                   
                }else{
                    switch (key){
                        case "cnpj":
                            cnpj = value;
                            break;

                        case "Razão Social":
                            razaoSocial = value;
                            break;

                        case "Prazo Máximo":
                        prazoMaximo = Integer.parseInt(value);
                            break;
                    }
                }
            }
        }
        System.out.println(Cliente.getClassesInstanciadas());
    }
}