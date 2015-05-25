/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.scream.entities.enums;

/**
 *
 * @author Mauricio
 */
public enum DefinicaoDeProntoEnum {
    
    SPRINT(1),
    PRODUCTBACKLOG(2),
    RELEASE(3);
    
    private int tipoDefinicao;
    
    DefinicaoDeProntoEnum(int tipoDefinicao){
        this.tipoDefinicao = tipoDefinicao;
    }

    public int getTipoDefinicao() {
        return tipoDefinicao;
    }

    public void setTipoDefinicao(int tipoDefinicao) {
        this.tipoDefinicao = tipoDefinicao;
    }
    

}
