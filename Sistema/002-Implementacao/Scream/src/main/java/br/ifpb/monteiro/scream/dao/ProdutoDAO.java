package br.ifpb.monteiro.scream.dao;

import javax.persistence.Query;

import br.ifpb.monteiro.scream.entities.Produto;
import br.ifpb.monteiro.scream.util.jpa.Transactional;

/**
 *
 * @author Markus
 */

public class ProdutoDAO extends GenericDAO<Produto>{

	private static final long serialVersionUID = 1L;

	public ProdutoDAO() {
		super(Produto.class);
	}

	@Transactional
	@Override
	public void delete(Produto entity) {
		
		if (getEntityManager().getTransaction().isActive()) {
		
		} else {
			
			getEntityManager().getTransaction().begin();
	
		}
		Query queryProduto = getEntityManager().createNativeQuery("DELETE FROM produto WHERE id = " + entity.getId());
		queryProduto.executeUpdate();
	}
	
}
