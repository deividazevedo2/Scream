package br.ifpb.monteiro.scream.services;

import static br.ifpb.monteiro.scream.dao.GenericDAO.getLogger;
import br.ifpb.monteiro.scream.dao.ProdutoDAO;
import br.ifpb.monteiro.scream.entities.Produto;
import br.ifpb.monteiro.scream.util.jpa.Transactional;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;

/**
 *
 * @author Markus
 */
public class ProdutoService {
    
    @Inject
    private ProdutoDAO produtoDAO;
    
    public int count() {
        return produtoDAO.count();
    }
    
    @Transactional
    public boolean create(Produto entity) {
        try {
            this.produtoDAO.create(entity);
            return true;
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Erro no Service ", e);
            return false;
        }
    }
    
    @Transactional
    public boolean update(Produto entity){
        try{
            produtoDAO.update(entity);
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    public Produto find(Long id) {
        return (Produto) produtoDAO.findById(id);
    }
    
    public List<Produto> findAll() {
        return produtoDAO.findAll();
    }
    
    public List<Produto> findRange(int[] range) {
        return produtoDAO.findRange(range);
    }
    
    //@Transactional
    public void remove(Produto entity) {
        this.produtoDAO.delete(entity);
    }
    
    public ProdutoDAO getProdutoDAO() {
        return produtoDAO;
    }
    
    public void setProdutoDAO(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }
    
    
}
