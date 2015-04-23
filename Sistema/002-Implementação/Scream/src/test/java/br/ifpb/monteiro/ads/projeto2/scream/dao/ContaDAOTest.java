/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifpb.monteiro.ads.projeto2.scream.dao;

import br.ifpb.monteiro.ads.projeto2.scream.entities.Conta;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.fail;
import org.junit.BeforeClass;
import org.junit.Test;
import static sun.management.Agent.error;

/**
 *
 * @author Hugo
 */
public class ContaDAOTest {
    
    private final Conta conta;
    
    public ContaDAOTest() {
        conta = new Conta();
        conta.setNome("Hugo Correia");
        conta.setEmail("hugo@gmail.com");
        conta.setSenha("123");
        conta.setUsuario("Hugo123");
    }
    
    @BeforeClass
    public static void setUp() {
               
    }
    
    @AfterClass
    public static void tearDown() {
    }

    /**
     * Test of create method, of class GenericDAO.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        GenericDAO instance = new ContaDAO();
        instance.create(conta); 
    }
        
    
}
