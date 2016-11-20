package cainammello.qbeacon.service.updateAPI;

import java.util.List;
import cainammello.qbeacon.model.Sala;
import cainammello.qbeacon.service.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cainammello on 11/18/16.
 */
public class SalaApi extends Api<Sala> {

    //classe não pode ser instanciada
    private static SalaApi instance = null;

    //construtor private
    private SalaApi() { }

    //sempre utilizar o mesmo objeto em todo o codigo
    //gerando otimização na utilização dos recursos
    public synchronized static SalaApi getInstance() {
        if(instance == null)
            instance = new SalaApi();
        return instance;
    }

    //recupera todos os objetos do banco de dedados do servidor
    @Override
    public void getAll(Callback callback) {
        ((ISalaAPI)getCall(ISalaAPI.class)).getAll().enqueue(callback);
    }

    //recupera um objeto por id do banco de dedados do servidor
    @Override
    public void getByKey(int key, Callback callback) {
        ((ISalaAPI)getCall(ISalaAPI.class)).getByKey(key).enqueue(callback);
    }

    private interface ISalaAPI {
        @GET("sala")
        Call<List<Sala>> getAll();

        @GET("sala/{key}")
        Call<List<Sala>> getByKey(@Path("key") int key);
    }

}
