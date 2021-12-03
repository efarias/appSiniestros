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

public class ActivityRegistrarse extends AppCompatActivity {

    private EditText nombreUsuario, contrasennaUsuario1, contrasennaUsuario2;
    private EditText correo, genero, fechaNacimiento;
    private FirebaseAuth mAuth;
    private FirebaseUser usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        nombreUsuario = findViewById(R.id.tvUserName);
        correo = findViewById(R.id.tvUserEmail);
        genero = findViewById(R.id.tvUserGenero);
        fechaNacimiento = findViewById(R.id.tvUserFechaNacimiento);
        contrasennaUsuario1 = findViewById(R.id.contraseñaUsuarioRegistro);
        contrasennaUsuario2 = findViewById(R.id.contraseñaUsuarioRegistro2);

        mAuth = FirebaseAuth.getInstance();
        usuarioActual = mAuth.getCurrentUser();

    }


    public void GuardarRegistroUsuario(View view) {

        if(contrasennaUsuario2.getText().toString().equals(contrasennaUsuario1.getText().toString())){
            Usuario usuario = new Usuario(correo.getText().toString(),nombreUsuario.getText().toString(),genero.getText().toString(),fechaNacimiento.getText().toString(),contrasennaUsuario1.getText().toString());

            mAuth.createUserWithEmailAndPassword(usuario.getCorreo(),usuario.getContrasenna()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        FirebaseUser usuarioNuevo = mAuth.getCurrentUser();
                        mensajeOk(usuarioNuevo.getUid());
                        startActivity(new Intent(getApplicationContext(),ActivityPrincipal.class));
                    } else {
                        mensajeError();
                    }
                }
            });
        }else{
            Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }


    }
    private void mensajeOk(String idUsuario){
        Toast.makeText(this, "Usuario nuevo creado. ID: "+idUsuario, Toast.LENGTH_SHORT).show();
    }

    private void mensajeError(){
        Toast.makeText(this, "No se pudo crear la cuenta", Toast.LENGTH_SHORT).show();
    }

    public void irAIniciarSesion(View view){
        Intent intento = new Intent(getApplicationContext(), ActivityPrincipal.class);
        startActivity(intento);
    }
}