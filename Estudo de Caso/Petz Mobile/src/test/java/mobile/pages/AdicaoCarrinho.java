package mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class AdicaoCarrinho extends Page {

    private By txtNomeProduto =  new By.ById("br.com.petz:id/tv_product_name");
    private By txtprecoNormal = new By.ById("br.com.petz:id/tv_product_price");
    private By txtQuantidade = new By.ById("br.com.petz:id/tv_qty");
    private By btnIrParaCarrinho = new By.ById("br.com.petz:id/rl_go_to_shopping_cart");

    public AdicaoCarrinho(AndroidDriver<MobileElement> driver){
        super(driver);

        waitPresenceOf(txtNomeProduto);
    }

    public String getTxtNomeProduto() { return driver.findElement(txtNomeProduto).getText(); }
    public String getTxtPrecoNormal() { return driver.findElement(txtprecoNormal).getText(); }
    public String getTxtQuantidade() { return driver.findElement(txtQuantidade).getText(); }

    public void clickOnBtnIrParaCarrinho() { driver.findElement(btnIrParaCarrinho).click(); }
}
