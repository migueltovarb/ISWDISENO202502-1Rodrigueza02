package sistemaMascotas;

import java.util.ArrayList;
import java.util.List;

public class Mascotas {
    private String nombre;
    private String especie;
    private float edad;
    private Duenio duenioPertenece;
    private List<ControlesVeterinarios> controles;

    public Mascotas(String nombre, String especie, float edad, Duenio duenioPertenece) {
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.duenioPertenece = duenioPertenece;
        this.controles = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public float getEdad() {
        return edad;
    }

    public void setEdad(float edad) {
        this.edad = edad;
    }

    public Duenio getDuenioPertenece() {
        return duenioPertenece;
    }

    public void setDuenioPertenece(Duenio duenioPertenece) {
        this.duenioPertenece = duenioPertenece;
    }

    public List<ControlesVeterinarios> getControles() {
        return controles;
    }

    public void registrarControl(ControlesVeterinarios control) {
        controles.add(control);
        System.out.println("Control registrado para " + nombre + " - Tipo: " + control.getTipoControl());
    }

    public String generarResumen() {
        return "Mascota: " + nombre + " (" + especie + ", " + edad + " años) - Controles realizados: " + controles.size();
    }

    @Override
    public String toString() {
        return "Mascota: " + nombre + " (" + especie + ", " + edad + " años) - Dueño: " + duenioPertenece.getNombreCompleto();
    }
}
