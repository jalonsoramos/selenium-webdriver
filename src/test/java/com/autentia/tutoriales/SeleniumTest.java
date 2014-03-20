package com.autentia.tutoriales;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumTest {
    
	private WebDriver driver;
	
	@Before
	public void setUp() {
		driver = new HtmlUnitDriver();
	}
	
	@After
	public void tearDown() {
		driver.close();
	}
	
	@Test
	public void connectToAdictos()  {
		driver.get("http://www.adictosaltrabajo.com");
		Assert.assertEquals("Adictos al Trabajo. Formaci—n y desarrollo | JAVA, JEE, UML, XML |. Tutoriales sobre nuevas tecnolog’as.", 
				            driver.getTitle());
	}
	
	@Test
	public void connectToGoogle()  {
		driver.get("http://www.google.es");
		
		Assert.assertEquals("Google", driver.getTitle());
		
		//<input type="text" title="Buscar" value="" maxlength="2048" name="q" class="lst" autocomplete="off">
		
		WebElement webElementById = driver.findElement(By.name("q"));
		WebElement webElementByClassName = driver.findElement(By.className("lst"));
		WebElement webElementByCssSelector = driver.findElement(By.cssSelector("input[name=\"q\"]"));
		WebElement webElementByTagName = driver.findElement(By.tagName("input"));
		WebElement webElementByXPath = driver.findElement(By.xpath("//*[@name=\"q\"]"));
	}
	
	@Test
	public void fillForm() throws UnknownHostException  {
		final WebDriver driver = new FirefoxDriver();
		
		
		driver.get("http://" + InetAddress.getLocalHost().getHostAddress() + "/~jalonso/selenium/Selenium2.html");
		
		final String OPTION_MADRID = "Madrid";
		driver.findElement(By.id("nombre")).sendKeys("Juan");
		driver.findElement(By.id("apellidos")).sendKeys("Alonso");
		driver.findElement(By.id("email")).sendKeys("jalonso@autentia.com");
		
		final List<WebElement> options = driver.findElements(By.tagName("option"));
		for (WebElement option : options) {
			if (option.getText().equals(OPTION_MADRID)) {
				option.setSelected();
			}
		}
		
		final List<WebElement> radios = driver.findElements(By.name("newsletters"));
		for (WebElement radio : radios) {
			if (radio.getValue().equals("true")) {
				radio.setSelected();
			}
		}
		
		driver.findElement(By.id("enviar")).click();
	
		final Alert alert = driver.switchTo().alert();
		if (!alert.getText().equals("OK")) {
			Assert.fail(alert.getText());
		}
		
		driver.close();
	}
	
	@Test
	@Ignore
	public void testRemoteInternetExplorer() throws MalformedURLException  { 
		final DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		final WebDriver driver = new RemoteWebDriver(new URL("http://192.168.1.131:4444/wd/hub"), capabilities); 
		driver.get("http://www.google.es");
		
		Assert.assertEquals("Google", driver.getTitle());
		driver.close();
	} 
}
