package com.example.appSiniestros;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String[] permisos = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};
    private boolean hayPermiso;
    private ImageView fotoPrincipal;
    private FirebaseAuth mAuth;
    private FirebaseUser usuarioActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        usuarioActual = mAuth.getCurrentUser();

        hayPermiso = false;
        fotoPrincipal = findViewById(R.id.imagenPrincipal);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            requestPermissions(permisos, 100);
        }
    }
    private void comprobarPermisos(){

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permisos OK", Toast.LENGTH_SHORT).show();
                hayPermiso = true;
            }else {
                solicitarPermisos();
            }
        }
    }

    private void solicitarPermisos(){
        new AlertDialog.Builder(this)
                .setTitle("Se requiere permisos de cámara y de escritura")
                .setMessage("Esta aplicación hace uso de la cámara para tomar una foto. Por favor " +
                        "otorgue este permiso para que la aplicación funcione correctamente")
                .setPositiveButton("Ver Permiso", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        requestPermissions(permisos, 100);
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create().show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 100) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                hayPermiso = true;
            } else {
                Toast.makeText(this, "Se requiere permisos de cámara para funcionar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void crearNoticia (View view){
        comprobarPermisos();
        if (hayPermiso){
            startActivity(new Intent(this,ActivityCrearSiniestro.class));
        }
    }

    public void ListadoDeNoticias(View view){

        startActivity(new Intent(this,ActivityListaDeNoticias.class));
    }


    public void CerrarSesion(View view) {
        mAuth.signOut();
        startActivity(new Intent(getApplicationContext(), ActivityPrincipal.class));
    }

}
