package br.edu.ifpb.scream.core.dao;

import br.edu.ifpb.scream.core.UserAccount;
import br.edu.ifpb.scream.generic.GenericDAO;

/**
 *
 * @author Mauricio
 */
public class UserAccountDAO extends GenericDAO<UserAccount>{


	private static final long serialVersionUID = 1L;

	public UserAccountDAO() {
		super(UserAccount.class);
	}
}
