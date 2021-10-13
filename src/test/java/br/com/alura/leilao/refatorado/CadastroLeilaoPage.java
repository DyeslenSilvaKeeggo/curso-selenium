package br.com.alura.leilao.refatorado;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage {

    private WebDriver browser;
	private static final String URL_Cadastro_Leilao = "http://localhost:8080/leiloes/new";
    
    public CadastroLeilaoPage(WebDriver browser) {
        this.browser = browser;        
    }

	public void fechar() {
	    this.browser.quit();
	}

		public LeiloesPage cadastrarLeilao(String dataAbertura, String nome, String valorInicial)  {
		    this.browser.findElement(By.xpath("//a[@id='novo_leilao_link']")).click();
			this.browser.findElement(By.xpath("//input[@id=\"nome\"]")).sendKeys(nome);
		    this.browser.findElement(By.xpath("//input[@id=\"valorInicial\"]")).sendKeys(valorInicial);
		    this.browser.findElement(By.xpath("//input[@id=\"dataAbertura\"]")).sendKeys(dataAbertura);
		    this.browser.findElement(By.xpath("//button[@id=\"button-submit\"]")).submit();
		    return new LeiloesPage(browser);
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