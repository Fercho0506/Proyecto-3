package usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import actividades.Actividad;
import actividades.Quiz;
import actividades.Recurso;
import core.Controller;
import core.Consola;
import learningPath.LearningPath;


public class Profesor extends Usuario {
	
	ArrayList<LearningPath> learningpaths;
	
	public Profesor(String nombre, String correo, String password) {
		super(nombre, correo, password, "Profesor");
		learningpaths = new ArrayList<>();
			}

	@Override
	public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menú Profesor");
            System.out.println("1. Crear Learning Path");
            System.out.println("2. Administrar Learning Paths");
            System.out.println("3. Volver al inicio");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character

                switch (opcion) {
                    case 1:
                        Controller.crearLearningPath(Consola.mapaLearningPaths, this, scanner);
                        break;
                    case 2:
                        Controller.mostrarLearningPathsSeleccionar(this, scanner);
                        break;
                    case 3:
                        return; // Volver al menú principal
                    default:
                        System.out.println("Opción inválida, intente de nuevo.");
                }
            } else {
                System.out.println("Entrada no válida. Intente de nuevo.");
                scanner.next(); // Clear the invalid input
            }
        }
    }
	
	public LearningPath crearLearningPath(String titulo, String descripcion, String objetivo,String nivelDificultad) {

        return new LearningPath(titulo,descripcion,objetivo,nivelDificultad);
	}
	
	public void mostrarLearningPaths() {
		if( learningpaths.size()>0) {
			System.out.println("\nEstos son los LearningPaths de "+ this.correo);
			for(int i = 0; i<learningpaths.size();i++) {
				LearningPath learningpath = learningpaths.get(i);
	
				System.out.println(i + "." + learningpath.getTitulo());
			}
		}
		else {
			System.out.println("Todavía no tienes Learning Paths asociados.");
		}
	}
	
	
	
	public Actividad crearQuiz(String descripcion, String objetivo, String nivelDificultad, int duracion,
								Date fechaLim, boolean obligatoria, double calificacionMin) {

        return new Quiz(descripcion, objetivo, nivelDificultad, duracion,
                 fechaLim, calificacionMin, obligatoria);
		}
	
	public Recurso crearRecurso(String descripcion, String objetivo, String nivelDificultad, int duracion,
			 Date fechaLim, boolean obligatoria, String tipoRecurso) {

        return new Recurso(descripcion, objetivo, nivelDificultad, duracion,
                 fechaLim, obligatoria,tipoRecurso);
		}
	
	public Actividad CrearExamen() {
			
			return null;
		}
	
	
	public void addLearningPath(LearningPath learningpath) {
		learningpaths.add(learningpath);
		
	}

	public ArrayList<LearningPath> getLearningpaths() {
		return learningpaths;
	}

	public void setLearningpaths(ArrayList<LearningPath> learningpaths) {
		this.learningpaths = learningpaths;
	}
}
