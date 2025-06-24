package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    private static String URL_LOGIN = "http://localhost:8080/login";

    private WebDriver browser;

    @BeforeAll
    public static void beforeAll(){
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver-win64\\chromedriver.exe"); // configuração de ambiente
    }

    @BeforeEach
    public void beforeEach(){
        this.browser = new ChromeDriver(); // abre o navegador
        this.browser.navigate().to(URL_LOGIN);
    }

    @AfterEach
    public void afterEach(){
        this.browser.quit();
    }

    @Test
    void efetuarLoginComDadosValidos() {
        browser.findElement(By.id("username")).sendKeys("fulano");  // procura um elemento pelo id e passa um texto como chave
        browser.findElement(By.id("password")).sendKeys("pass");    // procura um elemento pelo id e passa um texto como chave
        browser.findElement(By.id("login-form")).submit();  // procura um elemento pelo id e por ser um form apenas de um submit irá acionar o butão

        Assert.assertFalse(browser.getCurrentUrl().equals(URL_LOGIN));  // Garante que o usuário *saiu* da pagina de login depois da autenticação
        Assert.assertEquals("fulano Sair", browser.findElement(By.id("usuario-logado")).getText()); // Garante que o usuario logado é o mesmo que fez a autenticação
    }

    @Test
    void loginComDadosInvalidos() {

        // procura um elemento pelo id e passa um texto como chave
        browser.findElement(By.id("username")).sendKeys("Usuario Invalido");
        // procura um elemento pelo id e passa um texto como chave
        browser.findElement(By.id("password")).sendKeys("1234");
        // procura um elemento pelo id e por ser um form apenas de um submit irá acionar o butão
        browser.findElement(By.id("login-form")).submit();

        // Garante que o usuário permanece na tela de login
        Assert.assertTrue(browser.getCurrentUrl().equals("http://localhost:8080/login?error"));
        // Garante que o usuario recebeu uma mensagem de usuário e senha inválidos.
        Assert.assertTrue(browser.getPageSource().contains("Usuário e senha inválidos."));

    }
}
