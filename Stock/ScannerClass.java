import java.io.PrintStream;
import java.util.Iterator;
import java.util.Scanner;

public class ScannerClass {
   public ScannerClass() {
   }

   public void iniciarEscaneo(Scanner var1) {
      while(true) {
         scanProduct(var1);
         System.out.print("¿Desea escanear otro producto? (si/no): ");
         String var2 = var1.nextLine().trim().toLowerCase();
         if (!var2.equals("no")) {
            if (var2.equals("si")) {
               continue;
            }

            System.out.println("Respuesta no válida.");
         }

         return;
      }
   }

   public static void scanProduct(Scanner var0) {
      try {
         System.out.print("Escanee o ingrese el SKU del producto: ");
         int var1 = var0.nextInt();
         var0.nextLine();
         Iterator var2 = Product.products.iterator();

         while(var2.hasNext()) {
            Product var3 = (Product)var2.next();
            if (var3.getSku() == var1) {
               PrintStream var10000 = System.out;
               String var10001 = var3.getName();
               var10000.println("Producto encontrado: " + var10001 + " - Precio: " + var3.getPrice());
               return;
            }
         }

         System.out.println("Producto NO registrado. No se puede procesar la venta.");
      } catch (Exception var4) {
         System.out.println("El código debe de ser un número entero");
         var0.nextLine();
      }

   }
}