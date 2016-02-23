package br.edu.ifpb.scream.generic;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.eclipse.persistence.internal.jpa.EntityManagerFactoryProvider;

import br.ifpb.monteiro.scream.util.jpa.Transactional;
import javax.persistence.Transient;

/**
 * Class is a generic DAO that provides the basis
 * for the implementation of all DAOs (superclass), 
 * which contains basic operations with the database.
 * @author Mauricio
 * @param <T>
 */
public class GenericDAO<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
	
    private static final Logger logger = Logger.getGlobal();
    
    @Transient
    @Inject
    private EntityManager entityManager;
    
    protected Class<T> entity;
    
    /**
     * Class constructor that captures the entity that call this class.
     * @param entityClass
     */
    public GenericDAO(Class<T> entityClass) {
        this.entity = entityClass;
    }
    
    /**
     * Method used to save a new account on database or edit an existing registration.
     *
     * @param entity
     */
    public void update(T entity) {
        getLogger().info("DAO Create Acessado");
        try {
            entityManager.merge(entity);
        } catch (Exception e) {
            getLogger().log(Level.WARNING, entity.toString(), e);
            getLogger().log(Level.INFO, "Erro no DAO: {0}", e.getMessage());
        }
        
    }
    
    /**
     * Method used to save a new account on database.
     *
     * @param entity
     */
    public void create(T entity) {
        getLogger().info("DAO Create Acessado");
        
        try{
            entityManager.persist(entity);
        }catch(Exception e){
            getLogger().log(Level.WARNING, entity.toString(), e);
            getLogger().log(Level.INFO, "Erro no DAO: {1}", e.getMessage());
            
        }
        
    }
    
    /**
     * Method used to remove an account on database.
     *
     * @param entity
     */
    @Transactional
    public void delete(T entity) {
        entityManager.remove(entity);
    }
    
    /**
	 * Method to return a list of all the results found in the database
	 * for the entity that call. 
	 * The query is made through Criteria.
     *
     * @return List<T>
     */
    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entity));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    /**
     * Method used to fetch a record in the database
	 * for a certain entity (T) by passing its ID as a parameter.
     *
     * @param id
     * @return T object;
     */
    public T findById(Long id) {
    	
    	return entityManager.find(entity, id);
    }
    
    /**
     * Method used to get a list in a certain range
	 * in the database record of a specific (T) entity.
     * @param range
     * @return List<T>
     */
    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entity));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
    
    /**
     *	Method used to return the size of a specific (T) entity.
     * @return int
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entity);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

	/**
     *	Method used to provides a query search on the database.
     * @return List<T>
     */
    public List<T> query(String query, Object... params) {
        List<T> result = null;
        Query q = entityManager.createQuery(query);
        int paramPos = 1;
        for (Object o : params) {
            q.setParameter(paramPos++, o);
        }
        result = q.getResultList();
        return result;
    }
	
	//Getters and Setters
	
	public EntityManager getEntityManager() {
        return entityManager;
    }
    
    public static Logger getLogger() {
        return logger;
    }
    
    public Class<T> getEntity() {
        return entity;
    }
    
    public void setEntity(Class<T> entity) {
        this.entity = entity;
    }
    
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
