package com.example.myformulario;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    usermodel usmodel;
    int MaxId = 0;

    //    FirebaseDatabase database;
    DatabaseReference mRootReference;
    EditText edit_nombre, edit_adress,edit_adress2,edit_adress3,edit_adress4,edit_adress5;
    Button btn_guardar,btn_mostrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_guardar = findViewById(R.id.btn_guardar);
        btn_mostrar = findViewById(R.id.btn_mostrar);
        edit_nombre = findViewById(R.id.ed_nombre);
        edit_adress = findViewById(R.id.ed_adress);
        edit_adress2 = findViewById(R.id.ed_adress2);
        edit_adress3 = findViewById(R.id.ed_adress3);
        edit_adress4 = findViewById(R.id.ed_adress4);
        edit_adress5 = findViewById(R.id.ed_adress5);

        usmodel = new usermodel();


        mRootReference = FirebaseDatabase.getInstance().getReference().child("user");
        mRootReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @org.jetbrains.annotations.NotNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    MaxId = (int) snapshot.getChildrenCount();
                }else{

                }

            }

            @Override
            public void onCancelled(@NonNull @org.jetbrains.annotations.NotNull DatabaseError error) {

            }
        });

//        btn_mostrar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, InformacionActivity.class));
//            }
//        });

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usmodel.setNombre(edit_nombre.getText().toString().trim());
                usmodel.setAdress(edit_adress.getText().toString().trim());
                usmodel.setAdress_2(edit_adress2.getText().toString().trim());
                usmodel.setAdress_3(edit_adress3.getText().toString().trim());
                usmodel.setAdress_4(edit_adress4.getText().toString().trim());
                usmodel.setAdress_5(edit_adress5.getText().toString().trim());

                mRootReference.child(String.valueOf(MaxId+1)).setValue(usmodel);
                Toast.makeText(MainActivity.this, "upload data", Toast.LENGTH_SHORT).show();



                /*String nombre = edit_nombre.getText().toString();
                mRootReference.child("Usuario").setValue(nombre);
                String adress = edit_adress.getText().toString();
                mRootReference.child("Usuario").setValue(adress);
                String adress_2 = edit_adress2.getText().toString();
                mRootReference.child("Usuario").setValue(adress_2);
                String adress_3 = edit_adress3.getText().toString();
                mRootReference.child("Usuario").setValue(adress_3);
                String adress_4 = edit_adress4.getText().toString();
                mRootReference.child("Usuario").setValue(adress_4);
                String adress_5 = edit_adress5.getText().toString();
                mRootReference.child("Usuario").setValue(adress_5);*/



            }
        });

    }


//    private void cargarDatosFirebases(String nombre, String adress, String adress_2, String adress_3, String adress_4, String adress_5) {
//
//        Map<String, Object> datosUsarios = new HashMap<>();
//        datosUsarios.put("nombre", nombre);
//        datosUsarios.put("adress", adress);
//        datosUsarios.put("adress_2", adress_2);
//        datosUsarios.put("adress_3", adress_3);
//        datosUsarios.put("adress_4", adress_4);
//        datosUsarios.put("adress_5", adress_5);
//
//
//        mRootReference.child("Usario").push().setValue(datosUsarios);
//    }


}