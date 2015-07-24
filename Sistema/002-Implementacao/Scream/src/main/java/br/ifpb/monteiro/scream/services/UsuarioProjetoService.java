/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.scream.services;

import static br.ifpb.monteiro.scream.dao.GenericDAO.getLogger;
import br.ifpb.monteiro.scream.dao.UsuarioProjetoDAO;
import br.ifpb.monteiro.scream.entities.UsuarioProjeto;
import br.ifpb.monteiro.scream.util.jpa.Transactional;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;

/**
 *
 * @author Mauricio
 */
public class UsuarioProjetoService {
    
    @Inject
    private UsuarioProjetoDAO usuarioProjetoDAO;
    
    public int count() {
        return usuarioProjetoDAO.count();
    }
    
    @Transactional
    public void create(UsuarioProjeto entity) {
        try {
            this.usuarioProjetoDAO.create(entity);
        } catch (Exception e) {
            getLogger().log(Level.SEVERE, "Erro no UsuarioProjeto", e);
        }
    }
    
    public UsuarioProjeto find(Long id) {
        return (UsuarioProjeto) usuarioProjetoDAO.findById(id);
    }
    
    public List<UsuarioProjeto> findAll() {
        return usuarioProjetoDAO.findAll();
    }
    
    public List<UsuarioProjeto> findRange(int[] range) {
        return usuarioProjetoDAO.findRange(range);
    }
    
    @Transactional
    public void remove(UsuarioProjeto entity) {
        this.usuarioProjetoDAO.delete(entity);
    }
    
    public UsuarioProjetoDAO getUsuarioProjetoDAO() {
        return usuarioProjetoDAO;
    }
    
    public void setUsuarioProjetoDAO(UsuarioProjetoDAO usuarioProjetoDAO) {
        this.usuarioProjetoDAO = usuarioProjetoDAO;
    }
    
}
