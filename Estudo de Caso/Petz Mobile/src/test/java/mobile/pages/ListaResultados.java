package mobile.pages;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class ListaResultados extends Page {

    private By listaResultados = new By.ById("br.com.petz:id/rv_products_search");

    private By txtNomeProdutoEscolhido = new By.ById("br.com.petz:id/prodName");
    private By txtValorNormalProdutoEScolhido = new By.ById("br.com.petz:id/mainPrice");
    private By txtValorAssinanteProdutoEscolhido = new By.ById("br.com.petz:id/subscribersPrice");

    private MobileElement cardProdutoEscolhido;

    public ListaResultados(AndroidDriver<MobileElement> driver){
        super(driver);

        waitPresenceOf(listaResultados);
    }

    public void escolherProduto(Integer indice) {
        cardProdutoEscolhido = driver.findElements(new By.ById("br.com.petz:id/wrapper")).get(indice);
    }

    public String getTxtNomeProdutoEscolhido() { return cardProdutoEscolhido.findElement(txtNomeProdutoEscolhido).getText(); }
    public String getTxtValorNormalProdutoEScolhido() { return cardProdutoEscolhido.findElement(txtValorNormalProdutoEScolhido).getText(); }
    public String getTxtValorAssinanteProdutoEscolhido() { return cardProdutoEscolhido.findElement(txtValorAssinanteProdutoEscolhido).getText(); }

    public void clickOnProdutoEscolhido() {
        cardProdutoEscolhido.click();
    }
}
