package InterfazAgregar;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class PanelEditarLearning  extends JPanel{
	private JTextField titulo;
	private JTextField descripcion;
	private JTextField nivel;
	private JTextField objetivos;
    private JComboBox<String> rating;
    private JComboBox<String> completado;
    
    public PanelEditarLearning( )
    {
    	JLabel nombreRest = new JLabel("Ingrese el nombre del Learning Path:");
        titulo = new JTextField(20);
        JLabel nombreDesc = new JLabel("Ingrese la descripcion del Learning Path:");
        descripcion = new JTextField(40);
        JLabel nombreNivel = new JLabel("Ingrese la dificultad del Learning Path:");
        nivel = new JTextField(10);
        JLabel nombreObj = new JLabel("Ingrese los objetivos del Learning Path:");
        objetivos = new JTextField(40);

        JLabel ingresarCalificaciones = new JLabel("Ingrese una calificación (de 1 a 5):");
        String[] calificaciones = { "1", "2", "3", "4", "5" }; 
        rating = new JComboBox<>(calificaciones);

        JLabel ingresarCompletado = new JLabel("¿Ya visitó el restaurante?");
        String[] visitado = { "Sí", "No" };
        completado = new JComboBox<>(visitado); 
        add(nombreRest); 
        add(titulo);
        add(nombreDesc);
        add(descripcion);
        add(nombreNivel);
        add(nivel);
        add(nombreObj);
        add(objetivos);
        add(ingresarCalificaciones); 
        add(rating); 
        add(ingresarCompletado); 
        add(completado);
    }
    
    public boolean getCompletado( )
    {
        return (completado.getSelectedItem() == "Si");
    }


    public int getRating( )
    {
        String calif = ( String )rating.getSelectedItem( );
        return Integer.parseInt( calif );
    }

    public String getNombre( )
    {
    	return titulo.getText().strip();
    }
    
    public String getNivel( )
    {
    	return nivel.getText().strip();
    }
    
    public String getObjetivos( )
    {
    	return objetivos.getText().strip();
    }
    
    public String getDescripcion( )
    {
    	return descripcion.getText().strip();
    }
}
