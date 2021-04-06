package mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class Login extends Page {

    private By txtMsg = new By.ById("br.com.petz:id/tv_login_internal_title");

    public Login(AndroidDriver<MobileElement> driver){
        super(driver);

        waitPresenceOf(txtMsg);
    }

    public String getTxtMsg() {
        return driver.findElement(txtMsg).getText();
    }
}
