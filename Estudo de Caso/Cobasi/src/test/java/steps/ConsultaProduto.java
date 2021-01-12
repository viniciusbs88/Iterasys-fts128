package steps;

import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import pages.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.junit.runners.Parameterized.*;

public class ConsultaProduto {
    private static WebDriver driver; // Selenium WebDriver utilizado para inicializar o projeto
    private static String url; // url base para iniciar todos os testes
    String pastaPrint = "evidence/" + new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(Calendar.getInstance().getTime()) + "/";

    protected HomePage homePage; // objeto correspondente ao mapeamento da página inicial
    protected SearchPage searchPage;
    protected DetailsPage detailsPage;
    protected CreateUserPage createUserPage;
    protected LoginPage loginPage;

    private String termoConsulta;
    private String browser;
/*
    public ConsultaProduto(String termoConsulta, String browser){
        this.termoConsulta = termoConsulta;
        this.browser = browser;
    }
*/
    @Parameters
    public static Collection<String[]> lerArquivo() throws IOException {
        return lerCSV("db/consultas petz.csv");
    }


    public static Collection<String[]> lerCSV(String nomeCSV) throws IOException {
        BufferedReader arquivo = new BufferedReader(new FileReader(nomeCSV));
        String linha;

        List<String[]> dados = new ArrayList<String[]>();

        while((linha = arquivo.readLine()) != null)
            dados.add(linha.split(";"));
        return dados;
    }

    @Before(order = 1)
    public static void startTests(){
        System.setProperty("webdriver.chrome.driver","drivers/chrome/87/chromedriver.exe");
        System.setProperty("webdriver.edge.driver","drivers/edge/msedgedriver.exe");
        System.setProperty("webdriver.gecko.driver","drivers/firefox/geckodriver.exe");
        System.setProperty("webdriver.ie.driver","drivers/ie/IEDriverServer.exe");

        url = "https://www.cobasi.com.br/";
    }

    public void iniciar(){
        String browser = "Chrome";

        driver = getDriver(browser); // recebe o driver correspondente ao tipo de browser
        driver.manage().window().maximize(); // maximiza a tela para iniciar os testes

        driver.get(url);

        homePage = new HomePage(driver);

        System.out.println("Passo 1 - Concluiu o setup");
    }

//    @After
    public void finishTests(){
        driver.quit();
        System.out.println("Passo 7 - Fechou o browser");
    }

    @Dado("^que acesso o site da Cobasi$")
    public void que_acesso_o_site_da_Cobasi() throws IOException {
        iniciar();
        tirarPrint("Passo 2 - Acessou o site da Cobasi");
    }

    @Quando("^procuro por \"([^\"]*)\"$")
    public void procuro_por(String termo) throws IOException {
        homePage.setCampoConsulta(termo);
        tirarPrint("Passo 3 - Procurou por " + termo);
        searchPage = homePage.buscar();
    }

    @Entao("^exibe uma lista de produtos relacionados a \"([^\"]*)\"$")
    public void exibe_uma_lista_de_produtos_relacionados_a(String termo) throws IOException {
        tirarPrint("Passo 4 - Exibiu a lista de produtos relacionados com " + termo);
        assertEquals(termo.toUpperCase(),searchPage.getTituloResultadoBusca());
    }

    @Quando("^seleciono o primeiro produto da lista$")
    public void seleciono_o_primeiro_produto_da_lista() throws IOException {
        detailsPage = searchPage.selecionarResultado(1);
        tirarPrint("Passo 5 - Selecionou o primeiro produto da lista");
    }

    @Entao("^verifico a marca como \"([^\"]*)\" com preco normal de \"([^\"]*)\" e \"([^\"]*)\" para assinantes$")
    public void verifico_que_a_marca_e_com_preco_normal_de_e_para_assinantes(String marca, String precoNormal, String precoAssinante) throws IOException {
        tirarPrint("Passo 6 - Verificou a marca e precos normal e assinante");

        assertEquals(marca,detailsPage.getMarca());
        assertEquals(precoNormal,detailsPage.getPrecoNormal());
        assertEquals(precoAssinante,detailsPage.getPrecoAssinante());
    }

