/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.scream.services;

import static br.ifpb.monteiro.scream.dao.GenericDAO.getLogger;
import br.ifpb.monteiro.scream.dao.ProjetoDAO;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.util.jpa.Transactional;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;

/**
 *
 * @author Mauricio
 */
public class ProjetoService {
    
    @Inject
    private ProjetoDAO projetoDao;
    
    @Inject
    private DefinicaoDeProntoService definicaoDeProntoService;
    
    @Transactional
    public Boolean create(Projeto entity) {
        try {
            definicaoDeProntoService.createDefinicaoProntoPB(entity);
            definicaoDeProntoService.createDefinicaoProntoRelease(entity);
            definicaoDeProntoService.createDefinicaoProntoSprint(entity);
            this.projetoDao.create(entity);
            return true;
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Erro no ProjetoService ", e);
            return false;
        }
    }
    
    public Projeto find(Long id) {
        return (Projeto) projetoDao.findById(id);
    }
    
    public List<Projeto> findAll() {
        return projetoDao.findAll();
    }
    
    public List<Projeto> findRange(int[] range) {
        return projetoDao.findRange(range);
    }
    
    @Transactional
    public void remove(Projeto entity) {
        this.projetoDao.delete(entity);
    }
    
    @Transactional
    public void update(Projeto entity){
        this.projetoDao.update(entity);
    }
    
    public ProjetoDAO getProjetoDao() {
        return projetoDao;
    }
    
    public void setProjetoDao(ProjetoDAO projetoDao) {
        this.projetoDao = projetoDao;
    }
    
    public DefinicaoDeProntoService getDefinicaoDeProntoService() {
        return definicaoDeProntoService;
    }
    
    public void setDefinicaoDeProntoService(DefinicaoDeProntoService definicaoDeProntoService) {
        this.definicaoDeProntoService = definicaoDeProntoService;
    }
    
}
