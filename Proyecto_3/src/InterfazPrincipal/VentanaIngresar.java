package InterfazPrincipal;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;

import core.Mundo;
@SuppressWarnings("serial")
public class VentanaIngresar extends JFrame{
	
	private JTextField correo;
	private JTextField contraseña;
	private JTextField tipoUsuario;
	private Mundo mundo;
	
	public VentanaIngresar( Mundo mundo) {
		this.mundo=mundo;
		setLayout( new FlowLayout( ) );
		
		tipoUsuario= new JTextField("Ingrese que tipo de usuario es (Profesor o estudiante):");
		correo= new JTextField("Ingresar correo:");
		contraseña= new JTextField("Ingresar contraseña:");
		
		add(tipoUsuario);
		add(correo);
		add(contraseña);
		
		setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 600 );
        setLocationRelativeTo( null );
        setVisible( true );
        ingresar();
	}
	
	public void ingresar() {
		String cor= correo.getText().strip();
		String us= tipoUsuario.getText().toLowerCase().strip();
		String con= contraseña.getText().strip();
		boolean valido= mundo.validarUsuario(cor, con);
		if (valido && us=="profesor") {
			new VentanaPrincipalProfesor(mundo);
		}
		else {
			new VentanaPrincipalAlumno(mundo);
		}
        dispose();
		
	}
}
