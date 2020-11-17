package iterasys;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Carrinho {

    String url;
    WebDriver driver;
    FluentWait<WebDriver> fluentWait;

    By logoCabecalho = By.cssSelector("a.navbar-brand");
    By menuCursos = By.cssSelector("li.active.item_sub.nav-menu-courses");
    By inputBusca = By.id("searchtext");
    By btnBusca = By.id("btn_form_search");
    By btnMatriculese = By.cssSelector("a[title=\"Matricule-se\"]");
    By btnComprar = By.cssSelector("div.box.box-flutuante.global-header-scrolling a.btn-comprar.addToCart");
    By btnConcluirPedido = By.id("concluir_pedido");

    @Before
    public void inicializar(){
        url = "https://iterasys.com.br";
        System.setProperty("webdriver.chrome.driver","drivers/ChromeDriver/86/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();

        this.fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(10000))
                .pollingEvery(Duration.ofMillis(1)).ignoring(NoSuchElementException.class);
    }

    @Test
    public void incluirCursoEmCarrinho(){
        driver.get(url);

        String nomeCurso = "Selenium WebDriver em Java";
        By linkCurso = By.cssSelector("a[title=\"" + nomeCurso + "\"]");

        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(logoCabecalho)); // aguardar carregar o cabeçalho
        driver.findElement(menuCursos).click(); // pressionar a opção cursos
        // Página nova
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(inputBusca)); // aguardar o campo de busca
        driver.findElement(inputBusca).sendKeys(nomeCurso); // preencher o campo de busca
        driver.findElement(btnBusca).click(); // pressionar o botão buscar
        // Página nova
        fluentWait.until(ExpectedConditions.elementToBeClickable(linkCurso)); // aguardar carregar resultado correto da busca
        driver.findElement(btnMatriculese).click(); // pressionar botão Matricule-se
        // Página nova
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(btnConcluirPedido)); // aguardar carregar botão de concluir pedido
        driver.findElement(btnConcluirPedido).click(); // pressionar botão Concluir Pedido
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
