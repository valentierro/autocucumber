package stepDefination;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

@RunWith(Cucumber.class)
public class stepDefination {
	public WebDriver driver;
	

	@Given("^usuario navega para site Verity Group$")
	public void usuario_navega_para_site_verity_group() throws Throwable {
		System.setProperty("webdriver.gecko.driver", "C:\\work\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
		driver.get("https://verity.com.br/");
	}

	@When("^usuario preenche Empresa \"([^\"]*)\" Nome \"([^\"]*)\" Email \"([^\"]*)\" Telefone \"([^\"]*)\" Assunto \"([^\"]*)\" Mensagem \"([^\"]*)\"$")
	public void enviarMensagem(String empresa, String nome, String email, String telefone, String assunto,
			String mensagem) throws Throwable {
		Actions act = new Actions(driver);
		driver.findElement(By.linkText("CONTATO")).click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;  
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[contains(@name,'empresa')]")).sendKeys(empresa);
		driver.findElement(By.xpath("//input[contains(@name,'nome')]")).sendKeys(nome);
		driver.findElement(By.xpath("//input[contains(@name,'email')]")).sendKeys(email);
		driver.findElement(By.xpath("//input[contains(@name,'telefone')]")).sendKeys(telefone);
		driver.findElement(By.xpath("//form[@method='POST']//label//select")).click();
		Thread.sleep(2000);
		act.sendKeys(Keys.ARROW_DOWN);
		act.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//textarea[@name='mensagem']")).sendKeys(mensagem);
		driver.findElement(By.xpath("//button[@class='submit']")).click();
		Thread.sleep(6000);
	}

	@Then("^texto \"([^\"]*)\" exibida$")
	public void texto_something_exibida(String textoEnvio) throws Throwable {
		WebElement lblMensagem= driver.findElement(By.xpath("//div[contains(@class,'button')]"));  
		 String msgEnvio = lblMensagem.getAttribute("innerText").toString();
		 
		 Assert.assertTrue(msgEnvio.contains(textoEnvio));
	}

}