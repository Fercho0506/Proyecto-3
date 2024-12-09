package InterfazPrincipal;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import learningPath.LearningPath;



@SuppressWarnings("serial")
public class PanelListaLearning extends JPanel implements ListSelectionListener{
	
	 private JList<LearningPath> listaDeLearnings;
	 private DefaultListModel<LearningPath> dataModel;
	 private VentanaPrincipalProfesor ventanaPrincipal;
	
	 public PanelListaLearning( VentanaPrincipalProfesor ventanaPrincipal )
	    {
	        this.ventanaPrincipal = ventanaPrincipal;
	        setBorder( new TitledBorder( "LearningPaths" ) );
	        setLayout( new BorderLayout( ) );
	        
	        dataModel = new DefaultListModel<>( );
	        listaDeLearnings = new JList<>( dataModel );
	        listaDeLearnings.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
	        listaDeLearnings.addListSelectionListener( this );

	        
	        JScrollPane scroll = new JScrollPane( listaDeLearnings );
	        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
	        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

	        add( scroll );
	    }
	 
	 
	    public void actualizarLearnings( List<LearningPath> learnings )
	    {
	        List<LearningPath> nuevosLearnings = new ArrayList<LearningPath>( );
	        for( LearningPath q : learnings )
	        {
	            if( !dataModel.contains( q ) )
	            	nuevosLearnings.add( q );
	        }
	        dataModel.addAll( nuevosLearnings );
	    }

	    @Override
	    public void valueChanged( ListSelectionEvent e )
	    {
	        LearningPath seleccionado = listaDeLearnings.getSelectedValue( );

	        this.ventanaPrincipal.cambiarLearningSeleccionado( seleccionado );
	    }


	    public void seleccionarLearning( LearningPath learning )
	    {
	    	listaDeLearnings.setSelectedValue( learning, true );
	    }
	 
}
