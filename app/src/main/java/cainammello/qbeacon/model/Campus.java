package cainammello.qbeacon.model;

import com.orm.SugarRecord;

/**
 * Created by cainammello on 11/3/16.
 */
public class Campus extends SugarRecord{

    private int key;
    private String nomeCampus;

    public Campus() {
    }

    public Campus(String nomeCampus) {
        this.nomeCampus = nomeCampus;
    }

    public String getNomeCampus() {
        return nomeCampus;
    }

    public void setNomeCampus(String nomeCampus) {
        this.nomeCampus = nomeCampus;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Campus{" +
                "key=" + key +
                ", nomeCampus='" + nomeCampus + '\'' +
                '}';
    }
}
