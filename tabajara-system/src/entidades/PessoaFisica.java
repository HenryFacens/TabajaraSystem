package entidades;

import javax.swing.*;
import java.util.Date;

public class PessoaFisica extends Cliente{

    private String cpf;

    private int qntMaxParcelas;

    public PessoaFisica(String nome, Endereco endereco, Date dataCadastro, int qntMaxParcelas) {
        super(nome, endereco, dataCadastro);
        this.cpf = cpf;
        this.qntMaxParcelas = qntMaxParcelas;
    }


    @Override
    public String getDocumento() {
        return getCpf();
    }

    @Override
    public String paraString() {
        return "Nome:" + getNome() + "\nEndereço" + getEndereco() + "\nData de Cadastro" + getDataCadastro() + "\nCPF" + cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
