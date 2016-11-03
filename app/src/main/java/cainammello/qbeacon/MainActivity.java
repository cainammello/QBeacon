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
import cainammello.qbeacon.model.Disciplina;
import cainammello.qbeacon.model.Docente;
import cainammello.qbeacon.protocolo.QBeaconProtocolo;

public class MainActivity extends AppCompatActivity implements BeaconConsumer {

    protected static final String TAG = "DEBUG ";
    private BeaconManager beaconManager;

    @BindView (R.id.docente)
    TextView tvDocente;

    @BindView (R.id.disciplina)
    TextView tvDisciplina;

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

        /*

        Docente david = new Docente("David Sena", "ZSP3");
        david.setId(0L);
        david.save();

        Docente samy = new Docente("Samy Sá", "ZXP4");
        samy.setId(1L);
        samy.save();

        Disciplina fup = new Disciplina("Fundamentos Programação", "123");
        fup.setId(0L);
        fup.save();

        Disciplina discreta = new Disciplina("Matemática Discreta", "124");
        discreta.setId(1L);
        discreta.save();

        */

        Log.i(TAG, SugarRecord.listAll(Docente.class).toString());
        Log.i(TAG, SugarRecord.listAll(Disciplina.class).toString());

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

                    final Docente docente = QBeaconProtocolo.extrairDocente(b.getBluetoothName());
                    final Disciplina disciplina = QBeaconProtocolo.extrairDisciplina(b.getBluetoothName());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvDocente.setText("Beacon info docente: " + (docente != null? docente.toString(): "NULL"));
                            tvDisciplina.setText("Beacon info disciplina: " + (disciplina != null? disciplina.toString(): "NULL"));
                        }
                    });

                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {    }

    }

}
