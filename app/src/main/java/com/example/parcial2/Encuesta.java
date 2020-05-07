package com.example.parcial2;

public class Encuesta {
    private String cedula;
    private String nombre;
    private String estrato;
    private String salario;
    private String nivel_educativo;
    private String fecha;

    public Encuesta() {
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getNivel_educativo() {
        return nivel_educativo;
    }

    public void setNivel_educativo(String nivel_educativo) {
        this.nivel_educativo = nivel_educativo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Encuesta{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", estrato='" + estrato + '\'' +
                ", salario='" + salario + '\'' +
                ", nivel_educativo='" + nivel_educativo + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}
