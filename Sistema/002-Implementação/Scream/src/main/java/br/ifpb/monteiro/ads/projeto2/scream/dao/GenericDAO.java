/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.ads.projeto2.scream.dao;

//import br.edu.ifpb.monteiro.ads.sgp.dao.facades.GenericDaoIF;
//import br.edu.ifpb.monteiro.ads.sgp.model.Identifiable;
import br.ifpb.monteiro.ads.projeto2.scream.dao.facade.GenericDaoIF;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.ifpb.monteiro.ads.projeto2.scream.entities.Identifiable;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Mauricio
 * @param <T>
 */
@Default
@Dependent
public class GenericDAO<T extends Identifiable> implements GenericDaoIF {

    private Class<T> entityClass;
    private static final Logger logger = Logger.getGlobal();

    @PersistenceContext(unitName="com.mycompany_Scream_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public GenericDAO() {
    }

    public GenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void create(Identifiable entity) {
        logger.info("DAO Create Acessado");
        try {
            
            getEntityManager().persist(entity);
        } catch (Exception e) {
            logger.log(Level.INFO, "Erro no DAO: {0}", e.getMessage());
        }
        
    }

    @Override
    public void edit(Identifiable entity) {
        getEntityManager().merge(entity);
    }

    @Override
    public void remove(Identifiable entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    @Override
    public Identifiable find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public List<Identifiable> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    @Override
    public List<Identifiable> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

}
