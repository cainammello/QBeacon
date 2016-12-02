package cainammello.qbeacon.service;

import android.util.Log;

import com.orm.SugarRecord;
import com.orm.query.Select;

import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cainammello.qbeacon.model.Bloco;
import cainammello.qbeacon.model.Campus;
import cainammello.qbeacon.model.Disciplina;
import cainammello.qbeacon.model.Docente;
import cainammello.qbeacon.model.Historico;
import cainammello.qbeacon.model.Instituicao;
import cainammello.qbeacon.model.Sala;
import cainammello.qbeacon.service.updateAPI.BlocoApi;
import cainammello.qbeacon.service.updateAPI.CampusApi;
import cainammello.qbeacon.service.updateAPI.DisciplinaApi;
import cainammello.qbeacon.service.updateAPI.DocenteApi;
import cainammello.qbeacon.service.updateAPI.HistoricoApi;
import cainammello.qbeacon.service.updateAPI.InstituicaoApi;
import cainammello.qbeacon.service.updateAPI.SalaApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by felipe on 22/11/16.
 */
public class Updater {

    public static final long UPDATE_INTERVAL = 10 * 1000;
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

                    // Log.i("DEBUG", "Salas: " + Select.from(Sala.class).list().toString());
                    //Log.i("DEBUG", "Campus: " + SugarRecord.findAll(Campus.class).toString());
                    //Log.i("DEBUG", "Docente: " + SugarRecord.findAll(Docente.class).toString());
                    //Log.i("DEBUG", "Bloco: " + SugarRecord.findAll(Bloco.class).toString());
                    //Log.i("DEBUG", "Instituição: " + SugarRecord.findAll(Instituicao.class).toString());
                    //Log.i("DEBUG", "Disciplina: " + SugarRecord.findAll(Disciplina.class).toString());

                    // ...

                    final long timestamp;
                    List<Historico> historicos = Select.from(Historico.class).list();

                    if(historicos.size() > 0) {
                        timestamp = historicos.get(0).getTimestamp();
                    } else {
                        timestamp = 0;
                    }

                    HistoricoApi.getInstance().getByTimestamp(timestamp, new Callback() {
                        @Override
                        public void onResponse(Call call, Response response) {
                            List<Historico> historicos = (List<Historico>) response.body();
                            //Log.i("DEBUG", "Histórico recebido with timestamp " + timestamp + " : " + historicos.toString());

                            if(historicos.size() == 0) return;

                            SugarRecord.deleteAll(Historico.class);
                            Collections.sort(historicos);

                            historicos.get(historicos.size() - 1).save();

                            // ...
                            for (Historico h: historicos) {

                                if(h.getModelUpdated().equals("sala")) {
                                    SalaApi.getInstance().getByKey(h.getKeyUpdated(), new Callback() {
                                        @Override
                                        public void onResponse(Call call, Response response) {
                                            List<Sala> salas = (List<Sala>) response.body();
                                            if(salas.size() > 0)
                                                salas.get(0).saveByKey();
                                        }

                                        @Override
                                        public void onFailure(Call call, Throwable t) {
                                            Log.i("DEBUG", "Erro ao atualizar Sala");
                                        }
                                    });
                                }

                                if(h.getModelUpdated().equals("bloco")) {
                                    BlocoApi.getInstance().getByKey(h.getKeyUpdated(), new Callback() {
                                        @Override
                                        public void onResponse(Call call, Response response) {
                                            List<Bloco> blocos = (List<Bloco>) response.body();
                                            if(blocos.size() > 0)
                                                blocos.get(0).saveByKey();
                                        }

                                        @Override
                                        public void onFailure(Call call, Throwable t) {
                                            Log.i("DEBUG", "Erro ao atualizar Bloco");
                                        }
                                    });
                                }

                                if(h.getModelUpdated().equals("instituicao")) {
                                    InstituicaoApi.getInstance().getByKey(h.getKeyUpdated(), new Callback() {
                                        @Override
                                        public void onResponse(Call call, Response response) {
                                            List<Instituicao> instituicaos = (List<Instituicao>) response.body();
                                            if(instituicaos.size() > 0)
                                                instituicaos.get(0).saveByKey();
                                        }

                                        @Override
                                        public void onFailure(Call call, Throwable t) {
                                            Log.i("DEBUG", "Erro ao atualizar Instituição");
                                        }
                                    });
                                }

                                if(h.getModelUpdated().equals("docente")) {
                                    DocenteApi.getInstance().getByKey(h.getKeyUpdated(), new Callback() {
                                        @Override
                                        public void onResponse(Call call, Response response) {
                                            List<Docente> docentes = (List<Docente>) response.body();
                                            if(docentes.size() > 0)
                                                docentes.get(0).saveByKey();
                                        }

                                        @Override
                                        public void onFailure(Call call, Throwable t) {
                                            Log.i("DEBUG", "Erro ao atualizar Docente");
                                        }
                                    });
                                }

                                if(h.getModelUpdated().equals("campus")) {
                                    CampusApi.getInstance().getByKey(h.getKeyUpdated(), new Callback() {
                                        @Override
                                        public void onResponse(Call call, Response response) {
                                            List<Campus> campuses = (List<Campus>) response.body();
                                            if(campuses.size() > 0)
                                                campuses.get(0).saveByKey();
                                        }

                                        @Override
                                        public void onFailure(Call call, Throwable t) {
                                            Log.i("DEBUG", "Erro ao atualizar Campus");
                                        }
                                    });
                                }

                                if(h.getModelUpdated().equals("disciplina")) {
                                    DisciplinaApi.getInstance().getByKey(h.getKeyUpdated(), new Callback() {
                                        @Override
                                        public void onResponse(Call call, Response response) {
                                            List<Disciplina> disciplinas = (List<Disciplina>) response.body();
                                            if(disciplinas.size() > 0)
                                                disciplinas.get(0).saveByKey();
                                        }

                                        @Override
                                        public void onFailure(Call call, Throwable t) {
                                            Log.i("DEBUG", "Erro ao atualizar Disciplina");

                                        }
                                    });
                                }

                            }

                        }

                        @Override
                        public void onFailure(Call call, Throwable t) {
                            Log.i("DEBUG", "Falha ao receber histórico!");
                        }
                    });

                    //...

                }
            }, 0, UPDATE_INTERVAL);
        }
    }

    public synchronized void stop() {
        timer.cancel();
        running = false;
    }

}
