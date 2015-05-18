package br.ifpb.monteiro.scream.dao;

import br.ifpb.monteiro.scream.entities.Produto;

/**
 *
 * @author Markus
 */

public class ProdutoDAO extends GenericDAO<Produto>{

    private static final long serialVersionUID = 1L;
    
    public ProdutoDAO() {
        super(Produto.class);
    }
    
    
}
