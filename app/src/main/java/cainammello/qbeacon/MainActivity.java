package cainammello.qbeacon;

import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import com.orm.SugarContext;
import com.orm.SugarRecord;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;
import java.util.Collection;
import butterknife.BindView;
import butterknife.ButterKnife;
import cainammello.qbeacon.model.Bloco;
import cainammello.qbeacon.model.Campus;
import cainammello.qbeacon.model.Disciplina;
import cainammello.qbeacon.model.Docente;
import cainammello.qbeacon.model.Sala;
import cainammello.qbeacon.protocolo.QBeaconProtocolo;

public class MainActivity extends AppCompatActivity implements BeaconConsumer {

    protected static final String TAG = "DEBUG ";
    private BeaconManager beaconManager;

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

        ButterKnife.bind(this);

        SugarContext.init(this);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        beaconManager.getBeaconParsers().add(new BeaconParser(). setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));

        beaconManager.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //iniciaBanco();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SugarContext.terminate();
        beaconManager.unbind(this);
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setMonitorNotifier(new MonitorNotifier() {

            @Override
            public void didEnterRegion(Region region) {
                Log.i(TAG, "I just saw an beacon for the first time!");
            }

            @Override
            public void didExitRegion(Region region) {
                Log.i(TAG, "I no longer see an beacon");
            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.i(TAG, "I have just switched from seeing/not seeing beacons: "+state);
            }

        });

        try {
            beaconManager.startMonitoringBeaconsInRegion(new Region("myMonitoringUniqueId", null, null, null));
        } catch (RemoteException e) {    }

        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {

                    Beacon b = beacons.iterator().next();
                    Log.i(TAG, "Recebendo nome: " + b.getBluetoothName());


                    final Sala sala = QBeaconProtocolo.extrairSala(b.getBluetoothName());
                    final Bloco bloco = QBeaconProtocolo.extrairBloco(b.getBluetoothName());
                    final Docente docente = QBeaconProtocolo.extrairDocente(b.getBluetoothName());
                    final Disciplina disciplina = QBeaconProtocolo.extrairDisciplina(b.getBluetoothName());
                    final Campus campus = QBeaconProtocolo.extrairCampus(b.getBluetoothName());


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvSala.setText(sala != null? sala.getSalaNumero(): "NULL");
                            tvBloco.setText(bloco != null? bloco.getBlocoNumero(): "NULL");
                            tvDocente.setText(docente != null? docente.getNome(): "NULL");
                            tvDisciplina.setText(disciplina != null? disciplina.getNome(): "NULL");
                            tvCampus.setText(campus != null? campus.getNomeCampus(): "NULL");

                        }
                    });

                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {    }

    }

    public void iniciaBanco(){
        //sala
        Sala sala1 = new Sala("Sala 01");
        sala1.setId(0L);
        sala1.save();
        Sala sala2 = new Sala("Sala 02");
        sala2.setId(1L);
        sala2.save();

        //Bloco
        Bloco bloco1 = new Bloco("Bloco 01");
        bloco1.setId(0L);
        bloco1.save();
        Bloco bloco2 = new Bloco("Bloco 02");
        bloco2.setId(1L);
        bloco2.save();

        //docente
        Docente david = new Docente("David Sena", "ZSP3");
        david.setId(0L);
        david.save();
        Docente samy = new Docente("Samy Sá", "ZXP4");
        samy.setId(1L);
        samy.save();

        //disciplina
        Disciplina fup = new Disciplina("Fundamentos Programação", "123");
        fup.setId(0L);
        fup.save();
        Disciplina discreta = new Disciplina("Matemática Discreta", "124");
        discreta.setId(1L);
        discreta.save();

        //instituição
        Campus campus1 = new Campus("Campus Quixadá");
        campus1.setId(0L);
        campus1.save();

        Log.i(TAG, SugarRecord.listAll(Sala.class).toString());
        Log.i(TAG, SugarRecord.listAll(Bloco.class).toString());
        Log.i(TAG, SugarRecord.listAll(Docente.class).toString());
        Log.i(TAG, SugarRecord.listAll(Disciplina.class).toString());
        Log.i(TAG, SugarRecord.listAll(Campus.class).toString());

    }

}
