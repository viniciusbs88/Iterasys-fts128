package mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class Page {
    protected AndroidDriver<MobileElement> driver;
    protected FluentWait<AndroidDriver<MobileElement>> fluentWait;

    /**
     * Construtor padrão
     *
     * @param driver
     */
    public Page(AndroidDriver<MobileElement> driver){
        this.driver = driver;
        this.fluentWait = new FluentWait<AndroidDriver<MobileElement>>(driver).withTimeout(Duration.ofMillis(30000))
                .pollingEvery(Duration.ofMillis(1)).ignoring(NoSuchElementException.class);
    }

    public void waitPresenceOf(By locator){
        fluentWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void waitVisibilityOfElementLocated(MobileElement element){
        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    public void setTextField(MobileElement element, String text){
        element.clear();
        element.sendKeys(text);
    }
}
