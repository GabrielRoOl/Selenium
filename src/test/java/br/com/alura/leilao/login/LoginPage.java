package br.com.alura.leilao.login;

import br.com.alura.leilao.leiloes.LeiloesPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

    private static String URL_LEILOES = "http://localhost:8080/login";
    private WebDriver browser;

    public LoginPage() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver-win64\\chromedriver.exe"); // configuração de ambiente
        this.browser = new ChromeDriver(); // abre o navegador
        this.browser.navigate().to(URL_LEILOES);
    }

    public void quit() {
        this.browser.quit();
    }

    public void preencheFormularioDeLogin(String username, String password) {
        browser.findElement(By.id("username")).sendKeys(username);
        browser.findElement(By.id("password")).sendKeys(password);
    }

    public LeiloesPage efetuaLogin() {
        browser.findElement(By.id("login-form")).submit();
        return new LeiloesPage(browser);
    }

    public boolean isPaginaLogin() {
        return browser.getCurrentUrl().equals(URL_LEILOES);
    }

    public String getNomeUsuarioLogado() {
        return browser.findElement(By.id("usuario-logado")).getText();
    }

    public boolean erroDeLogin() {
        return browser.getCurrentUrl().equals("http://localhost:8080/login?error");
    }

    public boolean usuarioOuSenhaIncorreto() {
        return browser.getPageSource().contains("Usuário e senha inválidos.");
    }

    public void navegaPaginaLances() {
        browser.navigate().to("http://localhost:8080/leiloes/2");
    }

    public boolean contemTexto(String texto) {
        return browser.getPageSource().contains(texto);
    }
}
