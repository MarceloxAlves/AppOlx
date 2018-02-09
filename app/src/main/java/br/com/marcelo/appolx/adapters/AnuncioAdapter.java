package br.com.marcelo.appolx.adapters;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import br.com.marcelo.appolx.Anuncio;
import br.com.marcelo.appolx.R;
import io.objectbox.Box;

/**
 * Created by Marcelo on 07/02/2018.
 */

public class AnuncioAdapter extends RecyclerView.Adapter<AnuncioAdapter.ViewHolder> {

    private final Context context;
    private  List<Anuncio> anuncios;
    private  Box<Anuncio> anuncioBox;


    public AnuncioAdapter(Context context, List<Anuncio> anuncios, Box<Anuncio> anuncioBox) {
        this.context = context;
        this.anuncios =  anuncios;
        this.anuncioBox = anuncioBox;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.view_holder, parent, false);


        ViewHolder  viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Anuncio anuncio = anuncios.get(position);

        holder.txtTitulo.setText(anuncio.getTitulo());
        holder.txtValor.setText(String.valueOf(anuncio.getValor()));
        holder.txtData.setText(anuncio.getData());

        setupLongClick(holder,anuncio,position);

    }

    private void setupLongClick(ViewHolder holder, Anuncio anuncio, int position) {
        holder.itemView.setOnLongClickListener(view -> {
            PopupMenu popupMenu = new PopupMenu(context,view);
            popupMenu.getMenuInflater().inflate(R.menu.menu_popup_anuncio,popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){
                    case R.id.item_menu_deletar:
                        AlertDialog.Builder builder =  new AlertDialog.Builder(context);
                        builder.setTitle("Deletar");
                        builder.setMessage("Deseja deletar "+ anuncio.getTitulo() +"?");
                        builder.setPositiveButton("SIM", (dialog, i) -> {
                            anuncios.remove(anuncio);
                            anuncioBox.remove(anuncio);
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position,getItemCount());
                            Toast.makeText(context, "item removido", Toast.LENGTH_SHORT).show();
                        });
                        builder.setNegativeButton("NAO", (dialog, i) -> popupMenu.dismiss());

                        builder.create().show();
                        break;
                    case R.id.item_menu_editar:
                        
                        break;
                }
                return true;
            });
            popupMenu.show();
            return true;
        });
    }

    @Override
    public int getItemCount() {
       return this.anuncios.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtTitulo;
        public TextView txtValor;
        public TextView txtData;

        public TextView tvAlunoEmail;
        public ViewHolder(View itemView) {

            super(itemView);

            txtTitulo = (TextView)itemView.findViewById(R.id.txt_anuncio_titulo);
            txtValor = (TextView)itemView.findViewById(R.id.txt_anuncio_valor);
            txtData = (TextView)itemView.findViewById(R.id.txt_anuncio_data);

        }

    }
}
