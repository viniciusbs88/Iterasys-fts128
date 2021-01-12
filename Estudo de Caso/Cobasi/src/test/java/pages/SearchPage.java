package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchPage extends Page {

    private By textoResultadoBusca = By.cssSelector("ul.neemu-breadcrumb-container li strong");
    private By listaResultados = By.cssSelector("li.nm-product-item");
    private By nomeSaudacao = By.cssSelector("div.dropdown.login.have-dropdown p span");

    public SearchPage(WebDriver driver){
        super(driver);
    }

    public String getTituloResultadoBusca() {
        return driver.findElement(textoResultadoBusca).getText();
    }

    public DetailsPage selecionarResultado(int i) {
        driver.findElements(listaResultados).get(i - 1).click();

        return new DetailsPage(driver);
    }

    public String getNomeSaudacao(){ return driver.findElement(nomeSaudacao).getText(); }
}
