package br.com.alura.leilao.refatorado;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTeste {

	private LoginPage paginaDeLogin;
	private WebDriver driver;
	
	@BeforeEach
	public void beforeEach() {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		this.paginaDeLogin = new LoginPage(driver);
	}

	@AfterEach
	public void afterEach() {
		this.paginaDeLogin.fechar();
	}

	@Test
	public void deveriaEfetuarLoginComDadosValidos() {
		paginaDeLogin.preencherFormularioDeLogin("fulano", "pass");
		paginaDeLogin.efetuarLogin();

		String nomeDoUsuarioLogado = paginaDeLogin.getNomeUsuarioLogado();
		assertEquals("fulano", nomeDoUsuarioLogado);
		assertTrue(paginaDeLogin.isPaginaAtual());
	}

	@Test
	public void naoDeveriaEfetuarLoginComDadosInvalidos() {
		paginaDeLogin.preencherFormularioDeLogin("invalido", "3889");
		paginaDeLogin.efetuarLogin();

		assertNull(paginaDeLogin.getNomeUsuarioLogado());
		assertTrue(paginaDeLogin.isPaginaAtual());
		assertTrue(paginaDeLogin.isMensagemDeLoginInvalidoVisivel());
	}

	@Test
	public void naoDeveriaAcessarUrlRestritaSemEstarLogado() {
		LancesPage paginaDeLances = new LancesPage();
		assertFalse(paginaDeLances.isPaginaAtual());
		assertFalse(paginaDeLances.isTituloLeilaoVisivel());

		paginaDeLances.fechar();
	}

}
