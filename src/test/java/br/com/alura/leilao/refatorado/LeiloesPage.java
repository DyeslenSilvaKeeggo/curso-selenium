package br.com.alura.leilao.refatorado;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesPage {

	private static final String URL_Cadastro_Leilao = "http://localhost:8080/leiloes/new";
	private static final String URL_Leiloes = "http://localhost:8080/leiloes";
	private WebDriver browser;
	private LeiloesPage leiloesPage;

	public LeiloesPage(WebDriver browser2) {
		this.browser = browser2;
//		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
//		this.browser = new ChromeDriver();
//		this.browser.navigate().to(URL_Cadastro_Leilao);
//		this.browser.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

//	public LeiloesPage() {
//		leiloesPage = new LeiloesPage();
//	}

	public void fechar() {
		this.browser.quit();
	}

	public CadastroLeilaoPage carregaFormulario() {
//		this.browser.navigate().to(URL_Cadastro_Leilao);
		return new CadastroLeilaoPage(browser);
	}

	public boolean isLeilaoCadastrado(String data, String nome, String valor) {
		System.out.println(nome);
		System.out.println(valor);
		System.out.println(data);
		WebElement linhaDaTabela = this.browser.findElement(By.xpath("//table//tbody//tr[last()]"));
		WebElement colunaNome = linhaDaTabela.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunaData = linhaDaTabela.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunaValorInicial = linhaDaTabela.findElement(By.cssSelector("td:nth-child(3)"));

		System.out.println(colunaNome.getText());
		System.out.println(colunaData.getText());
		System.out.println(colunaValorInicial.getText());
		
		return colunaNome.getText().equals(nome) && colunaData.getText().equals(data)
				&& colunaValorInicial.getText().equals(valor);
	}

	public boolean isPaginaAtual() {
		return browser.getCurrentUrl().equals(URL_Leiloes);
	}
}
