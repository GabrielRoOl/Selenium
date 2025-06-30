package br.com.alura.leilao.leiloes;

import org.openqa.selenium.WebDriver;

public class LeiloesPage {

    private static String URL_NEW_AUCTION = "http://localhost:8080/leiloes/new";
    private WebDriver browser;

    public LeiloesPage(WebDriver browser) {
        this.browser = browser;
    }

    public void quit() {
        this.browser.quit();
    }

    public AuctionRegistrationPage loadForm() {
        this.browser.navigate().to(URL_NEW_AUCTION);
        return new AuctionRegistrationPage(browser);
    }
}
