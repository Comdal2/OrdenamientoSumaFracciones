package com.isaac.ejercicio7;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Arrays;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {
    EditText editTextNumeroDatos;
    TextView textViewResultado;
    Fraccion[] fracciones;
    int indiceActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumeroDatos = findViewById(R.id.editTextNumeroDatos);
        textViewResultado = findViewById(R.id.textViewResultado);
    }
    public void iniciarIngresoFracciones(View view) {
        int n = Integer.parseInt(editTextNumeroDatos.getText().toString());
        fracciones = new Fraccion[n];
        indiceActual = 0;
        mostrarDialogoIngresoFracciones();
    }
    // Método para mostrar el diálogo y recoger las fracciones del usuario
    private void mostrarDialogoIngresoFracciones() {
        if (indiceActual < fracciones.length) {
            FraccionDialog fraccionDialog = new FraccionDialog(this, new FraccionDialog.OnFraccionEnteredListener() {
                @Override
                public void onFraccionEntered(Fraccion fraccion) {
                    fracciones[indiceActual] = fraccion;
                    indiceActual++;
                    mostrarDialogoIngresoFracciones();
                }
            });
            fraccionDialog.show();
        } else {
            procesarDatos();
        }
    }
    // Método para procesar los datos y realizar las operaciones solicitadas
    public void procesarDatos() {
        // Ordenar las fracciones usando quicksort
        Arrays.sort(fracciones, new Comparator<Fraccion>() {
            @Override
            public int compare(Fraccion f1, Fraccion f2) {
                return f2.compareTo(f1); // Orden descendente
            }
        });

        // Calcular la suma de las fracciones
        Fraccion suma = sumarFracciones(fracciones);

        // Mostrar resultados
        textViewResultado.setText("Ordenadas: " + Arrays.toString(fracciones) + "\nSuma: " + suma.toString());
    }
    private Fraccion sumarFracciones(Fraccion[] fracciones) {
        Fraccion suma = new Fraccion(0, 1); // Iniciar con 0/1
        for (Fraccion f : fracciones) {
            suma = suma.add(f);
        }
        return suma.simplify(); // Devolver la fracción simplificada
    }
}