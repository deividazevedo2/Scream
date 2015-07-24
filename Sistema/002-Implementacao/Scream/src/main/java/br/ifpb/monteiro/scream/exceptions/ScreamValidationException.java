package br.ifpb.monteiro.scream.exceptions;

/**
 *
 * @author Mauricio
 */
public class ScreamValidationException extends Exception {

	private static final long serialVersionUID = -2106711612590991520L;
	
	private String msg;

    public ScreamValidationException() {
    }
    
    public ScreamValidationException(String message){
        super(message);
        this.setMsg(message);
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
    
    
}
