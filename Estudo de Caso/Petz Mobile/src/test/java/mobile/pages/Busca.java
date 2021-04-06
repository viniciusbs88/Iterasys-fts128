package mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class Busca extends Page {

    private By inputBusca = new By.ById("br.com.petz:id/et_search");
    private By listaSugestoes = new By.ById("br.com.petz:id/rv_autocomplete_search");
    private By primeiraSugestao = new By.ById("br.com.petz:id/rl_row_wrapper");

    public Busca(AndroidDriver<MobileElement> driver) {
        super(driver);

        waitPresenceOf(inputBusca);
    }

    public void setInputBusca(String nomeProduto) {
        driver.findElement(inputBusca).click();
        driver.findElement(inputBusca).sendKeys(nomeProduto);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public void waitListaSugestoes() { waitPresenceOf(listaSugestoes); }

    public void clickOnPrimeiraSugestao() {
        driver.findElement(listaSugestoes).findElements(primeiraSugestao).get(1).click();
    }
}
