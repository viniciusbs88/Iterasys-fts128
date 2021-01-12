package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage extends Page{
    private By opcaoIdentidade = By.cssSelector("#vtexIdUI-auth-selector h4.vtexIdUI-heading");
    private By pedidoEmail = By.cssSelector("#vtexIdUI-email-confirmation h4 span.vtexIdUI-heading");
    private By botaoAcessoEmail = By.id("loginWithAccessKeyBtn");
    private By campoEmail = By.cssSelector("div.controls.vtexIdUI-email-field input");
    private By botaoConfirmar = By.id("sendAccessKeyBtn");
    private By pedidoChave = By.cssSelector("#vtexIdUI-confirm-access-code h4 span.vtexIdUI-heading");
    private By campoCodigo = By.id("access-code");
    private By botaoEntrar = By.id("confirmLoginAccessKeyBtn");

    public LoginPage(WebDriver driver){
        super(driver);
        waitVisibilityOfElementLocated(opcaoIdentidade);
    }

    public String getOpcaoIdentidade() {
        return driver.findElement(opcaoIdentidade).getText();
    }

    public String getPedidoEmail(){
        waitVisibilityOfElementLocated(pedidoEmail);
        return driver.findElement(pedidoEmail).getText(); }

    public String getPedidoChave(){
        waitVisibilityOfElementLocated(pedidoChave);
        return driver.findElement(pedidoChave).getText();
    }


    public void setEmail(String email) {
        driver.findElement(campoEmail).click();
        setTextField(campoEmail,email);
    }

    public void setCodigo(String codigo){
        setTextField(campoCodigo,codigo);
    }

    public SearchPage confirmarLogin() {
        driver.findElement(botaoEntrar).click();

        return new SearchPage(driver);
    }

    public void receberChaveEmail() {
        driver.findElement(botaoAcessoEmail).click();
    }

    public void confirmarEmail(){
        driver.findElement(botaoConfirmar).click();
    }

    public String getCodigoDoEmail(String email) {
        // Manipulação do maildrop para obtenção do código
        System.out.println("iniciando a obtenção do código");
        Page mailDrop = new Page(new ChromeDriver());
        mailDrop.driver.get("https://maildrop.cc/inbox/" + email.substring(0,email.indexOf("@")));

        mailDrop.waitVisibilityOfElementLocated(By.cssSelector("div.messagetotal-container h5"));
        // enquanto a mensagem de caixa vazia estiver disponível, aguardará e apertará reload
        while(mailDrop.driver.findElement(By.cssSelector("div.messagetotal-container h5")).getText().equals("This inbox is empty.")) {
            try {
                Thread.sleep(10000);
                mailDrop.driver.findElement(By.cssSelector("div.inboxheader-container button")).click();
                System.out.println("reload do email");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        By primeiroEmail = By.cssSelector("div.messagelist-container div.messagelist-row:nth-child(1)");

        mailDrop.waitVisibilityOfElementLocated(primeiroEmail);
        mailDrop.driver.findElement(primeiroEmail).click(); // clicou no primeiro email

        By codigo = By.cssSelector("b[style=\"font-size:36px !important;text-align:center; \"]");
        mailDrop.waitVisibilityOfElementLocated(By.className("messagedata-iframe"));
        mailDrop.driver.switchTo().frame(0);

        String codigoEmail = mailDrop.driver.findElement(codigo).getText();
        mailDrop.driver.switchTo().defaultContent();
        mailDrop.driver.quit();

        return codigoEmail;
    }
}
