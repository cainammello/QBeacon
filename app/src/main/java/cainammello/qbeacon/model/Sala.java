package cainammello.qbeacon.model;

import com.orm.SugarRecord;

/**
 * Created by cainammello on 11/3/16.
 */
public class Sala extends SugarRecord {

    private String salaNumero;

    public Sala() {
    }

    public Sala(String salaNumero) {
        this.salaNumero = salaNumero;
    }

    public String getSalaNumero() {
        return salaNumero;
    }

    public void setSalaNumero(String salaNumero) {
        this.salaNumero = salaNumero;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "salaNumero='" + salaNumero + '\'' +
                '}';
    }
}
