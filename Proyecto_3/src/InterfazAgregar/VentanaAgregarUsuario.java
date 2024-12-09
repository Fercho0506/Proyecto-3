package InterfazAgregar;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JTextField;

import InterfazPrincipal.VentanaIngresar;
import core.Mundo;

@SuppressWarnings("serial")
public class VentanaAgregarUsuario extends JFrame{
	
	private JTextField correo;
	private JTextField contraseña;
	private JTextField tipoUsuario;
	private JTextField nombre;
	private Mundo mundo;
	
	public VentanaAgregarUsuario( Mundo mundo) {
		this.mundo=mundo;
		setLayout( new FlowLayout( ) );
		
		tipoUsuario= new JTextField("Ingrese que tipo de usuario es (Profesor o estudiante):");
		correo= new JTextField("Ingresar correo:");
		contraseña= new JTextField("Ingresar contraseña:");
		nombre= new JTextField("Ingrese su nombre:");
		
		add(tipoUsuario);
		add(correo);
		add(contraseña);
		add(nombre);
		
		setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 600 );
        setLocationRelativeTo( null );
        setVisible( true );
        
        Registrar();
	}
	
	public void Registrar() {
		String cor= correo.getText().strip();
		String us= tipoUsuario.getText().toLowerCase().strip();
		String con= contraseña.getText().strip();
		String nom= nombre.getText().strip();
		mundo.agregarUsuario(cor, con, us, nom);
		new VentanaIngresar(mundo);
        dispose();
		
	}
	
}
