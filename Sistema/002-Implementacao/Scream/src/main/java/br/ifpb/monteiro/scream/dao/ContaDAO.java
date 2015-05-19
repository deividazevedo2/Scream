package br.ifpb.monteiro.scream.dao;

import br.ifpb.monteiro.scream.entities.Conta;

import java.util.List;
import java.util.logging.Level;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Mauricio
 */
public class ContaDAO extends GenericDAO<Conta>{


	private static final long serialVersionUID = 1L;

	public ContaDAO() {
		super(Conta.class);
	}
}
