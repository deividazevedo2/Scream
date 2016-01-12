package br.ifpb.monteiro.scream.util.jpa;

import static br.edu.ifpb.scream.generic.GenericDAO.getLogger;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Transient;

/**
 * This class controls transactions requested
 * from services and DAO classes 
 * @author Mauricio
 */
@Priority(Interceptor.Priority.APPLICATION)
@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getGlobal();
    
    @Transient
    @Inject
    private EntityManager manager;

	/**
	* This method is responsible to create and validate,
	* updates and deletes from database requested from DAO and services.
	* @param InvocationContext context
	*/
    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        logger.info("Transação Obtida");
        EntityTransaction transaction = manager.getTransaction();
        boolean owner = false;

        try {

            if (!transaction.isActive()) {
                logger.info("Iniciando Transação");
                transaction.begin();
                logger.info("Transação Iniciada");
                transaction.rollback();
                logger.info("Rollback");

                transaction.begin();
                logger.info("Transação Reiniciada");

                owner = true;
            }

            return context.proceed();
        } catch (Exception e) {
            logger.log(Level.WARNING,"Falha na Transação" , e);
            if (transaction != null && owner) {
                logger.log(Level.INFO,"catch Rollback ");
                transaction.rollback();

            }

            throw e;
        } finally {
            if (transaction != null && transaction.isActive() && owner) {
                transaction.commit();
                logger.log(Level.INFO,"Transação Commitada");
            }
        }
    }

}
