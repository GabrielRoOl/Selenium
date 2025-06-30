package br.com.alura.leilao.login;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

    private LoginPage loginPage;

    @BeforeEach
    public void beforeEach() {
        this.loginPage = new LoginPage();
    }

    @AfterEach
    public void afterEach() {
        this.loginPage.quit();
    }

    @Test
    void efetuarLoginComDadosValidos() {
        loginPage.preencheFormularioDeLogin("fulano", "pass");
        loginPage.efetuaLogin();

        Assert.assertFalse(loginPage.isPaginaLogin());
        Assert.assertEquals("fulano Sair", loginPage.getNomeUsuarioLogado());
    }

    @Test
    void loginComDadosInvalidos() {
        loginPage.preencheFormularioDeLogin("invalido", "123");
        loginPage.efetuaLogin();

        Assert.assertTrue(loginPage.erroDeLogin());
        Assert.assertTrue(loginPage.usuarioOuSenhaIncorreto());
    }

    @Test
    void naoDeveAcessarPaginaRestritaSemLogar() {
        loginPage.navegaPaginaLances();

        Assert.assertTrue(loginPage.isPaginaLogin());
        Assert.assertFalse(loginPage.contemTexto("Dados do Leilão"));
    }
}
