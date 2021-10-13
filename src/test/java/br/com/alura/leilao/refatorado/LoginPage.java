package br.com.alura.leilao.refatorado;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private static final String URL_LOGIN = "http://localhost:8080/login";
	private WebDriver browser;

	public LoginPage(WebDriver driver) {
		this.browser = driver;
//		System.setProperty("webdriver.chrome.driver",
//				System.getProperty("user.dir") + "/drivers/chromedriver.exe");
//		this.browser = new ChromeDriver();
		browser.navigate().to(URL_LOGIN);
	}

	public void preencherFormularioDeLogin(String usuario, String senha) {
		browser.findElement(By.name("username")).sendKeys(usuario);
		browser.findElement(By.name("password")).sendKeys(senha);
	}

	public LeiloesPage efetuarLogin() {
		browser.findElement(By.id("login-form")).submit();
		;
		return new LeiloesPage(browser);
	}

	public String getNomeUsuarioLogado() {
		try {
			return browser.findElement(By.id("usuario-logado")).getText();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public boolean isPaginaAtual() {
		return browser.getCurrentUrl().contains(URL_LOGIN);
	}

	public boolean isMensagemDeLoginInvalidoVisivel() {
		return browser.getPageSource().contains("Usuário e senha inválidos");
	}

	public void fechar() {
		this.browser.quit();
	}

}
