package mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;

public class Carrinho extends Page {

    private By txtNomeProduto =  new By.ById("br.com.petz:id/tv_prod_name");
    private By txtprecoNormal = new By.ById("br.com.petz:id/tv_prod_main_price");
    private By txtQuantidade = new By.ById("br.com.petz:id/tv_qty");

    private By txtItensSubtotal = new By.ById("br.com.petz:id/tv_subtotal_items");
    private By txtValorSubtotal = new By.ById("br.com.petz:id/tv_price_subtotal");

    private By inputCEP = new By.ById("br.com.petz:id/et_zip_code_shopping_cart");
    private By secaoEntrega = new By.ById("br.com.petz:id/tv_shipping_options_title");
    private By checkEntregaEconomica = new By.ById("br.com.petz:id/economic");
    private By txtValorEntregaEconomica = new By.ByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.RelativeLayout[1]/android.widget.ScrollView/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.RelativeLayout[2]/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView[2]");

    private By txtItensTotal = new By.ById("br.com.petz:id/tv_total_items");
    private By txtValorTotal = new By.ById("br.com.petz:id/tv_total_price");


    private By btnContinuarCheckout = new By.ById("br.com.petz:id/rl_btn_continue_to_checkout");

    public Carrinho(AndroidDriver<MobileElement> driver){
        super(driver);

        waitPresenceOf(txtNomeProduto);
    }

    public void setInputCEP(String cep) {
        driver.findElement(inputCEP).click();
        driver.findElement(inputCEP).sendKeys(cep);
        driver.pressKey(new KeyEvent(AndroidKey.NUMPAD_ENTER));
    }

    public String getTxtNomeProduto() { return driver.findElement(txtNomeProduto).getText(); }
    public String getTxtPrecoNormal() { return driver.findElement(txtprecoNormal).getText(); }
    public String getTxtQuantidade() { return driver.findElement(txtQuantidade).getText(); }

    public String getTxtItensSubtotal() { return driver.findElement(txtItensSubtotal).getText(); }
    public String getTxtValorSubtotal() { return driver.findElement(txtValorSubtotal).getText(); }
    public String getTxtValorEntregaEconomica() {
        String valor = driver.findElement(txtValorEntregaEconomica).getText();
        System.out.println("valor frete = \"" + valor + "\" - " + valor.contains("Grátis"));
        if(valor.contains("Grátis"))
            return "0";
        return valor.substring(valor.indexOf(" ") + 1);
    }
    public String getTxtItensTotal() { return driver.findElement(txtItensTotal).getText(); }
    public String getTxtValorTotal() { return driver.findElement(txtValorTotal).getText(); }

    public void clickOnCheckEntregaEconomica() { driver.findElement(checkEntregaEconomica).click(); }
    public void clickOnBtnContinuarCheckout() { driver.findElement(btnContinuarCheckout).click(); }

    public void waitEscolhaEntrega() {
        waitPresenceOf(secaoEntrega);
    }
}
