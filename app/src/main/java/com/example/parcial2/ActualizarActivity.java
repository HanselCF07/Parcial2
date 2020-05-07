package com.example.parcial2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActualizarActivity extends AppCompatActivity {//implements AdapterView.OnItemSelectedListener{
    EditText cedula, nombre, salario;
    Button actualizar;
    Spinner spinner_1, spinner_2, spinner_3;
    String seleccion_spinner_1, seleccion_spinner_2, seleccion_spinner_3;

    Encuesta encuesta;
    EncuestaController ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        ec = new EncuestaController(getApplicationContext());
        Cursor mCursor = ec.allEncuestas();

        cedula = findViewById(R.id.edt_cedula);
        nombre = findViewById(R.id.edt_nombre);
        salario = findViewById(R.id.edt_salario);

        ArrayList<String> spinner1_list = new ArrayList<String>();
        spinner1_list.add("1");
        spinner1_list.add("2");
        spinner1_list.add("3");
        spinner1_list.add("4");
        spinner1_list.add("5");
        spinner1_list.add("6");
        spinner_1 = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner1_list);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter);
        spinner_1.setOnItemSelectedListener(new SpinnerUnoClass());

        ArrayList<String> spinner2_list = new ArrayList<String>();
        spinner2_list.add("Bachillerato");
        spinner2_list.add("Pregrado");
        spinner2_list.add("Maestría");
        spinner2_list.add("Doctorado");
        spinner_2 = findViewById(R.id.spinner_2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner2_list);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_2.setAdapter(adapter2);
        spinner_2.setOnItemSelectedListener(new SpinnerDosClass());

        //LLENAR EL SPINNER CON LAS CEDULAS A ESCOGER
        ArrayList<String> spinner3_list = new ArrayList<>();
        mCursor.moveToFirst();
        while (!mCursor.isAfterLast()) {
            spinner3_list.add( mCursor.getString(mCursor.getColumnIndexOrThrow("_id"))); //add the item
            mCursor.moveToNext();
        }

        spinner_3 = findViewById(R.id.spinner_3);
        ArrayAdapter<String> adapter_3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner3_list);
        spinner_3.setAdapter(adapter_3);
        spinner_3.setOnItemSelectedListener(new SpinnerTresClass());


        actualizar = findViewById(R.id.btn_actualizar);
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encuesta = new Encuesta();

                encuesta.setCedula(seleccion_spinner_3);
                encuesta.setNombre(nombre.getText().toString());
                encuesta.setEstrato(seleccion_spinner_1);
                encuesta.setSalario(salario.getText().toString());
                encuesta.setNivel_educativo(seleccion_spinner_2);

                long id = ec.actualizarEncuesta(encuesta);
                Toast.makeText(getApplicationContext(), "Encuesta actualizada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class SpinnerUnoClass implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            seleccion_spinner_1 = parent.getItemAtPosition(position).toString();
            Toast.makeText(view.getContext(), "Your choose :"+seleccion_spinner_1 ,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class SpinnerDosClass implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            seleccion_spinner_2 = parent.getItemAtPosition(position).toString();
            Toast.makeText(view.getContext(), "Your choose :"+seleccion_spinner_2 ,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    class SpinnerTresClass implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            seleccion_spinner_3 = parent.getItemAtPosition(position).toString();
            Encuesta encuesta = ec.buscarEncuesta(seleccion_spinner_3);

            spinner_1.setSelection(Integer.parseInt(encuesta.getEstrato())-1);
            if(encuesta.getNivel_educativo().equals("Bachillerato")){
                spinner_2.setSelection(0);
            }
            if(encuesta.getNivel_educativo().equals("Pregrado")){
                spinner_2.setSelection(1);
            }
            if(encuesta.getNivel_educativo().equals("Maestría")){
                spinner_2.setSelection(2);
            }
            if(encuesta.getNivel_educativo().equals("Doctorado")){
                spinner_2.setSelection(3);
            }
            nombre.setText(encuesta.getNombre());
            salario.setText(encuesta.getSalario());

            Toast.makeText(view.getContext(), "Your choose :"+seleccion_spinner_3 ,Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}
