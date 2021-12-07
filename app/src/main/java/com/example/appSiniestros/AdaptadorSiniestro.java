package com.example.appSiniestros;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class AdaptadorSiniestro extends FirebaseRecyclerAdapter<Siniestro, AdaptadorSiniestro.SiniestroViewholder>{
    public AdaptadorSiniestro(
            @NonNull FirebaseRecyclerOptions<Siniestro> options){
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull SiniestroViewholder holder, int position, @NonNull Siniestro model)
    {
        holder.titulo.setText(model.getTitulo());
        holder.fecha.setText(model.getFecha());
        System.out.println(model.getFecha());
        //Mostrar foto
        File imagenFile = new File(model.getFoto());
        System.out.println(model.getFoto());
        if(imagenFile.exists()){
            Bitmap imgBitmap = BitmapFactory.decodeFile(imagenFile.getAbsolutePath());
            holder.foto.setImageBitmap(imgBitmap);
        }
        //Abrir Mapa
        holder.mapaSiniestro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapsSiniestro.class);
                intent.putExtra("latitud", model.getLatitud());
                intent.putExtra("longitud", model.getLongitud());
                view.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public AdaptadorSiniestro.SiniestroViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_siniestros, parent, false);
        return new AdaptadorSiniestro.SiniestroViewholder(view);
    }

    class SiniestroViewholder extends RecyclerView.ViewHolder {
        TextView titulo, fecha;
        ImageView foto;
        Button mapaSiniestro;

        public SiniestroViewholder(@NonNull View itemView){
            super(itemView);

            titulo = itemView.findViewById(R.id.tvTituloSiniestro);
            fecha = itemView.findViewById(R.id.tvFecha);
            foto = itemView.findViewById(R.id.fotoSiniestro);
            mapaSiniestro = itemView.findViewById(R.id.btnMapaSiniestro);
        }
    }
}

/*
public class AdaptadorSiniestro extends FirebaseRecyclerAdapter{

    //private ArrayList<Siniestro> siniestros;
    FirebaseDatabase database;
    FirebaseUser usuarioActual;

    public AdaptadorSiniestro(ArrayList<Siniestro> siniestros){

        //this.siniestros = siniestros;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.plantilla_noticias, parent, false );

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        int p = position;
        Siniestro siniestro = new Siniestro(siniestros.get(p).getTitulo(), siniestros.get(p).getNota(), siniestros.get(p).getFoto(), siniestros.get(p).getFecha(), siniestros.get(p).getUbicacion(), siniestros.get(p).getUsuario(), siniestros.get(p).getId());

        holder.titulo.setText(siniestros.get(position).getTitulo());
        holder.fecha.setText(siniestros.get(position).getFecha());
        holder.mostrarNoticia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MostrarNoticia.class);
                intent.putExtra("noticia", siniestro);
                view.getContext().startActivity(intent);

            }
        });

        File imagenFile = new File(siniestros.get(position).getFoto());
        if(imagenFile.exists()){
            Bitmap imgBitmap = BitmapFactory.decodeFile(imagenFile.getAbsolutePath());
            holder.foto.setImageBitmap(imgBitmap);
        }

        //holder.foto.setImageResource(noticias.get(position).getFoto());

    }

    @Override
    public int getItemCount() {

        return siniestros.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView titulo;
        private TextView fecha;
        private ImageView foto;
        private Button mostrarNoticia;

        public ViewHolder(View itemView){
            super(itemView);

            titulo = itemView.findViewById(R.id.TituloNoticia);
            foto = itemView.findViewById(R.id.fotoNoticia);
            fecha = itemView.findViewById(R.id.tvFecha);
            mostrarNoticia = itemView.findViewById(R.id.btnVerNoticias);
            
        }
    }
}
*/
