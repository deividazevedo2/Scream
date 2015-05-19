/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.scream.services;

import br.ifpb.monteiro.scream.dao.ProjetoDAO;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.util.jpa.Transactional;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Mauricio
 */
public class ProjetoService {
    
    @Inject
    private ProjetoDAO projetoDao;

        @Transactional
    public void create(Projeto entity) {
        try {
            this.projetoDao.create(entity);
        } catch (Exception e) {
            System.err.println("Erro no Service: " + e.getMessage());
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
    
    public ProjetoDAO getProjetoDao() {
        return projetoDao;
    }

    public void setProjetoDao(ProjetoDAO projetoDao) {
        this.projetoDao = projetoDao;
    }
    
    
}
