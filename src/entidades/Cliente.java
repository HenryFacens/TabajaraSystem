package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Cliente {
    private String nome;
    private Endereco endereco;
    private Date dataCadastro;

    protected static List<Object> classesInstanciadas = new ArrayList<>();


    public Cliente(String nome, Endereco endereco, Date dataCadastro) {
        this.nome = nome;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
    }

    public static List<Object> getClassesInstanciadas() {
        return classesInstanciadas;
    }

    public abstract String getDocumento();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public abstract String paraString();
}
