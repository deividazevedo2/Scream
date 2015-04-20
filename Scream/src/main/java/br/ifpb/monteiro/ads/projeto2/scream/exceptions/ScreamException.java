/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
