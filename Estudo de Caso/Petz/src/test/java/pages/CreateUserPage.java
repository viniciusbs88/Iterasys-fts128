package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

import java.util.Locale;

public class CreateUserPage extends Page{
    private By radioPF = By.id("tipoPessoaFisica");
    private By campoNome = By.id("Nome");
    private By campoEmail = By.id("Email");
    private By campoDDD = By.name("cliente.dddCelular");
    private By campoCelular = By.name("cliente.celular");
    private By campoDDDTelefone = By.name("cliente.dddTelefone");
    private By campoTelefone = By.name("cliente.telefone");
    private By selectSexo = By.id("Sexo");
    private By campoNascimento = By.id("dataNascimento");
    private By campoCPF = By.id("CPF-CNPJ");
    private By campoSenha = By.id("Senha");
    private By campoConfirmarSenha = By.id("confirmasenha");
    private By btnCriarConta = By.id("criarContaButton");
    private By tituloForm = By.cssSelector("h1.pull-left");

    private By txtModal = By.cssSelector("div.modal-body p.modal-message p");
    private By btnConfirmaModal = By.cssSelector("div.modal-body div.btns a.btn.modal-petz-btn.ok");

    public CreateUserPage(WebDriver driver){
        super(driver);
        waitVisibilityOfElementLocated(radioPF);
    }

    public void setNome(String nome){
        setTextField(campoNome, nome);
    }
    public void setEmail(String email){
        setTextField(campoEmail,email);
    }

    public void setDDD(String ddd){
        setTextField(campoDDD, ddd);
    }

    public void setCelular(String celular){
        setTextField(campoCelular, celular);
    }

    public void  setDDDTelefone(String ddd){
        setTextField(campoDDDTelefone, ddd);
    }

    public void setTelefone(String telefone){
        setTextField(campoTelefone, telefone);
    }

    public void setSexo(String opt){
        driver.findElement( By.cssSelector("#Sexo option[value = \"" + opt.toLowerCase() + "\"]")).click();
    }

    public void setNascimento(String nasc){
        setTextField(campoNascimento, nasc);
    }

    public void setCPF(String cpf){
        setTextField(campoCPF, cpf);
    }

    public void setSenha(String senha){
        setTextField(campoSenha, senha);
    }

    public void setConfirmarSenha(String senha){
        setTextField(campoConfirmarSenha, senha);
    }

    public void finalizarCadastro(){
        driver.findElement(btnCriarConta).click();
    }

    public String getTitulo() {
        return driver.findElement(tituloForm).getText();
    }

    public String getMensagemModal() { return driver.findElement(txtModal).getText(); }

    public void confirmarCadastro(){
        driver.findElement(btnConfirmaModal).click();
    }
}
