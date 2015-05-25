/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.scream.controllers;

import br.ifpb.monteiro.scream.entities.CriterioAceitacao;
import br.ifpb.monteiro.scream.services.CriterioAceitacaoService;
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
public class CriterioAceitacaoController {
   
    @Inject
    CriterioAceitacaoService criterioAceitacaoService;
    
    CriterioAceitacao criterioAceitacao;
    
    @PostConstruct
    public void Init(){
        criterioAceitacao = new CriterioAceitacao();
    }
    
    public void create(){
        criterioAceitacaoService.create(criterioAceitacao);
    }
    
    public void remove(CriterioAceitacao entity) {
        this.criterioAceitacaoService.remove(entity);
    }
    
    public void update(CriterioAceitacao entity){
        this.criterioAceitacaoService.update(entity);
    }
    
    
    //Pesquisas no Banco
    
    public int count() {
        return criterioAceitacaoService.count();
    }
    
    public CriterioAceitacao find(Long id) {
        return (CriterioAceitacao) criterioAceitacaoService.find(id);
    }
    
    
    public List<CriterioAceitacao> findRange(int[] range) {
        return criterioAceitacaoService.findRange(range);
    }
    
    public List<CriterioAceitacao> findAll(){
        List<CriterioAceitacao> criterioAceitacao = criterioAceitacaoService.findAll();
        return criterioAceitacao;
    }
    
}
