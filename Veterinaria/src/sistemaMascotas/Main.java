package sistemaMascotas;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        SistemaVeterinaria sistema = new SistemaVeterinaria();
        
        Duenio duenio1 = new Duenio("Laura Pérez", "1023456789", "3001234567");
        sistema.registrarDuenio(duenio1);
        
        Mascotas mascota1 = new Mascotas("Luna", "Perro", 3, duenio1);
        sistema.registrarMascota(duenio1, mascota1);
        
        ControlesVeterinarios control1 = new ControlesVeterinarios(
            new Date(), 
            TipoControl.VACUNA, 
            "Vacuna antirrábica aplicada. Todo en orden."
        );
        sistema.registrarControl(mascota1, control1);
        
        ControlesVeterinarios control2 = new ControlesVeterinarios(
            new Date(), 
            TipoControl.CHEQUEO, 
            "Chequeo general. Mascota en buen estado de salud."
        );
        sistema.registrarControl(mascota1, control2);
        
        sistema.consultarHistorial(mascota1);
        
        sistema.generarReporteMascota(mascota1);
        
        System.out.println("\n=== PRUEBAS DE VALIDACIÓN ===");
        
        Mascotas mascota2 = new Mascotas("Luna", "Gato", 2, duenio1);
        sistema.registrarMascota(duenio1, mascota2);
        
        Mascotas mascotaNoRegistrada = new Mascotas("Max", "Perro", 5, duenio1);
        ControlesVeterinarios control3 = new ControlesVeterinarios(
            new Date(), 
            TipoControl.DESPARASITACION, 
            "Desparasitación"
        );
        sistema.registrarControl(mascotaNoRegistrada, control3);
    }
}
