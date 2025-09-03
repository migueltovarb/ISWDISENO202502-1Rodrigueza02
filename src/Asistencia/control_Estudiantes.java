package Asistencia;
import java.util.Scanner;

public class control_Estudiantes {
	
    public static void main(String[] args) {
        final int DIAS_SEMANA = 5;
        final int NUM_ESTUDIANTES = 4;

        Scanner sc = new Scanner(System.in);
        char[][] asistencia = new char[NUM_ESTUDIANTES][DIAS_SEMANA];
        String[] nombres = new String[NUM_ESTUDIANTES];
        boolean datosIngresados = false;
        int opcion;
	
	        
        do {
            System.out.println("\nMENU");
            System.out.println("1. Ver asistencia individual");
            System.out.println("2. Ver resumen general");
            System.out.println("3. Volver a registrar");
            System.out.println("4. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

	            
            if (opcion == 3) { 
                for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                    System.out.print("Nombre del estudiante " + (i + 1) + ": ");
                    String nombre = sc.nextLine().trim();
                    if (nombre.isEmpty()) {
                        nombre = "Estudiante " + (i + 1);
                    }
                    nombres[i] = nombre;
	                        
                    for (int j = 0; j < DIAS_SEMANA; j++) {
                        char valor;
                        boolean valido;
                        do {
                            System.out.print("Día " + (j + 1) + " (P/A) para " + nombres[i] + ": ");
                            String entrada = sc.nextLine();
                           
                            if (entrada.length() == 0) {
                                valor = ' ';
                            } else {
                                valor = entrada.charAt(0);
                            }
                            valido = (valor == 'P' || valor == 'p' || valor == 'A' || valor == 'a');
                            if (!valido) {
                                System.out.println("Invalido, use P o A.");
                            }
                        } while (!valido);

                        if (valor == 'p') valor = 'P';
                        if (valor == 'a') valor = 'A';
                        asistencia[i][j] = valor;
                    }
                }
                datosIngresados = true;
                System.out.println("Asistencia registrada.");
            }
            else if (opcion == 1) {
                if (!datosIngresados) {
                    System.out.println("Primero debe registrar la asistencia (3).");
                } else {
                    for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                        System.out.print(nombres[i] + ": ");
                        for (int j = 0; j < DIAS_SEMANA; j++) {
                            System.out.print(asistencia[i][j] + " ");
                        }
                        System.out.println();
                    }
                }
            }
            else if (opcion == 2) {
                if (!datosIngresados) {
                    System.out.println("Primero debe registrar la asistencia (3).");
                } else {

                    for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                        int totalP = 0;
                        for (int j = 0; j < DIAS_SEMANA; j++) {
                            if (asistencia[i][j] == 'P') totalP++;
                        }
                        System.out.println(nombres[i] + " asistió " + totalP + " días.");
                    }


                    System.out.print("Estudiantes que asistieron todos los días: ");
                    boolean alguno = false;
                    for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                        boolean todos = true;
                        for (int j = 0; j < DIAS_SEMANA; j++) {
                            if (asistencia[i][j] == 'A') {
                                todos = false;
                                break;
                            }
                        }
                        if (todos) {
                            if (alguno) System.out.print(", ");
                            System.out.print(nombres[i]);
                            alguno = true;
                        }
                    }
                    if (!alguno) System.out.print("Ninguno");
                    System.out.println();


                    int mayorAusencia = 0;
                    for (int j = 0; j < DIAS_SEMANA; j++) {
                        int ausencia = 0;
                        for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                            if (asistencia[i][j] == 'A') ausencia++;
                        }
                        if (ausencia > mayorAusencia) mayorAusencia = ausencia;
                    }
                    System.out.print("Dias con mayor numero de ausencias: ");
                    if (mayorAusencia == 0) {
                        System.out.print("Ninguno");
                    } else {
                        boolean primero = true;
                        for (int j = 0; j < DIAS_SEMANA; j++) {
                            int ausencia = 0;
                            for (int i = 0; i < NUM_ESTUDIANTES; i++) {
                                if (asistencia[i][j] == 'A') ausencia++;
                            }
                            if (ausencia == mayorAusencia) {
                                if (!primero) System.out.print(", ");
                                System.out.print("Día " + (j + 1) + " (" + ausencia + ")");
                                primero = false;
                            }
                        }
                    }
                    System.out.println();
                }
            }
            else if (opcion == 4) {
                System.out.println("Adios");
            }
            else {
                System.out.println("Opcion Invalida.");
            }

        } while (opcion != 4);

        sc.close();
    }
}
