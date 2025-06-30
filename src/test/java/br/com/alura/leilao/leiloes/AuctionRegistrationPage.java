package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AuctionRegistrationPage {

    private WebDriver browser;

    public AuctionRegistrationPage(WebDriver browser) {
        this.browser = browser;
    }

    public void quit() {
        this.browser.quit();
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
}
