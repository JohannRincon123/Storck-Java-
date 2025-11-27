import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Product {
    public static List <Product> products = new ArrayList<>();
    public static Scanner scanner = new Scanner(System.in);

    public int sku;
    public String name;
    public double cost;
    public double price;
    public int stock;
    public double costDozen;
    public int sold;
    public Date expirationDate;


   public Product(int var1, String var2, double var3, double var5, int var7, double var8, int var10, Date var11) {
      this.sku = var1;
      this.name = var2;
      this.cost = var3;
      this.price = var5;
      this.stock = var7;
      this.costDozen = var8;
      this.sold = var10;
      this.expirationDate = var11;
   }

   public void addProduct(Scanner var1) {
      System.out.print("Ingresar código de identificación: ");
      this.sku = var1.nextInt();
      var1.nextLine();
      System.out.print("Ingresar nombre del producto: ");
      this.name = var1.nextLine();
      System.out.print("Ingresar costo de compra del producto: ");
      this.cost = var1.nextDouble();
      System.out.print("Ingresar el precio de venta al público: ");
      this.price = var1.nextDouble();
      System.out.print("Ingresar precio al público por docena: ");
      this.costDozen = var1.nextDouble();
      System.out.print("Ingrese cuántas unidades se han vendido: ");
      this.sold = var1.nextInt();
      var1.nextLine();
      products.add(this);
   }  
   
   @Override
   public String toString() {
      return "SKU: " + this.sku + ", Nombre: " + this.name + ", Costo: " + this.cost + ", Precio: " + this.price + ", Docena: " + this.costDozen;
   }

      public static void showProducts() {
      System.out.println("\n=== LISTA DE PRODUCTOS ===");

      for(int var0 = 0; var0 < products.size(); ++var0) {
         System.out.println(var0 + 1 + ". " + String.valueOf(products.get(var0)));
         
      }
   }

         public void menuGestion(Scanner var1) {
      while(true) {
         System.out.println("\n=== GESTIÓN DE INVENTARIO ===");
         System.out.println("1. Agregar producto");
         System.out.println("2. Mostrar productos");
         System.out.println("3. Volver al menú principal");
         System.out.print("Seleccione una opción: ");
         String var2 = var1.nextLine().trim();
         if (var2.equals("1")) {
            this.addProduct(var1);
         } else if (var2.equals("2")) {
            showProducts();
         } else {
            if (var2.equals("3")) {
               return;
            }

            System.out.println("Opción no válida.");
         }
      }

   }
      public int getSku() {
      return this.sku;
   }

   public String getName() {
      return this.name;
   }

   public double getCost() {
      return this.cost;
   }

   public double getPrice() {
      return this.price;
   }

   public int getStock() {
      return this.stock;
   }

   public double getCostDozen() {
      return this.costDozen;
   }

   public int getSold() {
      return this.sold;
   }

   public Date getExpirationDate() {
      return this.expirationDate;
}
} 

   

