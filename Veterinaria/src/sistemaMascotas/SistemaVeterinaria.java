package sistemaMascotas;

import java.util.ArrayList;
import java.util.List;

public class SistemaVeterinaria {
    private List<Duenio> duenios;
    private List<Mascotas> mascotas;

    public SistemaVeterinaria() {
        this.duenios = new ArrayList<>();
        this.mascotas = new ArrayList<>();
    }

    public SistemaVeterinaria(List<Duenio> duenios, List<Mascotas> mascotas) {
        this.duenios = duenios != null ? duenios : new ArrayList<>();
        this.mascotas = mascotas != null ? mascotas : new ArrayList<>();
    }

    public void registrarDuenio(Duenio duenio) {
        if (duenio.getNombreCompleto() == null || duenio.getNombreCompleto().trim().isEmpty()) {
            System.out.println("Error: El nombre completo es obligatorio.");
            return;
        }
        if (duenio.getDocumento() == null || duenio.getDocumento().trim().isEmpty()) {
            System.out.println("Error: El documento es obligatorio.");
            return;
        }
        if (duenio.getTelefono() == null || duenio.getTelefono().trim().isEmpty()) {
            System.out.println("Error: El teléfono es obligatorio.");
            return;
        }
        
        duenios.add(duenio);
        System.out.println("Dueño registrado: " + duenio.getNombreCompleto());
    }

    public void registrarMascota(Duenio duenio, Mascotas mascota) {
        if (mascota.getNombre() == null || mascota.getNombre().trim().isEmpty()) {
            System.out.println("Error: El nombre de la mascota es obligatorio.");
            return;
        }
        if (mascota.getEspecie() == null || mascota.getEspecie().trim().isEmpty()) {
            System.out.println("Error: La especie es obligatoria.");
            return;
        }
        
        if (!duenios.contains(duenio)) {
            System.out.println("Error: El dueño no está registrado en el sistema.");
            return;
        }
        
        duenio.registrarMascota(mascota);
        mascotas.add(mascota);
    }

    public void registrarControl(Mascotas mascota, ControlesVeterinarios control) {
        if (!mascotas.contains(mascota)) {
            System.out.println("Error: No se puede registrar control para una mascota inexistente.");
            return;
        }
        
        if (control.getObservaciones() == null || control.getObservaciones().trim().isEmpty()) {
            System.out.println("Error: Las observaciones son obligatorias.");
            return;
        }
        
        mascota.registrarControl(control);
    }

    public void consultarHistorial(Mascotas mascota) {
        if (!mascotas.contains(mascota)) {
            System.out.println("Error: Mascota no encontrada en el sistema.");
            return;
        }
        
        System.out.println("\n=== HISTORIAL MÉDICO ===");
        System.out.println(mascota.toString());
        System.out.println("\nControles realizados:");
        
        if (mascota.getControles().isEmpty()) {
            System.out.println("No hay controles registrados.");
        } else {
            for (ControlesVeterinarios control : mascota.getControles()) {
                System.out.println("- " + control.consultarHistorial());
            }
        }
        System.out.println("========================\n");
    }

    public void generarReporteMascota(Mascotas mascota) {
        if (!mascotas.contains(mascota)) {
            System.out.println("Error: Mascota no encontrada en el sistema.");
            return;
        }
        
        System.out.println("\n=== REPORTE DE MASCOTA ===");
        System.out.println(mascota.generarResumen());
        System.out.println("Dueño: " + mascota.getDuenioPertenece().toString());
        System.out.println("==========================\n");
    }

    public List<Duenio> getDuenios() {
        return duenios;
    }

    public List<Mascotas> getMascotas() {
        return mascotas;
    }

    @Override
    public String toString() {
        return "SistemaVeterinaria{" +
                "Total de dueños=" + duenios.size() +
                ", Total de mascotas=" + mascotas.size() +
                '}';
    }
}

