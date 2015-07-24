/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.scream.services;

import static br.ifpb.monteiro.scream.dao.GenericDAO.getLogger;
import br.ifpb.monteiro.scream.dao.TesteAceitacaoDAO;
import br.ifpb.monteiro.scream.entities.TesteAceitacao;
import br.ifpb.monteiro.scream.util.jpa.Transactional;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;

/**
 *
 * @author Mauricio
 */
public class TesteAceitacaoService {
    
    @Inject
    private TesteAceitacaoDAO criterioAceitacaoDAO;
    
    
    @Transactional
    public void create(TesteAceitacao entity) {
        try {
            this.criterioAceitacaoDAO.create(entity);
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Erro no CriterioAceitacaoService", e);
        }
    }
    
    @Transactional
    public void update(TesteAceitacao entity){
        try {
            this.criterioAceitacaoDAO.update(entity);
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Erro no CriterioAceitacaoService", e);
        }
    }
    
    @Transactional
    public void remove(TesteAceitacao entity) {
        try {
            this.criterioAceitacaoDAO.delete(entity);
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Erro no CriterioAceitacaoService", e);
        }
    }
    
    public int count() {
        return criterioAceitacaoDAO.count();
    }
    
    public TesteAceitacao find(Long id) {
        return (TesteAceitacao) criterioAceitacaoDAO.findById(id);
    }
    
    public List<TesteAceitacao> findAll() {
        return criterioAceitacaoDAO.findAll();
    }
    
    public List<TesteAceitacao> findRange(int[] range) {
        return criterioAceitacaoDAO.findRange(range);
    }
    
    public TesteAceitacaoDAO getCriterioAceitacaoDAO() {
        return criterioAceitacaoDAO;
    }
    
    public void setCriterioAceitacaoDAO(TesteAceitacaoDAO criterioAceitacaoDAO) {
        this.criterioAceitacaoDAO = criterioAceitacaoDAO;
    }
    
    
    
}
