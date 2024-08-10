package com.example.greengarden2;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        // Crear archivo para usuarios y escribir los datos
        File file1 = new File(getFilesDir(), "datos.txt");
        try {
            FileWriter writer = new FileWriter(file1);
            writer.append("root,root@root.com,toor,TOOR\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        /*
        // Crear archivo para registro de agua
        File file2 = new File(getFilesDir(), "agua.txt");

        try {
            FileWriter writer = new FileWriter(file2);
            writer.append("15,150000,enero\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        /*
        // Crear archivo para registro de electricidad
        File file3 = new File(getFilesDir(), "electricidad.txt");
        try {
            FileWriter writer = new FileWriter(file3);
            writer.append("15,150000,enero\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
        // Crear archivo para registro de consejos
        File file4 = new File(getFilesDir(), "consejos.txt");
        try {
            FileWriter writer = new FileWriter(file4);

            // Lista de consejos para ahorrar agua y electricidad en un array
            String[] consejos = {
                    "Utiliza abono orgánico.",
                    "Recoge agua de lluvia para regar las plantas.",
                    "Realiza el mantenimiento de tu jardín.",
                    "Actúa rápido ante plagas y enfermedades.",
                    "Cambiar los abonos químicos por abono natural",
                    "No olvides podar las partes secas de las plantas",
                    "Las semillas normales se entierran a una profundidad de 2 o 3 veces su diámetro.",
                    "El trasplante se realiza cuando la nueva planta supera la altura del recipiente",
                    " Elige un rincón de la vivienda que reciba luz natural y directa la mayor parte del día. ",
                    "Plantar dependerá, en gran medida, de donde vivas y de los cultivos que escojas. Por regla general, el principio de la primavera es el momento de la siembra y se puede trasplantar de mayo a junio."
            };
            // Recorrer el array y agregar los consejos al archivo
            writer.append("Los fertilizantes orgánicos son materiales naturales que pueden proceder de procesos bioquímicos naturales, a diferencia de los inorgánicos, que son materiales de origen sintético.\n");

            for (String consejo : consejos) {
                writer.append(consejo).append("\n");
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent(this, login.class);
        startActivity(intent);
    }
}
