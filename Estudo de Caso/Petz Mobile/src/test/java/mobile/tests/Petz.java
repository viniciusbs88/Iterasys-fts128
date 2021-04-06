package mobile.tests;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import mobile.pages.*;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class Petz {

    private AndroidDriver<MobileElement> driver;
    private String termoBusca;
    private String cep;
    private Integer steps;

    String pastaPrint = "evidence/" + new SimpleDateFormat("yyyy-MM-dd HH-mm").format(Calendar.getInstance().getTime()) + "/";

    public Petz(String termoBusca, String cep){
        this.termoBusca = termoBusca;
        this.cep = cep;
        this.steps = 1;
    }

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "11");
        desiredCapabilities.setCapability("automationName", "UIAutomator2");
        desiredCapabilities.setCapability("autoAcceptAlerts", "true");
        desiredCapabilities.setCapability("autoGrantPermissions", "true");
        //desiredCapabilities.setCapability("deviceName", "emulator5554");
        desiredCapabilities.setCapability("appPackage", "br.com.petz");
        desiredCapabilities.setCapability("appActivity", "br.com.hanzo.petz.view.MainActivity");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new AndroidDriver<MobileElement>(remoteUrl, desiredCapabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Parameterized.Parameters
    public static Collection<String[]> lerArquivo() throws IOException {
        return lerCSV("db/comprasMobile.csv");
    }

    public static Collection<String[]> lerCSV(String nomeCSV) throws IOException {
        BufferedReader arquivo = new BufferedReader(new FileReader(nomeCSV));
        String linha;

        List<String[]> dados = new ArrayList<String[]>();

        while((linha = arquivo.readLine()) != null)
            dados.add(linha.split(";"));
        return dados;
    }

    public void tirarPrint(String nomePrint) throws IOException {
        File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenShot,new File(pastaPrint + "Passo " + (steps++) + " - " + nomePrint + ".png"));
    }


    @Test
    public void comprarProduto() throws IOException{
        // Aplicativo aberto. Acessando sem realizar login
        Landing landing = new Landing(driver);

        tirarPrint("Tela inicial - Acesso sem login");
        landing.clickOnBtnComecarSemLogin();

        // Clicar no bot�o de busca
        Home home = new Home(driver);
        tirarPrint("Acesso ao �cone lupa");
        home.clickOnIconeLupa();

        // Escrever o termo da busca e acessar o primeiro elemento da lista de sugest�es
        Busca busca = new Busca(driver);
        busca.setInputBusca(termoBusca);
        tirarPrint("Buscando por " + termoBusca);
        busca.waitListaSugestoes();
        tirarPrint("Selecionar� primeira sugest�o de produto");
        busca.clickOnPrimeiraSugestao();

/*      TRECHO DA LISTA DE OP��ES DE BUSCA COMENTADO, POIS ESSA TELA S� ABRE AO UTILIZAR O ENTER
        DO TECLADO DO CELULAR E O COMANDO N�O EST� FUNCIONANDO CORRETAMENTE NA AUTOMA��O

        ListaResultados listaResultados = new ListaResultados(driver);

        listaResultados.escolherProduto(1);
        String nomeProduto = listaResultados.getTxtNomeProdutoEscolhido();
        String precoNormal = listaResultados.getTxtValorNormalProdutoEScolhido();
        String precoAssinante = listaResultados.getTxtValorAssinanteProdutoEscolhido();

        assertTrue(nomeProduto.contains(termoBusca));

        listaResultados.clickOnProdutoEscolhido();
*/
        // Acesso � tela de detalhes do primeiro produto apresentado como sugest�o
        Detalhes detalhes = new Detalhes(driver);

        tirarPrint("Tela de detalhamento do produto");
        String nomeProduto = detalhes.getTxtNomeProduto();
        String precoNormal = detalhes.getTxtPrecoNormal();
        String precoAssinante = detalhes.getTxtPrecoAssinante();

        assertEquals(nomeProduto, detalhes.getTxtNomeProduto());
        assertEquals(precoNormal, detalhes.getTxtPrecoNormal());
        assertEquals(precoAssinante, detalhes.getTxtPrecoAssinante());
        assertEquals("1", detalhes.getTxtQuantidade());

        nomeProduto += " - " + detalhes.getTxtOptTamanho();

        // Aumento da quantidade de itens para 2 a fim de validar os c�lculos
        detalhes.clickOnBtnAumentarQuantidade();
        String qtd = detalhes.getTxtQuantidade();

        tirarPrint("Aumento da quantidade de itens para 2");
        assertEquals("2", qtd);

        tirarPrint("Adicionar� ao carrinho");
        detalhes.clickOnBtnAdicionarAoCarrinho();

        // Valida��o da tela de confirma��o de adi��o do produto ao carrinho
        AdicaoCarrinho adicaoCarrinho = new AdicaoCarrinho(driver);
        tirarPrint("Valida��o da confirma��o de inclus�o no carrinho");
        assertEquals(nomeProduto, adicaoCarrinho.getTxtNomeProduto());
        assertEquals(precoNormal, adicaoCarrinho.getTxtPrecoNormal());
        assertEquals(qtd, adicaoCarrinho.getTxtQuantidade());

        adicaoCarrinho.clickOnBtnIrParaCarrinho();

        // Valida��o do carrinho e inclus�o de CEP para verifica��o de frete e realiza��o de checkout
        Carrinho carrinho = new Carrinho(driver);

        tirarPrint("Valida��o do produto no carrinho");
        assertEquals(nomeProduto, carrinho.getTxtNomeProduto());
        assertEquals(precoNormal, carrinho.getTxtPrecoNormal());
        assertEquals(qtd, carrinho.getTxtQuantidade());

        assertEquals("(" + qtd + " itens)",carrinho.getTxtItensSubtotal());
        assertEquals(Float.toString(Integer.parseInt(qtd) * Float.parseFloat(precoNormal)), carrinho.getTxtValorSubtotal());

        // Inclus�o de CEP
        carrinho.setInputCEP(cep);
        tirarPrint("Preenchimento do CEP");
        carrinho.waitEscolhaEntrega();
        carrinho.clickOnCheckEntregaEconomica();
        tirarPrint("Escolha da entrega econ�mica");
        String frete = carrinho.getTxtValorEntregaEconomica();

        assertEquals("(" + qtd + " itens)",carrinho.getTxtItensTotal());
        assertEquals(Float.toString(Integer.parseInt(qtd) * Float.parseFloat(precoNormal) + Float.parseFloat(frete)), carrinho.getTxtValorTotal());

        tirarPrint("Valor da compra validado com frete");
        carrinho.clickOnBtnContinuarCheckout();

        // Valida��o do carregamento da tela de login para checkout
        Login login = new Login(driver);
        assertEquals("Para prosseguir, fa�a seu login", login.getTxtMsg());
        tirarPrint("Conclus�o do fluxo no login");
    }
}

