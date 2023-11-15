package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {

    protected static List<Object> classesInstanciadas = new ArrayList<>();
    

    private List<ItensPedidos> lista = new ArrayList<>();
    private final Integer indentificador;
    private Date data;
    private double valorPago;
    private double valorTotal;
    private Cliente cliente;
    private String produtos;
    private boolean pago;

    private final String documento;
    
    public Compra(List<ItensPedidos> lista, Date data, String documento, Integer indentificador) {
        this.lista = lista;
        this.data = data;
        this.valorTotal = calcularValorTotal();
        this.indentificador = indentificador;
        this.documento = documento;
    }

    public Compra(Cliente cliente, String produtos, Integer indentificador) {
        this(new ArrayList<>(), new Date(), cliente.getDocumento(), indentificador);
        this.cliente = cliente;
        this.produtos = produtos;
        Compra.classesInstanciadas.add(this);
    }

    public static List<Object> getClassesInstanciadas() {
        return classesInstanciadas;
    } 

    private double calcularValorTotal(){
        double total = 0;
        for (ItensPedidos pedidos: lista) {
            total += pedidos.getValorTotal();
        }
        return total;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public boolean getPago() {
        return pago;
    }

    public Integer getId(){
        return indentificador;
    }
    public Date getData(){
        return data;
    }

    public String paraString(){
        return  "indentificador:" + indentificador + '\n' +
                "pago:" + pago + '\n' +
                "data:" + data + '\n'
                + produtos + '\n' +
                "documento:" + documento + '\n'+ "end:end\n\n";
    }

}