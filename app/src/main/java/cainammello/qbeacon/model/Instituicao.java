package cainammello.qbeacon.model;

import com.orm.SugarRecord;

/**
 * Created by cainammello on 11/20/16.
 */
public class Instituicao extends SugarRecord {

    private int key;
    private String nomeInstituicao;

    public Instituicao () {

    }

    public Instituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    @Override
    public String toString() {
        return "Instituicao{" +
                "key=" + key +
                ", nomeInstituicao='" + nomeInstituicao + '\'' +
                '}';
    }
}
