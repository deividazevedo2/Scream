package br.ifpb.monteiro.ads.projeto2.scream.aceitacao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AceitacaoControleAcesso {

	private static WebDriver driver;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception{
        driver = new FirefoxDriver();
        driver.get("localhost:8080/Scream/");
    }

	@Test
    public void testLogarNaoAutorizado(){

		driver.findElement(By.id("formularioLogin:username")).sendKeys("hugo");
        driver.findElement(By.id("formularioLogin:senha")).sendKeys("123");
        driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
        driver.get("localhost:8080/Scream/produto/index.xhtml");
        
        driver.findElement(By.id("formProduto:datatableProdutos:j_idt36")).click();
        driver.findElement(By.id("formProduto:novoNome")).sendKeys("Mais outro Novo Produto");
        driver.findElement(By.id("formProduto:j_idt56")).sendKeys("Este produto também está sendo criado para teste!");
        driver.findElement(By.id("formProduto:novoProduto")).click();
        driver.get("localhost:8080/Scream/produto/index.xhtml");

        driver.findElement(By.id("j_idt14")).click();
	}
	
	@Test
    public void testLogarAutorizado(){

		driver.findElement(By.id("formularioLogin:username")).sendKeys("adm");
        driver.findElement(By.id("formularioLogin:senha")).sendKeys("123");
        driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
        driver.get("localhost:8080/Scream/produto/index.xhtml");
        
        driver.findElement(By.id("formProduto:datatableProdutos:j_idt36")).click();
        driver.findElement(By.id("formProduto:novoNome")).sendKeys("Novo Produto");
        driver.findElement(By.id("formProduto:j_idt56")).sendKeys("Este produto está sendo criado para teste!");
        driver.findElement(By.id("formProduto:novoProduto")).click();
        driver.get("localhost:8080/Scream/produto/index.xhtml");
        
        driver.findElement(By.id("formProduto:datatableProdutos:j_idt36")).click();
        driver.findElement(By.id("formProduto:novoNome")).sendKeys("Outro Novo Produto");
        driver.findElement(By.id("formProduto:j_idt56")).sendKeys("Este produto também está sendo criado para teste!");
        driver.findElement(By.id("formProduto:novoProduto")).click();
        driver.get("localhost:8080/Scream/produto/index.xhtml");     
        
        driver.findElement(By.id("j_idt14")).click();
	}

}