package br.ifpb.monteiro.ads.projeto2.scream.exceptions;

/**
 *
 * @author Mauricio
 */
public class ScreamException extends Exception {
    
    private String msg;

    public ScreamException() {
    }
    
    public ScreamException(String message){
        super(message);
        this.msg = message;
    }
    
}
