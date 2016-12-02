package cainammello.qbeacon.beacon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.util.Log;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

/**
 * Created by felipe on 22/11/16.
 */
public class BeaconFinder implements BeaconConsumer {

    protected static final String TAG = "DEBUG";

    private BeaconManager beaconManager;
    private BeaconFinderListener listener;
    private Activity activity;

    private String lastUUIDBeacons = "";

    public void start(Activity activity) {
        this.activity = activity;
        beaconManager = BeaconManager.getInstanceForApplication(activity);
        //informar para a aplicação qual tipo de beacon ela vai escanear (no caso Ibeacons)
        beaconManager.getBeaconParsers().add(new BeaconParser(). setBeaconLayout("m:0-3=4c000215,i:4-19,i:20-21,i:22-23,p:24-24"));

        beaconManager.bind(this);
    }
 
    public void stop() {
        beaconManager.unbind(this);
    }

    private void onBeaconReceived(Beacon beacon) {

        String uuid = beacon.getId1().toString().replace("-", ""); //.replaceAll("(\\d{6})(\\d{2})", "$1");

        if(true || !lastUUIDBeacons.equals(uuid)) {
            //lastUUIDBeacons = uuid;

            Log.i("DEBUG", "Rebendo Beacon: " + uuid);

            if(listener != null)
                listener.onBeaconFinded(beacon, uuid);

        }

    }

    public void setListener(BeaconFinderListener listener) {
        this.listener = listener;
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
                    while(b != null && listener != null) {
                        onBeaconReceived(b);
                        b = beacons.iterator().next();
                    }

                }
            }
        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {    }

    }

    @Override
    public Context getApplicationContext() {
        return activity.getApplicationContext();
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {
        activity.unbindService(serviceConnection);
    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return activity.bindService(intent, serviceConnection, i);
    }

    public interface BeaconFinderListener {
        void onBeaconFinded(Beacon beacon, String uuid);
    }

}
