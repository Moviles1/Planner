package com.example.lunaplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;

public class Registro extends AppCompatActivity {

    EditText NombreEt,CorreoEt,Contrasena,ConfirmarContrasenaET;
    Button RegistrarUsuario;
    TextView TengounaCuentatxt;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    String nombre = " ", correo = " ", password = " ", confirmarpassword = " ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
    }
}