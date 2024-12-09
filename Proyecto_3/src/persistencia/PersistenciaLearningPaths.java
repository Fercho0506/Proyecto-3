package persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import learningPath.LearningPath;
import usuarios.Usuario;
import usuarios.Profesor;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PersistenciaLearningPaths {
    public static String FILE_NAME = "learningPaths.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public static void guardarLearningPaths(Map<String, LearningPath> mapaLearningPaths) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(mapaLearningPaths, writer);
            System.out.println("Learning paths guardados exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar los learning paths.");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, LearningPath> cargarLearningPaths(Map<String, Usuario> usuarios, boolean crearArchivoVacio) {
        Map<String, LearningPath> mapaLearningPaths = new HashMap<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Archivo de learning paths no encontrado.");
            if (crearArchivoVacio) {
                guardarLearningPaths(mapaLearningPaths);
                System.out.println("Archivo de learning paths vacío creado exitosamente: " + FILE_NAME);
            } else {
                System.out.println("Desea crear un archivo vacío?\n1. Si\n2. No");
                Scanner scanner = new Scanner(System.in);
                if (scanner.hasNextInt()) {
                    int opcion = scanner.nextInt();
                    scanner.nextLine();
                    if (opcion == 1) {
                        guardarLearningPaths(mapaLearningPaths);
                        System.out.println("Archivo de learning paths vacío creado exitosamente: " + FILE_NAME);
                    } else {
                        System.out.println("No se creará ningún archivo.");
                    }
                } else {
                    System.out.println("Entrada no válida. No se creará ningún archivo.");
                }
                scanner.close();
            }
            return mapaLearningPaths;
        }
        try (Reader reader = new FileReader(FILE_NAME)) {
            Type learningPathMapType = new TypeToken<Map<String, LearningPath>>() {}.getType();
            mapaLearningPaths = gson.fromJson(reader, learningPathMapType);
            System.out.println("Learning paths cargados exitosamente.");
            for (Map.Entry<String, LearningPath> entry : mapaLearningPaths.entrySet()) {
                LearningPath learningPath = entry.getValue();
                Usuario profesor = learningPath.getProfesor();
                if (profesor != null) {
                    Usuario usuario = usuarios.get(profesor.getEmail());
                    if (usuario instanceof Profesor) {
                        ((Profesor) usuario).addLearningPath(learningPath);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al cargar los learning paths.");
            e.printStackTrace();
        }
        return mapaLearningPaths;
    }

    public static void agregarLearningPath(LearningPath learningPath, String correo, Map<String, Usuario> usuarios) {
        Map<String, LearningPath> mapaLearningPaths = cargarLearningPaths(usuarios, true);
        Usuario usuario = usuarios.get(correo);
        if (usuario instanceof Profesor) {
            ((Profesor) usuario).addLearningPath(learningPath);
            learningPath.setProfesor((Profesor) usuario);
            if (mapaLearningPaths.get(learningPath.getTitulo()) == null) {
                mapaLearningPaths.put(learningPath.getTitulo(), learningPath);
                guardarLearningPaths(mapaLearningPaths);
            }
            System.out.println("Learning path añadido y guardado exitosamente.");
        } else {
            System.out.println("Ocurrió un error");
        }
    }

    public static LocalDate convertirFecha(String fecha) {
        return LocalDate.parse(fecha);
    }
}