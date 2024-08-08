package com.example.lunaplanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;

public class Registro extends AppCompatActivity {

    EditText NombreEt, CorreoEt, Contrasena, ConfirmarContrasenaET;
    Button RegistrarUsuario;
    TextView TengounaCuentatxt;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    String nombre = " ", correo = " ", password = " ", confirmarpassword = " ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Registrar");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        NombreEt = findViewById(R.id.NombreEt);
        CorreoEt = findViewById(R.id.CorreoEt);
        Contrasena = findViewById(R.id.Contrasena);
        ConfirmarContrasenaET = findViewById(R.id.ConfirmarcontrasenaEt);
        RegistrarUsuario = findViewById(R.id.RegistrarUsuario);
        TengounaCuentatxt = findViewById(R.id.TengounaCuentatxt);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(Registro.this);
        progressDialog.setTitle("Espere por favor");
        progressDialog.setCanceledOnTouchOutside(false);

        RegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidarDatos(view);
            }
        });

        TengounaCuentatxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registro.this, Login.class));
            }
        });
    }

    private void ValidarDatos(View view) {
        nombre = NombreEt.getText().toString();
        correo = CorreoEt.getText().toString();
        password = Contrasena.getText().toString();
        confirmarpassword = ConfirmarContrasenaET.getText().toString();

        if (TextUtils.isEmpty(nombre)) {
            Snackbar.make(view, "Ingrese nombre", Snackbar.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Snackbar.make(view, "Ingrese correo válido", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {
            Snackbar.make(view, "Ingrese contraseña minimo 6 digitos", Snackbar.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmarpassword)) {
            Snackbar.make(view, "Confirme Contraseña", Snackbar.LENGTH_SHORT).show();
        } else if (!password.equals(confirmarpassword)) {
            Snackbar.make(view, "Las contraseñas no coinciden", Snackbar.LENGTH_SHORT).show();
        } else {
            CrearCuenta();
        }
    }

    private void CrearCuenta() {
        progressDialog.setMessage("Creando su Cuenta...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(correo, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        GuardarInformacion();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        mostrarToast(e.getMessage());
                    }
                });
    }

    private void GuardarInformacion() {
        progressDialog.setMessage("Guardando su Información");
        String uid = firebaseAuth.getUid();

        HashMap<String, String> Datos = new HashMap<>();
        Datos.put("uid", uid);
        Datos.put("correo", correo);
        Datos.put("nombres", nombre);
        Datos.put("password", password);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Usuarios");
        databaseReference.child(uid)
                .setValue(Datos)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        progressDialog.dismiss();
                        mostrarToast("Cuenta creada con éxito");
                        startActivity(new Intent(Registro.this, MenuPrincipal.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        mostrarToast(e.getMessage());
                    }
                });
    }

    private void mostrarToast(String mensaje) {
        // Muestra el Toast si no hay otro ya en cola
        Toast.makeText(Registro.this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
