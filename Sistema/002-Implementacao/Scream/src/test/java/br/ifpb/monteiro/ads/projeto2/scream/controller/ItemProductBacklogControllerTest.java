package br.ifpb.monteiro.ads.projeto2.scream.controller;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ifpb.monteiro.scream.dao.ItemProductBacklogDAO;
import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.services.ItemProductBacklogService;
import br.ifpb.monteiro.scream.util.jpa.EntityManagerProducer;


@RunWith(MockitoJUnitRunner.class)
public class ItemProductBacklogControllerTest {

//	private ItemProductBacklog itemProductBacklog;
//	
	private static EntityManager em;
	
	@Mock
	private ItemProductBacklogService itemProductBacklogService;

	@Mock
	private ItemProductBacklogDAO itemProductBacklogDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
		em = emp.create();
	}
	
	@Test
	public void testCreate() {
		ItemProductBacklog ipb = new ItemProductBacklog();
		ipb.setDescricao("Fazer Scream");
		ipb.setDescricao("Sou Uma Descricao!");

		Mockito.when(itemProductBacklogService.create(ipb)).thenReturn(true);

		assertEquals(true, itemProductBacklogService.create(ipb));
	}


}
