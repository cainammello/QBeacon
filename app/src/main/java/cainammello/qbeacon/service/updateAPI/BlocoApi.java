package cainammello.qbeacon.service.updateAPI;

import java.util.List;

import cainammello.qbeacon.model.Bloco;
import cainammello.qbeacon.service.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cainammello on 11/18/16.
 */
public class BlocoApi extends Api<Bloco> {

    //classe não pode ser instanciada
    private static BlocoApi instance = null;

    //construtor private
    private BlocoApi() { }

    //sempre utilizar o mesmo objeto em todo o codigo
    //gerando otimização na utilização dos recursos
    public synchronized static BlocoApi getInstance() {
        if(instance == null)
            instance = new BlocoApi();
        return instance;
    }

    //recupera todos os objetos do banco de dedados do servidor
    @Override
    public void getAll(Callback callback) {
        ((IBlocoAPI)getCall(IBlocoAPI.class)).getAll().enqueue(callback);
    }

    //recupera um objeto por id do banco de dedados do servidor
    @Override
    public void getByKey(int key, Callback callback) {
        ((IBlocoAPI)getCall(IBlocoAPI.class)).getByKey(key).enqueue(callback);
    }

    private interface IBlocoAPI {
        @GET("bloco")
        Call<List<Bloco>> getAll();

        @GET("bloco/{key}")
        Call<List<Bloco>> getByKey(@Path("key") int key);
    }

}
