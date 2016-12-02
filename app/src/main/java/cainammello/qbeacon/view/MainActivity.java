package cainammello.qbeacon.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.orm.SugarContext;

import org.altbeacon.beacon.Beacon;

import butterknife.BindView;
import butterknife.ButterKnife;
import cainammello.qbeacon.R;
import cainammello.qbeacon.beacon.BeaconFinder;
import cainammello.qbeacon.model.Bloco;
import cainammello.qbeacon.model.Campus;
import cainammello.qbeacon.model.Disciplina;
import cainammello.qbeacon.model.Docente;
import cainammello.qbeacon.model.Instituicao;
import cainammello.qbeacon.model.Sala;
import cainammello.qbeacon.protocolo.QBeaconProtocolo;
import cainammello.qbeacon.service.Updater;

public class MainActivity extends AppCompatActivity implements BeaconFinder.BeaconFinderListener {

    protected static final String TAG = "DEBUG";

    private Updater updater;
    private BeaconFinder beaconFinder;

    //instanciando os componentes da tela
    @BindView (R.id.sala)
    TextView tvSala;

    @BindView (R.id.bloco)
    TextView tvBloco;

    @BindView (R.id.docente)
    TextView tvDocente;

    @BindView (R.id.disciplina)
    TextView tvDisciplina;

    @BindView (R.id.instituicao)
    TextView tvInstituicao;

    @BindView (R.id.campus)
    TextView tvCampus;

    @BindView (R.id.aulaAnterior)
    TextView tvAulaAnterior;

    @BindView (R.id.aulaProxima)
    TextView tvAulaProxima;

    @BindView (R.id.inicio)
    TextView tvInicio;

    @BindView (R.id.fim)
    TextView tvFim;

    @BindView (R.id.view_info)
    View vInfo;

    @BindView (R.id.view_progress)
    View vProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //iniciando os componentes com o ButterKnife
        ButterKnife.bind(this);

        //iniciando o banco de dados com o SugarContext
        SugarContext.init(this);

        updater = Updater.getInstance();
        updater.start();
        beaconFinder = new BeaconFinder();
        beaconFinder.setListener(this);
        beaconFinder.start(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
        updater.stop();
        beaconFinder.stop();
    }

    @Override
    public void onBeaconFinded(Beacon b, String uuid) {

        QBeaconProtocolo beaconProtocolo = new QBeaconProtocolo();

        //alimenta os objetos com os dados que veem do beacon
        final Sala sala = beaconProtocolo.getObjectFrom(Sala.class, uuid, 1);
        final Bloco bloco = beaconProtocolo.getObjectFrom(Bloco.class, uuid, 1);
        final Docente docente = beaconProtocolo.getObjectFrom(Docente.class, uuid, 1);
        final Disciplina disciplina = beaconProtocolo.getObjectFrom(Disciplina.class, uuid, 1);
        final Instituicao instituicao = beaconProtocolo.getObjectFrom(Instituicao.class, uuid, 1);
        final Campus campus = beaconProtocolo.getObjectFrom(Campus.class, uuid, 1);
        final Disciplina aulaAnt = beaconProtocolo.getObjectFrom(Disciplina.class, uuid, 2);
        final Disciplina aulaProx = beaconProtocolo.getObjectFrom(Disciplina.class, uuid, 3);
        final Integer horaI = beaconProtocolo.getValueFrom(uuid, 1);
        final Integer minI = beaconProtocolo.getValueFrom(uuid, 2);
        final Integer horaF = beaconProtocolo.getValueFrom(uuid, 3);
        final Integer minF = beaconProtocolo.getValueFrom(uuid, 4);


        //passa os valores dos objetos para os componentes da tela
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                vProgress.setVisibility(View.GONE);
                vInfo.setVisibility(View.VISIBLE);
                tvSala.setText(sala != null? "Sala " + sala.getName(): "NULL");
                tvBloco.setText(bloco != null? "Bloco " + bloco.getName(): "NULL");
                tvDocente.setText(docente != null? docente.getName(): "NULL");
                tvDisciplina.setText(disciplina != null? disciplina.getName(): "NULL");
                tvInstituicao.setText(instituicao != null? instituicao.getName(): "NULL");
                tvCampus.setText(campus != null? campus.getName(): "NULL");
                tvAulaAnterior.setText(aulaAnt != null? aulaAnt.getName(): "NULL");
                tvAulaProxima.setText(aulaProx != null? aulaProx.getName(): "NULL");
                tvInicio.setText(String.format("%02d", horaI) + ":" + String.format("%02d", minI));
                tvFim.setText(String.format("%02d", horaF) + ":" + String.format("%02d", minF));
            }
        });
    }
}
