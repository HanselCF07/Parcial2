package com.example.parcial2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {//implements AdapterView.OnItemSelectedListener{
    EditText cedula, nombre, salario;
    Button guardar;
    String seleccion_spinner_1, seleccion_spinner_2;

    Encuesta encuesta;
    EncuestaController ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ec = new EncuestaController(getApplicationContext());

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
        Spinner spinner_1 = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner1_list);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_1.setAdapter(adapter);
        spinner_1.setOnItemSelectedListener(new SpinnerUnoClass());

        ArrayList<String> spinner2_list = new ArrayList<String>();
        spinner2_list.add("Bachillerato");
        spinner2_list.add("Pregrado");
        spinner2_list.add("Maestría");
        spinner2_list.add("Doctorado");
        Spinner spinner_2 = findViewById(R.id.spinner_2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner2_list);
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_2.setAdapter(adapter2);
        spinner_2.setOnItemSelectedListener(new SpinnerDosClass());

        guardar = findViewById(R.id.btnguardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                encuesta = new Encuesta();

                encuesta.setCedula(cedula.getText().toString());
                encuesta.setNombre(nombre.getText().toString());
                encuesta.setEstrato(seleccion_spinner_1);
                encuesta.setSalario(salario.getText().toString());
                encuesta.setNivel_educativo(seleccion_spinner_2);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fecha_String = sdf.format(new Date());
                encuesta.setFecha(fecha_String);

                long id = ec.agregarEncuesta(encuesta);
                Toast.makeText(getApplicationContext(), "Encuesta registrada", Toast.LENGTH_SHORT).show();
                limpiarFormulario();
            }
        });
    }

    public void limpiarFormulario(){
        cedula.setText("");
        nombre.setText("");
        salario.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_listar) {
            Intent i = new Intent(getApplicationContext(), ListadoActivity.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.action_actualizar) {
            Intent i = new Intent(getApplicationContext(), ActualizarActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
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

}
