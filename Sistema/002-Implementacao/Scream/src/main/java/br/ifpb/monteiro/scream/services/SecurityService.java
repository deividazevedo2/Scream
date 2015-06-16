package br.ifpb.monteiro.scream.services;

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

import br.ifpb.monteiro.scream.dao.ContaDAO;
import br.ifpb.monteiro.scream.entities.Conta;

public class SecurityService {
	
	@Inject
	private ContaDAO contaDao;
	
	private void generatePassword(Conta conta, String plainTextPassword) {
		RandomNumberGenerator rng = new SecureRandomNumberGenerator();
		Object salt = rng.nextBytes();

		// Now hash the plain-text password with the random salt and multiple
		// iterations and then Base64-encode the value (requires less space than Hex):
		String hashedPasswordBase64 = new Sha256Hash(plainTextPassword, salt, 1024).toBase64();

		conta.setSenha(hashedPasswordBase64);
		conta.setSalt(salt.toString());
	}

	public void registrar(Conta conta, String plainTextPassword) {
		generatePassword(conta, plainTextPassword);
		contaDao.update(conta);
	}
	
	public boolean isAuthorized(String roleIdentifier){
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.hasRole(roleIdentifier)) {
			System.out.println("##########################################################");
		}
		return false;
	}

	public Conta login(String username, String password, Boolean rememberMe) {
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
			Conta conta = getCurrentUser(); 
			return conta;
		}
		return null;
	}

	public Conta getConta(String username){
		List<Conta> contas = contaDao.query("select conta from Conta conta "
				+ "where conta.usuario = ?1", 
				username);
		Conta conta = null;
		if (contas.size() == 1) {
			conta = contas.get(0);
		}
		return conta;
	}

	public Conta getCurrentUser() {
		Conta conta = null;
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			String login = (String) currentUser.getPrincipal();
			List<Conta> contas = contaDao.query("select conta from Conta conta "
					+ "where conta.usuario = ?1", login);
			if (contas.size() == 1) {
				conta = contas.get(0);
			}
		}
		return conta;
	}

	public void logout() {
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
	}

//	public static void main(String[] args) {
//		 EntityManagerFactory ef = Persistence.createEntityManagerFactory("Scream");
//		 EntityManager em = ef.createEntityManager();
//		 Query q = em.createQuery("select conta from Conta conta "
//					+ "where conta.usuario = ?1");
//		q.setParameter(1, "hugo");
//		Conta conta = null;
//		List<Conta> contas = q.getResultList();
//		if (contas !=null && !contas.isEmpty()) {
//			conta = contas.get(0);
//		}
//		SecurityService ss = new SecurityService();
//		ss.generatePassword(conta, "123");
//		em.getTransaction().begin();
//		em.merge(conta);
//		em.getTransaction().commit();
//		em.close();
//	}
	

}