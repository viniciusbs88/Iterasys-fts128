package todo;

import org.junit.After;
import org.junit.Assert;
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

public class ListasPuro {

    String url;
    WebDriver driver;
    FluentWait<WebDriver> fluentWait;

    private By btnSignIn = By.id("mectrl_headerPicture");
    private By inputEmail = By.id("i0116");
    private By inputPassword = By.id("i0118");
    private By inputNomeLista = By.id("baseAddInput-addList");
    private By btnProximo = By.id("idSIButton9");
    private By listaDeListas = By.cssSelector("div.sortable-lists");
    private By labelNovaLista = By.cssSelector("div.sortable-lists div.listFadeShrink-enter-done:nth-child(1)");
    //private By lista = By.id("AQMkADAwATM0MDAAMS1mYjhlLWJkMzEtMDACLTAwCgAuAAADozXOj_TaaEK2XSkw9ukVkQEAMUgCj75aoEmVYUBLi1rbmQAD2b6fDgAAAA==");
    private By labelListaPrincipal = By.cssSelector("h2.listTitle");
    private By inputNomeTarefa = By.id("baseAddInput-addTask");
    private By menuReticencias = By.cssSelector("i.icon.fontIcon.ms-Icon.ms-Icon--More.iconSize-24");
    private By opcaoRenomear = By.cssSelector("i.icon.fontIcon.ms-Icon.ms-Icon--Rename.iconSize-24");
    private By opcaoExcluir = By.cssSelector("i.icon.fontIcon.ms-Icon.ms-Icon--Delete.iconSize-24");
    private By btnConfirmarExclusao = By.cssSelector("button.button.red");
    private By inputEditarTituloLista = By.cssSelector("input.chromeless.editing.tasksToolbar-input");
    private By itemMusicas = By.cssSelector("div.taskItem.theme-blue");

    @Before
    public void inicializar(){
        url = "https://todo.microsoft.com";
        System.setProperty("webdriver.chrome.driver","drivers/ChromeDriver/86/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();

        this.fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofMillis(10000))
                .pollingEvery(Duration.ofMillis(1)).ignoring(NoSuchElementException.class);

    }

    @Test
    public void chegarNaHome(){
        // tela inicial
        driver.findElement(btnSignIn).click();
        // tela usuário
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(inputEmail));
        driver.findElement(inputEmail).sendKeys(""); // inserir usuário entre as aspas
        driver.findElement(btnProximo).click();
        // tela senha
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(inputPassword));
        driver.findElement(inputPassword).sendKeys(""); // inserir senha entre as aspas
        driver.findElement(btnProximo).click();
        // tela permanecer conectado
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(btnProximo));
        driver.findElement(btnProximo).click();
    }


    @Test
    public void criarListaComTresItens(){
        driver.get(url);

        chegarNaHome();

        String nomeLista = "Musicas";

        fluentWait.until(ExpectedConditions.presenceOfElementLocated(listaDeListas)); // aguardar listas serem carregadas

        driver.findElement(inputNomeLista).click();
        driver.findElement(inputNomeLista).clear();
        driver.findElement(inputNomeLista).sendKeys(nomeLista);
        //TODO print de tela
        driver.findElement(inputNomeLista).sendKeys(Keys.ENTER);

        fluentWait.until(ExpectedConditions.presenceOfElementLocated(labelListaPrincipal));

        assertThat(driver.findElement(labelNovaLista).getText(),is(nomeLista));

        int i = 0;
        for(; i < 3; i++){
            driver.findElement(inputNomeTarefa).sendKeys("Musica" + (i + 1) + Keys.ENTER);
            fluentWait.until(ExpectedConditions.numberOfElementsToBe(itemMusicas,i + 1));
        }

        fluentWait.until(ExpectedConditions.numberOfElementsToBe(itemMusicas,i));
        Assert.assertEquals(driver.findElements(itemMusicas).size(), i);
    }

    @Test
    public void alterarLista(){
        driver.get(url);

        chegarNaHome();

        String nomeLista = "Musicas";
        String nomeListaAtualizada = "Minhas Musicas";
        By lista = By.xpath("//div[contains(@aria-label,\"" + nomeLista + "\")]");
        By listaAtualizada = By.xpath("//div[contains(@aria-label,\"" + nomeListaAtualizada + "\")]");

        driver.findElement(lista).click();
        driver.findElement(menuReticencias).click();
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(opcaoRenomear));
        driver.findElement(opcaoRenomear).click();

        driver.findElement(inputEditarTituloLista).sendKeys(nomeListaAtualizada + Keys.ENTER);

        fluentWait.until(ExpectedConditions.invisibilityOfElementLocated(inputEditarTituloLista));

        //assertThat(driver.findElements(lista).size(),is(0));
        //assertThat(driver.findElements(listaAtualizada).size(),is(1));
    }

    @Test
    public void excluirLista(){
        driver.get(url);

        chegarNaHome();

        String nomeLista = "Minhas Musicas";
        By lista = By.xpath("//div[contains(@aria-label,\"" + nomeLista + "\")]");

        driver.findElement(lista).click();
        driver.findElement(menuReticencias).click();
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(opcaoExcluir));
        driver.findElement(opcaoExcluir).click();
        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(btnConfirmarExclusao));
        driver.findElement(btnConfirmarExclusao).click();

        //assertThat(driver.findElements(lista).size(),is(0));
    }

    @After
    public void finalizar(){
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }
}
