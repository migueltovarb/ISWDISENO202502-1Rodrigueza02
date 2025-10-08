package sistemaMascotas;

import java.util.ArrayList;
import java.util.List;

public class Duenio {
    private String nombreCompleto;
    private String documento;
    private String telefono;
    private List<Mascotas> mascotas;

    public Duenio(String nombreCompleto, String documento, String telefono) {
        this.nombreCompleto = nombreCompleto;
        this.documento = documento;
        this.telefono = telefono;
        this.mascotas = new ArrayList<>();
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Mascotas> getMascotas() {
        return mascotas;
    }

    public void registrarMascota(Mascotas mascota) {
        for (Mascotas m : mascotas) {
            if (m.getNombre().equalsIgnoreCase(mascota.getNombre())) {
                System.out.println("Error: Ya existe una mascota con el nombre '" + mascota.getNombre() + "' para este dueño.");
                return;
            }
        }
        mascotas.add(mascota);
        System.out.println("Mascota '" + mascota.getNombre() + "' registrada correctamente para " + nombreCompleto);
    }

    @Override
    public String toString() {
        return "Dueño: " + nombreCompleto + " (Doc: " + documento + ", Tel: " + telefono + ")";
    }
}

