package br.com.marcelo.appolx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import br.com.marcelo.appolx.utils.Validator;
import io.objectbox.Box;

public class AnuncioFormActivity extends AppCompatActivity {
    EditText   txtDescricao;
    EditText   txtValor;
    EditText   txtLocal;
    Box<Anuncio> anuncioBox;
    Anuncio anuncioEdit;
    boolean editando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_anuncio_form);
        txtDescricao = findViewById(R.id.txt_anuncio_descricao);
        txtValor = findViewById(R.id.txt_anuncio_valor);
        txtLocal = findViewById(R.id.txt_anuncio_local);
        Intent intent = getIntent();
        this.anuncioBox = ((App)getApplication()).getBoxStore().boxFor(Anuncio.class);
        long id =  intent.getLongExtra("anuncio",0);
        if(id > 0){
            editando =  true;
            anuncioEdit  = anuncioBox.get(id);
            txtDescricao.setText(anuncioEdit.getTitulo());
            txtValor.setText(String.valueOf(anuncioEdit.getValor()));
            txtLocal.setText(anuncioEdit.getLocal());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar_form,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            if(item.getItemId() == R.id.menu_item_salvar) {
                if (editando){
                    anuncioEdit.setTitulo(Validator.validade(txtDescricao));
                    anuncioEdit.setLocal(Validator.validade(txtLocal));
                    anuncioEdit.setValor(Double.valueOf(Validator.validade(txtValor)));
                }else{
                    anuncioEdit = new Anuncio(Validator.validade(txtDescricao),
                            Double.valueOf(Validator.validade(txtValor)),
                            Validator.validade(txtLocal));
                }

                if (anuncioBox.put(anuncioEdit) > 0) {
                    Toast.makeText(this, "Registro Salvo com Sucesso", Toast.LENGTH_SHORT).show();
                    this.finish();
                }
            }
        } catch (IllegalArgumentException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
