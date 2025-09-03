package tienda;

import java.util.Scanner;

public class TiendaDescuentoClientes {

    public static final double DESC_ROPA = 0.10;       
    public static final double DESC_TECNOLOGIA = 0.05; 
    public static final double DESC_ALIMENTOS = 0.02; 
    
    public static final double UMBRAL_ADICIONAL = 500000;
    public static final double DESC_ADICIONAL = 0.05;  

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n;
        do {
            System.out.print("Ingrese el número de productos a comprar (mínimo 1): ");
            n = sc.nextInt();
        } while (n < 1);

        double[] precios = new double[n];
        String[] nombres = new String[n];
        int[] tipos = new int[n];

        double totalSinDescuento = 0;
        double totalConDescuento = 0;

 
        for (int i = 0; i < n; i++) {
            sc.nextLine(); 
            System.out.println("\nProducto " + (i + 1));
            System.out.print("Ingrese nombre: ");
            nombres[i] = sc.nextLine();

            int tipo;
            do {
                System.out.print("Ingrese tipo (1: ropa, 2: tecnología, 3: alimentos): ");
                tipo = sc.nextInt();
            } while (tipo < 1 || tipo > 3);
            tipos[i] = tipo;

            System.out.print("Ingrese precio: ");
            precios[i] = sc.nextDouble();

            totalSinDescuento += precios[i];

        
            double descuento = 0;
            switch (tipo) {
                case 1:
                    descuento = precios[i] * DESC_ROPA;
                    break;
                case 2: 
                    descuento = precios[i] * DESC_TECNOLOGIA;
                    break;
                case 3: 
                    descuento = precios[i] * DESC_ALIMENTOS;
                    break;
            }

            totalConDescuento += precios[i] - descuento;
        }


        if (totalConDescuento > UMBRAL_ADICIONAL) {
            double descAdicional = totalConDescuento * DESC_ADICIONAL;
            totalConDescuento -= descAdicional;
            System.out.println("\n¡Felicidades! Tu compra supera $" + UMBRAL_ADICIONAL);
            System.out.println("Se aplicó un descuento adicional del 5%.");
        }

  
        double ahorro = totalSinDescuento - totalConDescuento;
        System.out.println("\nRESUMEN DE COMPRA");
        for (int i = 0; i < n; i++) {
            System.out.println("Producto: " + nombres[i] + " | Precio: $" + precios[i]);
        }
        System.out.println("\nTotal sin descuento: $" + totalSinDescuento);
        System.out.println("Total con descuento: $" + totalConDescuento);
        System.out.println("Ahorro total: $" + ahorro);
    }
}
