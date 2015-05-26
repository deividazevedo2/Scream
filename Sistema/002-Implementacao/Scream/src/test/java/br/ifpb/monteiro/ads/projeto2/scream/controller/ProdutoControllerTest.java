package br.ifpb.monteiro.ads.projeto2.scream.controller;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import br.ifpb.monteiro.scream.dao.ProdutoDAO;
import br.ifpb.monteiro.scream.entities.Produto;
import br.ifpb.monteiro.scream.services.ProdutoService;
import br.ifpb.monteiro.scream.util.jpa.EntityManagerProducer;

/**
 *
 * @author Mauricio
 */
@RunWith(MockitoJUnitRunner.class)
public class ProdutoControllerTest {

	private Produto produto;

	private static EntityManager em;

	@Mock
	private ProdutoService produtoService;

	@Mock
	private ProdutoDAO produtoDAO;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
		em = emp.create();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testCreate() {
		Produto p = new Produto();
		p.setNome("Scream");
		p.setDescricao("Sou Uma Descricao!");

		Mockito.when(produtoService.create(p)).thenReturn(true);

		assertEquals(true, produtoService.create(p));
	}

//	@Test
//	public void testUpdate(){
//		
//		Produto p = new Produto();
//		p.setNome("Scrim");
//		p.setDescricao("Sou Uma Descricao!");
//
//		Mockito.when(produtoService.create(p)).thenReturn(true);
//
//		produtoService.create(p);
//
//		String nome = "Scrim";
//		String nomeResult="Scream";
//
//		List<Produto> produtos = null;
//		List<Produto> produtosResult = null;
//		
//		Mockito.when(produtoDAO.query("select produto from Produto produto "
//				+ "where produto.nome = ?1", nome)).thenReturn(produtos);
//
//		produtos = produtoDAO.query("select produto from Produto produto "
//				+ "where produto.nome = ?1", nome);
//		
//		System.out.println(produtos);
//		
//		Mockito.when(produtoService.update(produtos.get(0))).thenReturn(true);
//		
//		Produto produto = produtos.get(0);
//		produto.setNome("Scream");
//		produtoService.update(produto);
//		
//		Mockito.when(produtoDAO.query("select produto from Produto produto "
//				+ "where produto.nome = ?1", nomeResult)).thenReturn(produtosResult);
//		
//		produtosResult = produtoDAO.query("select produto from Produto produto "
//				+ "where produto.nome = ?1", nomeResult);
//		
//		assertEquals("Scream", produtosResult.get(0).getNome());
//		
//
//	}

}
