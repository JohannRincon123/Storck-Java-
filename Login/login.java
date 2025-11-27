import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class login {

    private static final String RUTA_ARCHIVO = "usuarios.txt";
    private static Map<String, String> usuariosRegistrados = cargarUsuarios();
    private static String usuarioActual = null;
    private static Scanner sc = new Scanner(System.in);


    // --------------------- CARGAR Y GUARDAR USUARIOS --------------------- //

    private static Map<String, String> cargarUsuarios() {
        Map<String, String> data = new HashMap<>();

        try {
            File archivo = new File(RUTA_ARCHIVO);

            // Si no existe, crearlo vacío
            if (!archivo.exists()) {
                archivo.createNewFile();
                return data;
            }

            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split(":");

                if (partes.length == 2) {
                    data.put(partes[0], partes[1]);
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }


    private static void guardarUsuarios() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO));

            for (Map.Entry<String, String> entry : usuariosRegistrados.entrySet()) {
                bw.write(entry.getKey() + ":" + entry.getValue());
                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // --------------------- FUNCIONES PRINCIPALES --------------------- //

    private static String registrarUsuario() {
        System.out.println("\n=== REGISTRAR NUEVO USUARIO ===");
        System.out.print("Ingrese nuevo nombre de usuario: ");
        String username = sc.nextLine().trim();

        if (username.isEmpty()) return "El nombre de usuario no puede estar vacío.";
        if (usuariosRegistrados.containsKey(username)) return "Ese usuario ya existe.";

        System.out.print("Ingrese contraseña: ");
        String password = sc.nextLine();

        System.out.print("Confirme la contraseña: ");
        String confirmar = sc.nextLine();

        if (password.isEmpty()) return "La contraseña no puede estar vacía.";
        if (!password.equals(confirmar)) return "Las contraseñas no coinciden.";

        usuariosRegistrados.put(username, password);
        guardarUsuarios();

        return "Usuario '" + username + "' registrado correctamente.";
    }


    private static String login() {
        System.out.println("\n=== INICIAR SESIÓN ===");
        System.out.print("Usuario: ");
        String username = sc.nextLine();

        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        if (username.isEmpty() || password.isEmpty()) {
            return "Debes ingresar usuario y contraseña.";
        }

        if (!usuariosRegistrados.containsKey(username)) {
            return "El usuario no existe.";
        }

        if (!usuariosRegistrados.get(username).equals(password)) {
            return "Contraseña incorrecta.";
        }

        usuarioActual = username;
        return "Bienvenido, " + username + ". Iniciaste sesión correctamente.";
    }


    private static String logout() {
        if (usuarioActual == null) {
            return "No hay ningún usuario conectado.";
        }

        String nombre = usuarioActual;
        usuarioActual = null;
        return "El usuario '" + nombre + "' cerró sesión correctamente.";
    }


    private static String estadoSesion() {
        if (usuarioActual != null) {
            return "Usuario conectado: " + usuarioActual;
        }
        return "No hay ninguna sesión activa.";
    }


    private static String cambiarContraseña() {
        if (usuarioActual == null) {
            return "Debes iniciar sesión para cambiar tu contraseña.";
        }

        System.out.print("Ingresa tu contraseña actual: ");
        String actual = sc.nextLine();

        if (!usuariosRegistrados.get(usuarioActual).equals(actual)) {
            return "La contraseña actual no es correcta.";
        }

        System.out.print("Nueva contraseña: ");
        String nueva = sc.nextLine();

        System.out.print("Confirma la nueva contraseña: ");
        String confirmar = sc.nextLine();

        if (nueva.isEmpty()) return "La contraseña no puede estar vacía.";
        if (!nueva.equals(confirmar)) return "Las contraseñas no coinciden.";

        usuariosRegistrados.put(usuarioActual, nueva);
        guardarUsuarios();

        return "La contraseña fue cambiada correctamente.";
    }


    // --------------------- MENÚ PRINCIPAL --------------------- //

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== MENÚ DE LOGIN =====");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Cambiar contraseña");
            System.out.println("4. Estado de sesión");
            System.out.println("5. Cerrar sesión");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            String opcion = sc.nextLine();
            String resultado = "";

            switch (opcion) {
                case "1":
                    resultado = registrarUsuario();
                    break;
                case "2":
                    resultado = login();
                    break;
                case "3":
                    resultado = cambiarContraseña();
                    break;
                case "4":
                    resultado = estadoSesion();
                    break;
                case "5":
                    resultado = logout();
                    break;
                case "6":
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    resultado = "Opción no válida.";
            }

            System.out.println(resultado);
        }
    }
}