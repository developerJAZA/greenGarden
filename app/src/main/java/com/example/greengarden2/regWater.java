package com.example.greengarden2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.greengarden2.modelos.Agua;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class regWater extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_reg_water);

        // Obtener referencias a los elementos de la interfaz de usuario
        Button botonRegresar = findViewById(R.id.botonRetroceso4);
        Button botonRegistrar = findViewById(R.id.btnRegistrarAgua);
        EditText volumen = findViewById(R.id.editTextVolumen);
        EditText precio = findViewById(R.id.editTextPrecioA);
        EditText mes = findViewById(R.id.editTextMesA);

        // Intent utilizado para regresar a la actividad ServiceRegister
        Intent intent1 = new Intent(this, Categorias.class);

        // Intent utilizado para navegar a la actividad Principal
        Intent intent2 = new Intent(this, Principal.class);

        // Configuración del botón "Regresar"
        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent1);
            }
        });

        // Configuración del botón "Registrar"
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar que los campos no estén vacíos
                if (!volumen.getText().toString().isEmpty() && !precio.getText().toString().isEmpty() && !mes.getText().toString().isEmpty()) {
                    String mesBuscado = mes.getText().toString();
                    boolean mesExiste = verificarMes(mesBuscado);
                    if (mesExiste) {
                        // El mes ya existe en el archivo
                        Toast.makeText(regWater.this, "El mes ya existe", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean datosGuardados = guardarDatos(volumen.getText().toString(), precio.getText().toString(), mes.getText().toString());
                        if (datosGuardados) {
                            // Se cambia de actividad
                            startActivity(intent1);
                        } else {
                            Toast.makeText(regWater.this, "Error al guardar el archivo",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Algunos campos están vacíos
                    Toast.makeText(regWater.this, "Los campos no pueden estar vacíos", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Verifica si un mes existe en el archivo "agua.txt".
     *
     * @param mesBuscado El mes a buscar.
     * @return true si el mes existe, false en caso contrario.
     */
    public boolean verificarMes(String mesBuscado) {
        File file = new File(getFilesDir(), "agua.txt");
        mesBuscado = mesBuscado.toLowerCase();
        try {
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String linea;

            while ((linea = bufferedReader.readLine()) != null) {
                String mes = linea.split(",")[2]; // Suponiendo que el mes está en la tercera columna separada por coma (,)
                if (mes.equalsIgnoreCase(mesBuscado)) {
                    bufferedReader.close();
                    return true; // El mes existe
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // El mes no existe
    }

    /**
     * Guarda los datos en el archivo "agua.txt" si el mes no existe.
     *
     * @param volumen El volumen a guardar.
     * @param precio  El precio a guardar.
     * @param mes     El mes a guardar.
     * @return true si se guarda correctamente, false en caso contrario.
     */
    public boolean guardarDatos(String volumen, String precio, String mes) {
        File file = new File(getFilesDir(), "agua.txt");
        mes = mes.toLowerCase();
        try {
            // Verificar si el archivo existe
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter writer = new FileWriter(file, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            // Crear una instancia de Agua y escribir los datos en el archivo
            Agua agua = new Agua(Float.parseFloat(volumen), Float.parseFloat(precio), mes);
            String linea = String.format(Locale.getDefault(), "%.2f,%.2f,%s", agua.getVolumen(), agua.getPrecio(), agua.getMes());
            bufferedWriter.write(linea);
            bufferedWriter.newLine();
            bufferedWriter.close();
            return true; // Los datos se guardaron correctamente
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Error al guardar los datos
    }
}