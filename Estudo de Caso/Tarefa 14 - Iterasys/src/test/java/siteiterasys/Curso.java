package siteiterasys;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class Curso{
    WebDriver driver;
    String url;
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    public void tirarPrint(String nomePrint) throws IOException {
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot,new File(pastaPrint + nomePrint + ".png"));
    }

    @Before
    public void iniciar(){
        url = "https://iterasys.com.br";
        System.setProperty("webdriver.chrome.driver","drivers/ChromeDriver/86/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @After
    public void encerrar(){
        driver.quit();
    }

    @Test
    public void consultarTestLink() throws InterruptedException, IOException {
        driver.get(url);
        tirarPrint("Passo1 - Tela Inicial");
        String curso = "Formação em Teste de Software";

        By inputSearchText = By.id("searchtext");
        By btnComprar = By.cssSelector("span.comprar");
        By txtCurso = By.cssSelector("span.item-title");

        driver.findElement(inputSearchText).sendKeys(curso + Keys.ENTER);
        tirarPrint("Passo2 - Resultado da Busca");
        Thread.sleep(3_000);
        driver.findElement(btnComprar).click();
        tirarPrint("Passo3 - Detalhes do Curso");
        assertTrue(driver.findElement(txtCurso).getText().contains(curso));

        assertEquals("R$ 1.990,00",driver.findElement(By.cssSelector("span.new-price")).getText());
        assertEquals("SUBTOTAL R$ 1.990,00",driver.findElement(By.cssSelector("div.subtotal")).getText());
        assertTrue(driver.findElement(By.cssSelector("div.ou-parcele")).getText().contains("ou em 12 x de R$ 165,83"));
    }

    @Test
    public void verificarLogin() throws IOException, InterruptedException {
        driver.get(url);
        tirarPrint("Passo1 - Tela Inicial");
        By btnLogin = By.cssSelector("li.active.login_header a");

        assertEquals("Login",driver.findElement(btnLogin).getText());

        driver.findElement(btnLogin).click();
        Thread.sleep(5_000);
        tirarPrint("Passo2 - Preenchimento Login");

        By inputEmail = By.id("email");
        By inputSenha = By.id("senha");
        By btnEntrar = By.id("btn_login");

        driver.findElement(inputEmail).sendKeys(""); // TODO inserir o usuario
        driver.findElement(inputSenha).sendKeys(""); // TODO inserir a senha
        tirarPrint("Passo 3 - Login Preenchido");
        driver.findElement(btnEntrar).click();
        Thread.sleep(5_000);
        tirarPrint("Passo 4 - Resultado Login");

        By imgFoto = By.cssSelector("#dropdownMenutopo img");
        By btnLogout = By.cssSelector("li.li_logout");

        assertTrue(driver.findElements(imgFoto).size() == 1);
        assertTrue(driver.findElements(btnLogout).size() == 1);

    }

}
