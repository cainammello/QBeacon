package cainammello.qbeacon.model;

import android.support.annotation.Nullable;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by felipe on 21/11/16.
 */
public abstract class AbsObject extends SugarRecord {

    private int key;
    private String name;

    public AbsObject() {}

    public AbsObject(int key, String name) {
        this.key = key;
        this.name = name;
    }

    @Nullable
    public static <T> T getByKey(Class<T> type, int key) {
        List<T> result = SugarRecord.find(type, "key = ?", "" + key);
        return result.size() > 0? result.get(0): null;
    }

    public <T extends AbsObject> long saveByKey() {
        AbsObject o = AbsObject.getByKey(this.getClass(), getKey());
        if(o != null) {
            setId(o.getId());
        }
        return super.save();
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

    @Override
    public String toString() {
        return "AbsObject{" +
                "name='" + name + '\'' +
                '}';
    }
}
