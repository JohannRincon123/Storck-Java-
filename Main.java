import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        login sistema = new login();

        while (true) {

            System.out.println("\n===== MENÚ =====");

            if (!sistema.haySesionActiva()) {
                System.out.println("1. Registrar usuario");
                System.out.println("2. Iniciar sesión");
                System.out.println("3. Salir");
            } else {
                System.out.println("1. Cambiar contraseña");
                System.out.println("2. Cerrar sesión");
                System.out.println("3. Salir");
            }

            System.out.print("Seleccione: ");
            String opcion = sc.nextLine();
            String resultado = "";

            if (!sistema.haySesionActiva()) {

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

            } else {

                switch (opcion) {
                    case "1":
                        resultado = sistema.cambiarContraseña();
                        break;
                    case "2":
                        resultado = sistema.cerrarSesion();
                        break;
                    case "3":
                        System.out.println("Saliendo...");
                        return;
                    default:
                        resultado = "Opción no válida.";
                }

            }

            System.out.println(resultado);
        }
    }
}