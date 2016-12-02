package cainammello.qbeacon.protocolo;

import android.util.Log;

import cainammello.qbeacon.model.AbsObject;
import cainammello.qbeacon.model.Bloco;
import cainammello.qbeacon.model.Campus;
import cainammello.qbeacon.model.Disciplina;
import cainammello.qbeacon.model.Docente;
import cainammello.qbeacon.model.Historico;
import cainammello.qbeacon.model.Instituicao;
import cainammello.qbeacon.model.Sala;

/**
 * Created by cainammello on 11/3/16.
 */

//métodos para extrair as informações do pacote enviado pelo beacon
public class QBeaconProtocolo {

    private MessageSector[] messageSectors = new MessageSector[] {
            new MessageSector(2, Sala.class),
            new MessageSector(2, Bloco.class),
            new MessageSector(2, Disciplina.class),

            // Hora inicio
            new MessageSector(2, Integer.class), // ****************

            new MessageSector(4, Docente.class),
            new MessageSector(2, Instituicao.class),

            // Minuto inicio
            new MessageSector(2, Integer.class), // ****************

            // Aulas anterior e próxima
            new MessageSector(2, Campus.class),
            new MessageSector(2, Disciplina.class),
            new MessageSector(2, Disciplina.class),

            // Hora fim
            new MessageSector(2, Integer.class), // ****************

            new MessageSector(2, Historico.class),
            new MessageSector(2, Object.class),
            new MessageSector(2, Object.class),

            // Minuto fim
            new MessageSector(2, Integer.class), // ****************
    };

    public QBeaconProtocolo() {}

    public Integer getValueFrom(String msg, int position) {

        MessageSector sector = null;
        int index = 0;
        for (MessageSector m: messageSectors) {
            if (m.type == Integer.class) {
                position--;
                if(position < 1 ){
                    sector = m;
                    break;
                }
            }
            index += m.length;
        }

        String subst = msg.substring(index, index + sector.length);

        int value = Integer.parseInt(subst, 16);
        Log.i("DEBUG", "Str = " + subst + " : Value = " + Integer.parseInt(subst, 16));
        return value;
    }

    public <T> T getObjectFrom(Class<T> type, String msg, int position) {

        MessageSector sector = null;
        int index = 0;
        for (MessageSector m: messageSectors) {
            if (m.type == type) {
                position--;
                if(position < 1 ){
                    sector = m;
                    break;
                }
            }
            index += m.length;
        }

        String subst = msg.substring(index, index + sector.length);

        int key = Integer.parseInt(subst, 16);

        // Log.i("DEBUG", "Pegando chave de " + type.getName() + " com valor " + key);

        return AbsObject.getByKey(type, key);

    }

    private class MessageSector {

        public Class type;
        public int length;

        public <T> MessageSector(Class<T> type) {
            this(1, type);
        }

        public <T> MessageSector(int length, Class<T> type) {
            this.length = length;
            this.type = type;
        }

    }

}
