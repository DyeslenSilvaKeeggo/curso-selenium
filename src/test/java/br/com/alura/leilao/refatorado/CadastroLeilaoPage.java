package br.com.alura.leilao.refatorado;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.alura.leilao.refatora.PageObject;

public class CadastroLeilaoPage extends PageObject {

	private static final String URL_Cadastro_Leilao = "http://localhost:8080/leiloes/new";

	
	public CadastroLeilaoPage(WebDriver driver) {
		super(driver);
	}

	public void fechar() {
	    super.browser.quit();
	}

		public LeiloesPage cadastrarLeilao(String dataAbertura, String nome, String valorInicial)  {
		    super.browser.findElement(By.xpath("//a[@id='novo_leilao_link']")).click();
			super.browser.findElement(By.xpath("//input[@id=\"nome\"]")).sendKeys(nome);
		    super.browser.findElement(By.xpath("//input[@id=\"valorInicial\"]")).sendKeys(valorInicial);
		    super.browser.findElement(By.xpath("//input[@id=\"dataAbertura\"]")).sendKeys(dataAbertura);
		    super.browser.findElement(By.xpath("//button[@id=\"button-submit\"]")).submit();
		    return new LeiloesPage(super.browser);
		}
			
		public boolean isPaginaAtual() {
			return browser.getCurrentUrl().equals(URL_Cadastro_Leilao);
		}

		public boolean isMensagemDeValidacaoVisivel() {
			String pageSource = browser.getPageSource();
			return pageSource.contains("minino 3 caracteres")&&
					pageSource.contains("nao deve estar em branco")&&
					pageSource.contains("deve ser um valor maior de  0.1")&&
					pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
		}	
}