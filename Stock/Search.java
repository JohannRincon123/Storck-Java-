import java.util.Scanner;

public class Search {

    public void iniciarBusqueda(Scanner sc) {

        while (true) {
            System.out.print("Ingrese el nombre del producto a buscar: ");
            String nombre = sc.nextLine().toLowerCase();

            Product encontrado = null;

            // Búsqueda
            for (Product p : Product.products) {
                if (p.getName().toLowerCase().equals(nombre)) {
                    encontrado = p;
                    break;
                }
            }

            if (encontrado != null) {
                System.out.println("Producto encontrado");
                System.out.println(encontrado);
            } else {
                System.out.println("El producto no existe en el sistema");
            }

            System.out.print("¿Desea continuar? (Si/No): ");
            String respuesta = sc.nextLine().trim().toLowerCase();

            if (respuesta.equals("si")) {
                continue;
            }

            if (!respuesta.equals("no")) {
                System.out.println("Respuesta no válida. Asumiendo no.");
            }

            return;
        }
    }
}
