package br.ifpb.monteiro.scream.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import br.ifpb.monteiro.scream.entities.Conta;
import br.ifpb.monteiro.scream.services.SecurityService;

public class ScreamRealm extends AuthorizingRealm {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Scream"); 

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {

		SaltedAuthenticationInfo info = null;
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;
		String username = upToken.getUsername();
		String passwd = new String(upToken.getPassword());

		// Null username is invalid
		if (username == null) {
			throw new AccountException();
		}

		if (passwd == null || passwd.isEmpty()) {
			throw new UnknownAccountException();
		}

		//		SecurityService ss = new SecurityService();
		Conta conta = null;

		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select conta from Conta conta "
				+ "where conta.usuario = ?1 ");
		q.setParameter(1, username);
		
		@SuppressWarnings("unchecked")
		List<Conta> contas = q.getResultList();
		if (contas.size() == 1) {
			conta = contas.get(0);
		}
		em.close();
		if (conta != null) {
			info = new ScreamSaltedAuthenticationInfo(conta.getUsuario(), conta.getSenha(), conta.getSalt());
		}
		return info;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		String username = (String) principal.getPrimaryPrincipal();
		Set<String> roles = new HashSet<String>();;
		SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();
		SecurityService ss = new SecurityService();
		Conta conta = ss.getConta(username);
		//			authorization.addStringPermission(IdeenProprietaPermissions.READ_WRITE.toString());
		authorization.setRoles(roles);
		//resgatar da base de dados as permissões e verificar se ele tem acesso ao recurso
		return authorization;
	}
}
