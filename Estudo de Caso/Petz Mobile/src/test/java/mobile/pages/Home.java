package mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class Home extends Page {

    private By iconeLupa = new By.ById("br.com.petz:id/menu_search");

    public Home(AndroidDriver<MobileElement> driver) {
        super(driver);

        waitPresenceOf(iconeLupa);
    }

    public void clickOnIconeLupa() { driver.findElement(iconeLupa).click(); }
}
