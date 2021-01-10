package guitests;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.support.ui.FluentWait;
import pages.HomePage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class BaseTests {
    private static WebDriver driver; // Selenium WebDriver utilizado para inicializar o projeto
    private String url; // url base para iniciar todos os testes
    String pastaPrint = "evidencias/" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime()) + "/";

    protected HomePage homePage; // objeto correspondente ao mapeamento da página inicial

    private String termoConsulta;
    private String browser;


    public BaseTests(String termoConsulta, String browser){
        this.termoConsulta = termoConsulta;
        this.browser = browser;
    }

    @Parameters
    public static Collection<String[]> lerArquivo() throws IOException {
        return lerCSV("db/consultas google.csv");
    }


    public static Collection<String[]> lerCSV(String nomeCSV) throws IOException {
        BufferedReader arquivo = new BufferedReader(new FileReader(nomeCSV));
        String linha;

        List<String[]> dados = new ArrayList<String[]>();

        while((linha = arquivo.readLine()) != null)
            dados.add(linha.split(";"));
        return dados;
    }


    @BeforeClass
    public static void startTests(){
        System.setProperty("webdriver.chrome.driver","drivers/chrome/87/chromedriver.exe");
        System.setProperty("webdriver.edge.driver","drivers/edge/msedgedriver.exe");
        System.setProperty("webdriver.gecko.driver","drivers/firefox/geckodriver.exe");
        System.setProperty("webdriver.ie.driver","drivers/ie/IEDriverServer.exe");
    }

    @Before
    public void carregarPaginaInicial(){
        url = "https://google.com/";

        driver = getDriver(browser); // recebe o driver correspondente ao tipo de browser
        driver.manage().window().maximize(); // maximiza a tela para iniciar os testes

        driver.get(url);

        homePage = new HomePage(driver);
    }

    @After
    public void finishTests(){
        driver.quit();
    }

    /****************************************************************
     *                      Métodos Auxiliares
     ****************************************************************/

    /****************************************************************
     *
     * @param browser
     * @return
     */


    private static WebDriver getDriver(String browser){
        switch (browser){
            case "Firefox":
                return new FirefoxDriver();
            case "IE":
                return new InternetExplorerDriver();
            case "Edge":
                return new EdgeDriver();
            case "Chrome":
            default:
                return new ChromeDriver();
        }
    }

    public void tirarPrint(String nomePrint) throws IOException {
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot,new File(pastaPrint + nomePrint + ".png"));
    }
}
