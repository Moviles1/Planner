package com.example.lunaplanner.Detalle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.lunaplanner.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Detalle_Nota extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    Button Boton_Importante;

    TextView Id_nota_Detalle, Uid_Usuario_Detalle, Correo_usuario_Detalle, Titulo_Detalle, Descripcion_Detalle,
            Fecha_Registro_Detalle, Fecha_Nota_Detalle, Estado_Detalle;
    //Declarar los strings para almacenar los datos recuperados de actividad anterior
    String id_nota_R, uid_usuario_R, correo_usuario_R, fecha_registro_R, titulo_R, descripcion_R, fecha_R, estado_R;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_nota);

        // Configuración de la ActionBar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Actualizar Nota");
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        InicializarVistas();
        RecuperarDatos();
        SetearDatosRecuperados();
    }



    private void InicializarVistas() {
        Id_nota_Detalle = findViewById(R.id.Id_nota_Detalle);
        Uid_Usuario_Detalle = findViewById(R.id.Uid_Usuario_Detalle);
        Correo_usuario_Detalle = findViewById(R.id.Correo_usuario_Detalle);
        Titulo_Detalle = findViewById(R.id.Titulo_Detalle);
        Descripcion_Detalle = findViewById(R.id.Descripcion_Detalle);
        Fecha_Registro_Detalle = findViewById(R.id.Fecha_Registro_Detalle);
        Fecha_Nota_Detalle = findViewById(R.id.Fecha_Nota_Detalle);
        Estado_Detalle = findViewById(R.id.Estado_Detalle);

        Boton_Importante = findViewById(R.id.Boton_Importante);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
    }

    private void RecuperarDatos() {
        Bundle intent = getIntent().getExtras();

        id_nota_R = intent.getString("id_nota");
        uid_usuario_R = intent.getString("uid_usuario");
        correo_usuario_R = intent.getString("correo_usuario");
        fecha_registro_R = intent.getString("fecha_registro");
        titulo_R = intent.getString("titulo");
        descripcion_R = intent.getString("descripcion");
        fecha_R = intent.getString("fecha_nota");
        estado_R = intent.getString("estado");
    }

    private void SetearDatosRecuperados() {
        Id_nota_Detalle.setText(id_nota_R);
        Uid_Usuario_Detalle.setText(uid_usuario_R);
        Correo_usuario_Detalle.setText(correo_usuario_R);
        Fecha_Registro_Detalle.setText(fecha_registro_R);
        Titulo_Detalle.setText(titulo_R);
        Descripcion_Detalle.setText(descripcion_R);
        Fecha_Nota_Detalle.setText(fecha_R);
        Estado_Detalle.setText(estado_R);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}