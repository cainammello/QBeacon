package cainammello.qbeacon.service.updateAPI;

import java.util.List;
import cainammello.qbeacon.model.Instituicao;
import cainammello.qbeacon.service.Api;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cainammello on 11/18/16.
 */
public class InstituicaoApi extends Api<Instituicao> {

    //classe não pode ser instanciada
    private static InstituicaoApi instance = null;

    //construtor private
    private InstituicaoApi() { }

    //sempre utilizar o mesmo objeto em todo o codigo
    //gerando otimização na utilização dos recursos
    public synchronized static InstituicaoApi getInstance() {
        if(instance == null)
            instance = new InstituicaoApi();
        return instance;
    }

    //recupera todos os objetos do banco de dedados do servidor
    @Override
    public void getAll(Callback callback) {
        ((IInstituicaoAPI)getCall(IInstituicaoAPI.class)).getAll().enqueue(callback);
    }

    //recupera um objeto por id do banco de dedados do servidor
    @Override
    public void getByKey(int key, Callback callback) {
        ((IInstituicaoAPI)getCall(IInstituicaoAPI.class)).getByKey(key).enqueue(callback);
    }

    private interface IInstituicaoAPI {
        @GET("instituicao")
        Call<List<Instituicao>> getAll();

        @GET("instituicao/{key}")
        Call<List<Instituicao>> getByKey(@Path("key") int key);
    }

}
