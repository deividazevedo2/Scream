/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.scream.controllers;

import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.services.ProjetoService;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;

import java.io.Serializable;
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
public class ProjetoController implements Serializable {
    
	private static final long serialVersionUID = -6619122444449272405L;

	@Inject
    private ProjetoService projetoService;
    
    private Projeto projeto;
    private Projeto projetoSelecionado;
    private List<Projeto> projetos;

    @PostConstruct
    public void Init(){
        projeto = new Projeto();
        projetos = projetoService.findAll();
        
    }
    
    public void create() {

    	projeto.setIsCompleted(false);
        projetoService.create(projeto);
        JsfUtil.addSuccessMessage("Projeto adicionado com sucesso!"); 
    }
    
    public void printa(){
    	System.out.println(projetoSelecionado);
    }
    
    public void update(){
        projetoService.update(projeto);
         JsfUtil.addSuccessMessage("Atualização do Projeto feita com sucesso!");
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

	public Projeto getProjetoSelecionado() {
		return projetoSelecionado;
	}

	public void setProjetoSelecionado(Projeto projetoSelecionado) {
		this.projetoSelecionado = projetoSelecionado;
	}

	public List<Projeto> getProjetos() {
		return projetos;
	}

	public void setProjetos(List<Projeto> projetos) {
		this.projetos = projetos;
	}
    
}
