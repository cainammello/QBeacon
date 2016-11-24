package cainammello.qbeacon.service.updateAPI;

import java.util.List;

import cainammello.qbeacon.model.Historico;
import cainammello.qbeacon.service.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cainammello on 11/18/16.
 */
public class HistoricoApi extends Api<Historico> {

    //classe não pode ser instanciada
    private static HistoricoApi instance = null;

    //construtor private
    private HistoricoApi() { }

    //sempre utilizar o mesmo objeto em todo o codigo
    //gerando otimização na utilização dos recursos
    public synchronized static HistoricoApi getInstance() {
        if(instance == null)
            instance = new HistoricoApi();
        return instance;
    }

    //recupera todos os objetos do banco de dedados do servidor
    @Override
    public void getAll(Callback callback) {
        ((IHistoricoAPI)getCall(IHistoricoAPI.class)).getAll().enqueue(callback);
    }

    //recupera um objeto por id do banco de dedados do servidor
    @Override
    public void getByKey(int key, Callback callback) {
        ((IHistoricoAPI)getCall(IHistoricoAPI.class)).getByKey(key).enqueue(callback);
    }

    private interface IHistoricoAPI {
        @GET("historico")
        Call<List<Historico>> getAll();

        @GET("historico/{key}")
        Call<List<Historico>> getByKey(@Path("key") int key);
    }

}
