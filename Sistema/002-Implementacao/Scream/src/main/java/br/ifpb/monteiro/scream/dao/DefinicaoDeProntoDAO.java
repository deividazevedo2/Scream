package br.ifpb.monteiro.scream.dao;

import br.ifpb.monteiro.scream.entities.DefinicaoDePronto;
import br.ifpb.monteiro.scream.exceptions.ScreamException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mauricio
 */
public class DefinicaoDeProntoDAO extends GenericDAO<DefinicaoDePronto>{

    public DefinicaoDeProntoDAO() {
        super(DefinicaoDePronto.class);
    }

    @Override
    public void create(DefinicaoDePronto entity) {
         List<DefinicaoDePronto> definicoes = query("select definicao from DefinicaoDePronto definicao "
                + "where definicao.projeto = ?1 AND definicao.tipoDefinicao = ?2", entity.getProjeto(),entity.getTipoDefinicao());
         
        if(definicoes.size()<1)
            super.create(entity);
        
    }  
    
}
