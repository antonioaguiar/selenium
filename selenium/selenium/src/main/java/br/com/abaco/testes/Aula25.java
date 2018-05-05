package br.com.abaco.testes;

import java.io.PrintStream;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class Aula25
{
  private static WebDriver driver;
  
  @BeforeClass
  public static void startTestClass()
  {
    System.setProperty("webdriver.gecko.driver", "/home/aguiar/Downloads/drivers/geckodriver");
    driver = new FirefoxDriver();
    System.out.println("Iniciando os testes");
  }
  
  @AfterClass
  public static void endTestClass()
  {
    driver.quit();
    System.out.println("Fim dos testes");
  }
  
  @Before
  public void initTest()
  {
    System.out.println("Configurar Teste");
    
    driver.manage().window().setSize(new Dimension(1200, 765));
    driver.navigate().to("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
  }
  
  @After
  public void endTest()
  {
    System.out.println("Teste encerrado!");
  }
  
  @Test
  public void deveInteragirComRadioButton()
  {
    System.out.println(".. interagindo com radio button.");
    driver.findElement(By.id("elementosForm:sexo:0")).click();
    Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
  }
  
  @Test
  public void deveInteragirComCheckBox()
  {
    System.out.println(".. interagindo com check box.");
    driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
    
    Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
  }
  
  @Test
  public void deveInteragirComCombo()
  {
    System.out.println(".. interagindo com combo box.");
    WebElement el = driver.findElement(By.id("elementosForm:escolaridade"));
    
    Select combo = new Select(el);
    
    combo.selectByIndex(2);
    combo.selectByValue("superior");
    combo.selectByVisibleText("Mestrado");
    
    Assert.assertEquals("Mestrado", combo.getFirstSelectedOption().getText());
  }
  
  @Test
  public void deveVerificarValoresCombo()
  {
    System.out.println(".. verificando valores numa combo box");
    WebElement el = driver.findElement(By.id("elementosForm:escolaridade"));
    
    Select combo = new Select(el);
    
    List<WebElement> options = combo.getOptions();
    
    Assert.assertEquals(8L, options.size());
    
    boolean achou = true;
    for (WebElement option : options) {
      if (option.getText().equals("Mestrado"))
      {
        achou = true;
        break;
      }
    }
    Assert.assertTrue(achou);
  }
}
