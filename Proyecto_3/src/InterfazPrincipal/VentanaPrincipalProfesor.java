package InterfazPrincipal;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import InterfazAgregar.VentanaCrearLearningPath;
import core.Mundo;
import learningPath.LearningPath;



@SuppressWarnings("serial")
public class VentanaPrincipalProfesor extends JFrame {
	
	private Mundo mundo;
	
	private PanelBotones pBotones;

    private PanelDetallesLearning pDetalles;

    private PanelListaLearning pLista;

    private VentanaCrearLearningPath ventanaAgregar;
    
    public VentanaPrincipalProfesor( Mundo mundo )
    {
        this.mundo = mundo;
        setLayout( new BorderLayout( ) );

        pBotones = new PanelBotones( this );
        add( pBotones, BorderLayout.NORTH );

        pLista = new PanelListaLearning( this );
        add( pLista );

        pDetalles = new PanelDetallesLearning( );
        add( pDetalles, BorderLayout.SOUTH );

        actualizarlearnings( );

        setTitle( "Learniings" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 600 );
        setLocationRelativeTo( null );
        setVisible( true );
    }

    public void mostrarVetanaNuevoLearning( )
    {
        if( ventanaAgregar == null || !ventanaAgregar.isVisible( ) )
        {
            ventanaAgregar = new VentanaCrearLearningPath( this );
            ventanaAgregar.setVisible( true );
        }
    }

    public void agregarLearning( String nombre, String descripcion, String objetivos, String nivel )
    {
        
    	LearningPath nuevo = new LearningPath(nombre, descripcion, objetivos, nivel);
        mundo.agregarLearning(nuevo);
        actualizarlearnings();
        cambiarLearningSeleccionado(nuevo);
    }

    public List<LearningPath> getLearnings()
    {
        return mundo.getLearnings();
    }

    private void actualizarlearnings( )
    {
        List<LearningPath> todos = this.mundo.getLearnings();

        pLista.actualizarLearnings(todos);
        if (!todos.isEmpty()) {
            cambiarLearningSeleccionado(todos.get(0));
        } else {
            pDetalles.actualizarLearning(null);
        }
    }

    
    public void cambiarLearningSeleccionado( LearningPath seleccionado )
    {
        pDetalles.actualizarLearning(seleccionado);
    }

    public static void main( String[] args )
    {
        Mundo mundo = new Mundo( );
        new VentanaValidarUsuario( mundo );
    }
}
