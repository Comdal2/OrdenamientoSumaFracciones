package com.isaac.ejercicio7;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FraccionDialog extends Dialog {
    private EditText editTextNumerador;
    private EditText editTextDenominador;
    private Button buttonAgregar;
    private OnFraccionEnteredListener listener;

    public interface OnFraccionEnteredListener {
        void onFraccionEntered(Fraccion fraccion);
    }

    public FraccionDialog(Context context, OnFraccionEnteredListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_fraccion);

        editTextNumerador = findViewById(R.id.editTextNumerador);
        editTextDenominador = findViewById(R.id.editTextDenominador);
        buttonAgregar = findViewById(R.id.buttonAgregar);

        buttonAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int numerador = Integer.parseInt(editTextNumerador.getText().toString());
                    int denominador = Integer.parseInt(editTextDenominador.getText().toString());
                    if (denominador == 0) {
                        throw new IllegalArgumentException("El denominador no puede ser cero.");
                    }
                    listener.onFraccionEntered(new Fraccion(numerador, denominador));
                    dismiss();
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Por favor, ingrese números válidos.", Toast.LENGTH_LONG).show();
                } catch (IllegalArgumentException e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
