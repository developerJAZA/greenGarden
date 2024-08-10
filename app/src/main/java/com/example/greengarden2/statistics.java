package com.example.greengarden2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.greengarden2.modelos.Abono;
import com.example.greengarden2.modelos.Agua;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class statistics extends AppCompatActivity {
    private TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_statistics);

        // Obtener referencias a los elementos de la interfaz de usuario
        Button botonRegresar = findViewById(R.id.botonRetroceso4);
        tableLayout = findViewById(R.id.myTableLayout);
        Intent intent = new Intent(this, Principal.class);

        //Carga de los archivos de agua y electricidad
        File aguaFile = new File(getFilesDir(), "agua.txt");
        File abonoFile = new File(getFilesDir(), "abono.txt");

        //Listas de Agua y Electricidad
        List<Agua> listaAgua = leerArchivoAgua(aguaFile);
        List<Abono> listaAbono = leerArchivoAbono(abonoFile);


        //Crear la Tabla
        addElementAgua(listaAgua);
        addElementAbono(listaAbono);
        addPromedioAgua(listaAgua);
        addPromedioAbono(listaAbono);

        //Boton Regresar
        botonRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    private void addPromedioAgua(List<Agua> aguaList){
        // Obtén una referencia al TableLayout en tu actividad o fragmento

        float promedioConsumoAgua = calcularPromedioVolumenAgua(aguaList);
        float promedioPrecioAgua = calcularPromedioPrecioAgua(aguaList);

        TableRow row = new TableRow(this);
        //AÑADE LA INFORMACIÓN A LA CELDA 1
        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(10, 10, 10, 10);
        cell1.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el
        // color deseado

        //AÑADE LA INFORMACIÓN A LA CELDA 2
        TextView cell2 = new TextView(this);
        cell2.setText("Agua");
        cell2.setPadding(10, 10, 10, 10);
        cell2.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el color


        //AÑADE LA INFORMACIÓN A LA CELDA 3
        TextView cell3 = new TextView(this);
        cell3.setText(String.valueOf(promedioConsumoAgua));
        cell3.setPadding(10, 10, 10, 10);
        cell3.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
        // el color

        //AÑADE LA INFORMACIÓN A LA CELDA 4
        TextView cell4 = new TextView(this);
        cell4.setText(String.valueOf(promedioPrecioAgua));
        cell4.setPadding(10, 10, 10, 10);
        cell4.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
        // el color

        // Agrega las celdas a la fila
        row.addView(cell1);
        row.addView(cell2);
        row.addView(cell3);
        row.addView(cell4);
        // Agrega la fila al TableLayout
        tableLayout.addView(row);
    }

    private void addPromedioAbono(List<Abono> abonoList){
        // Obtén una referencia al TableLayout en tu actividad o fragmento

        float promedioConsumoAbono = calcularPromedioKilogramo(abonoList);
        float promedioPrecioAbono = calcularPromedioPrecioAbono(abonoList);

        TableRow row = new TableRow(this);
        //AÑADE LA INFORMACIÓN A LA CELDA 1
        TextView cell1 = new TextView(this);
        cell1.setText("Promedio");
        cell1.setPadding(10, 10, 10, 10);
        cell1.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el
        // color deseado

        //AÑADE LA INFORMACIÓN A LA CELDA 2
        TextView cell2 = new TextView(this);
        cell2.setText("Abono");
        cell2.setPadding(10, 10, 10, 10);
        cell2.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el color


        //AÑADE LA INFORMACIÓN A LA CELDA 3
        TextView cell3 = new TextView(this);
        cell3.setText(String.valueOf(promedioConsumoAbono));
        cell3.setPadding(10, 10, 10, 10);
        cell3.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
        // el color

        //AÑADE LA INFORMACIÓN A LA CELDA 4
        TextView cell4 = new TextView(this);
        cell4.setText(String.valueOf(promedioPrecioAbono));
        cell4.setPadding(10, 10, 10, 10);
        cell4.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
        // el color

        // Agrega las celdas a la fila
        row.addView(cell1);
        row.addView(cell2);
        row.addView(cell3);
        row.addView(cell4);
        // Agrega la fila al TableLayout
        tableLayout.addView(row);
    }

    private void addElementAgua(List<Agua> aguaList){
        // Obtén una referencia al TableLayout en tu actividad o fragmento

        for (Agua item: aguaList) {
            // Crea una nueva fila y agrega las celdas
            TableRow row = new TableRow(this);
            //AÑADE LA INFORMACIÓN A LA CELDA 1
            TextView cell1 = new TextView(this);
            cell1.setText(item.getMes());

            cell1.setPadding(10, 10, 10, 10);
            cell1.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el
            // color deseado

            //AÑADE LA INFORMACIÓN A LA CELDA 2
            TextView cell2 = new TextView(this);
            cell2.setText("Agua");
            cell2.setPadding(10, 10, 10, 10);
            cell2.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el color


            //AÑADE LA INFORMACIÓN A LA CELDA 3
            TextView cell3 = new TextView(this);
            cell3.setText(String.valueOf((item.getVolumen())));
            cell3.setPadding(10, 10, 10, 10);
            cell3.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
            // el color

            //AÑADE LA INFORMACIÓN A LA CELDA 4
            TextView cell4 = new TextView(this);
            cell4.setText(String.valueOf((item.getPrecio())));
            cell4.setPadding(10, 10, 10, 10);
            cell4.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
            // el color

            // Agrega las celdas a la fila
            row.addView(cell1);
            row.addView(cell2);
            row.addView(cell3);
            row.addView(cell4);
            // Agrega la fila al TableLayout
            tableLayout.addView(row);
        }
    }

    private void addElementAbono(List<Abono> abonoList){
        // Obtén una referencia al TableLayout en tu actividad o fragmento

        for (Abono item: abonoList) {
            // Crea una nueva fila y agrega las celdas
            TableRow row = new TableRow(this);
            //AÑADE LA INFORMACIÓN A LA CELDA 1
            TextView cell1 = new TextView(this);
            cell1.setText(item.getMes());

            cell1.setPadding(10, 10, 10, 10);
            cell1.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el
            // color deseado

            //AÑADE LA INFORMACIÓN A LA CELDA 2
            TextView cell2 = new TextView(this);
            cell2.setText("Abono");
            cell2.setPadding(10, 10, 10, 10);
            cell2.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por el color


            //AÑADE LA INFORMACIÓN A LA CELDA 3
            TextView cell3 = new TextView(this);
            cell3.setText(String.valueOf((item.getKilogramo())));
            cell3.setPadding(10, 10, 10, 10);
            cell3.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
            // el color

            //AÑADE LA INFORMACIÓN A LA CELDA 4
            TextView cell4 = new TextView(this);
            cell4.setText(String.valueOf((item.getPrecio())));
            cell4.setPadding(10, 10, 10, 10);
            cell4.setBackgroundResource(R.color.white); // Cambia R.color.tableCellBackground por
            // el color

            // Agrega las celdas a la fila
            row.addView(cell1);
            row.addView(cell2);
            row.addView(cell3);
            row.addView(cell4);
            // Agrega la fila al TableLayout
            tableLayout.addView(row);
        }
    }

    private static List<Agua> leerArchivoAgua(File archivo) {
        List<Agua> listaAgua = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                float volumen = Float.parseFloat(datos[0]);
                float precio = Float.parseFloat(datos[1]);
                String mes = datos[2];

                Agua agua = new Agua(volumen, precio, mes);
                listaAgua.add(agua);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaAgua;
    }

    private static List<Abono> leerArchivoAbono(File archivo) {
        List<Abono> listaAbono = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                float kilogramo = Float.parseFloat(datos[0]);
                float precio = Float.parseFloat(datos[1]);
                String mes = datos[2];

                Abono abono = new Abono(kilogramo, precio, mes);
                listaAbono.add(abono);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listaAbono;
    }

    private float calcularPromedioVolumenAgua(List<Agua> aguaList) {
        float sum = 0;
        for (Agua item : aguaList) {
            sum += item.getVolumen();
        }
        return sum / aguaList.size();
    }
    private float calcularPromedioPrecioAgua(List<Agua> aguaList) {
        float sum = 0;
        for (Agua item : aguaList) {
            sum += item.getPrecio();
        }
        return sum / aguaList.size();
    }

    private float calcularPromedioKilogramo(List<Abono> abonoList) {
        float sum = 0;
        for (Abono item : abonoList) {
            sum += item.getKilogramo();
        }
        return sum / abonoList.size();
    }

    private float calcularPromedioPrecioAbono(List<Abono> abonoList) {
        float sum = 0;
        for (Abono item : abonoList) {
            sum += item.getPrecio();
        }
        return sum / abonoList.size();
    }
}