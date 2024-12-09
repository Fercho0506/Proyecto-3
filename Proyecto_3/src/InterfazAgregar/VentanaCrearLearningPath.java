package InterfazAgregar;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import InterfazPrincipal.VentanaPrincipalProfesor;



@SuppressWarnings("serial")
public class VentanaCrearLearningPath extends JFrame{
	
	private PanelEditarLearning panelDetalles;

    private PanelBotonesAgregar panelBotones;

    private VentanaPrincipalProfesor ventanaPrincipal;
    
    public VentanaCrearLearningPath( VentanaPrincipalProfesor principal )
    {
        this.ventanaPrincipal = principal;
        setLayout( new BorderLayout( ) );

 
        panelBotones = new PanelBotonesAgregar(this);
        
        JPanel panelSur = new JPanel(new BorderLayout());
        panelSur.add(panelDetalles, BorderLayout.CENTER);
        panelSur.add(panelBotones, BorderLayout.SOUTH);
        add(panelSur, BorderLayout.SOUTH);
        setTitle("Crear LearningPath");
      
        pack( );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setResizable( false );
    }

    public void agregarLearning( )
    {
    	String nombre = panelDetalles.getNombre();
    	String nivel = panelDetalles.getNivel();
    	String objetivos = panelDetalles.getObjetivos();
    	String descripcion = panelDetalles.getDescripcion();

        if (nombre.isEmpty()) {
            System.out.println("El nombre del LearningPath no puede estar vacío.");
            return;
        }

        ventanaPrincipal.agregarLearning(nombre, descripcion, objetivos, nivel);

        cerrarVentana();
    }

    public void cerrarVentana( )
    {
        dispose( );
    }
}
