package InterfazPrincipal;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import learningPath.LearningPath;

@SuppressWarnings("serial")
public class PanelDetallesLearning extends JPanel{
	private JLabel titulo;
	private JLabel descripcion;
	private JLabel nivel;
	private JLabel objetivos;
    private JLabel rating;
    private JCheckBox completado;
    
    
    public PanelDetallesLearning( )
    {
    	setLayout(new GridLayout(3, 1));

    	titulo = new JLabel("Nombre:");
        add(titulo);
    	descripcion = new JLabel("Descripcion:");
        add(descripcion);
        nivel = new JLabel("Nivel de dificultad:");
        add(nivel);
        objetivos = new JLabel("Objetivos:");
        add(objetivos);

        rating = new JLabel("Ingrese su calificación (de 1 a 5)");
        add(rating);

        completado = new JCheckBox("Visitado");
        completado.setEnabled(false); 
        add(completado);
        
    }

    private void actualizarLearning( String nombre ,String desc, String obj, String niv, int rat, boolean comp)
    {
    	titulo.setText(nombre);
    	descripcion.setText(desc);
    	nivel.setText(niv);
    	objetivos.setText(obj);	
        rating.setText(Integer.toString(rat));
        completado.setSelected(comp);
    }

    public void actualizarLearning( LearningPath learning )
    {
        this.actualizarLearning( learning.getTitulo( ),learning.getDescripcion() , learning.getObjetivos(), 
        		learning.getNivelDificultad(), learning.getRating(), true);
    }
}
