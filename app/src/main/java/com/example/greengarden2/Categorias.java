package com.example.greengarden2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Categorias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categorias);

        Button volver =findViewById(R.id.botonRetroceso4);
        Button regAgua =findViewById(R.id.buttonAgua);
        Button regAbono =findViewById(R.id.buttonAbono);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Categorias.this,Principal.class);
                startActivity(back);
            }
        });

        regAgua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irRegWater = new Intent(Categorias.this,regWater.class);
                startActivity(irRegWater);
            }
        });

        regAbono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irRegAbono = new Intent(Categorias.this,RegAbono.class);
                startActivity(irRegAbono);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}