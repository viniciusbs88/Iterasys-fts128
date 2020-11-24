package iterasys;

import org.apache.commons.io.FileUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class Carrinho {

    String url;
    WebDriver driver;
    FluentWait<WebDriver> fluentWait;
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    By logoCabecalho = By.cssSelector("a.navbar-brand");
    By menuCursos = By.cssSelector("li.active.item_sub.nav-menu-courses");
    By inputBusca = By.id("searchtext");
    By btnBusca = By.id("btn_form_search");
    By btnMatriculese = By.cssSelector("a[title=\"Matricule-se\"]");
    By btnComprar = By.cssSelector("div.box.box-flutuante.global-header-scrolling a.btn-comprar.addToCart");
    By btnConcluirPedido = By.id("concluir_pedido");
    By txtTituloItemResultado = By.cssSelector("li.item span.titulo");
    By txtTituloItemCarrinho = By.cssSelector("span.item-title");
    By txtFacaLogin = By.cssSelector("form.form-group.hidden-xs.hidden-sm.col-md-5.jumbotron.form-login h3.text-center");

    public void tirarPrint(String nomePrint) throws IOException {
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot,new File(pastaPrint + nomePrint + ".png"));
    }

    @Before
    public void inicializar(){
        url = "https://iterasys.com.br";
        System.setProperty("webdriver.chrome.driver","drivers/ChromeDriver/87/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();

        this.fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(10000))
                .pollingEvery(Duration.ofMillis(1)).ignoring(NoSuchElementException.class);
    }

    @Test
    public void incluirCursoEmCarrinho() throws IOException {
        driver.get(url);

        String nomeCurso = "Formação em Teste de Software";
        // ^ usado para indicar que o atributo title deve ter o valor começando com a string nomeCurso
        By linkCurso = By.cssSelector("a[title^=\"" + nomeCurso + "\"]");

        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(logoCabecalho)); // aguardar carregar o cabeçalho

        tirarPrint("Passo1 - Tela Inicial");
        assertTrue(driver.findElement(menuCursos).getText().contains("Cursos"));
        driver.findElement(menuCursos).click(); // pressionar a opção cursos
        // Página nova
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(inputBusca)); // aguardar o campo de busca
        tirarPrint("Passo2 - Tela de Cursos");
        driver.findElement(inputBusca).sendKeys(nomeCurso); // preencher o campo de busca

        tirarPrint("Passo3 - Consulta Preenchida");
        driver.findElement(btnBusca).click(); // pressionar o botão buscar
        // Página nova
        fluentWait.until(ExpectedConditions.elementToBeClickable(linkCurso)); // aguardar carregar resultado correto da busca
        tirarPrint("Passo4 - Resultado da Busca");

        assertTrue(driver.findElement(txtTituloItemResultado).getText().contains(nomeCurso));

        driver.findElement(btnMatriculese).click(); // pressionar botão Matricule-se
        // Página nova
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(btnConcluirPedido)); // aguardar carregar botão de concluir pedido
        tirarPrint("Passo 5 - Carrinho");

        assertTrue(driver.findElement(txtTituloItemCarrinho).getText().contains(nomeCurso));

        driver.findElement(btnConcluirPedido).click(); // pressionar botão Concluir Pedido
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(txtFacaLogin)); // aguardar carregar botão de concluir pedido

        tirarPrint("Passo6 - Tela Identificacao");
        assertTrue(driver.findElement(txtFacaLogin).getText().contains("FAÇA O LOGIN"));
    }

    @After
    public void finalizar(){
        try {
            Thread.sleep(3_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
