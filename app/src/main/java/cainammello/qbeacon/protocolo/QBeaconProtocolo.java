package cainammello.qbeacon.protocolo;

import android.util.Log;

import com.orm.SugarRecord;

import cainammello.qbeacon.model.Disciplina;
import cainammello.qbeacon.model.Docente;

/**
 * Created by cainammello on 11/3/16.
 */
public class QBeaconProtocolo {

    private QBeaconProtocolo() {}

    public static Docente extrairDocente(String mensagem) {

        String msg = mensagem.substring(2);
        int byte1 = Character.getNumericValue(msg.charAt(0));
        int byte2 = Character.getNumericValue(msg.charAt(1));

        int value = byte1 + 256 * byte2;

        Log.i("DEBUG", "Find docente " + value);

        return SugarRecord.findById(Docente.class, value);

    }

    public static Disciplina extrairDisciplina(String mensagem) {

        String msg = mensagem.substring(4);
        int byte1 = Character.getNumericValue(msg.charAt(0));

        int value = byte1;
        Log.i("DEBUG", "Find disciplina " + value);

        return SugarRecord.findById(Disciplina.class, value);

    }

}
