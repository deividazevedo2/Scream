/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifpb.monteiro.ads.projeto2.scream.util.jpa;


import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Mauricio
 */

@ApplicationScoped
public class EntityManagerProducer {

	private static EntityManagerFactory factory;
        private static final Logger log = Logger.getGlobal();
        
	protected EntityManagerProducer() {
		EntityManagerProducer.factory = Persistence.createEntityManagerFactory("com.mycompany_Scream_war_1.0-SNAPSHOTPU");
	}
	
	@Produces
	@RequestScoped
	public EntityManager create() {
                log.info("Entity Manager criado");
		return EntityManagerProducer.factory.createEntityManager();
	}
	
	public void close(@Disposes EntityManager manager) {
		manager.close();
	}
	
}
