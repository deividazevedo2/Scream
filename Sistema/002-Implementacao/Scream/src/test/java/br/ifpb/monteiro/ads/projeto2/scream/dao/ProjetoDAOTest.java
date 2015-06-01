/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.ads.projeto2.scream.dao;

import br.ifpb.monteiro.scream.dao.ProjetoDAO;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.util.jpa.EntityManagerProducer;
import java.util.List;
import javax.persistence.EntityManager;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Mauricio
 */
public class ProjetoDAOTest {
    
    private static ProjetoDAO projetoDAO;
    private static EntityManager em;
    
    
    @BeforeClass
    public static void setUpBeforeClass(){
        
        projetoDAO = new ProjetoDAO();
        EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
        em = emp.create();
        
        projetoDAO.setEntityManager(em);
        
    }
    
    /**
     * Testa a criação de um novo Projeto
     */
    @Test
    public void testCreate() {
        criaProjeto("Projeto1");
    }
    
    
    @Test
    public void testQuery() {
        
        Projeto p = criaProjeto("Projeto1");
        
        String nome = "Projeto1";
        
        List<Projeto> projetos = projetoDAO.query("select projeto from Projeto projeto "
                + "where projeto.nome = ?1", nome);
        
        Projeto projeto = projetos.get(0);
        
        assertEquals(p.getNome(), projeto.getNome());
        
    }
    
    @Test
    public void testDelete() {
        
        criaProjeto("OutroProjeto");
        Boolean valid = true;
        
        String nome = "OutroProjeto";
        
        List<Projeto> projetos = projetoDAO.query("select projeto from Projeto projeto "
                + "where projeto.nome = ?1", nome);
        
        projetoDAO.getEntityManager().getTransaction().begin();
        projetoDAO.delete(projetos.get(0));
        projetoDAO.getEntityManager().getTransaction().commit();
        
        List<Projeto> produtosResult = projetoDAO.query("select projeto from Projeto projeto "
                + "where projeto.nome = ?1", nome);
        
        if (produtosResult.size() < 1) {
            valid = false;
        }
        
        assertEquals(false, valid);
        
    }
    
    
    @Test
    public void testUpdate() {
        
        criaProjeto("AlgumOutroProjeto");
        
        String nome = "AlgumOutroProjeto";
        
        List<Projeto> projetos = projetoDAO.query("select projeto from Projeto projeto "
                + "where projeto.nome = ?1", nome);
        
        Projeto p = projetos.get(0);
        p.setNome("NaoOutroProjeto");
        
        projetoDAO.getEntityManager().getTransaction().begin();
        projetoDAO.update(p);
        projetoDAO.getEntityManager().getTransaction().commit();
        
        String nomeResult = "NaoOutroProjeto";
        
        List<Projeto> projetosResult = projetoDAO.query("select projeto from Projeto projeto "
                + "where projeto.nome = ?1", nomeResult);
        
        assertEquals("NaoOutroProjeto", projetosResult.get(0).getNome());
        
    }
    
    @Test
    public void testFindAll() {
        
        criaProjeto("Projeto1");
        criaProjeto("Projeto2");
        
        Boolean valid = false;
        
        List<Projeto> produtos = projetoDAO.findAll();
        
        if (produtos.size() >= 2){
            valid = true;
        }
        
        assertEquals(true, valid);
    }
    
    @Test
    public void testFindById() {
        criaProjeto("ProjetoDoId");
        
        Projeto p;
        Boolean valid = false;
        
        p = projetoDAO.findById(1l);
        
        if(p != null)
            valid = true;
        
        assertEquals(true, valid);
    }
    
    @Test
    public void testCount() {
        
        criaProjeto("Pacote");
        criaProjeto("Scream");
        
         List<Projeto> projetosResult = projetoDAO.query("select projeto from Projeto projeto");
        
        assertEquals(projetosResult.size(), projetoDAO.count());
        
    }
    
    public Projeto criaProjeto(String nome){
        
        Projeto p = new Projeto();
        p.setSprintDuration(14);
        p.setNome(nome);
        p.setIsCompleted(false);
        
        projetoDAO.getEntityManager().getTransaction().begin();
        projetoDAO.create(p);
        projetoDAO.getEntityManager().getTransaction().commit();
        
        return p;
    }
    
}
