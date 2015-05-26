package br.ifpb.monteiro.ads.projeto2.scream.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.BeforeClass;
import org.junit.Test;

import br.ifpb.monteiro.scream.dao.ProdutoDAO;
import br.ifpb.monteiro.scream.entities.Produto;
import br.ifpb.monteiro.scream.util.jpa.EntityManagerProducer;

public class ProdutoDAOTest {

	private static ProdutoDAO produtoDAO;
	private static EntityManager em;


	@BeforeClass
	public static void setUpBeforeClass(){

		produtoDAO = new ProdutoDAO();
		EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
		em = emp.create();

		produtoDAO.setEntityManager(em);

	}

	/**
	 * Testa a criação de um novo Produto
	 */
	@Test
	public void testCreate() {
		criaProduto("Produto1");
	}


	@Test
	public void testQuery() {

		Produto p = criaProduto("Produto1");

		String nome = "Produto1";

		List<Produto> produtos = produtoDAO.query("select produto from Produto produto "
				+ "where produto.nome = ?1", nome);

		Produto produto = produtos.get(0);

		assertEquals(p.getNome(), produto.getNome());

	}

	@Test
	public void testDelete() {

		criaProduto("OutroProduto");
		Boolean valid = true;

		String nome = "OutroProduto";

		List<Produto> produtos = produtoDAO.query("select produto from Produto produto "
				+ "where produto.nome = ?1", nome);

		produtoDAO.getEntityManager().getTransaction().begin();
		produtoDAO.delete(produtos.get(0));
		produtoDAO.getEntityManager().getTransaction().commit();

		List<Produto> produtosResult = produtoDAO.query("select produto from Produto produto "
				+ "where produto.nome = ?1", nome);

		if (produtosResult.size() < 1) {
			valid = false;			
		}

		assertEquals(false, valid);

	}


	@Test
	public void testUpdate() {
		
		criaProduto("AlgumOutroProduto");

		String nome = "AlgumOutroProduto";

		List<Produto> produtos = produtoDAO.query("select produto from Produto produto "
				+ "where produto.nome = ?1", nome);

		Produto p = produtos.get(0);
		p.setNome("NaoOutroProduto");

		produtoDAO.getEntityManager().getTransaction().begin();
		produtoDAO.update(p);
		produtoDAO.getEntityManager().getTransaction().commit();

		String nomeResult = "NaoOutroProduto";

		List<Produto> produtosResult = produtoDAO.query("select produto from Produto produto "
				+ "where produto.nome = ?1", nomeResult);

		assertEquals("NaoOutroProduto", produtosResult.get(0).getNome());

	}

	@Test
	public void testFindAll() {
		
		criaProduto("Produto1");
		criaProduto("Produto2");

		Boolean valid = false;

		List<Produto> produtos = produtoDAO.findAll();

		if (produtos.size() >= 2){
			valid = true;
		}

		assertEquals(true, valid);
	}

	@Test
	public void testFindById() {
		criaProduto("ProdutoDoId");

		Produto p = null;
		Boolean valid = false;

		p = produtoDAO.findById(1l);

		if(p != null)
			valid = true;

		assertEquals(true, valid);
	}

	@Test
	public void testCount() {

		criaProduto("Pacote");
		criaProduto("Scream");

		assertEquals(4, produtoDAO.count());

	}

	public Produto criaProduto(String nome){

		Produto p = new Produto();
		p.setDescricao("novo produto de teste");
		p.setNome(nome);

		produtoDAO.getEntityManager().getTransaction().begin();
		produtoDAO.create(p);
		produtoDAO.getEntityManager().getTransaction().commit();

		return p;
	}

}
