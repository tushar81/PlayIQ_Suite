package Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeTest;
import Helper.BrowserDriver;

public class PlayIQ_External extends BrowserDriver 
{
	 
	@BeforeTest
	public void initbrowser()   
	{
		driver.get("https://uk.test.clearchannel.io/login/login");
		WebElement Uname = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_Login1_UserName")));
		WebElement pass = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_Login1_Password")));
		WebElement LogIn = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_Login1_LoginButton")));
		
		Uname.sendKeys(Constants.eUsername);
		pass.sendKeys(Constants.ePassword);
		LogIn.click();	
		//Thread.sleep(1500);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblMasterTitle")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnLogout")));		 
	}	
	
	 

}
