package com.example.appSiniestros;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ActivityListaDeNoticias extends AppCompatActivity {

    private RecyclerView recycler;
    private AdaptadorSiniestro adaptador;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    FirebaseUser usuarioActual;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_noticias);
        mAuth = FirebaseAuth.getInstance();
        usuarioActual = mAuth.getCurrentUser();

        reference = FirebaseDatabase.getInstance().getReference("Siniestros").child(usuarioActual.getUid());

        recycler = findViewById(R.id.recyclerListaNoticias);
        recycler.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Siniestro> options = new FirebaseRecyclerOptions.Builder<Siniestro>().setQuery(reference, Siniestro.class).build();

        adaptador = new AdaptadorSiniestro(options);

        recycler.setAdapter(adaptador);
    }

        @Override
        protected void onStart(){
            super.onStart();
            adaptador.startListening();
        }

        @Override
        protected void onStop(){
            super.onStop();
            adaptador.stopListening();
        }

}
