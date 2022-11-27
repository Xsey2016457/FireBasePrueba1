package com.example.firebaseprueba;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainCrear extends AppCompatActivity {
    Button btn_add;
    EditText nombre, edad, color;
    private FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_crear);

        this.setTitle("Crear Mascota");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFirestore = FirebaseFirestore.getInstance();

        nombre = findViewById(R.id.et_nombre);
        edad = findViewById(R.id.et_edad);
        color = findViewById(R.id.et_color);
        btn_add = findViewById(R.id.btn_añadir);
        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String n = nombre.getText().toString();
                String e = edad.getText().toString();
                String c = color.getText().toString();

                if (n.isEmpty() && e.isEmpty() && c.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingresar los Datos", Toast.LENGTH_SHORT).show();
                }else{
                    postPet(n, e, c);
                }
            }
        });
    }
    private void postPet(String n, String e, String c){
        Map<String, Object> map = new HashMap<>();
        map.put("nombre",nombre);
        map.put("edad", edad);
        map.put("color", color);

        mFirestore.collection("mascotas").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
            @Override
            public void onSuccess(DocumentReference documentReference){
                Toast.makeText(getApplicationContext(),"Creado Exitosamente",Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener(){
            @Override
            public void onFailure(@NonNull Exception e){
                Toast.makeText(getApplicationContext(), "Error al Ingresar", Toast.LENGTH_SHORT).show();
            }
        });
        public boolean onSupportNavigateUp(){
            onBackPressed();
            return false;
        }
    }
}