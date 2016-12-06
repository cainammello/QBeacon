package cainammello.qbeacon.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.orm.SugarRecord;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cainammello.qbeacon.R;
import cainammello.qbeacon.model.Teste;

public class MainActivity extends AppCompatActivity {

    private static final int RESULT_TEST_OK = 0;

    @BindView(R.id.distance)
    EditText etDistance;

    @BindView(R.id.result)
    TextView tvResult;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    public void beginTest(View view) {
        try {
            Intent intent = new Intent(this, BeaconActivity.class);
            intent.putExtra("DISTANCE", Double.valueOf(etDistance.getText().toString()));
            startActivityForResult(intent, RESULT_TEST_OK);
        } catch (Exception e) {
            Toast.makeText(this, "O campo distância não pode ficar vazio!", Toast.LENGTH_LONG).show();
        }
    }

    public void removeTests(View view) {
        SugarRecord.deleteAll(Teste.class);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        List<Teste> testes = SugarRecord.find(Teste.class, null, null);
        String out = "";
        for(Teste t: testes) {
            out += String.format("Teste: %d, Dist: %.3f m, scan: %.3f s, info %.3f s\n", t.getId().intValue(), t.getDistance(), (double)t.getScanInterval()/1000.0, (double)t.getLoadInfoInterval()/1000.0);
        }
        tvResult.setText(out);

        Toast.makeText(this, "Teste terminado!", Toast.LENGTH_LONG).show();

    }
}
