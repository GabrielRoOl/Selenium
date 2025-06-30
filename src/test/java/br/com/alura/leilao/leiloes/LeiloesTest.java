package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.login.LoginPage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LeiloesTest {

    private LeiloesPage leiloesPage;

    @AfterEach
    public void afterEach() {
        this.leiloesPage.quit();
    }
    
    @Test
    public void shouldRegisterAuction() {
        LoginPage loginPage = new LoginPage();
        loginPage.preencheFormularioDeLogin("fulano", "pass");
        this.leiloesPage = loginPage.efetuaLogin();
        AuctionRegistrationPage auctionRegistrationPage = leiloesPage.loadForm();

        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String name = "Leilão do dia " + date;
        String value = "500.00";

        this.leiloesPage = auctionRegistrationPage.registerAuction(name, value, date);

        Assert.assertTrue(auctionRegistrationPage.isRegistrationAuction(name, value, date));
    }
}
