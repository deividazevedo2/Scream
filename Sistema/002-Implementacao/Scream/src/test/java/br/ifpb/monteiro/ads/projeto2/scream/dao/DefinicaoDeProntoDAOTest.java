/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.ads.projeto2.scream.dao;

import br.ifpb.monteiro.scream.dao.DefinicaoDeProntoDAO;
import br.ifpb.monteiro.scream.dao.ProjetoDAO;
import br.ifpb.monteiro.scream.entities.DefinicaoDePronto;
import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.entities.enums.DefinicaoDeProntoEnum;
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
public class DefinicaoDeProntoDAOTest {
    
    private static DefinicaoDeProntoDAO definicaoDeProntoDAO;
    private static ProjetoDAO projetoDAO;
    private static EntityManager em;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
        definicaoDeProntoDAO = new DefinicaoDeProntoDAO();
        projetoDAO = new ProjetoDAO();
        EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
        em = emp.create();
        
        definicaoDeProntoDAO.setEntityManager(em);
        projetoDAO.setEntityManager(em);
    }
    
    
    @Test
    public void testCreateDefinicaoProntoPB() {
        criaDefinicaoDePronto("Aqui esta as definicoes de PB",DefinicaoDeProntoEnum.PRODUCTBACKLOG);
    }
    
    @Test
    public void testCreateDefinicaoProntoRelease() {
        criaDefinicaoDePronto("Aqui esta as definicoes da Release",DefinicaoDeProntoEnum.RELEASE);
    }
    
    @Test
    public void testCreateDefinicaoProntoSprint() {
        criaDefinicaoDePronto("Aqui esta as definicoes da Sprint",DefinicaoDeProntoEnum.SPRINT);
    }
    
    @Test
    public void testQuery() {
        
        DefinicaoDePronto dDP = criaDefinicaoDePronto("Aqui esta as definicoes de PB",
                DefinicaoDeProntoEnum.PRODUCTBACKLOG);
        
        String descricao = "Aqui esta as definicoes de PB";
        
        List<DefinicaoDePronto> definicoes = definicaoDeProntoDAO.query("select definicao from DefinicaoDePronto definicao "
                + "where definicao.descricao = ?1", descricao);
        
        DefinicaoDePronto dDPResult = definicoes.get(0);
        
        assertEquals(dDP.getDescricao(), dDPResult.getDescricao());
        
    }
    
    @Test
    public void testDelete() {
        
        criaDefinicaoDePronto("Aqui esta as definicoes de Product Backlog",DefinicaoDeProntoEnum.PRODUCTBACKLOG);
        Boolean valid = true;
        
        String descricao = "Aqui esta as definicoes de Product Backlog";
        
        List<DefinicaoDePronto> definicoes = definicaoDeProntoDAO.query("select definicao from DefinicaoDePronto definicao "
                + "where definicao.descricao = ?1", descricao);
        
        definicaoDeProntoDAO.getEntityManager().getTransaction().begin();
        definicaoDeProntoDAO.delete(definicoes.get(0));
        definicaoDeProntoDAO.getEntityManager().getTransaction().commit();
        
        List<DefinicaoDePronto> definicoesResult = definicaoDeProntoDAO.query("select definicao from DefinicaoDePronto definicao "
                + "where definicao.descricao = ?1", descricao);
        
        if (definicoesResult.size() < 1) {
            valid = false;
        }
        
        assertEquals(false, valid);
        
    }
    
    @Test
    public void testUpdate() {
        
        criaDefinicaoDePronto("Aqui esta as definicoes de uma outra SPRINT",DefinicaoDeProntoEnum.SPRINT);
        
        String descricao = "Aqui esta as definicoes de uma outra SPRINT";
        
        List<DefinicaoDePronto> definicoes = definicaoDeProntoDAO.query("select definicao from DefinicaoDePronto definicao "
                + "where definicao.descricao = ?1", descricao);
        
        DefinicaoDePronto dDP = definicoes.get(0);
        dDP.setDescricao("Uma outra descricao");
        
        definicaoDeProntoDAO.getEntityManager().getTransaction().begin();
        definicaoDeProntoDAO.update(dDP);
        definicaoDeProntoDAO.getEntityManager().getTransaction().commit();
        
        String descricaoResult = "Uma outra descricao";
        
        List<DefinicaoDePronto> definicoesResult = definicaoDeProntoDAO.query("select definicao from DefinicaoDePronto definicao "
                + "where definicao.descricao = ?1", descricaoResult);
        
        assertEquals(descricaoResult, definicoesResult.get(0).getDescricao());
    }
    
    @Test
    public void testFindAll() {
        
        criaDefinicaoDePronto("Aqui esta as definicoes do PB",DefinicaoDeProntoEnum.RELEASE);
        criaDefinicaoDePronto("Aqui esta as definicoes da Sprint",DefinicaoDeProntoEnum.SPRINT);
        criaDefinicaoDePronto("Aqui esta as definicoes da Release",DefinicaoDeProntoEnum.PRODUCTBACKLOG);
        
        Boolean valid = false;
        
        List<DefinicaoDePronto> dDP = definicaoDeProntoDAO.findAll();
        
        if (dDP.size() >= 3){
            valid = true;
        }
        
        assertEquals(true, valid);
    }
    
    @Test
    public void testFindById() {
        criaDefinicaoDePronto("Aqui esta as definicoes da Release",DefinicaoDeProntoEnum.PRODUCTBACKLOG);
        
        DefinicaoDePronto dDP = null;
        Boolean valid = false;
        
        dDP = definicaoDeProntoDAO.findById(1l);
        
        if(dDP != null)
            valid = true;
        
        assertEquals(true, valid);
    }
    
    @Test
    public void testCount() {
        
        criaDefinicaoDePronto("Aqui esta as definicoes da Release",DefinicaoDeProntoEnum.PRODUCTBACKLOG);
        criaDefinicaoDePronto("Aqui esta as definicoes da Release",DefinicaoDeProntoEnum.RELEASE);
        
        assertEquals(5, definicaoDeProntoDAO.count());
        
    }
    
    public DefinicaoDePronto criaDefinicaoDePronto(String descricao, DefinicaoDeProntoEnum dPE){
        Projeto p = criaProjeto("Scream");
        DefinicaoDePronto dDP = new DefinicaoDePronto();
        dDP.setDescricao(descricao);
        dDP.setTipoDefinicao(dPE);
        dDP.setProjeto(p);
        
        definicaoDeProntoDAO.getEntityManager().getTransaction().begin();
        definicaoDeProntoDAO.create(dDP);
        definicaoDeProntoDAO.getEntityManager().getTransaction().commit();
        
        return dDP;
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
