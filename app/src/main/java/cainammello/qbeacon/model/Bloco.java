package cainammello.qbeacon.model;

import com.orm.SugarRecord;

/**
 * Created by cainammello on 11/3/16.
 */
public class Bloco extends SugarRecord {

    private String blocoNumero;

    public Bloco() {

    }
    public Bloco(String blocoNumero) {
        this.blocoNumero = blocoNumero;
    }

    public String getBlocoNumero() {
        return blocoNumero;
    }

    public void setBlocoNumero(String blocoNumero) {
        this.blocoNumero = blocoNumero;
    }

    @Override
    public String toString() {
        return "Bloco{" +
                "blocoNumero='" + blocoNumero + '\'' +
                '}';
    }
}
