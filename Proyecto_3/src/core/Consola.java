package core;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import learningPath.LearningPath;
import usuarios.Usuario;

public class Consola {
    public static Map<String, Usuario> usuarios = new HashMap<>();
    public static Map<String, LearningPath> mapaLearningPaths = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        usuarios = Controller.cargarPersistenciaUsuarios();
        // imprimir usuarios
        for (Usuario usuario : usuarios.values()) {
            System.out.println(usuario.getNombre() + " - " + usuario.getCorreo() + " - " + usuario.getTipo());
        }
        mapaLearningPaths = Controller.cargarPersistenciaLearningPaths(usuarios);

        menu();
    }

    public static void menu() {
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println("1. Registrar usuario");
            System.out.println("2. Ingresar");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        registrarUsuario();
                        break;
                    case 2:
                        ingresar();
                        break;
                    case 3:
                        System.out.println("Saliendo...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción inválida, intente de nuevo.");
                }
            } else {
                System.out.println("Entrada no válida. Intente de nuevo.");
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static void ingresar() {
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        System.out.print("Contraseña: ");
        String password = scanner.nextLine();

        Usuario usuario = Controller.validarUsuario(usuarios, correo, password);
        if (usuario != null) {
            usuario.menu();
        } else {
            System.out.println("Correo o contraseña incorrectos.");
        }
    }

    private static void registrarUsuario() {
        System.out.println();
        System.out.println("1. Estudiante");
        System.out.println("2. Profesor");
        System.out.print("Seleccione el tipo de usuario: ");
        if (scanner.hasNextInt()) {
            int opcion = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Correo: ");
            String correo = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            switch (opcion) {
                case 1:
                    Controller.registrarEstudiante(usuarios, nombre, correo, password);
                    break;
                case 2:
                    Controller.registrarProfesor(usuarios, nombre, correo, password);
                    break;
                default:
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        } else {
            System.out.println("Entrada no válida. Intente de nuevo.");
            scanner.next(); // Clear the invalid input
        }
    }

    // Otros métodos...
}