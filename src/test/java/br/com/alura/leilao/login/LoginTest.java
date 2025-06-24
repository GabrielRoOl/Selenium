package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

    @Test
    void efetuarLoginComDadosValidos() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver-win64\\chromedriver.exe"); // configuração de ambiente
        WebDriver browser = new ChromeDriver(); // abre o navegador
        browser.navigate().to("http://localhost:8080/login");   // local que será aberto
        browser.findElement(By.id("username")).sendKeys("fulano");  // procura um elemento pelo id e passa um texto como chave
        browser.findElement(By.id("password")).sendKeys("pass");    // procura um elemento pelo id e passa um texto como chave
        browser.findElement(By.id("login-form")).submit();  // procura um elemento pelo id e por ser um form apenas de um submit irá acionar o butão

        Assert.assertFalse(browser.getCurrentUrl().equals("http://localhost:8080/login"));  // Garante que o usuário *saiu* da pagina de login depois da autenticação
        Assert.assertEquals("fulano Sair", browser.findElement(By.id("usuario-logado")).getText()); // Garante que o usuario logado é o mesmo que fez a autenticação

        browser.quit();
    }
}
