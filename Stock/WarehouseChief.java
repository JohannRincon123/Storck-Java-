import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WarehouseChief {

    public void menuPrincipal(Scanner sc) {
        while (true) {
            System.out.println("\n=== CONSULTAS DE BODEGA ===");
            System.out.println("1. Ver productos próximos a vencer");
            System.out.println("2. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    nearExpiration();
                    break;
                case "2":
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void nearExpiration() {
        nearExpiration(30);
    }

    public static void nearExpiration(int dias) {
        if (Product.products.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        LocalDate hoy = LocalDate.now();
        LocalDate limite = hoy.plusDays(dias);

        List<Product> proximos = new ArrayList<>();

        for (Product p : Product.products) {
            LocalDate exp = p.getExpirationDate();
            if (exp != null && !exp.isAfter(limite)) {
                proximos.add(p);
            }
        }

        if (proximos.isEmpty()) {
            System.out.println("No hay productos que venzan en los próximos " + dias + " días.");
        } else {
            System.out.println("\n=== PRODUCTOS PRÓXIMOS A VENCER (≤ " + dias + " días) ===");
            for (Product p : proximos) {
                long faltan = ChronoUnit.DAYS.between(hoy, p.getExpirationDate());
                System.out.println(p.getName() + " - Vence en " + faltan + " días (Fecha: " + p.getExpirationDate() + ")");
            }
        }
    }
}
