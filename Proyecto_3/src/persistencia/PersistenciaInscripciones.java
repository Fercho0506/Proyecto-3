package persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import learningPath.LearningPath;
import usuarios.Estudiante;
import usuarios.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PersistenciaInscripciones {
    private static final String FILE_NAME = "inscripciones.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public static void guardarInscripciones(Map<String, String> inscripciones) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(inscripciones, writer);
            System.out.println("Inscripciones guardadas exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la inscripción: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, String> cargarInscripciones(Map<String, Usuario> usuarios, Map<String, LearningPath> learningPaths) {
        Map<String, String> inscripciones = new HashMap<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Archivo de inscripciones no encontrado.");
            System.out.println("Desea crear un archivo vacío?\n1. Si\n2. No");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine();
                if (opcion == 1) {
                    guardarInscripciones(inscripciones);
                    System.out.println("Archivo de inscripciones vacío creado exitosamente: " + FILE_NAME);
                } else {
                    System.out.println("No se creará ningún archivo.");
                }
            } else {
                System.out.println("Entrada no válida. No se creará ningún archivo.");
            }
            scanner.close();
            return inscripciones;
        }

        try (Reader reader = new FileReader(FILE_NAME)) {
            inscripciones = gson.fromJson(reader, inscripciones.getClass());
            System.out.println("Inscripciones cargadas exitosamente.");
            for (Map.Entry<String, String> entry : inscripciones.entrySet()) {
                String correoEstudiante = entry.getKey();
                String nombreLearningPath = entry.getValue();
                Usuario estudiante = usuarios.get(correoEstudiante);
                LearningPath learningPath = learningPaths.get(nombreLearningPath);
                if (estudiante instanceof Estudiante && learningPath != null) {
                    ((Estudiante) estudiante).addLearningPath(learningPath);
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al cargar las inscripciones.");
            e.printStackTrace();
        }

        return inscripciones;
    }
}
