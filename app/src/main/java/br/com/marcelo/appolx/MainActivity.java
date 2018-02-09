package br.com.marcelo.appolx;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import java.util.List;

import br.com.marcelo.appolx.adapters.AnuncioAdapter;
import io.objectbox.Box;

public class MainActivity extends AppCompatActivity {
    List<Anuncio> anuncios;
    Box<Anuncio> anuncioBox;
    private RecyclerView rvAnuncios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvAnuncios =  findViewById(R.id.rv_anuncios);
        anuncioBox =  ((App)getApplication()).getBoxStore().boxFor(Anuncio.class);

    }


    @Override
    protected void onResume() {
        super.onResume();
        anuncios = getListagemAnuncio();
        AnuncioAdapter anuncioAdapter = new AnuncioAdapter(this,anuncios,anuncioBox);
        rvAnuncios.setAdapter(anuncioAdapter);
        rvAnuncios.setLayoutManager(new LinearLayoutManager(this));
    }

    // actionbar options menu


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void cadastrarAnuncio(View view) {
        startActivity(new Intent(this, AnuncioFormActivity.class));
    }

    private List<Anuncio> getListagemAnuncio(){

       return  anuncioBox.getAll();

    }


}
