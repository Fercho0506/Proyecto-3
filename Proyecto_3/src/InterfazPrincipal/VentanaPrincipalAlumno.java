package InterfazPrincipal;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.JFrame;

import actividades.Actividad;
import core.Mundo;
import learningPath.LearningPath;

@SuppressWarnings("serial")
public class VentanaPrincipalAlumno extends JFrame{
	
	private Mundo mundo;

    private PanelListaLearningEst pLista;
    private PanelDetallesLearning pDetalles;
    private PanelActividades actividades;
    
    public VentanaPrincipalAlumno( Mundo mundo )
    {
        this.mundo = mundo;
        setLayout( new BorderLayout( ) );

        pLista = new PanelListaLearningEst( this );
        add( pLista );
        
        List<Actividad> actvs= mundo.getActividades();
        actividades= new PanelActividades(actvs);
        add( actividades, BorderLayout.SOUTH );
        
        pDetalles = new PanelDetallesLearning( );
        add( pDetalles, BorderLayout.NORTH );

        actualizarlearnings( );

        setTitle( "Learniings" );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setSize( 400, 600 );
        setLocationRelativeTo( null );
        setVisible( true );
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
