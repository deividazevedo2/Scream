package br.ifpb.monteiro.scream.dao;

import java.io.Serializable;
import java.text.BreakIterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Mauricio
 * @param <T>
 */
public class GenericDAO<T> implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getGlobal();

    @Inject
    private EntityManager entityManager;

    protected Class<T> entity;

    /**
     * Construtor da classe que captura a entidade que chamar esta classe.
     *
     * @param entityClass
     */
    public GenericDAO(Class<T> entityClass) {
        this.entity = entityClass;
    }

    /**
     * Método get para a instância do EntityManager
     *
     * @return
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Metodo utilizado para salvar um novo cadastro no banco de dados ou editar
     * um cadastro existente.
     *
     * @param entity
     */
    public void update(T entity) {
        getLogger().info("DAO Create Acessado");
        try {
            entityManager.merge(entity);
        } catch (Exception e) {
            getLogger().log(Level.WARNING, entity.toString());
            getLogger().log(Level.INFO, "Erro no DAO: {0}", e.getMessage());
        }

    }
    
    /**
     * Metodo utilizado para salvar um novo cadastro no banco de dados ou editar
     * um cadastro existente.
     *
     * @param entity
     */
    public void create(T entity) {
        getLogger().info("DAO Create Acessado");
        
        try{
        	entityManager.persist(entity);        	
        }catch(Exception e){
        	getLogger().log(Level.WARNING, entity.toString());
	        getLogger().log(Level.INFO, "Erro no DAO: {1}", e.getMessage());

        }

    }

    /**
     * Método utilizado para remover um cadastro do banco de dados
     *
     * @param entity
     */
    public void delete(T entity) {
        entityManager.remove(entity);
    }

    /**
     * Método utilizado para retornar uma lista com todos os resultados
     * encontrados no banco de dados para a esntidade que a chamar. A consulta é
     * feita através de Criteria
     *
     * @return
     */
    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entity));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     * Método utilizado para buscar um registro no banco de dados para
     * determinada entidade através da passagem do seu ID como parâmetro.
     *
     * @param id
     * @return
     */
    public T findById(Long id) {
        return entityManager.find(entity, id);
    }

    /**
     *
     * @param range
     * @return
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
     *
     * @return
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entity);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    /**
     * Método get para a instância da entidade que usar esta classe
     *
     * @return
     */
    public Class<T> getEntity() {
        return entity;
    }

    /**
     * Método set para a instância da entidade que usar esta classe
     *
     * @param entity
     */
    public void setEntity(Class<T> entity) {
        this.entity = entity;
    }
    
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

	public static Logger getLogger() {
		return logger;
	}
	
	

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
