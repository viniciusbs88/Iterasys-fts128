package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page{

    private By campoConsulta = By.name("q");

    public HomePage(WebDriver driver){

        super(driver);
        waitVisibilityOfElementLocated(campoConsulta);
    }

    public void setCampoConsulta(String termoConsulta) {
        driver.findElement(campoConsulta).sendKeys(termoConsulta);
    }

    public void buscar() {
        driver.findElement(campoConsulta).sendKeys(Keys.ENTER);
    }
}
