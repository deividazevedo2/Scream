/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifpb.monteiro.ads.projeto2.scream.controller;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ifpb.monteiro.scream.dao.ContaDAO;
import br.ifpb.monteiro.scream.entities.UserAccount;
import br.ifpb.monteiro.scream.services.ContaService;
import br.ifpb.monteiro.scream.services.SecurityService;
import br.ifpb.monteiro.scream.util.jpa.EntityManagerProducer;

/**
 *
 * @author Mauricio
 */
@RunWith(MockitoJUnitRunner.class)
public class ContaControllerTest {


	private  UserAccount userAccount;

	private static SecurityService securityService;

	@Mock
	private static ContaDAO contaDAO;
	
	@Mock
	private static ContaService contaService;

	private static EntityManager em;

	public ContaControllerTest() {
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
		em = emp.create();
	
		contaService= Mockito.mock(ContaService.class);
		securityService= new SecurityService();
		contaService.setSecurityService(securityService);

	}

	@AfterClass
	public static void tearDownClass() {

	}

	@Before
	public void setUp() {
		Mockito.doCallRealMethod().when(contaDAO).setEntityManager(em);
		contaDAO.setEntityManager(em);
		userAccount= new UserAccount();
		userAccount.setNome("Marcus Patriota");
		userAccount.setEmail("marcuspatriota@gmail.com");
		userAccount.setUsuario("marcuspatriota");
		userAccount.setSenha("123");
		contaService.create(userAccount);
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testCreate() {
		userAccount= new UserAccount();
		userAccount.setNome("Scream");
		userAccount.setEmail("screm@gmail.com");
		userAccount.setUsuario("scream");
		userAccount.setSenha("scream");		
		
		Mockito.when(contaService.create(userAccount)).thenReturn(true);
		
		assertEquals(true, contaService.create(userAccount));

	}

	@Test
	public void testFazerLoginInvalido() {

	}

}
