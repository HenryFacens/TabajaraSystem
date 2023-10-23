package entidades;

import javax.swing.*;
import java.util.Date;

public class PessoaJuridica extends Cliente {

    private String cnpj;
    private String razaoSocial;
    private int prazoMaximo;
    public PessoaJuridica(String nome, Endereco endereco, Date dataCadastro, String cnpj, String razaoSocial,int prazoMaximo) {
        super(nome, endereco, dataCadastro);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.prazoMaximo = prazoMaximo;
        Cliente.classesInstanciadas.add(this);
    }

    @Override
    public String getDocumento() {
        return getCnpj();
    }

    @Override
    public String paraString() {
        return "Tipo:" + this.getClass() + "\nNome:" + getNome() + getEndereco().paraString() + "\nCadastro:" + getDataCadastro() + "\nCNPJ:" + cnpj
                + "\nRazão Social:" + razaoSocial + "\nPrazo Máximo:" + prazoMaximo + "\nend:end" + "\n\n";
    }
 
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
