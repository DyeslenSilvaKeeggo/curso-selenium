package br.com.alura.leilao.refatorado;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LeiloesTest {

	private LeiloesPage paginaDeLeiloes;
	private CadastroLeilaoPage paginaDeCadastro;
	private WebDriver driver;

	@BeforeEach
	public void beforeEach() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.paginaDeLeiloes = new LeiloesPage(driver);
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.preencherFormularioDeLogin("fulano", "pass");
		this.paginaDeLeiloes = loginPage.efetuarLogin();
		paginaDeCadastro = paginaDeLeiloes.carregaFormulario();

	}

	@AfterEach
	public void afterEach() {
		this.driver.quit();
//		this.paginaDeLeiloes.fechar();
	}

	@Test
	public void deveriaCadastrarLeilao() throws InterruptedException {
		String hoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nome = "Leilao do Dia: " + hoje;
		String valor = "500.00";
	
		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao(hoje, nome, valor);
		assertTrue(paginaDeLeiloes.isLeilaoCadastrado(hoje, nome, valor));
	}
	
	@Test
	public void deveriaValidarCadastroDeLeilao() {
		this.paginaDeLeiloes = paginaDeCadastro.cadastrarLeilao("","","");
		assertFalse(this.paginaDeCadastro.isPaginaAtual());
		assertTrue(this.paginaDeLeiloes.isPaginaAtual());
		assertFalse(this.paginaDeCadastro.isMensagemDeValidacaoVisivel());
	}

}
