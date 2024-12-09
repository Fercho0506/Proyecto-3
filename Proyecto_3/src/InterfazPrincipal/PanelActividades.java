package InterfazPrincipal;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import actividades.Actividad;

@SuppressWarnings("serial")
public class PanelActividades extends JPanel{
	
	 private JTable tablaActividades;
	 
	 public PanelActividades(List<Actividad> actividades) {
		 
	 String[] atributos = {
	            "Tipo", "Descripción", "Objetivo", "Nivel Dificultad", 
	            "Duración", "Fecha Límite", "Estado", "Obligatoria", 
	            "Promedio de Rating", "Completado"};
	 DefaultTableModel Tabla = new DefaultTableModel(atributos, 0);

     for (Actividad actividad : actividades) {
         Object[] fila = {
             actividad.getTipo(),
             actividad.getDescription(),
             actividad.getObjetivo(),
             actividad.getNivelDificultad(),
             actividad.getDuration(),
             actividad.getFechaLim(),
             actividad.getEstado(),
             actividad.isObligatoria() ? "Sí" : "No",
             actividad.getRatingProm(),
             actividad.isCompletado() ? "Sí" : "No"
         };
         Tabla.addRow(fila);
	 
	 }
     tablaActividades = new JTable(Tabla);
     tablaActividades.setFillsViewportHeight(true);
     JScrollPane scrollPane = new JScrollPane(tablaActividades);
     add(scrollPane, BorderLayout.CENTER);

     JLabel titulo = new JLabel("Actividades", SwingConstants.CENTER);
     add(titulo, BorderLayout.NORTH);
	 }

}
