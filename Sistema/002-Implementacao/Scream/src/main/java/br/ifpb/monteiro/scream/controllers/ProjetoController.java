/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.scream.controllers;

import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.services.ProjetoService;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author Mauricio
 */
public class ProjetoController {
    
    @Inject
    private ProjetoService projetoService;
    
    private Projeto projeto;
    
    @PostConstruct
    public void Init(){
        projeto = new Projeto();
    }
    
    public void create() {
        //		System.out.println(contaService);
        projetoService.create(projeto);
        JsfUtil.addSuccessMessage("Projeto adicionado com sucesso!");
        
    }
    
    public ProjetoService getProjetoService() {
        return projetoService;
    }
    
    public void setProjetoService(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }
    
    public Projeto getProjeto() {
        return projeto;
    }
    
    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
}
