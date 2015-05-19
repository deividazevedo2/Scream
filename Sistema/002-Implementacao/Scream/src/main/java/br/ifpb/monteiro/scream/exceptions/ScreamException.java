package br.ifpb.monteiro.scream.exceptions;

/**
 *
 * @author Mauricio
 */
public class ScreamException extends Exception {
    

	private static final long serialVersionUID = 1L;
	
	private String msg;

    public ScreamException() {
    }
    
    public ScreamException(String message){
        super(message);
        this.msg = message;
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
    
    
}
