package cainammello.qbeacon.service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by cainammello on 11/18/16.
 */
public abstract class Api<T> {

    public Api() {}

    public Object getCall(Class<?> clazz) {
        return new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(clazz);

    }

    public abstract void getAll(Callback callback);
    public abstract void getByKey(int key, Callback callback);

}
