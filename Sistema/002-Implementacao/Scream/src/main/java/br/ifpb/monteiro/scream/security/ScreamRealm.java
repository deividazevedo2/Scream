package br.ifpb.monteiro.scream.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
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

	// Não sei se roda 0.0
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		EntityManager em = emf.createEntityManager();
		String username = (String) principal.getPrimaryPrincipal();
		Set<String> roles = new HashSet<String>();; // Isso n sairia null???
		SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();
		SecurityService ss = new SecurityService();
		Conta conta = ss.getConta(username);
	  //authorization.addStringPermission(IdeenProprietaPermissions.READ_WRITE.toString());
		authorization.setRoles(roles);
		//resgatar da base de dados as permissões e verificar se ele tem acesso ao recurso
		
		//Isso deveria retornar as roles do usuário referente a conta.
		/*
		 * ##########IMPORTANTE##########
		 * Depois precisamos adicionar projeto aqui. Pq cada conta pode 
		 * ter permissões diferentes para cada projeto
		 */
		Query q = em.createQuery("select usuario_projeto.roles from Conta conta, UsuarioProjeto usuario_projeto "
				+ "where conta.usuario = ?1 AND conta.id = usuario_projeto.id_conta");
		q.setParameter(1, username);
		
		List<String> role = q.getResultList();
		List<String> rol = (List<String>) authorization.getRoles();
		
		if (role.get(0).equals(rol.get(0))) {
			return authorization;
		}
		
		return null;
	}
	
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//    	Conta conta = (Conta) principalCollection.fromRealm(getName()).iterator().next();
//        // find user by login name
//        Long uid = conta.getId();
//        try {
//            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//            for (Role role : roleDao.getRoleByUid(uid)) {
//                info.addRole(role.getRole());
//            }
//            return info;
//        } catch (BaseException ex) {
//            return null;
//        }
//    }
}
