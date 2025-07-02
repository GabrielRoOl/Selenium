package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.WebDriver;

public class LeiloesPage extends PageObject {

    private static String URL_NEW_AUCTION = "http://localhost:8080/leiloes/new";
    private WebDriver browser;

    public LeiloesPage(WebDriver browser) {
        super(browser);
    }

    public void quit() {
        this.browser.quit();
    }

    public AuctionRegistrationPage loadForm() {
        this.browser.navigate().to(URL_NEW_AUCTION);
        return new AuctionRegistrationPage(browser);
    }
}
