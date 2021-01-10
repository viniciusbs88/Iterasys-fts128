package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Page{
    private By tituloForm = By.cssSelector("#loginCliente h2");
    private By campoUsuario = By.name("login");
    private By campoSenha = By.id("senha");

    private By botaoEntrar = By.cssSelector("input.btn.modal-petz-btn.btn-redondo-verde");


    public LoginPage(WebDriver driver){
        super(driver);
        waitVisibilityOfElementLocated(tituloForm);
    }


    public String getTitulo() {
        return driver.findElement(tituloForm).getText();
    }

    public void setUsuario(String usuario) {
        driver.findElement(campoUsuario).click();
        setTextField(campoUsuario,usuario);
    }

    public void setSenha(String senha){
        setTextField(campoSenha,senha);
    }

    public SearchPage confirmarLogin() {
        driver.findElement(botaoEntrar).click();

        return new SearchPage(driver);
    }
}
