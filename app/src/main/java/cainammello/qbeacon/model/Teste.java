package cainammello.qbeacon.model;

import com.orm.SugarRecord;

/**
 * Created by felipe on 06/12/16.
 */
public class Teste extends SugarRecord {

    private double distance;
    private long scanInterval;
    private long loadInfoInterval;

    public Teste() {
    }

    public Teste(double distance, long scanInterval, long loadInfoInterval) {
        this.distance = distance;
        this.scanInterval = scanInterval;
        this.loadInfoInterval = loadInfoInterval;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public long getScanInterval() {
        return scanInterval;
    }

    public void setScanInterval(long scanInterval) {
        this.scanInterval = scanInterval;
    }

    public long getLoadInfoInterval() {
        return loadInfoInterval;
    }

    public void setLoadInfoInterval(long loadInfoInterval) {
        this.loadInfoInterval = loadInfoInterval;
    }
}
