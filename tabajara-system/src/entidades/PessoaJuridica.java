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
    }

    private boolean validarCPNJ(String cpf){
        return cpf.length() == 15;
    }

    @Override
    public String getDocumento() {
        return getCnpj();
    }

    @Override
    public String paraString() {
        return "Nome: " + getNome() + "\nEndereço: " + getEndereco() + "\nData de Cadastro: " + getDataCadastro() + "\nCNPJ: " + cnpj
                + "Razão Social: " + razaoSocial + "Prazo Máximo: " + prazoMaximo;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
