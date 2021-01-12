package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page{

    private By campoConsulta = By.cssSelector("input.fulltext-search-box.ui-autocomplete-input.neemu-search-field");
    private By botaoConsulta = By.cssSelector("input.btn-buscar");
    private By dropdownEntreOuCadastrese = By.cssSelector("header.header.ampstart-headerbar div.call-box");
    private By botaoEntrar = By.cssSelector("a.btn.btn-primary.btn-block.deslogado_btn");
    private By botaoCadastrar = By.cssSelector("a.btn.btn-outline-primary.btn-block.deslogado_btn");

    public HomePage(WebDriver driver){

        super(driver);
        waitVisibilityOfElementLocated(campoConsulta);
    }

    public void setCampoConsulta(String termoConsulta) {
        driver.findElement(campoConsulta).sendKeys(termoConsulta);
    }

    public SearchPage buscar() {
        driver.findElement(botaoConsulta).click();

        return new SearchPage(driver);
    }

    public CreateUserPage cadastrarUsuario(){
        driver.findElement(dropdownEntreOuCadastrese).click();
        driver.findElement(botaoCadastrar).click();

        return new CreateUserPage(driver);
    }

    public LoginPage logarUsuario(){
        driver.findElement(dropdownEntreOuCadastrese).click();
        driver.findElement(botaoEntrar).click();

        return new LoginPage(driver);
    }


}
