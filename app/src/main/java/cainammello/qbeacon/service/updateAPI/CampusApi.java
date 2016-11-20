package cainammello.qbeacon.service.updateAPI;

import java.util.List;

import cainammello.qbeacon.model.Campus;
import cainammello.qbeacon.service.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cainammello on 11/18/16.
 */
public class CampusApi extends Api<Campus> {

    //classe não pode ser instanciada
    private static CampusApi instance = null;

    //construtor private
    private CampusApi() { }

    //sempre utilizar o mesmo objeto em todo o codigo
    //gerando otimização na utilização dos recursos
    public synchronized static CampusApi getInstance() {
        if(instance == null)
            instance = new CampusApi();
        return instance;
    }

    //recupera todos os objetos do banco de dedados do servidor
    @Override
    public void getAll(Callback callback) {
        ((ICampusAPI)getCall(ICampusAPI.class)).getAll().enqueue(callback);
    }

    //recupera um objeto por id do banco de dedados do servidor
    @Override
    public void getByKey(int key, Callback callback) {
        ((ICampusAPI)getCall(ICampusAPI.class)).getByKey(key).enqueue(callback);
    }

    private interface ICampusAPI {
        @GET("campus")
        Call<List<Campus>> getAll();

        @GET("campus/{key}")
        Call<List<Campus>> getByKey(@Path("key") int key);
    }

}
