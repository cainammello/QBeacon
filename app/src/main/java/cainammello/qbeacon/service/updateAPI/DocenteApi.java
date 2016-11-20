package cainammello.qbeacon.service.updateAPI;

import java.util.List;
import cainammello.qbeacon.model.Docente;
import cainammello.qbeacon.service.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cainammello on 11/18/16.
 */
public class DocenteApi extends Api<Docente> {

    //classe não pode ser instanciada
    private static DocenteApi instance = null;

    //construtor private
    private DocenteApi() { }

    //sempre utilizar o mesmo objeto em todo o codigo
    //gerando otimização na utilização dos recursos
    public synchronized static DocenteApi getInstance() {
        if(instance == null)
            instance = new DocenteApi();
        return instance;
    }

    //recupera todos os objetos do banco de dedados do servidor
    @Override
    public void getAll(Callback callback) {
        ((IDocenteAPI)getCall(IDocenteAPI.class)).getAll().enqueue(callback);
    }

    //recupera um objeto por id do banco de dedados do servidor
    @Override
    public void getByKey(int key, Callback callback) {
        ((IDocenteAPI)getCall(IDocenteAPI.class)).getByKey(key).enqueue(callback);
    }

    private interface IDocenteAPI {
        @GET("docente")
        Call<List<Docente>> getAll();

        @GET("docente/{key}")
        Call<List<Docente>> getByKey(@Path("key") int key);
    }

}
