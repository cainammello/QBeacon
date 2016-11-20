package cainammello.qbeacon.model;

import com.orm.SugarRecord;

/**
 * Created by cainammello on 11/3/16.
 */
public class Sala extends SugarRecord {

    private int key;
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

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "key=" + key +
                ", salaNumero='" + salaNumero + '\'' +
                '}';
    }
}
