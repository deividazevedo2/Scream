/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ifpb.monteiro.ads.projeto2.scream.modelo;

import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Hugo
 */
public class ContaAceitacaoTest {
    
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    
//    public static void main(String[] args) throws InterruptedException{
//        AceitacaoTest x = new AceitacaoTest();
//        x.testLogin();
//    }
    
    @Before @Test
    public void setUp() throws Exception {
      driver = new FirefoxDriver();
      baseUrl = "localhost:8080/Scream";
      driver.get(baseUrl);
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }
    
    
    @Test
    public void testLogin() throws InterruptedException{
  
        Thread.sleep(5000);
        WebElement elemento;
        elemento = driver.findElement(By.id("usuario")); // Achar o campo para digitar o usuário
        elemento.sendKeys("hugo");
        elemento = driver.findElement(By.id("senha"));
        elemento.sendKeys("senhaerrada"); 
        elemento = driver.findElement(By.id("button_logar"));
        elemento.click();
        Thread.sleep(5000);
        
        elemento = driver.findElement(By.id("usuario")); // Achar o campo para digitar o usuário
        elemento.sendKeys("hugo");
        elemento = driver.findElement(By.id("senha"));
        elemento.sendKeys("123");
        elemento = driver.findElement(By.id("button_logar"));
        elemento.click();
        Thread.sleep(5000);
    }
    
    @After
      public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
          fail(verificationErrorString);
        }
      }

      private boolean isElementPresent(By by) {
        try {
          driver.findElement(by);
          return true;
        } catch (NoSuchElementException e) {
          return false;
        }
      }

      private boolean isAlertPresent() {
        try {
          driver.switchTo().alert();
          return true;
        } catch (NoAlertPresentException e) {
          return false;
        }
      }

      private String closeAlertAndGetItsText() {
        try {
          Alert alert = driver.switchTo().alert();
          String alertText = alert.getText();
          if (acceptNextAlert) {
            alert.accept();
          } else {
            alert.dismiss();
          }
          return alertText;
        } finally {
          acceptNextAlert = true;
        }
      }
    
}
