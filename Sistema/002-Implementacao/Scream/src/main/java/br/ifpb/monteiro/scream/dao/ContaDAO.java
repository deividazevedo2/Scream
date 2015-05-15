package br.ifpb.monteiro.scream.dao;

import br.ifpb.monteiro.scream.entities.Conta;

import java.io.Serializable;

/**
 *
 * @author Mauricio
 */
public class ContaDAO extends GenericDAO<Conta>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ContaDAO() {
        super(Conta.class);
    }


}
