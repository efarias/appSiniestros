package com.example.appSiniestros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ActivityListaDeNoticias extends AppCompatActivity {

    private ArrayList<Siniestro> siniestros;
    private RecyclerView recycler;
    private AdaptadorNoticia adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_noticias);

        siniestros = (ArrayList<Siniestro>) getIntent().getSerializableExtra("noticias");
        recycler = findViewById(R.id.recyclerListaNoticias);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        adaptador = new AdaptadorNoticia(siniestros);

        recycler.setAdapter(adaptador);

    }
}