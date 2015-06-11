package br.ifpb.monteiro.scream.controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.ifpb.monteiro.scream.entities.Conta;
import br.ifpb.monteiro.scream.services.ContaService;
import br.ifpb.monteiro.scream.services.SecurityService;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;

/**
 *
 * @author Mauricio
 */
@Named
@RequestScoped
//@Model
public class ContaController {
    
    @Inject
    private ContaService contaService;
    
    @Inject
    private SecurityService ss;
    
    private Conta usuarioLogado;
    
    private Conta conta;
    
    //	private String usuario;
    //
    //	private String senha;
    
    @PostConstruct
    public void Init(){
        this.conta = new Conta();
        
    }
    //	public ContaController() {
    //		if (this.conta == null) {
    //			this.conta = new Conta();
    //		}
    //	}
    
    public Conta getConta() {
        return conta;
    }
    
    public void setConta(Conta conta) {
        this.conta = conta;
    }
    
    public void create() {
        //		System.out.println(contaService);
        contaService.create(conta);
        JsfUtil.addSuccessMessage("Conta adicionada com sucesso!");
        //voltaTelaLogin();
    }
    //	public String getUsuario() {
    //		return usuario;
    //	}
    //
    //	public void setUsuario(String usuario) {
    //		this.usuario = usuario;
    //	}
    //
    //	public String getSenha() {
    //		return senha;
    //	}
    //
    //	public void setSenha(String senha) {
    //		this.senha = senha;
    //	}
    
    public ContaService getContaService() {
        return contaService;
    }
    
    public void setContaService(ContaService contaService) {
        this.contaService = contaService;
    }
    
    public String fazerLogin() {
        Conta conta  = null;
        try {
            conta = ss.login(this.conta.getUsuario(), this.conta.getSenha(), true);
        } catch (Exception e) {
            Logger.getLogger(ContaController.class.getName()).log(Level.SEVERE,"Erro na criação do Login", e);
            FacesContext
                    .getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Falha no Login:",
                                    "O login e senha informados não possuem credencias de acesso"));
        }
        return (conta != null)? "success" : null;
        
    }
    
    public void voltaTelaLogin() {
        try {
            ss.logout();
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("/Scream/login.xhtml");
        } catch (IOException ex) {
            JsfUtil.addErrorMessage(ex, "Pagina não encontrada");
            Logger.getLogger(ContaController.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    public Conta  usuarioLogado(){
        return usuarioLogado=ss.getCurrentUser();
    }
    public SecurityService getSs() {
        return ss;
    }
    
    public void setSs(SecurityService ss) {
        this.ss = ss;
    }
    
    public void edicao(){
        setConta(usuarioLogado());
    }
    
}