    @Quando("^seleciono a opcao CRIAR CONTA$")
    public void seleciono_a_opcao_CRIAR_CONTA() throws IOException {
        tirarPrint("Passo 3 - Criar Conta");
        createUserPage = homePage.cadastrarUsuario();
    }

    @Entao("^exibe a pagina de cadastro de novo usuario$")
    public void exibe_a_pagina_de_cadastro_de_novo_usuario() throws IOException {
        tirarPrint("Passo 4 - Tela de cadastro de usuario");
        assertEquals("Minha Conta", createUserPage.getTitulo());
    }

    @Quando("^pressiono o botao CRIAR CONTA$")
    public void pressiono_o_botao_CRIAR_CONTA() throws IOException {
        createUserPage.finalizarCadastro();
    }

    @Entao("^verifico que o usuario foi criado com sucesso$")
    public void verifico_que_o_usuario_foi_criado_com_sucesso() throws IOException {
        assertEquals("Dados salvos com sucesso", createUserPage.getMensagemModal());

        tirarPrint("Passo 6 - Modal de confirmacao de criacao de usuario com sucesso");

        createUserPage.confirmarCadastro();
    }

    @Quando("^seleciono a opcao ENTRAR$")
    public void selecionoAOpcaoENTRAR() throws IOException {
        tirarPrint("Passo 3 - Realizar login");
        loginPage = homePage.logarUsuario();
    }

    @Entao("^exibe a pagina de login de usuario$")
    public void exibeAPaginaDeLoginDeUsuario() throws IOException {
        assertEquals("Use uma das opções para confirmar sua identidade", loginPage.getOpcaoIdentidade());

        tirarPrint("Passo 4 - Tela de Escolha da opcao de login");
    }

    @Quando("^seleciono a opcao de RECEBER CHAVE POR EMAIL$")
    public void selecionoAOpcaoDeRECEBERCHAVEPOREMAIL() throws IOException {
        tirarPrint("Passo 5 - Escolha da chave por email");

        loginPage.receberChaveEmail();
    }

    @Entao("^exibe a pagina de solicitacao de email$")
    public void exibeAPaginaDeSolicitacaoDeEmail() throws IOException {
        assertEquals("Por favor informe seu email", loginPage.getPedidoEmail());

        tirarPrint("Passo 6 - Solicitacao de email");
    }

    @Quando("^preencho o campo \"([^\"]*)\" no formulario de recebimento de chave$")
    public void preenchoOCampoEmailNoFormularioDeRecebimentoDeChave(String email) throws IOException {
        loginPage.setEmail(email);

        tirarPrint("Passo 7 - Email preenchido");
    }

    @E("^pressiono o botao CONFIRMAR$")
    public void pressionoOBotaoCONFIRMAR() {
        loginPage.confirmarEmail();
    }

    @Entao("^exibe a pagina de validacao da chave$")
    public void exibeAPaginaDeValidacaoDaChave() throws IOException {
        assertEquals("Informar chave de acesso", loginPage.getPedidoChave());

        tirarPrint("Passo 8 - Tela de validacao da chave de acesso");
    }

    @Quando("^preencho o campo chave com o valor recebido no email \"([^\"]*)\"$")
    public void preenchoOCampoChaveComOValorRecebidoPorEmail(String email) throws IOException {
        String codigo = loginPage.getCodigoDoEmail(email);
        loginPage.setCodigo(codigo);

        tirarPrint("Passo 9 - Chave de acesso preenchida");
    }

    @E("^pressiono o botao ENTRAR$")
    public void pressionoOBotaoENTRAR() throws IOException {
        searchPage = loginPage.confirmarLogin();
    }

    @Entao("^verifico que o usuario \"([^\"]*)\" realizou login com sucesso$")
    public void verificoQueOUsuarioRealizouLoginComSucesso(String usuario) throws IOException {
        assertEquals(usuario, searchPage.getNomeSaudacao());

        tirarPrint("Passo 6 - Login realizado com sucesso");
    }

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
