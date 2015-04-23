///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package br.ifpb.monteiro.ads.projeto2.scream.dao;
//
//import br.ifpb.monteiro.ads.projeto2.scream.entities.Conta;
//import br.ifpb.monteiro.ads.projeto2.scream.entities.Identifiable;
//import java.util.List;
//import javax.persistence.EntityManager;
//import org.junit.After;
//import org.junit.AfterClass;
//import static org.junit.Assert.*;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
///**
// *
// * @author Hugo
// */
//public class GenericDAOIT {
//    
//    public GenericDAOIT() {
//    }
//    
//    @BeforeClass
//    public static void setUpClass() {
//        
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
////
////    /**
////     * Test of getEntityManager method, of class GenericDAO.
////     */
////    @Test
////    public void testGetEntityManager() {
////        System.out.println("getEntityManager");
////        GenericDAO instance = new GenericDAO();
////        EntityManager expResult = null;
////        EntityManager result = instance.getEntityManager();
////        assertEquals(expResult, result);
////        // TODO review the generated test code and remove the default call to fail.
////        fail("The test case is a prototype.");
////    }
//
//    /**
//     * Test of create method, of class GenericDAO.
//     */
//    @Test
//    public void testCreate() {
//        System.out.println("create");
//        Conta entity = new Conta();
//        GenericDAO instance = new GenericDAO();
//        instance.create(entity);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of edit method, of class GenericDAO.
//     */
//    @Test
//    public void testEdit() {
//        System.out.println("edit");
//        Identifiable entity = null;
//        GenericDAO instance = new GenericDAO();
//        instance.edit(entity);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of remove method, of class GenericDAO.
//     */
//    @Test
//    public void testRemove() {
//        System.out.println("remove");
//        Identifiable entity = null;
//        GenericDAO instance = new GenericDAO();
//        instance.remove(entity);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of find method, of class GenericDAO.
//     */
//    @Test
//    public void testFind() {
//        System.out.println("find");
//        Object id = null;
//        GenericDAO instance = new GenericDAO();
//        Identifiable expResult = null;
//        Identifiable result = instance.find(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findAll method, of class GenericDAO.
//     */
//    @Test
//    public void testFindAll() {
//        System.out.println("findAll");
//        GenericDAO instance = new GenericDAO();
//        List<Identifiable> expResult = null;
//        List<Identifiable> result = instance.findAll();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findRange method, of class GenericDAO.
//     */
//    @Test
//    public void testFindRange() {
//        System.out.println("findRange");
//        int[] range = null;
//        GenericDAO instance = new GenericDAO();
//        List<Identifiable> expResult = null;
//        List<Identifiable> result = instance.findRange(range);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of count method, of class GenericDAO.
//     */
//    @Test
//    public void testCount() {
//        System.out.println("count");
//        GenericDAO instance = new GenericDAO();
//        int expResult = 0;
//        int result = instance.count();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
//}
