
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistencia.PersistenciaLearningPaths;
import learningPath.LearningPath;
import usuarios.Profesor;
import usuarios.Usuario;
import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PersistenciaLearningPathsTest {
    private static final String TEST_FILE_NAME = "test_learningPaths.json";
    private Map<String, LearningPath> learningPaths;
    private Map<String, Usuario> usuarios;

    @BeforeEach
    void setUp() {
        learningPaths = new HashMap<>();
        usuarios = new HashMap<>();
        PersistenciaLearningPaths.FILE_NAME = TEST_FILE_NAME; // Use a test file to avoid conflicts
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testGuardarLearningPaths() {
        LearningPath learningPath = new LearningPath("Test Path", "Description", "Objectives", "Easy");
        learningPaths.put(learningPath.getTitulo(), learningPath);
        PersistenciaLearningPaths.guardarLearningPaths(learningPaths);

        File file = new File(TEST_FILE_NAME);
        assertTrue(file.exists(), "El archivo de learning paths debería existir después de guardar.");
    }

    @Test
    void testCargarLearningPaths() {
        LearningPath learningPath = new LearningPath("Test Path", "Description", "Objectives", "Easy");
        learningPaths.put(learningPath.getTitulo(), learningPath);
        PersistenciaLearningPaths.guardarLearningPaths(learningPaths);

        Map<String, LearningPath> loadedLearningPaths = PersistenciaLearningPaths.cargarLearningPaths(usuarios, true);
        assertEquals(1, loadedLearningPaths.size(), "Debería haber un learning path cargado.");
        assertTrue(loadedLearningPaths.containsKey(learningPath.getTitulo()), "El learning path cargado debería ser 'Test Path'.");
    }

    @Test
    void testConvertirFecha() {
        String fechaStr = "2023-10-01";
        LocalDate fecha = PersistenciaLearningPaths.convertirFecha(fechaStr);
        assertEquals(LocalDate.of(2023, 10, 1), fecha, "La fecha convertida debería ser 2023-10-01.");
    }
}