package cainammello.qbeacon.protocolo;
import android.util.Log;
import com.orm.SugarRecord;
import cainammello.qbeacon.model.Bloco;
import cainammello.qbeacon.model.Campus;
import cainammello.qbeacon.model.Disciplina;
import cainammello.qbeacon.model.Docente;
import cainammello.qbeacon.model.Sala;

/**
 * Created by cainammello on 11/3/16.
 */

//métodos para extrair as informações do pacote enviado pelo beacon
public class QBeaconProtocolo {

    private QBeaconProtocolo() {}

    public static Sala extrairSala(String mensagem) {

        //coloca em uma string o byte (id) referente a sala
        String msg = mensagem.substring(0);
        //tranforma o byte (id) em um inteiro
        int byte1 = Character.getNumericValue(msg.charAt(0));

        int value = byte1;
        Log.i("DEBUG", "Find sala " + value);

        //retorna do banco o valor referente ao id recebido
        return SugarRecord.findById(Sala.class, value);

    }

    public static Bloco extrairBloco(String mensagem) {

        String msg = mensagem.substring(1);
        int byte1 = Character.getNumericValue(msg.charAt(0));

        int value = byte1;
        Log.i("DEBUG", "Find bloco " + value);

        return SugarRecord.findById(Bloco.class, value);

    }

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

    public static Campus extrairCampus(String mensagem) {

        String msg = mensagem.substring(5);
        int byte1 = Character.getNumericValue(msg.charAt(0));

        int value = byte1;
        Log.i("DEBUG", "Find campus " + value);

        return SugarRecord.findById(Campus.class, value);

    }

}
