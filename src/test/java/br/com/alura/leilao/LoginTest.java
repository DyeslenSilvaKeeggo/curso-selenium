package br.com.alura.leilao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.openqa.selenium.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

	private LoginPage paginaDeLogin;
	
	@Test
	public void deveriaEfetuarLoginComDadosValidos() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/drivers/chromedriver.exe");
		
		WebDriver webDriver = new ChromeDriver();

		webDriver.navigate().to("http://localhost:8080/login");
		webDriver.findElement(By.name("username")).sendKeys("fulano");
		webDriver.findElement(By.name("password")).sendKeys("pass");
		webDriver.findElement(By.id("login-form")).submit();

		assertFalse(webDriver.getCurrentUrl().equals("http://localhost:8080/login"));
		assertEquals("fulano", webDriver.findElement(By.xpath("//a[text()='Sair']/preceding-sibling::span")).getText());
		webDriver.quit();
	}

	@Test
	public void naoDeveriaEfetuarLoginComDadosValidos() {

		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/drivers/chromedriver.exe");

		WebDriver webDriver = new ChromeDriver();


		webDriver.navigate().to("http://localhost:8080/login");
		webDriver.findElement(By.name("username")).sendKeys("invalido");
		webDriver.findElement(By.name("password")).sendKeys("pass12323");
		webDriver.findElement(By.id("login-form")).submit();

		 assertTrue(webDriver.getCurrentUrl().equals("http://localhost:8080/login?error"));
		assertTrue("fulano", webDriver.getPageSource().contains("Usuário e senha inválidos."));
		assertThrows(NoSuchElementException.class, () -> webDriver.findElement(By.xpath("//a[text()='Sair']/preceding-sibling::span")));
		webDriver.quit();
	}
	
	@Test
	public void naoDeveriaAcessarPaginaRestritaSemEstaLogado() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/drivers/chromedriver.exe");
		
		WebDriver webDriver = new ChromeDriver();
		webDriver.navigate().to("http://localhost:8080/leiloes/2");
		
		assertTrue(webDriver.getCurrentUrl().equals("http://localhost:8080/login"));
		assertFalse(webDriver.getPageSource().contains("Dados do leilão"));
	}

}
