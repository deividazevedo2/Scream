package br.edu.ifpb.scream.security;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

import br.edu.ifpb.scream.core.UserAccount;
import br.edu.ifpb.scream.core.dao.UserAccountDAO;

/**
 * Classe responsável pelo acesso à segurança 
 * @author Hugo Correia
 *
 */
public class SecurityService {
	
	@Inject
	private UserAccountDAO userAccountDao;
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Scream"); 
	
	/**
	 * Recebe o usuário atual e a senha indicada no cadastro
	 * @param userAccount
	 * @param plainTextPassword
	 */
	private void generatePassword(UserAccount userAccount, String plainTextPassword) {
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		Object salt = rng.nextBytes();

		// Now hash the plain-text password with the random salt and multiple
		// iterations and then Base64-encode the value (requires less space than Hex):
		String hashedPasswordBase64 = new Sha256Hash(plainTextPassword, salt, 1024).toBase64();

		userAccount.setSenha(hashedPasswordBase64);
		userAccount.setSalt(salt.toString());
	}

	public void register(UserAccount userAccount, String plainTextPassword) {
		generatePassword(userAccount, plainTextPassword);
		userAccountDao.update(userAccount);
	}

	
	public boolean isAuthorized(String roleIdentifier){
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.hasRole(roleIdentifier)) {
			System.out.println("##########################################################");
			return true;
		}
		return false;
	}

	public UserAccount login(String username, String password, Boolean rememberMe) {
		// get the currently executing user:
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();

		if (!currentUser.isAuthenticated()) {
			//collect user principals and credentials in a gui specific manner
			//such as username/password html form, X509 certificate, OpenID, etc.
			//We'll use the username/password example here since it is the most common.
			UsernamePasswordToken token = new UsernamePasswordToken(username,password);
			//this is all you have to do to support 'remember me' (no config - built in!):
			token.setRememberMe(rememberMe);
			currentUser.login(token);

			// save current username in the session, so we have access to our User model
//			currentUser.getSession().setAttribute("login", username);
			UserAccount userAccount = getCurrentUser(); 
			return userAccount;
		}
		return null;
	}

	public UserAccount getUserAccount(String username){
		List<UserAccount> userAccounts = userAccountDao.query("select userAccount from UserAccount userAccount "
				+ "where userAccount.usuario = ?1", 
				username);
		UserAccount userAccount = null;
		if (userAccounts.size() == 1) {
			userAccount = userAccounts.get(0);
		}
		return userAccount;
	}

	public UserAccount getCurrentUser() {
		UserAccount userAccount = null;
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			String login = (String) currentUser.getPrincipal();
			List<UserAccount> userAccounts = userAccountDao.query("select userAccount from UserAccount userAccount "
					+ "where userAccount.usuario = ?1", login);
			if (userAccounts.size() == 1) {
				userAccount = userAccounts.get(0);
			}
		}
		return userAccount;
	}

	public void logout() {
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
	}

}