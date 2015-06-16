/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.ads.projeto2.scream.dao;

import br.ifpb.monteiro.scream.dao.TesteAceitacaoDAO;
import br.ifpb.monteiro.scream.dao.ItemProductBacklogDAO;
import br.ifpb.monteiro.scream.entities.TesteAceitacao;
import br.ifpb.monteiro.scream.entities.ItemProductBacklog;
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
public class TesteAceitacaoDAOTest {
    
    private static ItemProductBacklogDAO IpbDAO;
    private static TesteAceitacaoDAO criterioAceitacaoDAO;
    private static EntityManager em;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
        IpbDAO = new ItemProductBacklogDAO();
        criterioAceitacaoDAO = new TesteAceitacaoDAO();
        EntityManagerProducer emp = new EntityManagerProducer("ScreamTest");
        em = emp.create();
        
        IpbDAO.setEntityManager(em);
        criterioAceitacaoDAO.setEntityManager(em);
    }
    
    
    @Test
    public void testCreate() {
    }
    
    @Test
    public void testQuery() {
        
        TesteAceitacao cA = criaTesteAceitacao("Fazer Testes");
        
        String descricao = "Fazer Testes";
        
        List<TesteAceitacao> criterios = criterioAceitacaoDAO.query(
                "select ca from TesteAceitacao ca "
                + "where ca.descricao = ?1", descricao);
        
        TesteAceitacao cAResult = criterios.get(0);
        
        assertEquals(cA.getDescricao(), cAResult.getDescricao());
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.delete(cAResult);
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
        
    }
    
    @Test
    public void testDelete() {
        
        
        criaTesteAceitacao("Fazer Testes Delete");
        Boolean valid = true;
        
        String descricao = "Fazer Testes Delete";
        
        List<TesteAceitacao> criterios = criterioAceitacaoDAO.query(
                "select ca from TesteAceitacao ca "
                + "where ca.descricao = ?1", descricao);
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.delete(criterios.get(0));
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
        
        List<TesteAceitacao> criteriosResult = criterioAceitacaoDAO.query(
                "select ca from TesteAceitacao ca "
                + "where ca.descricao = ?1", descricao);
        
        if (criteriosResult.size() < 1) {
            valid = false;
        }
        
        assertEquals(false, valid);
        
    }
    
    @Test
    public void testUpdate() {
        
        criaTesteAceitacao("Fazer Testes Update");
        
        String descricao = "Fazer Testes Update";
        
        List<TesteAceitacao> criterios = criterioAceitacaoDAO.query(
                "select ca from TesteAceitacao ca "
                + "where ca.descricao = ?1", descricao);
        
        TesteAceitacao cA = criterios.get(0);
        cA.setDescricao("Novo Teste Update");
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.update(cA);
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
        
        String descricaoResult = "Novo Teste Update";
        
        List<TesteAceitacao> definicoesResult = criterioAceitacaoDAO.query(
                "select ca from TesteAceitacao ca "
                + "where ca.descricao = ?1", descricaoResult);
        
        assertEquals(descricaoResult, definicoesResult.get(0).getDescricao());
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.delete(definicoesResult.get(0));
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
    }
    
    @Test
    public void testFindAll() {
        
        criaTesteAceitacao("Fazer Testes FindAll");
        
        Boolean valid = false;
        
        List<TesteAceitacao> cA = criterioAceitacaoDAO.findAll();
        
        if (cA.size() >= 1){
            valid = true;
        }
        
        assertEquals(true, valid);
        
        String descricao = "Fazer Testes FindAll";
        
        List<TesteAceitacao> criterios = criterioAceitacaoDAO.query(
                "select ca from TesteAceitacao ca "
                + "where ca.descricao = ?1", descricao);
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.delete(criterios.get(0));
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
        
    }
    
    @Test
    public void testFindById() {
        
        criaTesteAceitacao("Fazer O Teste FindById");
        
        String descricaoResult = "Fazer O Teste FindById";
        
        List<TesteAceitacao> criteriosResult = criterioAceitacaoDAO.query(
                "select ca from TesteAceitacao ca "
                + "where ca.descricao = ?1", descricaoResult);
        
        TesteAceitacao cA = null;
        Boolean valid = false;
        
        cA = criterioAceitacaoDAO.findById(criteriosResult.get(0).getId());
        
        if(cA != null)
            valid = true;
        
        assertEquals(true, valid);
        
        List<TesteAceitacao> criterios = criterioAceitacaoDAO.query(
                "select ca from TesteAceitacao ca "
                + "where ca.descricao = ?1", descricaoResult);
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.delete(criterios.get(0));
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
    }
    
    @Test
    public void testCount() {
        
        criaTesteAceitacao("Fazer Teste Count 1");
        criaTesteAceitacao("Fazer Teste Count 2");
        
        List<TesteAceitacao> criteriosCount = criterioAceitacaoDAO.query(
                "select ca from TesteAceitacao ca");
        
        assertEquals(criteriosCount.size(), criterioAceitacaoDAO.count());
        
        String descricao = "Fazer Teste Count 1";
        
        List<TesteAceitacao> criterios = criterioAceitacaoDAO.query(
                "select ca from TesteAceitacao ca "
                + "where ca.descricao = ?1", descricao);
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.delete(criterios.get(0));
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
        
        String descricao2 = "Fazer Teste Count 2";
        
        List<TesteAceitacao> criterios2 = criterioAceitacaoDAO.query(
                "select ca from TesteAceitacao ca "
                + "where ca.descricao = ?1", descricao2);
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.delete(criterios2.get(0));
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
        
    }
    
    public ItemProductBacklog criaItemProductBacklog(String descricao, int complexidade){
        
        ItemProductBacklog ipb = new ItemProductBacklog();
        ipb.setDescricao(descricao);
        ipb.setComplexidade(complexidade);
        
        IpbDAO.getEntityManager().getTransaction().begin();
        IpbDAO.create(ipb);
        IpbDAO.getEntityManager().getTransaction().commit();
        
        return ipb;
    }
    
    public TesteAceitacao criaTesteAceitacao(String descricao){
        
        TesteAceitacao p = new TesteAceitacao();
        p.setDescricao(descricao);
        p.setEstadoTeste(false);
        p.setItemProductBacklog(criaItemProductBacklog("Um Item Aqui", 10));
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.create(p);
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
        
        return p;
    }
}