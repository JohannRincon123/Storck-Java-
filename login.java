import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class login {

    private HashMap<String, String> usuarios = new HashMap<>();
    private String usuarioActual = null;
    private final String archivoUsuarios = "usuarios.txt";
    private final Scanner sc = new Scanner(System.in);

    public login() {
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        File file = new File(archivoUsuarios);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("=");
                if (partes.length == 2) {
                    usuarios.put(partes[0], partes[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error cargando usuarios.");
        }
    }

    private void guardarUsuarios() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivoUsuarios))) {
            for (String user : usuarios.keySet()) {
                pw.println(user + "=" + usuarios.get(user));
            }
        } catch (IOException e) {
            System.out.println("Error guardando usuarios.");
        }
    }

    // ------------------ FUNCIONES DEL SISTEMA ------------------ //

    public String registrarUsuario() {
        System.out.print("Nuevo nombre de usuario: ");
        String user = sc.nextLine();

        if (usuarios.containsKey(user)) {
            return "Ese usuario ya existe.";
        }

        System.out.print("Contraseña: ");
        String pass1 = sc.nextLine();

        System.out.print("Confirmar contraseña: ");
        String pass2 = sc.nextLine();

        if (!pass1.equals(pass2)) return "Las contraseñas no coinciden.";
        if (pass1.isEmpty()) return "La contraseña no puede estar vacía.";

        usuarios.put(user, pass1);
        guardarUsuarios();
        return "Usuario registrado correctamente.";
    }

    public String iniciarSesion() {
        if (usuarioActual != null) {
            return "Ya hay una sesión activa con: " + usuarioActual;
        }

        System.out.print("Usuario: ");
        String user = sc.nextLine();

        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        if (!usuarios.containsKey(user)) return "El usuario no existe.";

        if (!usuarios.get(user).equals(pass)) return "Contraseña incorrecta.";

        usuarioActual = user;
        return "Bienvenido, " + user;
    }

    public String cambiarContraseña() {
        // Esta función **NUNCA** se muestra si no hay sesión.
        System.out.print("Contraseña actual: ");
        String actual = sc.nextLine();

        if (!usuarios.get(usuarioActual).equals(actual)) {
            return "La contraseña actual no es correcta.";
        }

        System.out.print("Nueva contraseña: ");
        String nueva = sc.nextLine();

        System.out.print("Confirmar nueva contraseña: ");
        String confirmar = sc.nextLine();

        if (!nueva.equals(confirmar)) return "No coinciden.";
        if (nueva.isEmpty()) return "No puede estar vacía.";

        usuarios.put(usuarioActual, nueva);
        guardarUsuarios();

        return "Contraseña cambiada correctamente.";
    }

    public String cerrarSesion() {
        String temp = usuarioActual;
        usuarioActual = null;
        return "Sesión cerrada de: " + temp;
    }

    public boolean haySesionActiva() {
        return usuarioActual != null;
    }

}