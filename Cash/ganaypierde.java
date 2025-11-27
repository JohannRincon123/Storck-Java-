import java.time.LocalDate;
import java.util.*;

public class ganaypierde {

    static class Movimiento {
        String tipo;
        double monto;
        String descripcion;
        LocalDate fecha;

        Movimiento(String tipo, double monto, String descripcion) {
            this.tipo = tipo;
            this.monto = monto;
            this.descripcion = descripcion;
            this.fecha = LocalDate.now();
        }

        @Override
        public String toString() {
            return "Tipo: " + tipo +
                   " | Monto: $" + monto +
                   " | Fecha: " + fecha +
                   " | Descripcion: " + descripcion;
        }
    }

    static List<Movimiento> registros = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String opcion = "";

        while (!opcion.equals("4")) {

            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Registrar ganancia");
            System.out.println("2. Registrar perdida");
            System.out.println("3. Ver resumenes");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opcion: ");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    registrarMovimiento("Ganancia");
                    break;

                case "2":
                    registrarMovimiento("Perdida");
                    break;

                case "3":
                    verResumenes();
                    break;

                case "4":
                    System.out.println("\nSaliendo del sistema...");
                    break;

                default:
                    System.out.println("\nOpcion invalida.");
            }
        }
    }

    public static void registrarMovimiento(String tipo) {
        try {
            System.out.print("\nIngrese monto: ");
            double monto = Double.parseDouble(sc.nextLine());

            System.out.print("Ingrese descripcion: ");
            String descripcion = sc.nextLine();

            registros.add(new Movimiento(tipo, monto, descripcion));
            System.out.println("\n" + tipo + " registrada correctamente con fecha automatica.");

        } catch (NumberFormatException e) {
            System.out.println("Error: ingrese un numero valido.");
        }
    }

    public static void verResumenes() {
        if (registros.isEmpty()) {
            System.out.println("\nNo hay resumenes registrados.");
            return;
        }

        System.out.println("\n=== RESUMENES ===");
        for (int i = 0; i < registros.size(); i++) {
            Movimiento m = registros.get(i);
            System.out.println((i + 1) + ". " + m.tipo + " - $" + m.monto + " - " + m.fecha);
        }

        System.out.print("\nSeleccione un numero para ver detalles: ");

        try {
            int opcion = Integer.parseInt(sc.nextLine());

            if (opcion < 1 || opcion > registros.size()) {
                System.out.println("Numero invalido.");
                return;
            }

            System.out.println("\n--- DETALLE ---");
            System.out.println(registros.get(opcion - 1));

        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar un numero valido.");
        }
    }
}
