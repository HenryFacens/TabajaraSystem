package entidades;

import javax.swing.*;
import java.util.Date;

public class PessoaFisica extends Cliente{

    private String cpf;

    private int qntMaxParcelas;

    public PessoaFisica(String nome, Endereco endereco, Date dataCadastro, int qntMaxParcelas,String cpf) {
        super(nome, endereco, dataCadastro);
        this.cpf = cpf;
        this.qntMaxParcelas = qntMaxParcelas;
        Cliente.classesInstanciadas.add(this);
    }

    @Override
    public String getDocumento() {
        return getCpf();
    }

    @Override
    public String paraString() {
        return "Tipo:" + this.getClass() + "\nNome:" + getNome() + getEndereco().paraString() + "\nCadastro:" + getDataCadastro() + "\nCPF:" + cpf + "\nMaximas Parcelas:" + qntMaxParcelas + "\nend:end" + "\n\n";
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
