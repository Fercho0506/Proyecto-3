package learningPath;

import actividades.Actividad;
import usuarios.Profesor;
import usuarios.Usuario;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LearningPath implements Serializable {
	String titulo;
	String descripcion;
	String objetivos;
	String nivelDificultad;
	Usuario profesor;
	int duracion;
	int rating;
	List<Actividad> actividades;
	LocalDate fechaCreacion; 
	LocalDate fechaUltModificacion;
	
	
	
	public LearningPath (String titulo, String descripcion, String objetivos, String nivelDificultad) {
		super();
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.objetivos = objetivos;
		this.nivelDificultad = nivelDificultad;
		this.duracion = 0;
		this.rating = 0;
		this.actividades = new ArrayList<>();
		this.fechaCreacion = LocalDate.now();
		this.fechaUltModificacion = LocalDate.now();
	}


    public Usuario getProfesor() {
		return profesor;
	}



	public void setProfesor(Usuario profesor) {
		this.profesor = profesor;
	}



	public String getTitulo() {
		return titulo;
	}



	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getObjetivos() {
		return objetivos;
	}



	public void setObjetivos(String objetivos) {
		this.objetivos = objetivos;
	}



	public String getNivelDificultad() {
		return nivelDificultad;
	}



	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}



	public int getDuracion() {
		return duracion;
	}



	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}



	public int getRating() {
		return rating;
	}



	public void setRating(int rating) {
		this.rating = rating;
	}



	public List<Actividad> getActividades() {
		return actividades;
	}



	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}



	public LocalDate getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(LocalDate fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public LocalDate getFechaUltModificacion() {
		return fechaUltModificacion;
	}



	public void setFechaUltModificacion(LocalDate fechaUltModificacion) {
		this.fechaUltModificacion = fechaUltModificacion;
	}
	
	public void addActividad(Actividad actividad) {
		this.actividades.add(actividad);
		actualizarFechaUltModificacion();
		actualizarDuracion(actividad.getDuration());
	}
	public void eliminarActivdad(Actividad actividad) {
		this.actividades.remove(actividad);
		actualizarFechaUltModificacion();
		actualizarDuracion(actividad.getDuration());
	}
	
	private void actualizarFechaUltModificacion() {
		this.setFechaUltModificacion(LocalDate.now());
	}
	
	private void actualizarDuracion(double duracionIni) {
		duracion = this.getDuracion();
		duracion += (int) duracionIni;
		this.setDuracion(duracion);
	}
	
	public void eliminarActividad(Actividad actividad, LearningPath learningPath) {
		learningPath.eliminarActivdad(actividad);
	}
	public void mostrarActividades() {
		
		if(this.actividades.size()>0) {
			System.out.println("\nEstas son las actividades de "+ this.titulo+"\n");
			for(int i = 0; i<this.actividades.size();i++) {
				Actividad actividad = this.actividades.get(i);
	
				System.out.println("["+i+"]" + " " + actividad.getDescription());
				System.out.println("Tipo: "+ actividad.getTipo());
				System.out.println("Nivel: "+ actividad.getNivelDificultad());
				System.out.println("Objetivo: "+ actividad.getObjetivo());
				System.out.println("Duración estimada: "+ actividad.getDuration()+" minutos.");
				System.out.println("Fecha límite sugerida: "+ actividad.getFechaLim());
				System.out.println("------------------------------------------------");
				
			}
		}
		else {
			System.out.println("Todavía no hay actividades asociadas.");
		}
	}
	
	public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menú de " + this.titulo);
            System.out.println("");
            System.out.println("1. Ver actividades en " + this.titulo);
            System.out.println("2. Añadir actividad");
            System.out.println("3. Eliminar Actividad");
            System.out.println("4. Volver al menú anterior");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Clear the newline character

                switch (opcion) {
                    case 1:
                        mostrarActividades();
                        break;
                    case 2:
                        // Lógica para añadir actividad
                        System.out.println("Funcionalidad para añadir actividad no implementada.");
                        break;
                    case 3:
                        // Lógica para eliminar actividad
                        System.out.println("Funcionalidad para eliminar actividad no implementada.");
                        break;
                    case 4:
                        return; // Volver al menú anterior
                    default:
                        System.out.println("Opción inválida, intente de nuevo.");
                }
            } else {
                System.out.println("Entrada no válida. Intente de nuevo.");
                scanner.next(); // Clear the invalid input
            }
        }
    }
	
}
