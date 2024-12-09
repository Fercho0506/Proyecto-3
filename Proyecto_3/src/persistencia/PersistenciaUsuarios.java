package persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import usuarios.Usuario;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PersistenciaUsuarios {
    public static String FILE_NAME = "usuarios.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(Usuario.class, new UsuarioAdapter()) // Register the adapter here
            .setPrettyPrinting()
            .create();

    public static void guardarUsuarios(Map<String, Usuario> usuarios) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(usuarios, writer);
            System.out.println("Usuarios guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los usuarios.");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Usuario> cargarUsuarios() {
        Map<String, Usuario> usuarios = new HashMap<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Archivo de usuarios no encontrado.");
            System.out.println("Desea crear un archivo vacío?\n1. Si\n2. No");
            Scanner scanner = new Scanner(System.in);
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Clear the newline character

            if (opcion == 1) {
                guardarUsuarios(usuarios);
                System.out.println("Archivo de usuarios vacío creado exitosamente: " + FILE_NAME);
            } else {
                System.out.println("No se creará ningún archivo.");
            }

            scanner.close();
            return usuarios;
        }

        try (Reader reader = new FileReader(FILE_NAME)) {
            Type usuarioMapType = new TypeToken<Map<String, Usuario>>() {}.getType();
            usuarios = gson.fromJson(reader, usuarioMapType);
            System.out.println("Usuarios cargados exitosamente.");

            // Verificar que ningún usuario sea null
            for (Map.Entry<String, Usuario> entry : usuarios.entrySet()) {
                if (entry.getValue() == null) {
                    throw new IllegalStateException("Error al cargar usuarios: el usuario con correo " + entry.getKey() + " es null.");
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al cargar los usuarios.");
            e.printStackTrace();
        }

        return usuarios;
    }
}