package core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import learningPath.LearningPath;
import usuarios.Usuario;

public class Mundo {
	private Map<String, Usuario> usuarios = new HashMap<>();
    private Map<String, LearningPath> mapaLearningPaths = new HashMap<>();
    
    public Mundo() {
    	this.usuarios= Controller.cargarPersistenciaUsuarios();
    	this.mapaLearningPaths= Controller.cargarPersistenciaLearningPaths(usuarios);
    }
    
    public void agregarLearning( LearningPath learning) {
    	mapaLearningPaths.put(learning.getTitulo(), learning);
    	Controller.añadirLearningPath(usuarios, mapaLearningPaths, learning.getTitulo(), learning.getProfesor());
    }
    
    public List<LearningPath> getLearnings(){
    	return new ArrayList<LearningPath> (mapaLearningPaths.values());
    }
    
    public boolean validarUsuario(String correo, String con) {
    	return Controller.verificarIdentidad(usuarios, correo, con);
    }
    
    public void agregarUsuario(String correo, String con, String tipo, String nombre) {
    	if (tipo== "profesor") {
    		Controller.registrarProfesor(usuarios, nombre, correo, con);
    	}
    	else {
    		Controller.registrarEstudiante(usuarios, nombre, correo, con);
    	}
    }
}
