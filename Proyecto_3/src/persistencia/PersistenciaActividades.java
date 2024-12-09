package persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import actividades.*;
import core.Controller;
import learningPath.LearningPath;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class PersistenciaActividades {
    private static final String FILE_NAME = "actividades.json";
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

    public static void guardarActividades(Map<String, List<Actividad>> mapaActividades) {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(mapaActividades, writer);
            System.out.println("Actividades guardadas exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al guardar las actividades.");
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static Map<String, List<Actividad>> cargarActividades(Map<String, LearningPath> mapaLearningPaths) {
        Map<String, List<Actividad>> mapaActividades = new HashMap<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("Archivo de actividades no encontrado.");
            System.out.println("Desea crear un archivo vacío?\n1. Si\n2. No");
            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine();
                if (opcion == 1) {
                    guardarActividades(mapaActividades);
                    System.out.println("Archivo de actividades vacío creado exitosamente: " + FILE_NAME);
                } else {
                    System.out.println("No se creará ningún archivo.");
                }
            } else {
                System.out.println("Entrada no válida. No se creará ningún archivo.");
            }
            scanner.close();
            return mapaActividades;
        }

        try (Reader reader = new FileReader(FILE_NAME)) {
            mapaActividades = gson.fromJson(reader, mapaActividades.getClass());
            System.out.println("Actividades cargadas exitosamente.");
            for (Map.Entry<String, List<Actividad>> entry : mapaActividades.entrySet()) {
                String learningPathTitle = entry.getKey();
                List<Actividad> actividades = entry.getValue();
                LearningPath learningPath = mapaLearningPaths.get(learningPathTitle);
                if (learningPath != null) {
                    learningPath.setActividades(actividades);
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al cargar las actividades.");
            e.printStackTrace();
        }

        return mapaActividades;
    }

    public static String convertirFechatoString(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(fecha);
    }

    public static Date convertirFecha(String fecha) {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formato.parse(fecha);
        } catch (ParseException e) {
            System.out.println("Error al convertir la fecha: " + e.getMessage());
            return null;
        }
    }
}
