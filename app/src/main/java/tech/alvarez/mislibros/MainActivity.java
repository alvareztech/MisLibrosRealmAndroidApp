package tech.alvarez.mislibros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import tech.alvarez.mislibros.adapters.LibrosAdapter;
import tech.alvarez.mislibros.adapters.OnItemClick;

public class MainActivity extends AppCompatActivity implements OnItemClick {

    private Realm realm;
    private RecyclerView librosRecyclerView;
    private LibrosAdapter librosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        librosRecyclerView = (RecyclerView) findViewById(R.id.librosRecyclerView);
        librosRecyclerView.setHasFixedSize(true);
        librosRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i("MIAPP", "onStart");

        actualizarDatos();






        /*
        for (Libro l : libros) {
            Log.i("MIAPP",  ">" + l.getTitulo() + " " + l.getAutor() + " " + l.getCalificacion());
        }*/
    }

    private void actualizarDatos() {
        RealmResults<Libro> libros = realm.where(Libro.class).beginsWith("titulo", "li").findAll();
        libros = libros.sort("calificacion", Sort.DESCENDING);

        librosAdapter = new LibrosAdapter(libros, this);
        librosRecyclerView.setAdapter(librosAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        realm.close();
    }

    public void abrirPantallaAdicionar(View view) {
        Intent intent = new Intent(this, AdicionarActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(Libro libro) {

        realm.beginTransaction();
        libro.setAutor("Paulo Coelho");
        realm.commitTransaction();

        actualizarDatos();
    }
}
