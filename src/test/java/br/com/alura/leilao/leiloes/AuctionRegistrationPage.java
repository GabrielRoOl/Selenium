package br.com.alura.leilao.leiloes;

import br.com.alura.leilao.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuctionRegistrationPage extends PageObject {

    private static String URL_NEW_AUCTION = "http://localhost:8080/leiloes";

    public AuctionRegistrationPage(WebDriver browser) {
        super(browser);
    }

    public LeiloesPage registerAuction(String name, String value, String date) {
        this.browser.findElement(By.id("nome")).sendKeys(name);
        this.browser.findElement(By.id("valorInicial")).sendKeys(value);
        this.browser.findElement(By.id("dataAbertura")).sendKeys(date);
        this.browser.findElement(By.id("button-submit")).submit();
        return new LeiloesPage(browser);
    }

    public boolean isRegistrationAuction(String name, String value, String date) {
        WebElement lineTb = this.browser.findElement(By.cssSelector("#table-auction tbody tr:last-child"));

        WebElement colunmName = lineTb.findElement(By.cssSelector("td:nth-child(1)"));
        WebElement colunmDate = lineTb.findElement(By.cssSelector("td:nth-child(2)"));
        WebElement colunmValue = lineTb.findElement(By.cssSelector("td:nth-child(3)"));

        return colunmName.getText().equals(name) && colunmDate.getText().equals(date) && colunmValue.getText().equals(value);
    }

    public boolean isAtualPage() {
        return browser.getCurrentUrl().equals(URL_NEW_AUCTION);
    }

    public boolean isValidationsMessages() {
        String pageSource = browser.getPageSource();

        return pageSource.contains("não deve estar em branco") &&
                pageSource.contains("minimo 3 caracteres") &&
                pageSource.contains("deve ser um valor maior de 0.1") &&
                pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
    }
}
