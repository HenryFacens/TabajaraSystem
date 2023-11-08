package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {

    private static int ultimoIdentificador = 0;

    private List<ItensPedidos> lista = new ArrayList<>();
    private final String indentificador;
    private Date data;
    private double valorPago;
    private double valorTotal;
    private Cliente cliente;
    private String produtos;
    private boolean pago;
    
    private final String documento;

    public Compra(List<ItensPedidos> lista, Date data, String documento) {
        this.lista = lista;
        this.indentificador = String.valueOf(++ultimoIdentificador);
        this.data = data;
        this.valorTotal = calcularValorTotal();
        this.documento = documento;
    }

    public Compra(Cliente cliente, String produtos) {
        this(new ArrayList<>(), new Date(), cliente.getDocumento());
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

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public boolean getPago() {
        return pago;
    }

    public String paraString(){
        return  "indentificador:" + indentificador + '\n' +
                "pago:" + pago + '\n' +
                "data:" + data + '\n'
                + produtos + '\n' +
                "documento:" + documento + '\n'+ "end:end\n\n";
    }


}