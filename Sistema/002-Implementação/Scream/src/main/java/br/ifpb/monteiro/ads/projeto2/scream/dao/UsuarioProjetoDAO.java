/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.ads.projeto2.scream.dao;

import br.ifpb.monteiro.ads.projeto2.scream.dao.facade.UsuarioProjetoDAOIF;
import br.ifpb.monteiro.ads.projeto2.scream.entities.UsuarioProjeto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Mauricio
 */
@Stateless
public class UsuarioProjetoDAO extends GenericDAO<UsuarioProjeto> implements UsuarioProjetoDAOIF{
    
    
    @PersistenceContext(unitName="Scream")
    private EntityManager entityManager;

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
 
    public UsuarioProjetoDAO(){
        super(UsuarioProjeto.class);
    }
    
}
