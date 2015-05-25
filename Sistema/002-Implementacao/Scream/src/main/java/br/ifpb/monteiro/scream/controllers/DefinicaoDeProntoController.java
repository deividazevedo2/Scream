/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.scream.controllers;

import br.ifpb.monteiro.scream.entities.DefinicaoDePronto;
import br.ifpb.monteiro.scream.services.DefinicaoDeProntoService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Mauricio
 */
@Named
@RequestScoped
public class DefinicaoDeProntoController {
    
    @Inject
            DefinicaoDeProntoService definicaoDeProntoService;
    
    DefinicaoDePronto definicaoDePronto;
    
    @PostConstruct
    public void Init(){
        this.definicaoDePronto = new DefinicaoDePronto();
        
    }
    
    public void update(){
        definicaoDeProntoService.update(definicaoDePronto);
    }
    
    public DefinicaoDePronto find(long id){
        return (DefinicaoDePronto)definicaoDeProntoService.find(id);
    }
    
    public List<DefinicaoDePronto> findAll(){
        List<DefinicaoDePronto> definicoes = definicaoDeProntoService.findAll();
        return definicoes;
    }

    public DefinicaoDeProntoService getDefinicaoDeProntoService() {
        return definicaoDeProntoService;
    }

    public void setDefinicaoDeProntoService(DefinicaoDeProntoService definicaoDeProntoService) {
        this.definicaoDeProntoService = definicaoDeProntoService;
    }

    public DefinicaoDePronto getDefinicaoDePronto() {
        return definicaoDePronto;
    }

    public void setDefinicaoDePronto(DefinicaoDePronto definicaoDePronto) {
        this.definicaoDePronto = definicaoDePronto;
    }
    
    
    
    
}
