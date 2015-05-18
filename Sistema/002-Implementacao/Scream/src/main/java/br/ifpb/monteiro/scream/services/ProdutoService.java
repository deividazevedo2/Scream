package br.ifpb.monteiro.scream.services;

import br.ifpb.monteiro.scream.dao.ProdutoDAO;
import br.ifpb.monteiro.scream.entities.Produto;
import br.ifpb.monteiro.scream.util.jpa.Transactional;
import java.util.List;
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
    public void create(Produto entity) {
        try {
            this.produtoDAO.create(entity);
        } catch (Exception e) {
            System.err.println("Erro no Service: " + e.getMessage());
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
    
    @Transactional
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
