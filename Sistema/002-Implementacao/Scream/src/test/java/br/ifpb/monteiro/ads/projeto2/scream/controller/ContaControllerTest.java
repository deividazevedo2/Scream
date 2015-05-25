///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package br.ifpb.monteiro.ads.projeto2.scream.controller;
//
//import br.ifpb.monteiro.scream.dao.facade.ContaDaoIF;
//import br.ifpb.monteiro.scream.entities.Conta;
//import br.ifpb.monteiro.scream.services.ContaService;
//import br.ifpb.monteiro.scream.services.SecurityService;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.assertNotNull;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
///**
// *
// * @author Mauricio
// */
//@RunWith(MockitoJUnitRunner.class)
//public class ContaControllerTest {
//    
//       
//    private  Conta conta;
//        
//    @Mock
//    private ContaDaoIF contaDAO;
//    
//    private static SecurityService securityService;
//    
//    @Mock
//    private static ContaService contaService;
//    
//    public ContaControllerTest() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//        contaService= Mockito.mock(ContaService.class);
//        securityService= new SecurityService();
//        contaService.setSecurityService(securityService);
//        
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//        
//    }
//    
//    @Before
//    public void setUp() {
//        conta= new Conta();
//        conta.setNome("Marcus Patriota");
//        conta.setEmail("marcuspatriota@gmail.com");
//        conta.setUsuario("marcuspatriota");
//        conta.setSenha("123");
//        contaService.create(conta);
//    }
//    
//    @After
//    public void tearDown() {
//    }
//    
//    @Test
//    public void testCreate() {
//        conta= new Conta();
//        conta.setNome("Scream");
//        conta.setEmail("screm@gmail.com");
//        conta.setUsuario("scream");
//        conta.setSenha("scream");
//        contaService.create(conta);
//        assertNotNull("DAO Create Acessado",conta);
//        
//    }
//    
//    @Test
//    public void testFazerLoginInvalido() {
//       
//    }
//    
//    
//}