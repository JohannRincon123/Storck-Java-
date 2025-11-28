import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        login sistema = new login();

        // Instancias del sistema
        Product gestorInventario = new Product();
        WarehouseChief jefeBodega = new WarehouseChief();
        ScannerClass escaner = new ScannerClass();
        Search buscador = new Search();
        Statics estadisticas = new Statics();

        while (true) {

            System.out.println("\n===== MENÚ =====");

         
            //   MENÚ SIN SESIÓN ACTIVA
           
            if (!sistema.haySesionActiva()) {
                System.out.println("1. Registrar usuario");
                System.out.println("2. Iniciar sesión");
                System.out.println("3. Salir");

                System.out.print("Seleccione: ");
                String opcion = sc.nextLine();
                String resultado = "";

                switch (opcion) {
                    case "1":
                        resultado = sistema.registrarUsuario();
                        break;
                    case "2":
                        resultado = sistema.iniciarSesion();
                        break;
                    case "3":
                        System.out.println("Saliendo...");
                        return;
                    default:
                        resultado = "Opción no válida.";
                }

                System.out.println(resultado);
                continue;
            }

           
            //   MENÚ CON SESIÓN ACTIVA
         
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Gestión de inventario");
            System.out.println("2. Consultas de bodega");
            System.out.println("3. Escáner de productos");
            System.out.println("4. Búsqueda de productos");
            System.out.println("5. Reportes estadísticos");
            System.out.println("6. Cambiar contraseña");
            System.out.println("7. Cerrar sesión");
            System.out.println("8. gestión de ventas");  
            System.out.println("9. Salir del programa");  

            System.out.print("Seleccione: ");
            String opcion = sc.nextLine();

            switch (opcion) {

                case "1":
                    gestorInventario.menuGestion(sc);
                    break;

                case "2":
                    jefeBodega.menuPrincipal(sc);
                    break;

                case "3":
                    escaner.iniciarEscaneo(sc);
                    break;

                case "4":
                    buscador.iniciarBusqueda(sc);
                    break;

                case "5":
                    estadisticas.mostrarReporte();
                    break;

                case "6":
                    System.out.println(sistema.cambiarContraseña());
                    break;

                case "7":
                    System.out.println(sistema.cerrarSesion());
                    break;

                case "8": SalesManager.menuVentas();
                    break;

                case "9": 
                 System.out.println("Gracias por usar Stock Inventory Manager.");
                    return;  
                   
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
