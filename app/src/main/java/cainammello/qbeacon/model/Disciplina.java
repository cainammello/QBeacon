package cainammello.qbeacon.model;

import com.orm.SugarRecord;

/**
 * Created by cainammello on 11/3/16.
 */
public class Disciplina extends SugarRecord {

    private String nome;
    private String codigo;

    public Disciplina() {
    }

    public Disciplina(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Disciplina " + nome;
    }

}
