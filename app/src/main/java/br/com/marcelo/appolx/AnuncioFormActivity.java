package br.com.marcelo.appolx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import io.objectbox.Box;
import io.objectbox.BoxStore;

public class AnuncioFormActivity extends AppCompatActivity {
    EditText   txtDescricao;
    EditText   txtValor;
    EditText   txtLocal;
    Box<Anuncio> anuncioBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_anuncio_form);
        txtDescricao = findViewById(R.id.txt_anuncio_descricao);
        txtValor = findViewById(R.id.txt_anuncio_valor);
        txtLocal = findViewById(R.id.txt_anuncio_local);

        this.anuncioBox = ((App)getApplication()).getBoxStore().boxFor(Anuncio.class);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar_form,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_item_salvar) {
            Anuncio anuncio = new Anuncio(txtDescricao.getText().toString(),
                    Double.valueOf(txtValor.getText().toString()),
                    txtLocal.getText().toString());
            if (anuncioBox.put(anuncio) > 0) {
                Toast.makeText(this, "Registro Salvo com Sucesso", Toast.LENGTH_SHORT).show();
                this.finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
