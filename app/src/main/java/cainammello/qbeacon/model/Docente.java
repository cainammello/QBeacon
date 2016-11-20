package cainammello.qbeacon.model;

import com.orm.SugarRecord;

/**
 * Created by cainammello on 11/3/16.
 */
public class Docente extends SugarRecord {

    private int key;
    private String nome;
    private String SIAPE;

    public Docente() {
    }

    public Docente(String nome, String SIAPE) {
        this.nome = nome;
        this.SIAPE = SIAPE;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSIAPE() {
        return SIAPE;
    }

    public void setSIAPE(String SIAPE) {
        this.SIAPE = SIAPE;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "Docente{" +
                "key=" + key +
                ", nome='" + nome + '\'' +
                ", SIAPE='" + SIAPE + '\'' +
                '}';
    }
}
