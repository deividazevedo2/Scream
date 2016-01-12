package br.edu.ifpb.scream.core;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.edu.ifpb.scream.core.dao.UserAccountDAO;
import br.edu.ifpb.scream.security.SecurityService;
import br.ifpb.monteiro.scream.util.jpa.Transactional;
import br.ifpb.monteiro.scream.util.jsf.JsfUtil;

/**
 *
 * @author Mauricio
 */
public class CoreService {

	@Inject
	private UserAccountDAO userAccountDAO;

	@Inject
	private SecurityService securityService;

	public CoreService() {
		//        this.userAccountDao = new UserAccountDAO();
	}


	private boolean validarEmail(UserAccount entity){

		List<UserAccount> userAccounts = userAccountDAO.query("select userAccount from UserAccount userAccount "
				+ "where userAccount.email = ?1",
				entity.getEmail());

		if (userAccounts.size() > 0) {
			return false;
		}
		return true;
	}

	private boolean validarUsuario(UserAccount entity){

		List<UserAccount> userAccounts = userAccountDAO.query("select userAccount from UserAccount userAccount "
				+ "where userAccount.usuario = ?1",
				entity.getUsuario());

		if (userAccounts.size() > 0) {
			return false;
		}
		return true;
	}
	
	private boolean validarPass(UserAccount entity, String confirPass){
		if (entity.getSenha().equals(confirPass))
			return true;
		
		return false;
	}

	public int count() {
		return userAccountDAO.count();
	}

	//UserAccount Services

	@Transactional
	public boolean createUserAccount(UserAccount entity, String confirPass) {
		
		if(!validado(entity, confirPass)){
			
			if (!validarPass(entity, confirPass)){
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Falha no Registro:",
								"Password não confirmado!"));
			}if (!validarEmail(entity)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Falha no Registro:",
								"Ja existe uma userAccount com esse Email"));
			
			} if (!validarUsuario(entity)) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_WARN,
								"Falha no Registro:",
								"Ja existe uma userAccount com esse Username"));
			} 
			return false;
		}else{

			this.userAccountDAO.create(entity);
			this.securityService.register(entity, entity.getSenha());
			voltaTelaLogin();
			return true;
		}
	}

	@Transactional
	public void removeUserAccount(UserAccount entity) {
		this.userAccountDAO.delete(entity);
	}
	
	public boolean validado(UserAccount entity, String confirPass){
		return validarEmail(entity) && validarUsuario(entity) && validarPass(entity, confirPass);
	}

	public UserAccount findUserAccount(Long id) {
		return (UserAccount) userAccountDAO.findById(id);
	}

	public List<UserAccount> findAllUserAccount() {
		return userAccountDAO.findAll();
	}

	public List<UserAccount> findRangeUserAccount(int[] range) {
		return userAccountDAO.findRange(range);
	}


	public UserAccountDAO getUserAccountDao() {
		return userAccountDAO;
	}

	public void setUserAccountDao(UserAccountDAO userAccountDao) {
		this.userAccountDAO = userAccountDao;
	}


	//


	public void voltaTelaLogin() {
		try {
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("/Scream/login.xhtml");
		} catch (IOException ex) {
			JsfUtil.addErrorMessage(ex, "Pagina não encontrada");
			Logger.getLogger(UserAccountController.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
}
