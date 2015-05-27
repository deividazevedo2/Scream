package br.ifpb.monteiro.ads.projeto2.scream.dao;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

import br.ifpb.monteiro.scream.dao.ItemProductBacklogDAO;
import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.util.jpa.EntityManagerProducer;

public class ItemProductBacklogDAOTest {
	
	private static ItemProductBacklogDAO itemProductBacklogDAO;
	private static EntityManager em;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		itemProductBacklogDAO = new ItemProductBacklogDAO();
		EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
		em = emp.create();

		itemProductBacklogDAO.setEntityManager(em);
		
	}


	@Test
	public void testCreate() {
		criaItemProductBacklog("Primeiro Item do PB",1);
	}
	
	@Test
	public void testQuery() {

		ItemProductBacklog ipb = criaItemProductBacklog("Segundo Item do PB",2);

		String descricao = "Segundo Item do PB";

		List<ItemProductBacklog> itensPB = itemProductBacklogDAO.query("select item_pb from ItemProductBacklog item_pb "
				+ "where item_pb.descricao = ?1", descricao);

		ItemProductBacklog itemPB = itensPB.get(0);

		assertEquals(ipb.getDescricao(), itemPB.getDescricao());

	}
	
	@Test
	public void testDelete() {

		criaItemProductBacklog("Terceiro ND Item do PB",3);
		Boolean valid = true;

		String descricao = "Terceiro ND Item do PB";

		List<ItemProductBacklog> listaIpb = itemProductBacklogDAO.query("select item_pb from ItemProductBacklog item_pb "
				+ "where item_pb.descricao = ?1", descricao);

		itemProductBacklogDAO.getEntityManager().getTransaction().begin();
		itemProductBacklogDAO.delete(listaIpb.get(0));
		itemProductBacklogDAO.getEntityManager().getTransaction().commit();

		List<ItemProductBacklog> listaIpbResult = itemProductBacklogDAO.query("select item_pb from ItemProductBacklog item_pb "
				+ "where item_pb.descricao = ?1", descricao);

		if (listaIpbResult.size() < 1) {
			valid = false;			
		}

		assertEquals(false, valid);

	}
	
	@Test
	public void testUpdate() {
		
		criaItemProductBacklog("Quarto updar Item do PB",4);

		String descricao = "Quarto updar Item do PB";

		List<ItemProductBacklog> listIpb = itemProductBacklogDAO.query("select item_pb from ItemProductBacklog item_pb "
				+ "where item_pb.descricao = ?1", descricao);

		ItemProductBacklog ipb = listIpb.get(0);
		ipb.setDescricao("Outra descrição");

		itemProductBacklogDAO.getEntityManager().getTransaction().begin();
		itemProductBacklogDAO.update(ipb);
		itemProductBacklogDAO.getEntityManager().getTransaction().commit();

		String descricaoResult = "Outra descrição";

		List<ItemProductBacklog> ipbResult = itemProductBacklogDAO.query("select item_pb from ItemProductBacklog item_pb "
				+ "where item_pb.descricao = ?1", descricaoResult);

		assertEquals(descricaoResult, ipbResult.get(0).getDescricao());
	}
	
	@Test
	public void testFindAll() {
		
		criaItemProductBacklog("Quinto find Item do PB",5);
		criaItemProductBacklog("Sexto find Item do PB",6);

		Boolean valid = false;

		List<ItemProductBacklog> listIpb = itemProductBacklogDAO.findAll();

		if (listIpb.size() >= 2){
			valid = true;
		}

		assertEquals(true, valid);
	}
	
	@Test
	public void testFindById() {
		criaItemProductBacklog("Sétimo findId Item do PB",7);

		ItemProductBacklog ipb = null;
		Boolean valid = false;

		ipb = itemProductBacklogDAO.findById(1l);

		if(ipb != null)
			valid = true;

		assertEquals(true, valid);
	}
	
	@Test
	public void testCount() {

		criaItemProductBacklog("Oitavo Count Item do PB",8);
		criaItemProductBacklog("Non Count Item do PB",9);

		assertEquals(4, itemProductBacklogDAO.count());

	}
	
	public ItemProductBacklog criaItemProductBacklog(String descricao, int complexidade){

		ItemProductBacklog ipb = new ItemProductBacklog();
		ipb.setDescricao(descricao);
		ipb.setComplexidade(complexidade);

		itemProductBacklogDAO.getEntityManager().getTransaction().begin();
		itemProductBacklogDAO.create(ipb);
		itemProductBacklogDAO.getEntityManager().getTransaction().commit();

		return ipb;
	}

}
