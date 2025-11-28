import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Product {

    public static List<Product> products = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    private int sku;
    private String name;
    private double cost;
    private double price;
    private int stock;
    private double costDozen;
    private int sold;
    private LocalDate expirationDate;

    public Product() {}

    public Product(int sku, String name, double cost, double price, int stock,
                   double costDozen, int sold) {

        this.sku = sku;
        this.name = name;
        this.cost = cost;
        this.price = price;
        this.stock = stock;
        this.costDozen = costDozen;
        this.sold = sold;
        this.expirationDate = LocalDate.now().plusDays(30);
    }

    // === AGREGADO: SETTER PARA MODIFICAR STOCK ===
    public void setStock(int stock) {
        this.stock = stock;
    }

    // === GETTERS ===
    public int getSku() { return sku; }
    public String getName() { return name; }
    public double getCost() { return cost; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public double getCostDozen() { return costDozen; }
    public int getSold() { return sold; }
    public LocalDate getExpirationDate() { return expirationDate; }

    // === AGREGAR PRODUCTO ===
    public static void addProduct(Scanner sc) {

        System.out.print("Ingresar código de identificación (SKU): ");
        int sku = Integer.parseInt(sc.nextLine());

        System.out.print("Ingresar nombre del producto: ");
        String name = sc.nextLine();

        System.out.print("Ingresar costo de compra del producto: ");
        double cost = Double.parseDouble(sc.nextLine());

        System.out.print("Ingresar el precio de venta al público: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("Ingresar precio por docena: ");
        double costDozen = Double.parseDouble(sc.nextLine());

        System.out.print("Ingrese cuántas unidades se han vendido: ");
        int sold = Integer.parseInt(sc.nextLine());

        System.out.print("Ingrese stock actual: ");
        int stock = Integer.parseInt(sc.nextLine());

        Product p = new Product(sku, name, cost, price, stock, costDozen, sold);

        products.add(p);
        System.out.println("Producto agregado correctamente. Se dan 30 días para considerarlo vencido: " + p.getExpirationDate());
    }

    @Override
    public String toString() {
        return "SKU: " + sku +
                ", Nombre: " + name +
                ", Costo: " + cost +
                ", Precio: " + price +
                ", Docena: " + costDozen +
                ", Vendidos: " + sold +
                ", Stock: " + stock +
                ", Expira: " + expirationDate;
    }

    // === MOSTRAR PRODUCTOS ===
    public static void showProducts() {
        System.out.println("\n=== LISTA DE PRODUCTOS ===");

        if (products.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ". " + products.get(i));
        }
    }

    // === MENÚ DE INVENTARIO ===
    public void menuGestion(Scanner sc) {
        while (true) {
            System.out.println("\n=== GESTIÓN DE INVENTARIO ===");
            System.out.println("1. Agregar producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            String opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    addProduct(sc);
                    break;
                case "2":
                    showProducts();
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}