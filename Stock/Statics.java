import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Statics {

    public void mostrarReporte() {
        mostSold();
        lessSold();
    }

    public static void mostSold() {
        generarReporte("=== PRODUCTOS MÁS VENDIDOS ===",
                Comparator.comparingInt(Product::getSold).reversed());
    }

    public static void lessSold() {
        generarReporte("\n=== PRODUCTOS MENOS VENDIDOS ===",
                Comparator.comparingInt(Product::getSold));
    }

    private static void generarReporte(String titulo, Comparator<Product> comparator) {
        List<Product> productos = Product.products;

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados");
            return;
        }

        List<Product> copia = new ArrayList<>(productos);
        copia.sort(comparator);

        System.out.println(titulo);
        for (Product p : copia) {
            System.out.println(p.getName() + " - Vendidos: " + p.getSold());
        }
    }
}
