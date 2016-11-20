package cainammello.qbeacon.model;

import com.orm.SugarRecord;

/**
 * Created by cainammello on 11/20/16.
 */
public class Historico extends SugarRecord{

    private int key;
    private int keyUpdated;
    private String modelUpdated;
    private int timestamp;

    public Historico () {

    }

    public Historico( int keyUpdated, String modelUpdated, int timestamp) {
        this.keyUpdated = keyUpdated;
        this.modelUpdated = modelUpdated;
        this.timestamp = timestamp;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKeyUpdated() {
        return keyUpdated;
    }

    public void setKeyUpdated(int keyUpdated) {
        this.keyUpdated = keyUpdated;
    }

    public String getModelUpdated() {
        return modelUpdated;
    }

    public void setModelUpdated(String modelUpdated) {
        this.modelUpdated = modelUpdated;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Historico{" +
                "key=" + key +
                ", keyUpdated=" + keyUpdated +
                ", modelUpdated='" + modelUpdated + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
