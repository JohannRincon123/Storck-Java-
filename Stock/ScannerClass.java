import java.util.Scanner;

public class ScannerClass {

    public void iniciarEscaneo(Scanner sc) {
        while (true) {
            scanProduct(sc);

            String respuesta;
            do {
                System.out.print("¿Desea escanear otro producto? (si/no): ");
                respuesta = sc.nextLine().trim().toLowerCase();

                if (!respuesta.equals("si") && !respuesta.equals("no")) {
                    System.out.println("Respuesta no válida. Intente nuevamente.");
                }

            } while (!respuesta.equals("si") && !respuesta.equals("no"));

            if (respuesta.equals("no")) {
                System.out.println("Escaneo finalizado.");
                break;
            }
        }
    }

    public static void scanProduct(Scanner sc) {
        try {
            System.out.print("Escanee o ingrese el SKU del producto: ");
            int sku = sc.nextInt();
            sc.nextLine(); 

            boolean encontrado = false;

            for (Product p : Product.products) {
                if (p.getSku() == sku) {
                    System.out.println("Producto encontrado: " + p.getName() + " - Precio: " + p.getPrice());
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("Producto NO registrado. No se puede procesar la venta.");
            }

        } catch (Exception e) {
            System.out.println("El código debe ser un número entero.");
            sc.nextLine(); 
        }
    }
}
