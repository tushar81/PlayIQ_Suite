package Helper;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
//import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import Helper.Constants;


public class BrowserDriver extends TestListenerAdapter
{ 
	public static WebDriverWait wait;
	public static WebDriver driver;
	public static ITestContext ctx;
	public static Robot rb;
	 
	@BeforeSuite
	public static void setUp() 
	{
		//@SuppressWarnings("resource")//		
		/*AS ONLY IE Browser is supported today disable asking for browser
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Please enter the browser to run the Automation on ");
		 browsername = reader.next();	*/		
		String browsername = "IE";
		
		switch (browsername) 
		{         
        case "IE":
        	System.setProperty("webdriver.ie.driver", "C://Users//ukccs1tsa//Downloads//Tushar CC//Tools//Selenium//IEDriver//IEDriverServer.exe");
    		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
    		cap.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);    		    		
    		//cap.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);  
    		//cap.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
    		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
    		cap.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);		
    		driver  = new InternetExplorerDriver(cap);    		
            break;            
        case "CHROME":
        	System.setProperty("webdriver.chrome.driver", "C://Users//ukccs1tsa//Downloads//Tushar CC//Tools//Selenium//ChromeDriver//chromedriver.exe");		
    		driver=new ChromeDriver();    		
            break;
        case "FIREFOX":        
        case "FF":	
        	System.setProperty("webdriver.gecko.driver", "C://Users//ukccs1tsa//Downloads//Tushar CC//Tools//Selenium//GeckoDriver//geckodriver.exe");		
    		driver=new FirefoxDriver();    		
            break;
        default :
        	System.out.println("Tests to be Run in Phantom Browser with no UI");      
    		File file = new File("C:/Users/ukccs1tsa/Downloads/Tushar CC/Tools/Selenium/PhantomJS/phantomjs-2.1.1-windows/bin/phantomjs.exe");				
            System.setProperty("phantomjs.binary.path", file.getAbsolutePath());		
            driver = new PhantomJSDriver();
            break;
			}		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Constants.TimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constants.TimeOut, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, Constants.TimeOut);
		Constants.browsername = browsername;
		Reporter.log("*****************************-_ PlayIQ Test Suite Run Log - Executed on browser - "+ browsername + "_-*****************************");	
		 
		} 
		
		@AfterSuite
	public void tearDown() 
	{
		Reporter.log("************************************************************************************");
		driver.close();
	}
	 		
	@AfterMethod  
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException
	{ 
		//Take screenshot on failure		
		long millis = (testResult.getEndMillis()-testResult.getStartMillis());
				
		if (testResult.getStatus() != ITestResult.SUCCESS) 
			{ 
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
				FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + testResult.getName() + ".jpg"));
			}
		Reporter.log("Execution Time for Test "+testResult.getName().substring(0,(testResult.getName().length()-48) )+" is =  "+String.format("%d min, %d sec",TimeUnit.MILLISECONDS.toMinutes(millis),TimeUnit.MILLISECONDS.toSeconds(millis)-TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))));		
		Reporter.log("------------------------------------------------------------------------------");
		
	}
		
	//helper functions
	public boolean checkPageTitle(String titletext) throws InterruptedException
		{
			Thread.sleep(2000);
			WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lblMasterTitle")));
			Reporter.log("Title given in page - "+title.getText());
			
			if(titletext.equals(title.getText()))
				return true;
			else
				return false; 
		}
	
	public boolean checkPageTitle(String url,String titletext) throws InterruptedException
	{
		Boolean flag = false;
		Thread.sleep(1000);
		driver.navigate().to(url);
		WebElement title = wait.until(ExpectedConditions.elementToBeClickable(By.id("lblMasterTitle")));
		Reporter.log("Url - "+ driver.getCurrentUrl() );
		Reporter.log(" Has Page Title :  "+title.getText());
		//Check Logo
		if(driver.getCurrentUrl().contains("uk.test.clearchannel.io"))
			flag=External_checkPageLogo();
		else
			flag=Internal_checkPageLogo();
		
		Reporter.log("------------------------------------------------------------------------------");
		
		if(title.getText().equals(titletext)&&flag)
			return true;
		else
			{System.out.println(title.getText()+" EXPECTED Text - "+titletext);
			Reporter.log(title.getText()+" EXPECTED Text - "+titletext);
			return false;}
	}
	
	public boolean External_checkPageLogo() 
	{
		 WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'/assets/images/clear-channel_logo.png')]")));
		 int x = logo.getLocation().getX();		 int y = logo.getLocation().getY();
		 Reporter.log("CC Logo displayed with X & Y co-ordinates \n"+ x +" & "+ y );		 
		 Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", logo);
	        if (ImagePresent)	        	 
	        	return true;
	           else	        
	        	return false;
		}
		
	public boolean Internal_checkPageLogo() 
	{
		 WebElement ImageFile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[contains(@src,'/assets/css/img/clear-channel_play-iq.png')]")));
		 int x = ImageFile.getLocation().getX();		 int y = ImageFile.getLocation().getY();	  
		 Reporter.log("CC-PlayIQ Logo displayed with X & Y co-ordinates \n"+ x +" & "+ y );    
		 Boolean ImagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", ImageFile);
	        if (ImagePresent)	        
	        	return true;	        
	        else	        
	        	return false;
	    }        
    
	public void submitModal() throws AWTException, InterruptedException
	{
		Thread.sleep(500);
		driver.switchTo().activeElement();
		rb = new Robot();
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.keyPress(KeyEvent.VK_TAB);
		rb.keyRelease(KeyEvent.VK_TAB);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
	}

}

