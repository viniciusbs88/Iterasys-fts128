package mobile.pages;

import desktop.pages.CreateUserPage;
import desktop.pages.LoginPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Landing extends Page {

    private By btnComecarSemLogin = new By.ById("br.com.petz:id/tv_start_without_login");

    public Landing(AndroidDriver<MobileElement> driver){
        super(driver);

        waitPresenceOf(btnComecarSemLogin);
    }

    public void clickOnBtnComecarSemLogin() {
        driver.findElement(btnComecarSemLogin).click();
    }
}
