package br.ifpb.monteiro.ads.projeto2.scream.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AceitacaoFazerLoginTest {
	
	private static WebDriver driver;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
		driver.get("localhost:8080/scream/");
	}
	@Test
	public void testFazerLogin() {
		/**
		 * Tenta entrar sem preencher campos
		 */
		driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
		
		/**
		 * Tenta logar com o usuário e senha errados
		 */
		driver.findElement(By.id("formularioLogin:username")).sendKeys("userNameErrado");
		driver.findElement(By.id("formularioLogin:senha")).sendKeys("senhaErrada123");
		driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
		driver.findElement(By.id("formularioLogin:username")).clear(); // Limpa
		
		/**
		 * Tenta logar com o usuário certo e senha vazia
		 */
		driver.findElement(By.id("formularioLogin:username")).sendKeys("UsuarioTeste");
		driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
		driver.findElement(By.id("formularioLogin:username")).clear(); // Limpa

		
		/**
		 * Tenta logar com o usuário certo e senha incorreta
		 */
		driver.findElement(By.id("formularioLogin:username")).sendKeys("UsuarioTeste");
		driver.findElement(By.id("formularioLogin:senha")).sendKeys("senhaErrada123");
		driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
		driver.findElement(By.id("formularioLogin:username")).clear(); // Limpa


		/**
		 * Tenta logar com o usuário incorreto e senha correta
		 */
		driver.findElement(By.id("formularioLogin:username")).sendKeys("userNameErrado");
		driver.findElement(By.id("formularioLogin:senha")).sendKeys("minhasenha123");
		driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
		driver.findElement(By.id("formularioLogin:username")).clear(); // Limpa


		/**
		 * Login efetuado
		 */
		driver.findElement(By.id("formularioLogin:username")).sendKeys("UsuarioTeste");
		driver.findElement(By.id("formularioLogin:senha")).sendKeys("minhasenha123");
		driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
		driver.findElement(By.id("j_idt14")).click();
//		/**
//		 * Fazer logOut
//		 */
//		driver.findElement(By.id("j_idt14")).click();
//		driver.findElement(By.id("formularioLogin:username")).clear(); // Limpa

	}
	
	public void variosClicks() {
		driver.findElement(By.id("formularioLogin:username")).sendKeys("UsuarioTeste");
		driver.findElement(By.id("formularioLogin:senha")).sendKeys("minhasenha123");
		/**
		 * Vai clicar 5 vezes
		 */
		for (int i = 0; i < 5; i++) {
			driver.findElement(By.id("formularioLogin:botaoEntrar")).click();
		}
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
