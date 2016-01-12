package br.edu.ifpb.scream.security;

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

import br.edu.ifpb.scream.core.UserAccount;
import br.edu.ifpb.scream.team.Roles;
import br.edu.ifpb.scream.team.TeamMember;;

public class ScreamRealm extends AuthorizingRealm {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Scream"); 
	
	/**
	 * search from the user database and check the roles, by setting the corresponding session for returning
	 * a collection of all user roles for authorization checking for a determined feature   
	 * @param PrincipalCollection principal
	 * @return SimpleAuthorizationInfo authorization
	 * 
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		EntityManager em = emf.createEntityManager();
		String username = (String) principal.getPrimaryPrincipal();
		Set<String> roles = new HashSet<String>();; // Isso n sairia null???
		SimpleAuthorizationInfo authorization = new SimpleAuthorizationInfo();
		
		//resgatar da base de dados as permissões e verificar se ele tem acesso ao recurso
		
		//Isso deveria retornar as roles do usuário referente a conta.
		/*
		 * ##########IMPORTANTE##########
		 * Depois precisamos adicionar projeto aqui. Pq cada conta pode 
		 * ter permissões diferentes para cada projeto
		 */
		Query q = em.createQuery("select up from TeamMember up "
				+ "where up.userAccount.usuario=?1");
		q.setParameter(1, username);

		TeamMember individuo = (TeamMember) q.getResultList().get(0);
		if (individuo.getRoles() == Roles.ADM) {
			roles.add("ADM");
		}else if(individuo.getRoles() == Roles.PRODUCT_OWNER){
			roles.add("PRODUCT_OWNER");
		}else if (individuo.getRoles() == Roles.SCRUM_MASTER) {
			roles.add("SCRUM_MASTER");
		}else if (individuo.getRoles() == Roles.TEAM) {
			roles.add("TEAM");
		}
		
		authorization.setRoles(roles);

		Set<String> rol = authorization.getRoles(); 
		
		if (roles.isEmpty()) {
			return null;
		}else{
			return authorization;
		}
		
	}

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

		UserAccount userAccount = null;

		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select conta from UserAccount conta "
				+ "where conta.usuario = ?1 ");
		q.setParameter(1, username);
		
		@SuppressWarnings("unchecked")
		List<UserAccount> userAccounts = q.getResultList();
		if (userAccounts.size() == 1) {
			userAccount = userAccounts.get(0);
		}
		
		em.close();
		if (userAccount != null) {
			info = new ScreamSaltedAuthenticationInfo(userAccount.getUsuario(), userAccount.getSenha(), userAccount.getSalt());
		}
		return info;
	}

	
	
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//    	UserAccount conta = (UserAccount) principalCollection.fromRealm(getName()).iterator().next();
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
