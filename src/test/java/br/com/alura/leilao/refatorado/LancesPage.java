package br.com.alura.leilao.refatorado;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.alura.leilao.refatora.PageObject;

public class LancesPage extends PageObject {

	private static final String URL_LANCES ="http://localhost:8080/leilao"; 
	private WebDriver browser;
	
	public LancesPage() {
		super(null);
		browser.navigate().to(URL_LANCES);
	}

		
	public boolean isPaginaAtual() {
		return browser.getCurrentUrl().contains(URL_LANCES);
	}
	
	public boolean isTituloLeilaoVisivel() {
		return browser.getPageSource().contains("Dados do Leilão");
	}
	
	public void fechar() {
		this.browser.quit();
	}
}
