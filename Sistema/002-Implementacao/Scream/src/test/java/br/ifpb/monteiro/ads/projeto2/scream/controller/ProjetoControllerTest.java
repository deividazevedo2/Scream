/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.ads.projeto2.scream.controller;

import br.ifpb.monteiro.scream.dao.ProjetoDAO;
import br.ifpb.monteiro.scream.entities.Projeto;
import br.ifpb.monteiro.scream.services.ProjetoService;
import br.ifpb.monteiro.scream.util.jpa.EntityManagerProducer;
import javax.persistence.EntityManager;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;




/**
 *
 * @author Mauricio
 */
@RunWith(MockitoJUnitRunner.class)
public class ProjetoControllerTest {
    
    
    private Projeto projeto;
    
    private static EntityManager em;
    
    @Mock
    private ProjetoService projetoService;
    
    @Mock
    private ProjetoDAO projetoDAO;
    
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
        Projeto p = new Projeto();
        p.setNome("Scream");
        p.setIsCompleted(false);
        p.setSprintDuration(14);
        
        
        Mockito.when(projetoService.create(p)).thenReturn(true);
        
        assertEquals(true, projetoService.create(p));
    }
}
