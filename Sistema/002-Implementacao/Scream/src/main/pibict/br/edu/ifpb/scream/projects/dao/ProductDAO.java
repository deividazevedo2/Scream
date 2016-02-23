
package br.edu.ifpb.scream.projects.dao;

import java.util.List;

import javax.persistence.Query;

import br.edu.ifpb.scream.generic.GenericDAO;
import br.edu.ifpb.scream.projects.Product;
import br.ifpb.monteiro.scream.util.jpa.Transactional;

/**
 *
 * @author Mauricio
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
		
		Query queryProduto = getEntityManager().createNativeQuery("DELETE FROM product WHERE id = " + entity.getId());
		queryProduto.executeUpdate();
	}
	

    public List<Product> findAllProduto() {
		
		List<Product> produtos = query("Select p From Product p Order By p.id");
		
		return produtos;
    }
    
    @Override
    public Product findById(Long id) {
		
		List<Product> product = query("Select p From Product p Where p.id=?1",id);
		
		return product.get(0);
    }
}
