package br.ifpb.monteiro.scream.util.jpa;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Mauricio
 */
@Priority(Interceptor.Priority.APPLICATION)
@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getGlobal();
    
    @Inject
    private EntityManager manager;

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
            logger.info("Falha na Transação");
            if (transaction != null && owner) {
                System.out.println("catch Rollback ");
                transaction.rollback();

            }

            throw e;
        } finally {
            if (transaction != null && transaction.isActive() && owner) {
                transaction.commit();
                System.out.println("Transação Commitada");
            }
        }
    }

}
