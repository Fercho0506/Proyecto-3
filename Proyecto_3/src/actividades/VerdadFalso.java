package Activities;

import java.util.List;

public class VerdadFalso {
	private String questionText;
    private String explanation;
    private String respuesta;
    
    public VerdadFalso (String questionText,String explanation, String respuesta) {
    	this.questionText=questionText;
    	this.explanation= explanation;
    	this.respuesta= respuesta;
    }
    
    
    public String getRespuesta() {
    	return respuesta;
    }
    public boolean validar(String respuesta) {
    	
    	if (respuesta == getRespuesta()) {
    		return true;
    	}
    	return false;
    }
}
