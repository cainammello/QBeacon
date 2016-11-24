package cainammello.qbeacon.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

    @BindView (R.id.campus)
    TextView tvCampus;

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
    public void onBeaconFinded(Beacon b) {
        Log.i(TAG, "Recebendo nome: " + b.getBluetoothName());

        //alimenta os objetos com os dados que veem do beacon
        final Sala sala = QBeaconProtocolo.extrairSala(b.getBluetoothName());
        final Bloco bloco = QBeaconProtocolo.extrairBloco(b.getBluetoothName());
        final Docente docente = QBeaconProtocolo.extrairDocente(b.getBluetoothName());
        final Disciplina disciplina = QBeaconProtocolo.extrairDisciplina(b.getBluetoothName());
        final Campus campus = QBeaconProtocolo.extrairCampus(b.getBluetoothName());

        //passa os valores dos objetos para os componentes da tela
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvSala.setText(sala != null? sala.getSalaNumero(): "NULL");
                tvBloco.setText(bloco != null? bloco.getName(): "NULL");
                tvDocente.setText(docente != null? docente.getName(): "NULL");
                tvDisciplina.setText(disciplina != null? disciplina.getName(): "NULL");
                tvCampus.setText(campus != null? campus.getName(): "NULL");

            }
        });
    }
}
