package br.edu.ifpb.scream.core;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.edu.ifpb.scream.security.SecurityService;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;

/**
 *
 * @author Mauricio
 */
@Named
@RequestScoped
//@Model
public class LoginController implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Inject
    private CoreService coreService;
    
    @Inject
    private SecurityService ss;
    
    private UserAccount usuarioLogado;
    
    private UserAccount userAccount;

    
    @PostConstruct
    public void Init(){
        this.userAccount = new UserAccount();
        
    }
    
    public UserAccount getUserAccount() {
        return userAccount;
    }
    
    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
    
    public void create() {
        //		System.out.println(coreService);
        coreService.createUserAccount(userAccount);
        JsfUtil.addSuccessMessage("UserAccount adicionada com sucesso!");
        //voltaTelaLogin();
    }    
    
    public String fazerLogin() {
    	UserAccount userAccount  = null;
        try {
            userAccount = ss.login(this.userAccount.getUsuario(), this.userAccount.getSenha(), true);
        } catch (Exception e) {
            Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE,"Erro na criação do Login", e);
            FacesContext
                    .getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Falha no Login:",
                                    "O login e senha informados não possuem credencias de acesso"));
        }
        return (userAccount != null)? "success" : null; 
    }
    
    public void voltarTelaLogin() {
        try {
            ss.logout();
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect("/Scream/login.xhtml");
        } catch (IOException ex) {
            JsfUtil.addErrorMessage(ex, "Pagina não encontrada");
            Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
    
    public UserAccount usuarioLogado(){
        return usuarioLogado=ss.getCurrentUser();
    }
    public SecurityService getSs() {
        return ss;
    }
    
    public void setSs(SecurityService ss) {
        this.ss = ss;
    }
    
    public void edicao(){
        setUserAccount(usuarioLogado());
    }
    
}