package cainammello.qbeacon.model;

import com.orm.SugarRecord;

/**
 * Created by cainammello on 11/3/16.
 */
public class Campus extends SugarRecord{

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

    @Override
    public String toString() {
        return "Campus{" +
                "nomeCampus='" + nomeCampus + '\'' +
                '}';
    }
}
