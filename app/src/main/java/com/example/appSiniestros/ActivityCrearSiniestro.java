package com.example.appSiniestros;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ActivityCrearSiniestro extends AppCompatActivity {


    private ImageView foto;
    private EditText titulo, nota, fecha;
    private TextView latitud, longitud;
    private boolean fotoTomada;
    private String rutaImagen;
    private String ubicacion;
    private String lat, lon;
    private LocationManager locManager;
    private Location location;

    FirebaseUser usuarioActual;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_siniestro);

        foto = findViewById(R.id.MostrarFotoNoticia);
        titulo = findViewById(R.id.tituloNoticia);
        nota = findViewById(R.id.textoNoticia);
        fecha = findViewById(R.id.tdFecha);
        latitud = findViewById(R.id.tvLatitud);
        longitud = findViewById(R.id.tvLongitud);
        fotoTomada = false;

        mAuth = FirebaseAuth.getInstance();
        usuarioActual = mAuth.getCurrentUser();

        ubicacion();
        latitud.setText(String.format("Latitud: %.4f", location.getLatitude()));
        longitud.setText(String.format("Longitud: %.4f", location.getLongitude()));

        //obtener fecha y hora del momento actual
        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String hoy = formato.format(currentTime);
        fecha.setText(hoy);




    }

    public Location ubicacion(){
        //ubicacion

        //Revisa si permisos de ubicación están permitidos
        //Falta implementar un procedimiento en caso de que el gps esté desactivado

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ActivityCrearSiniestro.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }else{
            locManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        }

        return location;
    }

    public void tomarFoto(View view) {

        Intent intento = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File imagenArchivo = null;

        try{
            imagenArchivo = crearImagen();

        }catch(IOException ex){
            Log.e("Error", ex.toString());
        }

        if(imagenArchivo != null){
            Uri fotoUri = FileProvider.getUriForFile(this, "com.example.appSiniestros.fileprovider", imagenArchivo);
            intento.putExtra(MediaStore.EXTRA_OUTPUT,fotoUri);
            startActivityForResult(intento, 0);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

           // Bitmap b = (Bitmap) data.getExtras().get("data");
            Bitmap b = BitmapFactory.decodeFile(rutaImagen);
            fotoTomada = true;
            foto.setImageBitmap(b);
        }
    }

    public void guardarNoticia(View view) {

        if (fotoTomada && titulo.getText().length() != 0 && nota.getText().length() != 0) {

            Siniestro siniestro = new Siniestro(titulo.getText().toString(),nota.getText().toString(),rutaImagen,fecha.getText().toString(),location,usuarioActual);
            titulo.setText("");
            nota.setText("");
            //foto.setImageBitmap(null);
            Toast.makeText(this, "Noticia Guardada", Toast.LENGTH_SHORT).show();

            //pruebas
            System.out.println(ubicacion);
//            String t = noticias.get(titulo.getVerticalScrollbarPosition()).getTitulo();
//            Toast.makeText(this, t, Toast.LENGTH_SHORT).show();
//            String p = noticias.get(nota.getVerticalScrollbarPosition()).getNota();
//            Toast.makeText(this, p, Toast.LENGTH_SHORT).show();
//            Toast.makeText(this,rutaImagen,Toast.LENGTH_SHORT).show();

            fotoTomada = false;
            Intent intento = new Intent(this, MainActivity.class);
            startActivity(intento);
        } else {
            Toast.makeText(this, "error, faltan datos por ingresar", Toast.LENGTH_SHORT).show();
        }

    }

    private File crearImagen() throws IOException {
        String nombreImagen = "foto_";
        File directorio = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagen = File.createTempFile(nombreImagen, ".jpg", directorio);

        rutaImagen = imagen.getAbsolutePath();
        return imagen;
    }

    public void AbrirMapa(View view) {
        Intent intent = new Intent(this,MapsActivity.class);
        startActivity(intent);

    }
}