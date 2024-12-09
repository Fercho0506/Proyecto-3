package usuarios;

import java.io.Serializable;

public abstract class Usuario implements Serializable{
	protected String nombre;
	protected String correo;
	protected String password;
	protected String type;
	
	public Usuario(String nombre, String correo, String password, String type) {
		this.nombre = nombre;
		this.correo = correo;
		this.password = password;
		this.type = type;
	}
	
	public abstract void menu();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public String getEmail() {
		return correo;
    }

	public void setTipo(String type) {
		this.type = type;
	}

	public String getTipo() {
		return type;
	}
}
