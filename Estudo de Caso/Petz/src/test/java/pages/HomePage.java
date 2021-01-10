package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends Page{

    private By campoConsulta = By.id("search");
    private By botaoConsulta = By.className("button-search");
    private By dropdownEntreOuCadastrese = By.cssSelector("button.greetings");
    private By botaoEntrar = By.cssSelector("a.button-login");
    private By botaoCadastrar = By.cssSelector("li.not-loggedin div p a");

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
