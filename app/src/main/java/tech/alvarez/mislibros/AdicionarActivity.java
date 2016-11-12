package tech.alvarez.mislibros;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;

import io.realm.Realm;

public class AdicionarActivity extends AppCompatActivity {

    private Realm realm;

    private EditText tituloEditText;
    private EditText autorEditText;
    private RatingBar calificacionRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        realm = Realm.getDefaultInstance();

        tituloEditText = (EditText) findViewById(R.id.tituloEditText);
        autorEditText = (EditText) findViewById(R.id.autorEditText);
        calificacionRatingBar = (RatingBar) findViewById(R.id.calificacionRatingBar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
    }

    public void guardarLibro(View view) {

        String titulo = tituloEditText.getText().toString();
        String autor = autorEditText.getText().toString();
        float calificacion = calificacionRatingBar.getRating();

        // guardar
        realm.beginTransaction();
        Libro l = realm.createObject(Libro.class);
        l.setAutor(autor);
        l.setTitulo(titulo);
        l.setCalificacion(calificacion);
        realm.commitTransaction();


        finish();
    }
}
