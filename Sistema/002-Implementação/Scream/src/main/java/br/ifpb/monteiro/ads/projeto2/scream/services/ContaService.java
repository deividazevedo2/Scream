/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.ads.projeto2.scream.services;

import br.ifpb.monteiro.ads.projeto2.scream.beans.facade.ContaServiceIF;
import br.ifpb.monteiro.ads.projeto2.scream.dao.facade.ContaDaoIF;
import br.ifpb.monteiro.ads.projeto2.scream.dao.qualifers.ContaDaoQlf;
import br.ifpb.monteiro.ads.projeto2.scream.entities.Identifiable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 *
 * @author Mauricio
 */
@SessionScoped
public class ContaService implements ContaServiceIF{
    
    @EJB
    private ContaDaoIF contaDao;
    
    @Override
    public int count() {
        return contaDao.count();
    }

    @Transactional
    @Override
    public void create(Identifiable entity) {
        try{
            this.contaDao.create(entity);
            
        }catch(Exception e){
            System.err.println("Erro no Service: " + e.getMessage());
        }
    }

    @Override
    public void edit(Identifiable entity) {
        contaDao.edit(entity);
    }

    @Override
    public Identifiable find(Object id) {
        return contaDao.find(id);
    }

    @Override
    public List<Identifiable> findAll() {
        return contaDao.findAll();
    }

    @Override
    public List<Identifiable> findRange(int[] range) {
        return contaDao.findRange(range);
    }

    @Override
    public void remove(Identifiable entity) {
        this.contaDao.remove(entity);
    }
    
}
