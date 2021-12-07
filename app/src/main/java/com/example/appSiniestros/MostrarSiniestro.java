package com.example.appSiniestros;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class MostrarSiniestro extends AppCompatActivity {

    private Siniestro siniestro;
    private TextView titulo;
    private TextView fecha;
    private TextView nota;
    private ImageView foto;
    private Location ubicacion;

// Activity no implementada
// Posible implementacion para mostrar los detalles del Siniestro

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_noticia);

          siniestro = (Siniestro) getIntent().getSerializableExtra("noticia");
          titulo = findViewById(R.id.MostrarTituloNoticia);
          fecha = findViewById(R.id.tvFechaMostrar);
          nota = findViewById(R.id.MostrarTextoNoticia);
          foto = findViewById(R.id.MostrarFotoNoticia);
          //ubicacion = siniestro.getUbicacion();

        titulo.setText(siniestro.getTitulo());
        fecha.setText(siniestro.getFecha());
        nota.setText(siniestro.getNota());


        File imagenFile = new File(siniestro.getFoto());
        if(imagenFile.exists()){
            Bitmap imgBitmap = BitmapFactory.decodeFile(imagenFile.getAbsolutePath());
            foto.setImageBitmap(imgBitmap);
        }



    }

    public void MostrarMapa(View view) {
        Intent intent = new Intent(this,MapsSiniestro.class);
        intent.putExtra("ubicacion", ubicacion);
        startActivity(intent);
    }
}