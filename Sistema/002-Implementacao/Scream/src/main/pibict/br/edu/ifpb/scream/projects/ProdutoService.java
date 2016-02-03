package br.edu.ifpb.scream.projects;

import static br.edu.ifpb.scream.generic.GenericDAO.getLogger;
import br.edu.ifpb.scream.projects.dao.ProductDAO;
import br.edu.ifpb.scream.projects.Product;
import br.ifpb.monteiro.scream.util.jpa.Transactional;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;

/**
 *
 * @author Hugo Correia
 */
public class ProdutoService {
    
    @Inject
    private ProductDAO productDAO;
    
    public int count() {
        return productDAO.count();
    }
    
    @Transactional
    public boolean create(Product entity) {
        try {
            this.productDAO.create(entity);
            return true;
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Erro no Service ", e);
            return false;
        }
    }
    
    @Transactional
    public boolean update(Product entity){
        try{
            productDAO.update(entity);
            return true;
        }catch(Exception e){
            getLogger().log(Level.SEVERE, "Erro no Service ", e);
            return false;
        }
    }
    
    public Product find(Long id) {
        return productDAO.findById(id);
    }
    
    public List<Product> findAll() {
        return productDAO.findAllProduto();
    }
    
    public List<Product> findRange(int[] range) {
        return productDAO.findRange(range);
    }
    
    //@Transactional
    public void remove(Product entity) {
        this.productDAO.delete(entity);
    }
    
    public ProductDAO getProdutoDAO() {
        return productDAO;
    }
    
    public void setProdutoDAO(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    
    
}
