package br.ifpb.monteiro.scream.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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

import br.ifpb.monteiro.scream.dao.ContaDAO;
import br.ifpb.monteiro.scream.entities.Conta;
import br.ifpb.monteiro.scream.services.SecurityService;

public class ScreamRealm extends AuthorizingRealm {

	//	@Inject
	//	private SecurityService ss;

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
		BeanManager beanManager;
		Conta conta = null;

		try {
			beanManager = (BeanManager) new InitialContext().lookup("java:comp/BeanManager");
			Bean<ContaDAO> userDAObean = (Bean<ContaDAO>) beanManager.resolve(beanManager.getBeans(ContaDAO.class));
			CreationalContext<ContaDAO> creationalContext = beanManager.createCreationalContext(null);
			ContaDAO contaDao = userDAObean.create(creationalContext);

			List<Conta> contas = contaDao.query("select conta from Conta conta "
					+ "where conta.usuario = ?1");
			if (contas !=null && !contas.isEmpty()) {
				conta = contas.get(0);
			}
			//			Conta conta = userDAO.getConta(username);
			if (conta != null) {
				info = new ScreamSaltedAuthenticationInfo(username, conta.getSenha(), conta.getSalt());
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
