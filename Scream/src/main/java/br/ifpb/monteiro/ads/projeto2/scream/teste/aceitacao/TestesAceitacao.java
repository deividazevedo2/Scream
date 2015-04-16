package br.ifpb.monteiro.ads.projeto2.scream.teste.aceitacao;

import net.sourceforge.htmlunit.corejs.javascript.tools.debugger.Main;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Hugo Correia
 */
public class TestesAceitacao {
    
    public static void main(String[] args) throws InterruptedException{
        TestesAceitacao teste = new TestesAceitacao();
        teste.testLogin();
    }
    
    public void testLogin() throws InterruptedException{
        
        System.out.println("fooooooooooooi");
        WebDriver driver = new FirefoxDriver();
        driver.get("localhost:8080/Scream");
        Thread.sleep(5000);
        WebElement elemento;
        elemento = driver.findElement(By.id("usuario")); // Achar o campo para digitar o usuário
        elemento.sendKeys("hugo");
        elemento = driver.findElement(By.id("senha"));
        elemento.sendKeys("123");
        elemento = driver.findElement(By.id("button_logar"));
        elemento.click();
        Thread.sleep(5000);
        driver.quit();
    }
    
}
