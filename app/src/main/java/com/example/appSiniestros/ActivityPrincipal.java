package com.example.appSiniestros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class ActivityPrincipal extends AppCompatActivity {


    private EditText correo, contrasennaUsuario;


    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        correo = findViewById(R.id.nombreUsuario);
        contrasennaUsuario = findViewById(R.id.contraseñaUsuario);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioActual = mAuth.getCurrentUser();

        if(usuarioActual != null){
            Toast.makeText(this, "Usuario ID: "+usuarioActual.getUid()+ " / Nombre: "+usuarioActual.getDisplayName(), Toast.LENGTH_SHORT).show();
        }
    }


    public void IniciarSesion(View view){
        mAuth.signInWithEmailAndPassword(correo.getText().toString(), contrasennaUsuario.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser usuario = mAuth.getCurrentUser();

                    Toast.makeText(ActivityPrincipal.this, "Usuario: "+usuario.getUid(), Toast.LENGTH_SHORT).show();

                    Intent intento = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intento);

                } else {
                    Toast.makeText(ActivityPrincipal.this, "No fue posible iniciar sesión con los datos ingresados", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void irRegistroUsuario(View view) {
        startActivity(new Intent(getApplicationContext(),ActivityRegistrarse.class));
    }
}



        /*public void Registrarse (View view){

        Intent intento = new Intent(this, ActivityRegistrarse.class);
        intento.putExtra("usuarios",usuarios);
        startActivity(intento);

        }*/
