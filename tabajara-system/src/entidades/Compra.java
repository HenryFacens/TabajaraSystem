package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public  class Compra {

    private List<ItensPedidos> lista = new ArrayList<>();
    private final String indentificador;
    private Date data;
    private double valorPago;
    private double valorTotal;
    
    private final String documento;

    public Compra(List<ItensPedidos> lista, String indentificador, Date data, double valorTotal, String documento) {
        this.lista = lista;
        this.indentificador = indentificador;
        this.data = data;
        this.valorTotal = valorTotal;
        this.documento = documento;
    }


    private void ValorTotal(){
        double total = 0;
        for (ItensPedidos pedidos: lista) {
            total += pedidos.getValorTotal();
        }
//        return total;
    }

    public double getValorTotal(){
        return valorTotal;
    }
     public String paraString(){
        return "";
    }

}
