package com.example.parcial2;

import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "RecyclerAdapter";
    List<Encuesta> encuestaList;
    List<Encuesta> encuestaListAll;

    private RecyclerViewClickInterface recyclerViewClickInterface;

    public RecyclerAdapter(List<Encuesta> encuestaList, RecyclerViewClickInterface recyclerViewClickInterface) {
        this.encuestaList = encuestaList;
        this.encuestaListAll = new ArrayList<>(encuestaList);
        this.recyclerViewClickInterface = recyclerViewClickInterface;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.row_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tv_nombre.setText(encuestaList.get(position).getNombre());
        holder.tv_cedula.setText(encuestaList.get(position).getCedula());
        holder.tv_informacion.setText("Estrato: "+encuestaList.get(position).getEstrato()+"" +
                "  \nSalario: "+encuestaList.get(position).getSalario()+" \nNivel Educativo: "+encuestaList.get(position).getNivel_educativo()+" \nFecha: "+encuestaList.get(position).getFecha()+"");
    }

    @Override
    public int getItemCount() {
        return encuestaList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Encuesta> filteredList = new ArrayList<Encuesta>();

            if(charSequence.toString().isEmpty()){
                filteredList.addAll(encuestaListAll);
            }else{
                for(Encuesta e: encuestaListAll){
                    if(e.getCedula().toLowerCase().contains(charSequence.toString().toLowerCase())){
                        filteredList.add(e);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;

            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            encuestaList.clear();
            encuestaList.addAll((Collection<? extends Encuesta>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView tv_nombre, tv_cedula, tv_informacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            tv_nombre = itemView.findViewById(R.id.txt_nombre);
            tv_cedula = itemView.findViewById(R.id.txt_cedula);
            tv_informacion = itemView.findViewById(R.id.txt_informacion);

            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    Toast.makeText(view.getContext(), "Nombre: "+tv_nombre+" Cedula # "+tv_cedula.getText()+"", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Desliza a la izquierda para Eliminar", Toast.LENGTH_SHORT).show();
        }
    }


}















