package actividades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Actividad implements Serializable{
	String tipo;
	public String description;
	
	String objetivo;
	
	String nivelDificultad;
	
	double duration;
	
	List<Actividad> ActividadesPrerrequisito;
	
	Date fechaLim;
	
	List<Actividad> actividadesOpcionales;
	
	String estado;
	
	boolean completado;
	
	boolean obligatoria;

	public List<Reseña> reseñas;
	
	List<Double> ratings;
	
	double qualification;
	
	double ratingProm;
	
	public Actividad(String tipo, String description, String objetivo, String nivelDificultad, double duration,
					 Date fechaLim, boolean obligatoria) {
		this.tipo = tipo;
		this.description = description;
		this.objetivo = objetivo;
		this.nivelDificultad = nivelDificultad;
		this.duration = duration;
		this.ActividadesPrerrequisito = new ArrayList<>();
		this.fechaLim = fechaLim;
		this.actividadesOpcionales = new ArrayList<>();
		this.estado = "No enviada";
		this.completado = false;
		this.obligatoria = obligatoria;
		this.reseñas = new ArrayList<>();
		this.ratings = new ArrayList<>();
		this.ratingProm = 0;
		this.qualification = -1;
	}

	public String getDescription() {
		return description;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getNivelDificultad() {
		return nivelDificultad;
	}

	public void setNivelDificultad(String nivelDificultad) {
		this.nivelDificultad = nivelDificultad;
	}

	public double getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<Actividad> getActividadesPrerrequisito() {
		return ActividadesPrerrequisito;
	}

	public void setActividadesPrerrequisito(List<Actividad> actividadesPrerrequisito) {
		ActividadesPrerrequisito = actividadesPrerrequisito;
	}

	public Date getFechaLim() {
		return fechaLim;
	}

	public void setFechaLim(Date fechaLim) {
		this.fechaLim = fechaLim;
	}

	public List<Actividad> getActividadesOpcionales() {
		return actividadesOpcionales;
	}

	public void setActividadesOpcionales(List<Actividad> actividadesOpcionales) {
		this.actividadesOpcionales = actividadesOpcionales;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isCompletado() {
		return completado;
	}

	public void setCompletado(boolean completado) {
		this.completado = completado;
	}

	public List<Reseña> getReseñas() {
		return reseñas;
	}

	public void setReseñas(List<Reseña> reseñas) {
		this.reseñas = reseñas;
	}

	public boolean isObligatoria() {
		return obligatoria;
	}

	public void setObligatoria(boolean obligatoria) {
		this.obligatoria = obligatoria;
	}

	public void addReseña(Reseña reseña) {
		this.reseñas.add(reseña);
	}
	
	public abstract void menu();
	
	public void calcularProm() {
        double total = 0;
        for (int i=0; i< this.ratings.size();i++) {
            total += this.ratings.get(i);
            }
        total = total/this.ratings.size();
        this.ratingProm = total;
    }
	
	public void addRating(double rating) {
		this.ratings.add(rating);
		calcularProm();
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public void addPreReq(Actividad actividad) {
		this.ActividadesPrerrequisito.add(actividad);
	}
	public abstract void completar();

    public abstract void iniciar();

	public double getRatingProm() {
		return ratingProm;
	}

	public void setRatingProm(double ratingProm) {
		this.ratingProm = ratingProm;
	}

	public void setDuracion(double duracion) {
		this.duration = duracion;
	}
	public double getQualification() {
		return qualification;
	}
	public void setQualification(double qualification) {
		this.qualification = qualification;
	}
}
