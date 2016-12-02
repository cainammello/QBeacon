package cainammello.qbeacon.model;

import com.orm.SugarRecord;
import com.orm.query.Select;

import java.util.List;

/**
 * Created by cainammello on 11/20/16.
 */
public class Historico extends AbsObject implements Comparable<Historico> {

    private int keyUpdated;
    private String modelUpdated;
    private long timestamp;

    public Historico() {
    }

    @Override
    public long save() {
        SugarRecord.deleteAll(Historico.class);
        return super.save();
    }

    public static List<Historico> getAll() {
        return Select.from(Historico.class).orderBy("timestamp").list();
    }

    public int getKeyUpdated() {
        return keyUpdated;
    }

    public String getModelUpdated() {
        return modelUpdated;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(Historico h) {
        return timestamp < h.getTimestamp()? -1: timestamp > h.getKey()? 1: 0;
    }

}
