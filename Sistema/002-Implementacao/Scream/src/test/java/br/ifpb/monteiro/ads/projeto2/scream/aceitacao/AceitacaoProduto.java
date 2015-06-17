/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package br.ifpb.monteiro.ads.projeto2.scream.aceitacao;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Mauricio
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AceitacaoProduto {
    
    
    private static WebDriver driver;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception{
        driver = new FirefoxDriver();
        driver.get("localhost:8080/scream/");
        driver.findElement(By.id("formularioLogin:username")).sendKeys("adm");
        driver.findElement(By.id("formularioLogin:senha")).sendKeys("123");
        driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
        driver.get("localhost:8080/scream/produto/index.xhtml");
    }
    
    @Test
    public void testCriarProduto(){
        
        driver.findElement(By.id("formCadastroProduto:j_idt56")).sendKeys("Novo Produto");
        driver.findElement(By.id("formCadastroProduto:j_idt58")).sendKeys("Este produto está sendo criado para teste!");
        driver.findElement(By.id("formCadastroProduto:buttonSalvar")).click();
    }
    
    @Test
    public void testEditar() throws Exception {
        
        driver.get("localhost:8080/scream/produto/index.xhtml");
        driver.findElement(By.xpath("//tbody[@id='formProduto:datatableProdutos_data']/tr[3]/td[2]")).click();
        driver.findElement(By.id("formProduto:datatableProdutos:j_idt40")).click();
        driver.switchTo().activeElement();
        driver.findElement(By.id("formProduto:nome")).clear();
        driver.findElement(By.id("formProduto:nome")).sendKeys("Novo Produto Editado");
        driver.findElement(By.id("formProduto:j_idt49")).clear();
        driver.findElement(By.id("formProduto:j_idt49")).sendKeys("Este produto está sendo criado para teste e foi editado!");
        driver.findElement(By.id("formProduto:j_idt50")).click();
    }
    
    @Test
    public void testExcluirProduto(){
        
        driver.get("localhost:8080/scream/produto/index.xhtml");
        driver.findElement(By.xpath("//tbody[@id='formProduto:datatableProdutos_data']/tr[4]/td[2]")).click();
        driver.findElement(By.id("formProduto:datatableProdutos:j_idt41")).click();
        driver.findElement(By.id("formProduto:datatableProdutos:j_idt44")).click();
//        driver.findElement(By.id("formProduto:nome")).clear();
//        driver.findElement(By.id("formProduto:nome")).sendKeys("Novo Produto Editado");
//        driver.findElement(By.id("formProduto:j_idt49")).sendKeys("Este produto está sendo criado para teste e foi editado!");
//        driver.findElement(By.id("formProduto:j_idt50")).click();
    }
}
