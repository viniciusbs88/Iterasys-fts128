package desktop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class Page {
    protected WebDriver driver; // Selenium WebDriver
    protected FluentWait<WebDriver> fluentWait;

    /**
     * Construtor padrão
     *
     * @param driver
     */
    public Page(WebDriver driver){
        this.driver = driver;
        fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(10000))
                .pollingEvery(Duration.ofMillis(1)).ignoring(NoSuchElementException.class);
    }

    public void waitVisibilityOfElementLocated(By element){
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void setTextField(By locator, String text){
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }
}
