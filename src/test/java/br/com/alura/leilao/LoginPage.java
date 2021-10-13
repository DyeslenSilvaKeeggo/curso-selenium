package br.com.alura.leilao;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {
	
	private static final String URL_LOGIN = "http://localhost:8080/login";
	private WebDriver browser;
	
	public LoginPage() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/drivers/chromedriver.exe");
		this.browser = new ChromeDriver();
		browser.navigate().to(URL_LOGIN);
	}
	
	
	public static void beforeAll() {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/drivers/chromedriver.exe");
	}
	
	public void beforeEach() {
		this.browser = new ChromeDriver();
		browser.navigate().to(URL_LOGIN);
	}
	
	
}
