package mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Detalhes extends Page {

    private By txtNomeProduto =  new By.ById("br.com.petz:id/tv_prod_name");
    private By txtprecoNormal = new By.ById("br.com.petz:id/tv_prod_main_price");
    private By txtprecoAssinante = new By.ById("br.com.petz:id/tv_subscription_price");
    private By txtOptTamanho = new By.ById("br.com.petz:id/spinner_text");
    private By txtQuantidade = new By.ById("br.com.petz:id/tv_qty");
    private By btnAumentarQuantidade = new By.ById("br.com.petz:id/rl_qty_plus");
    private By btnAdicionarAoCarrinho = new By.ById("br.com.petz:id/tv_add_to_cart");

    public Detalhes(AndroidDriver<MobileElement> driver){
        super(driver);

        waitPresenceOf(txtNomeProduto);
        waitPresenceOf(txtprecoNormal);
        waitPresenceOf(txtprecoAssinante);
        waitPresenceOf(txtQuantidade);
    }

    public String getTxtNomeProduto() { return driver.findElement(txtNomeProduto).getText(); }
    public String getTxtPrecoNormal() { return driver.findElement(txtprecoNormal).getText(); }
    public String getTxtPrecoAssinante() { return driver.findElement(txtprecoAssinante).getText(); }
    public String getTxtQuantidade() { return driver.findElement(txtQuantidade).getText(); }

    public void clickOnBtnAumentarQuantidade() { driver.findElement(btnAumentarQuantidade).click(); }
    public void clickOnBtnAdicionarAoCarrinho() { driver.findElement(btnAdicionarAoCarrinho).click(); }

    public String getTxtOptTamanho() {
        return driver.findElement(txtOptTamanho).getText();
    }
}
