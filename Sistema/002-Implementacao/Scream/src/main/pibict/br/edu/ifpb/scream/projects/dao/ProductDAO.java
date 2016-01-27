
package br.edu.ifpb.scream.projects.dao;

import java.util.List;

import javax.persistence.Query;

import br.edu.ifpb.scream.generic.GenericDAO;
import br.edu.ifpb.scream.projects.Product;
import br.ifpb.monteiro.scream.util.jpa.Transactional;

/**
 *
 * @author Markus
 */

public class ProductDAO extends GenericDAO<Product>{

	private static final long serialVersionUID = 1L;

	public ProductDAO() {
		super(Product.class);
	}

	@Transactional
	@Override
	public void delete(Product entity) {
		
		if (getEntityManager().getTransaction().isActive()) {
		
		} else {
			
			getEntityManager().getTransaction().begin();
	
		}
		
		Query queryPro = getEntityManager().createNativeQuery("DELETE FROM teste_aceitacao USING item_product_backlog "
				+ "WHERE teste_aceitacao.item_product_backlog=item_product_backlog.id and item_product_backlog.produto_id = " + entity.getId());
		queryPro.executeUpdate();
		
		Query queryDef = getEntityManager().createNativeQuery("DELETE FROM item_product_backlog WHERE produto_id = " + entity.getId());
		queryDef.executeUpdate();
		
		Query queryProduto = getEntityManager().createNativeQuery("DELETE FROM produto WHERE id = " + entity.getId());
		queryProduto.executeUpdate();
	}
	

    public List<Product> findAllProduto() {
		
		List<Product> produtos = query("Select p From Product p Order By p.id");
		
		return produtos;
    }
}
