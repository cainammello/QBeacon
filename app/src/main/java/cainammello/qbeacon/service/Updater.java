package cainammello.qbeacon.service;

import android.util.Log;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cainammello.qbeacon.model.Historico;
import cainammello.qbeacon.service.updateAPI.HistoricoApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by felipe on 22/11/16.
 */
public class Updater {

    public static final long UPDATE_INTERVAL = 60 * 1000;
    private static Updater instance = null;

    private Timer timer;
    private boolean running = false;
    private Updater() {}

    public synchronized static Updater getInstance() {
        if(instance == null)
            instance = new Updater();

        return instance;
    }

    public synchronized void start() {
        if(!running) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {

                    HistoricoApi.getInstance().getAll(new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            List<Historico> historicos = (List<Historico>) response.body();
                            Log.i("DEBUG", "Histórico recebido: " + historicos.toString());
                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.i("DEBUG", "Falha ao receber histórico!");
                        }
                    });

                }
            }, 0, UPDATE_INTERVAL);
        }
    }

    public synchronized void stop() {
        timer.cancel();
        running = false;
    }

}
