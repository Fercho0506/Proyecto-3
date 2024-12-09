import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistencia.PersistenciaUsuarios;
import usuarios.Usuario;
import java.io.File;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PersistenciaUsuariosTest {

    @BeforeEach
    void setUp() {
        // Ensure the test starts with a clean state
        File file = new File(PersistenciaUsuarios.FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testGuardarUsuarios() {
        Map<String, Usuario> usuarios = new HashMap<>();
        Usuario usuario = new Usuario("test", "test@correo.com", "test123", "Estudiante") {
            @Override
            public void menu() {

            }
        };
        usuarios.put(usuario.getCorreo(), usuario);

        PersistenciaUsuarios.guardarUsuarios(usuarios);

        File file = new File(PersistenciaUsuarios.FILE_NAME);
        assertTrue(file.exists(), "El archivo de usuarios debería existir después de guardar.");
    }
}