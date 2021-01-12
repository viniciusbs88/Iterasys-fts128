package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailsPage extends Page{

    private By linkMarca = By.cssSelector("div.product__brand a");
    private By textoPrecoNormal = By.cssSelector("div.js-price-product span");
    private By textoPrecoAssinante = By.cssSelector("span.price__assinatura-price");

    public DetailsPage(WebDriver driver){
        super(driver);
        waitVisibilityOfElementLocated(linkMarca);
    }

    public String getMarca(){
        return driver.findElement(linkMarca).getText();
    }

    public String getPrecoNormal() {
        String preco = driver.findElement(textoPrecoNormal).getText();
        preco = preco.substring(preco.indexOf("R$"),preco.indexOf("à vista"));

        return preco;
    }

    public String getPrecoAssinante() {
        String preco = driver.findElement(textoPrecoAssinante).getText();
        preco = preco.substring(preco.indexOf("R$"),preco.indexOf(" ("));

        return preco;
    }
}
