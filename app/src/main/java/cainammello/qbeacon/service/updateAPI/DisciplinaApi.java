package cainammello.qbeacon.service.updateAPI;

import java.util.List;

import cainammello.qbeacon.model.Disciplina;
import cainammello.qbeacon.service.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cainammello on 11/18/16.
 */
public class DisciplinaApi extends Api<Disciplina> {

    //classe não pode ser instanciada
    private static DisciplinaApi instance = null;

    //construtor private
    private DisciplinaApi() { }

    //sempre utilizar o mesmo objeto em todo o codigo
    //gerando otimização na utilização dos recursos
    public synchronized static DisciplinaApi getInstance() {
        if(instance == null)
            instance = new DisciplinaApi();
        return instance;
    }

    //recupera todos os objetos do banco de dedados do servidor
    @Override
    public void getAll(Callback callback) {
        ((IDisciplinaAPI)getCall(IDisciplinaAPI.class)).getAll().enqueue(callback);
    }

    //recupera um objeto por id do banco de dedados do servidor
    @Override
    public void getByKey(int key, Callback callback) {
        ((IDisciplinaAPI)getCall(IDisciplinaAPI.class)).getByKey(key).enqueue(callback);
    }

    private interface IDisciplinaAPI {
        @GET("disciplina")
        Call<List<Disciplina>> getAll();

        @GET("disciplina/{key}")
        Call<List<Disciplina>> getByKey(@Path("key") int key);
    }
}
