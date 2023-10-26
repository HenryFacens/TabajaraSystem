package entidades;

import java.util.ArrayList;

public class ResultadoChaveValor {
    private ArrayList<String> chaves;
    private ArrayList<String> valores;

    public ResultadoChaveValor(ArrayList<String> chaves, ArrayList<String> valores) {
        this.chaves = chaves;
        this.valores = valores;
    }

    public ArrayList<String> getChaves() {
        return chaves;
    }

    public ArrayList<String> getValores() {
        return valores;
    }
}
