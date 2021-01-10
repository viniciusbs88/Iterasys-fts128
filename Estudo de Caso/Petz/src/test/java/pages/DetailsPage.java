package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailsPage extends Page{

    private By linkMarca = By.className("blue");
    private By textoPrecoNormal = By.className("price-current");
    private By textoPrecoAssinante = By.className("price-subscriber");

    public DetailsPage(WebDriver driver){
        super(driver);
        waitVisibilityOfElementLocated(linkMarca);
    }

    public String getMarca(){
        return driver.findElement(linkMarca).getText();
    }

    public String getPrecoNormal() {
        return driver.findElement(textoPrecoNormal).getText();
    }

    public String getPrecoAssinante() {
        return driver.findElement(textoPrecoAssinante).getText();
    }
}
