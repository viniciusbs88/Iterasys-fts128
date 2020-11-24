package mercadolivre;

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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BuscaProdutos {
    WebDriver driver;
    String url;
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    public void tirarPrint(String nomePrint) throws IOException {
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot,new File(pastaPrint + nomePrint + ".png"));
    }

    @Before
    public void iniciar(){
        url = "https://www.mercadolivre.com.br/";
        System.setProperty("webdriver.chrome.driver","drivers/ChromeDriver/87/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
    }

    @After
    public void encerrar(){
        driver.quit();
    }

    @Test
    public void consultarProdutoMicrofone() throws InterruptedException, IOException {
        driver.get(url);
        tirarPrint("Passo1 - Tela Inicial");
        String produto = "microfone";

        By inputTextoBusca = By.cssSelector("input.nav-search-input");
        By btnPesquisar = By.cssSelector("form.nav-search button.nav-search-btn");

        driver.findElement(inputTextoBusca).sendKeys(produto);
        tirarPrint("Passo2 - Campo de Consulta Preenchido");
        Thread.sleep(3_000);
        driver.findElement(btnPesquisar).click();
        tirarPrint("Passo3 - Resultados da Busca");

        By txtTituloBusca = By.cssSelector("h1.ui-search-breadcrumb__title");
        By txtTituloProduto = By.cssSelector("h2.ui-search-item__title");

        Thread.sleep(3_000);

        // Verificando se o título do resultado da busca (topo lateral esquerda) corresponde ao produto procurado
        assertEquals(produto.toUpperCase(), driver.findElement(txtTituloBusca).getText().toUpperCase());

        List<WebElement> titulosProdutos = driver.findElements(txtTituloProduto);

        // Verificando se o primeiro produto dentre os resultados possui a palavra microfone no titulo
        assertTrue(titulosProdutos.get(0).getText().toUpperCase().contains(produto.toUpperCase()));
    }

}
