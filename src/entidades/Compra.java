package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {

    private List<ItensPedidos> lista = new ArrayList<>();
    private final String indentificador;
    private Date data;
    private double valorPago;
    private double valorTotal;
    private Cliente cliente;
    private String produtos;
    
    private final String documento;

    public Compra(List<ItensPedidos> lista, String indentificador, Date data, String documento) {
        this.lista = lista;
        this.indentificador = indentificador;
        this.data = data;
        this.valorTotal = calcularValorTotal();
        this.documento = documento;
    }

    public Compra(Cliente cliente, String produtos) {
        this(new ArrayList<>(), "1", new Date(), cliente.getDocumento());
        this.cliente = cliente;
        this.produtos = produtos;
    }

    private double calcularValorTotal(){
        double total = 0;
        for (ItensPedidos pedidos: lista) {
            total += pedidos.getValorTotal();
        }
        return total;
    }

    public String paraString(){
        return "Compra{" +
                "lista=" + lista +
                ", indentificador='" + indentificador + '\'' +
                ", data=" + data +
                ", valorPago=" + valorPago +
                ", valorTotal=" + valorTotal +
                ", cliente=" + cliente +
                ", produtos=" + produtos +
                ", documento='" + documento + '\'' +
                '}';
    }


}