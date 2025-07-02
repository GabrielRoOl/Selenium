package br.com.alura.leilao.login;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;
import org.openqa.selenium.By;

public class LoginPage extends PageObject {

    private static String URL_LEILOES = "http://localhost:8080/login";

    public LoginPage() {
        super(null);
        this.browser.navigate().to(URL_LEILOES);
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
