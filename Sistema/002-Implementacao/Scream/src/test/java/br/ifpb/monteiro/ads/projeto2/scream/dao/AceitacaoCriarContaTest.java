package br.ifpb.monteiro.ads.projeto2.scream.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AceitacaoCriarContaTest {
    
    private static WebDriver driver;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        driver = new FirefoxDriver();
        driver.get("localhost:8080/Scream/");
    }
    
    /**
     * Tenta salvar sem nenhum campo preenchido preenchido, espera um alerta de erro.
     */
    //@Test
    public void testCCaOutAll(){
    	
        driver.findElement(By.id("formularioLogin:novaConta")).click();
        driver.findElement(By.id("j_idt5:salvar")).click();
        
    }
    /**
     * Tenta salvar apenas o nome de usuário e a senha, espera um alerta de erro.
     */
    //@Test
    public void testCCbOutNomeEmail(){

        driver.findElement(By.id("j_idt5:usuario")).sendKeys("UsuarioTeste");
        driver.findElement(By.id("j_idt5:senha")).sendKeys("minhasenha123");
        driver.findElement(By.id("j_idt5:salvar")).click();
        
    }
//    /**
//     * Tenta savar com o email inválido
//     */
//    @Test
//    public void testCCInvEmail(){
//        
//        driver.findElement(By.id("j_idt5:nome")).sendKeys("Meu Nome Completo");
//        driver.findElement(By.id("j_idt5:senha")).sendKeys("minhasenha123");
//		driver.findElement(By.id("j_idt5:email")).sendKeys("meuEmail,com");
//		driver.findElement(By.id("j_idt5:salvar")).click();
//        
//    }
    
    /**
     * Salva
     */
   // @Test
    public void testCCcOk(){
    	
    	driver.findElement(By.id("j_idt5:usuario")).clear();
    	
    	driver.findElement(By.id("j_idt5:usuario")).sendKeys("UsuarioTeste");
        driver.findElement(By.id("j_idt5:nome")).sendKeys("Meu Nome Completo");
        driver.findElement(By.id("j_idt5:email")).sendKeys("meuEmail@gmail.com");
        driver.findElement(By.id("j_idt5:senha")).sendKeys("minhasenha123");
        
        driver.findElement(By.id("j_idt5:salvar")).click();
    }
    
    /**
     * Tenta Salvar com email e usuário existente
     */
   // @Test
    public void testCCdInvEmailUsuario(){

    	driver.findElement(By.id("formularioLogin:novaConta")).click();
    	driver.findElement(By.id("j_idt5:usuario")).sendKeys("UsuarioTeste");
        driver.findElement(By.id("j_idt5:nome")).sendKeys("Meu Nome Completo");
        driver.findElement(By.id("j_idt5:email")).sendKeys("meuEmail@gmail.com");
        driver.findElement(By.id("j_idt5:senha")).sendKeys("minhasenha123");
        
        driver.findElement(By.id("j_idt5:salvar")).click();
        
        
    }
    /**
     * Salva com outro username e email
     */
   // @Test
    public void testCCeOkOutro(){

    	driver.findElement(By.id("j_idt5:nome")).clear();    	
    	driver.findElement(By.id("j_idt5:usuario")).clear();
    	driver.findElement(By.id("j_idt5:email")).clear();
    	
        driver.findElement(By.id("j_idt5:nome")).sendKeys("Outro Nome Completo");
        driver.findElement(By.id("j_idt5:usuario")).sendKeys("OutroUsuario");
        driver.findElement(By.id("j_idt5:email")).sendKeys("outro@gmail.com");
        driver.findElement(By.id("j_idt5:senha")).sendKeys("minhasenha123");
        driver.findElement(By.id("j_idt5:salvar")).click();
    }
    
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        
    }
    
}
