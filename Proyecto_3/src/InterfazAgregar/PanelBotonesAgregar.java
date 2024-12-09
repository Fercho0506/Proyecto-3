package InterfazAgregar;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class PanelBotonesAgregar  extends JPanel implements ActionListener{
	
	private static final String CREAR = "nuevo";
	
	private static final String CERRAR = "cerrar";

	private JButton butNuevo;
	private JButton butCerrar;
	
	private VentanaCrearLearningPath ventanaPrincipal;
	
	public PanelBotonesAgregar( VentanaCrearLearningPath principal) {
		this.ventanaPrincipal=principal;
		
		setLayout( new FlowLayout( ) );
		
		butNuevo = new JButton("Crear un Learning Path");
        butNuevo.setActionCommand(CREAR);
        butNuevo.addActionListener(this);
        add(butNuevo);

        butCerrar = new JButton("Cerrar ventana");
        butCerrar.setActionCommand(CERRAR);
        butCerrar.addActionListener(this); 
        add(butCerrar); 
	}
	
	 @Override
	    public void actionPerformed( ActionEvent e )
	    {
	        String comando = e.getActionCommand( );
	        if( comando.equals( CREAR ) )
	        {
	            ventanaPrincipal.agregarLearning( );
	        }
	        else if( comando.equals( CERRAR ) )
	        {
	            ventanaPrincipal.cerrarVentana( );
	        }
	    }
}
