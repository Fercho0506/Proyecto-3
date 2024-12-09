package InterfazPrincipal;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import InterfazAgregar.VentanaAgregarUsuario;
import core.Mundo;

@SuppressWarnings("serial")
public class VentanaValidarUsuario extends JFrame implements ActionListener{
	
	private static final String INGRESAR = "ingresar";
	private static final String REGISTRAR = "registrarse";

	private JButton butIngreso;
	private JButton butRegistro;
	private Mundo mundo;
	
	public VentanaValidarUsuario(Mundo mundo) {
		this.mundo = mundo;
        setLayout( new FlowLayout( ) );
        
        JLabel titulo= new JLabel("Ingrese a la plataforma");
        add(titulo);
        
        butIngreso= new JButton ("Usuario existente");
        butIngreso.setActionCommand(INGRESAR);
        butIngreso.addActionListener(this);
        add(butIngreso);
        
        butRegistro= new JButton ("Nuevo usuario");
        butRegistro.setActionCommand(REGISTRAR);
        butRegistro.addActionListener(this);
        add(butRegistro);
        
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 600 );
        setLocationRelativeTo( null );
        setVisible( true );
	}
   
	@Override
	    public void actionPerformed( ActionEvent e )
    {
        String comando = e.getActionCommand( );
        if( comando.equals( INGRESAR ) )
        {
            new VentanaIngresar(mundo);
        }
        else if( comando.equals( REGISTRAR ) )
        {
            new VentanaAgregarUsuario(mundo);
        }
        dispose();
    }
	
}
