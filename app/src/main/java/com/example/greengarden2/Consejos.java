package com.example.greengarden2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Consejos extends AppCompatActivity {
    private List<String> consejosList;
    private TextView consejos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consejos);

        Button botonRetroceso =findViewById(R.id.botonRetroceso4);

        botonRetroceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Consejos.this,Principal.class);
                startActivity(back);
            }
        });

        consejos = findViewById(R.id.textConsejos);
        consejosList = new ArrayList<>();

        // Leer los consejos del archivo
        leerConsejos();

        //Mostrar consejos aleatorios
        mostrarConsejoAleatorio();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void leerConsejos() {
        File file = new File(getFilesDir(), "consejos.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                consejosList.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void mostrarConsejoAleatorio() {
        if (consejosList.isEmpty()) {
            consejos.setText("No hay consejos disponibles.");
            return;
        }

        Random random = new Random();
        int index = random.nextInt(consejosList.size());
        String consejo = consejosList.get(index);
        consejos.setText(consejo);
    }

}