import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SalesManager {

    private static Scanner sc = new Scanner(System.in);

    private static List<Sale> salesHistory = new ArrayList<>();
    private static double totalSales = 0.0;

    // ==== FORMATEADOR DE FECHA/HORA BONITO ====
    private static final DateTimeFormatter FORMAT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    // ==== CLASE INTERNA PARA UNA VENTA ====
    static class Sale {
        String productName;
        int quantity;
        double unitPrice;
        double totalAmount;
        LocalDateTime dateTime;

        public Sale(String productName, int quantity, double unitPrice) {
            this.productName = productName;
            this.quantity = quantity;
            this.unitPrice = unitPrice;
            this.totalAmount = quantity * unitPrice;
            this.dateTime = LocalDateTime.now();
        }

        @Override
        public String toString() {
            return productName +
                    " | Cant: " + quantity +
                    " | Total: $" + totalAmount +
                    " | Fecha: " + dateTime.format(FORMAT);
        }
    }

    // ==== MENÚ DE VENTAS ====
    public static void menuVentas() {
        while (true) {
            System.out.println("\n=== MENÚ DE VENTAS ===");
            System.out.println("1. Registrar venta");
            System.out.println("2. Mostrar ventas del día");
            System.out.println("3. Cierre de caja");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String op = sc.nextLine();

            switch (op) {
                case "1":
                    registrarVenta();
                    break;
                case "2":
                    mostrarVentasDelDia();
                    break;
                case "3":
                    cierreDeCaja();
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Opción incorrecta.");
            }
        }
    }

    // ==== REGISTRAR VENTA ====
    public static void registrarVenta() {

        if (Product.products.isEmpty()) {
            System.out.println("No hay productos registrados para vender.");
            return;
        }

        System.out.println("\n=== PRODUCTOS DISPONIBLES ===");
        Product.showProducts();

        System.out.print("\nSeleccione número del producto a vender: ");
        int index = Integer.parseInt(sc.nextLine()) - 1;

        if (index < 0 || index >= Product.products.size()) {
            System.out.println("Producto no válido.");
            return;
        }

        Product selected = Product.products.get(index);

        System.out.println("Producto seleccionado: " + selected.getName());
        System.out.println("Stock disponible: " + selected.getStock());

        System.out.print("Cantidad a vender: ");
        int quantity = Integer.parseInt(sc.nextLine());

        if (quantity <= 0) {
            System.out.println("Cantidad inválida.");
            return;
        }

        if (quantity > selected.getStock()) {
            System.out.println("Error: No hay suficiente stock.");
            return;
        }

        selected.setStock(selected.getStock() - quantity);

        Sale sale = new Sale(selected.getName(), quantity, selected.getPrice());

        salesHistory.add(sale);
        totalSales += sale.totalAmount;

        System.out.println("Venta registrada: " + sale);
    }

    // ==== MOSTRAR VENTAS DEL DÍA ====
    public static void mostrarVentasDelDia() {

        if (salesHistory.isEmpty()) {
            System.out.println("Aún no se han registrado ventas.");
            return;
        }

        System.out.println("\n=== VENTAS REGISTRADAS HOY ===");
        for (Sale s : salesHistory) {
            System.out.println(s);
        }
        System.out.println("TOTAL ACUMULADO: $" + totalSales);
    }

    // ==== CIERRE DE CAJA ====
    public static void cierreDeCaja() {

        System.out.println("\n=== *** CIERRE DE CAJA *** ===");

        if (salesHistory.isEmpty()) {
            System.out.println("No hubo ventas hoy.");
        } else {
            System.out.println("\nVentas del día:");
            for (Sale venta : salesHistory) {
                System.out.println(venta);
            }
            System.out.println("\nTOTAL FINAL DEL DÍA: $" + totalSales);
            System.out.println("Hora de cierre: " + LocalDateTime.now().format(FORMAT));
        }

        salesHistory.clear();
        totalSales = 0.0;

        System.out.println("\nCierre de caja completado. Datos reiniciados.");
    }
}
