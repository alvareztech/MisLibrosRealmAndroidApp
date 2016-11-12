package tech.alvarez.mislibros.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import io.realm.RealmResults;
import tech.alvarez.mislibros.Libro;
import tech.alvarez.mislibros.R;

public class LibrosAdapter extends RecyclerView.Adapter<LibrosAdapter.ViewHolder> {

    private RealmResults<Libro> dataset;
    private OnItemClick  onItemClick;

    public LibrosAdapter(RealmResults<Libro> dataset, OnItemClick onItemClick) {
        this.dataset = dataset;
        this.onItemClick = onItemClick;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Libro l = dataset.get(position);

        holder.tituloTextView.setText(l.getTitulo());
        holder.autorTextView.setText(l.getAutor());
        holder.calificacionRatingBar.setRating(l.getCalificacion());

        holder.setOnItemClick(onItemClick, l);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tituloTextView;
        TextView autorTextView;
        RatingBar calificacionRatingBar;

        public ViewHolder(View itemView) {
            super(itemView);

            tituloTextView = (TextView) itemView.findViewById(R.id.tituloTextView);
            autorTextView = (TextView) itemView.findViewById(R.id.autorTextView);
            calificacionRatingBar = (RatingBar) itemView.findViewById(R.id.calificacionRatingBar);
        }

        public void setOnItemClick(final OnItemClick onItemClick, final Libro l) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onItemClick(l);
                }
            });
        }
    }
}
