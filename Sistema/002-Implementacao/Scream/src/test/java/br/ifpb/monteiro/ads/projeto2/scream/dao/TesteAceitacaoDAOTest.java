/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.ads.projeto2.scream.dao;

import br.ifpb.monteiro.scream.dao.CriterioAceitacaoDAO;
import br.ifpb.monteiro.scream.dao.ItemProductBacklogDAO;
import br.ifpb.monteiro.scream.entities.CriterioAceitacao;
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
    private static CriterioAceitacaoDAO criterioAceitacaoDAO;
    private static EntityManager em;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
        IpbDAO = new ItemProductBacklogDAO();
        criterioAceitacaoDAO = new CriterioAceitacaoDAO();
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
        
        CriterioAceitacao cA = criaCriterioAceitacao("Fazer Testes");
        
        String descricao = "Fazer Testes";
        
        List<CriterioAceitacao> criterios = criterioAceitacaoDAO.query("select ca from CriterioAceitacao ca "
                + "where ca.descricao = ?1", descricao);
        
        CriterioAceitacao cAResult = criterios.get(0);
        
        assertEquals(cA.getDescricao(), cAResult.getDescricao());
        
    }
    
    @Test
    public void testDelete() {
        
        
        criaCriterioAceitacao("Fazer Testes Delete");
        Boolean valid = true;
        
        String descricao = "Fazer Testes Delete";
        
        List<CriterioAceitacao> criterios = criterioAceitacaoDAO.query("select ca from CriterioAceitacao ca "
                + "where ca.descricao = ?1", descricao);
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.delete(criterios.get(0));
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
        
        List<CriterioAceitacao> criteriosResult = criterioAceitacaoDAO.query("select ca from CriterioAceitacao ca "
                + "where ca.descricao = ?1", descricao);
        
        if (criteriosResult.size() < 1) {
            valid = false;
        }
        
        assertEquals(false, valid);
        
    }
    
    @Test
    public void testUpdate() {
        
        criaCriterioAceitacao("Fazer Testes Update");
        
        String descricao = "Fazer Testes Update";
        
        List<CriterioAceitacao> criterios = criterioAceitacaoDAO.query("select ca from CriterioAceitacao ca "
                + "where ca.descricao = ?1", descricao);
        
        CriterioAceitacao cA = criterios.get(0);
        cA.setDescricao("Novo Teste Update");
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.update(cA);
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
        
        String descricaoResult = "Novo Teste Update";
        
        List<CriterioAceitacao> definicoesResult = criterioAceitacaoDAO.query("select ca from CriterioAceitacao ca "
                + "where ca.descricao = ?1", descricaoResult);
        
        assertEquals(descricaoResult, definicoesResult.get(0).getDescricao());
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.delete(definicoesResult.get(0));
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
    }
    
    @Test
    public void testFindAll() {
        
        criaCriterioAceitacao("Fazer Testes FindAll");
        
        Boolean valid = false;
        
        List<CriterioAceitacao> cA = criterioAceitacaoDAO.findAll();
        
        if (cA.size() >= 1){
            valid = true;
        }
        
        assertEquals(true, valid);
        
        String descricao = "Fazer Testes FindAll";
        
        List<CriterioAceitacao> criterios = criterioAceitacaoDAO.query("select ca from CriterioAceitacao ca "
                + "where ca.descricao = ?1", descricao);
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.delete(criterios.get(0));
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
        
    }
    
    @Test
    public void testFindById() {
        
        criaCriterioAceitacao("Fazer O Teste FindById");
        
        String descricaoResult = "Fazer O Teste FindById";
        
        List<CriterioAceitacao> criteriosResult = criterioAceitacaoDAO.query("select ca from CriterioAceitacao ca "
                + "where ca.descricao = ?1", descricaoResult);
        
        CriterioAceitacao cA = null;
        Boolean valid = false;
        
        cA = criterioAceitacaoDAO.findById(criteriosResult.get(0).getId());
        
        System.out.println("----------------------------"+cA+"-----------------------");
        System.out.println("----------------------------"+criterioAceitacaoDAO.findById(1l)+"-----------------------");
        if(cA != null)
            valid = true;
        
        assertEquals(true, valid);
        
        List<CriterioAceitacao> criterios = criterioAceitacaoDAO.query("select ca from CriterioAceitacao ca "
                + "where ca.descricao = ?1", descricaoResult);
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.delete(criterios.get(0));
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
    }
    
    @Test
    public void testCount() {
        
        criaCriterioAceitacao("Fazer Teste Count 1");
        criaCriterioAceitacao("Fazer Teste Count 2");
        
        assertEquals(2, criterioAceitacaoDAO.count());
        
        String descricao = "Fazer Teste Count 1";
        
        List<CriterioAceitacao> criterios = criterioAceitacaoDAO.query("select ca from CriterioAceitacao ca "
                + "where ca.descricao = ?1", descricao);
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.delete(criterios.get(0));
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
        
        String descricao2 = "Fazer Teste Count 2";
        
        List<CriterioAceitacao> criterios2 = criterioAceitacaoDAO.query("select ca from CriterioAceitacao ca "
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
    
    public CriterioAceitacao criaCriterioAceitacao(String descricao){
        
        CriterioAceitacao p = new CriterioAceitacao();
        p.setDescricao(descricao);
        p.setEstadoDocriterio(false);
        p.setItemProductBacklog(criaItemProductBacklog("Um Item Aqui", 10));
        
        criterioAceitacaoDAO.getEntityManager().getTransaction().begin();
        criterioAceitacaoDAO.create(p);
        criterioAceitacaoDAO.getEntityManager().getTransaction().commit();
        
        return p;
    }
}