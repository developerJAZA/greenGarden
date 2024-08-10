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

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_principal);

        Button volver = findViewById(R.id.btnVolver);
        Button estadisticas=findViewById(R.id.btnEstadistica);
        Button concejos=findViewById(R.id.btnConsejo);
        Button categoria=findViewById(R.id.btnCategoria);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Principal.this,MainActivity.class);
                startActivity(back);
            }
        });

        estadisticas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irEstadistica = new Intent(Principal.this,statistics.class);
                startActivity(irEstadistica);
            }
        });

        concejos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irConsejos = new Intent(Principal.this,Consejos.class);
                startActivity(irConsejos);
            }
        });

        categoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irCategorias = new Intent(Principal.this,Categorias.class);
                startActivity(irCategorias);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}