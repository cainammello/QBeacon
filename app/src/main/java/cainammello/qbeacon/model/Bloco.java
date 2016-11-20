package cainammello.qbeacon.model;
import com.orm.SugarRecord;

/**
 * Created by cainammello on 11/3/16.
 */
public class Bloco extends SugarRecord {

    private int key;
    private String name;

    public Bloco() {

    }

    public Bloco(String name) {
        this.name = name;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
