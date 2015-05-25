  /*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.scream.services;

import br.ifpb.monteiro.scream.dao.CriterioAceitacaoDAO;
import br.ifpb.monteiro.scream.entities.CriterioAceitacao;
import br.ifpb.monteiro.scream.util.jpa.Transactional;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Mauricio
 */
public class CriterioAceitacaoService {
    
    @Inject
    private CriterioAceitacaoDAO criterioAceitacaoDAO;
    
    
    @Transactional
    public void create(CriterioAceitacao entity) {
        try {
            this.criterioAceitacaoDAO.create(entity);
        } catch (Exception e) {
            System.err.println("Erro no CriterioAceitacaoService: " + e.getMessage());
        }
    }
    
    @Transactional
    public void update(CriterioAceitacao entity){
        try {
            this.criterioAceitacaoDAO.update(entity);
        } catch (Exception e) {
            System.err.println("Erro no CriterioAceitacaoService: " + e.getMessage());
        }
    }
    
    @Transactional
    public void remove(CriterioAceitacao entity) {
        try {
            this.criterioAceitacaoDAO.delete(entity);
        } catch (Exception e) {
            System.err.println("Erro no CriterioAceitacaoService: " + e.getMessage());
        }
    }
    
    public int count() {
        return criterioAceitacaoDAO.count();
    }
    
    public CriterioAceitacao find(Long id) {
        return (CriterioAceitacao) criterioAceitacaoDAO.findById(id);
    }
    
    public List<CriterioAceitacao> findAll() {
        return criterioAceitacaoDAO.findAll();
    }
    
    public List<CriterioAceitacao> findRange(int[] range) {
        return criterioAceitacaoDAO.findRange(range);
    }

    public CriterioAceitacaoDAO getCriterioAceitacaoDAO() {
        return criterioAceitacaoDAO;
    }

    public void setCriterioAceitacaoDAO(CriterioAceitacaoDAO criterioAceitacaoDAO) {
        this.criterioAceitacaoDAO = criterioAceitacaoDAO;
    }
    
    
    
}
