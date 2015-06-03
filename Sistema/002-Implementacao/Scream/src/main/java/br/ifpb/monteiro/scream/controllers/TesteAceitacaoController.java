/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.scream.controllers;

import br.ifpb.monteiro.scream.entities.TesteAceitacao;
import br.ifpb.monteiro.scream.services.TesteAceitacaoService;
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
public class TesteAceitacaoController {
   
    @Inject
    TesteAceitacaoService criterioAceitacaoService;
    
    TesteAceitacao criterioAceitacao;
    
    @PostConstruct
    public void Init(){
        criterioAceitacao = new TesteAceitacao();
    }
    
    public void create(){
        criterioAceitacaoService.create(criterioAceitacao);
    }
    
    public void remove(TesteAceitacao entity) {
        this.criterioAceitacaoService.remove(entity);
    }
    
    public void update(TesteAceitacao entity){
        this.criterioAceitacaoService.update(entity);
    }
    
    
    //Pesquisas no Banco
    
    public int count() {
        return criterioAceitacaoService.count();
    }
    
    public TesteAceitacao find(Long id) {
        return (TesteAceitacao) criterioAceitacaoService.find(id);
    }
    
    
    public List<TesteAceitacao> findRange(int[] range) {
        return criterioAceitacaoService.findRange(range);
    }
    
    public List<TesteAceitacao> findAll(){
        List<TesteAceitacao> criterioAceitacao = criterioAceitacaoService.findAll();
        return criterioAceitacao;
    }
    
}
