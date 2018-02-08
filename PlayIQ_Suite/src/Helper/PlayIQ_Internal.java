package Helper;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import Helper.BrowserDriver;

public class PlayIQ_Internal extends BrowserDriver
{

	@BeforeTest
	public void init() throws InterruptedException, AWTException 
	{		
		driver.get("https://uat.playiq.co.uk/Default");
		if(Constants.browsername.equalsIgnoreCase("IE"))
			{					
			Thread.sleep(1500);
			//if(driver.findElement(By.partialLinkText("Continue to this ")).isDisplayed())
				//driver.findElement(By.partialLinkText("Continue to this ")).click();		
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());     
			alert.authenticateUsing(new UserAndPassword(Constants.iUsername, Constants.iPassword));
			}		
		else
			{						
			Thread.sleep(1500);
			driver.switchTo().activeElement();
			Robot rb = new Robot();			
			rb.keyPress(KeyEvent.VK_U);
			rb.keyRelease(KeyEvent.VK_U);
			rb.keyPress(KeyEvent.VK_K);
			rb.keyRelease(KeyEvent.VK_K);
			rb.keyPress(KeyEvent.VK_C);
			rb.keyRelease(KeyEvent.VK_C);
			rb.keyPress(KeyEvent.VK_C);
			rb.keyRelease(KeyEvent.VK_C);
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_S);
			rb.keyPress(KeyEvent.VK_1);
			rb.keyRelease(KeyEvent.VK_1);
			rb.keyPress(KeyEvent.VK_T);
			rb.keyRelease(KeyEvent.VK_T);
			rb.keyPress(KeyEvent.VK_S);
			rb.keyRelease(KeyEvent.VK_S);
			rb.keyPress(KeyEvent.VK_A);
			rb.keyRelease(KeyEvent.VK_A);
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			
			rb.keyPress(KeyEvent.VK_3);
			rb.keyRelease(KeyEvent.VK_3);
			rb.keyPress(KeyEvent.VK_SHIFT); 
			rb.keyPress(KeyEvent.VK_3); 
			rb.keyRelease(KeyEvent.VK_3);
			rb.keyPress(KeyEvent.VK_U); 
			rb.keyRelease(KeyEvent.VK_U);
			rb.keyRelease(KeyEvent.VK_SHIFT);
			rb.keyPress(KeyEvent.VK_N); 
			rb.keyRelease(KeyEvent.VK_N);
			rb.keyPress(KeyEvent.VK_I); 
			rb.keyRelease(KeyEvent.VK_I);
			rb.keyPress(KeyEvent.VK_T); 
			rb.keyRelease(KeyEvent.VK_T);
			rb.keyPress(KeyEvent.VK_E); 
			rb.keyRelease(KeyEvent.VK_E);
			rb.keyPress(KeyEvent.VK_S); 
			rb.keyRelease(KeyEvent.VK_S);
			rb.keyPress(KeyEvent.VK_T); 
			rb.keyRelease(KeyEvent.VK_T);
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);			
			}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Sathaye"))); 
		Thread.sleep(1500); 
	}	

	public String submitOrder() throws InterruptedException, AWTException
	{
		//Used to create ASDA and Sainsbury and Tesco Ireland COntracts
		WebElement store = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Stores")));
		store.click();		
		
		Select List = new Select(driver.findElement(By.id("ddlSavedStoreList")));
		List.selectByVisibleText("Autobot List");
		driver.findElement(By.xpath("//*[@id='LocationPanel']/div/div/div/div[6]/div/div[3]/i")).click();
		
		WebElement ViewAvail =  wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_btnSearchAvail")));
		ViewAvail.click();
		Thread.sleep(500);
		pleaseWait();
		
		WebElement SelectAll =  wait.until(ExpectedConditions.elementToBeClickable(By.id("SelectAllCells")));
		SelectAll.click();
		
		WebElement GetPricing =  wait.until(ExpectedConditions.elementToBeClickable(By.id("divGetPricingButton")));
		GetPricing.click();
		
		WebElement CreateOrder =  wait.until(ExpectedConditions.elementToBeClickable(By.id("divPricingCreateOrder")));
		CreateOrder.click();
		
		WebElement Adv = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtAdvertiserName")));
		Adv.sendKeys("APP");
		Thread.sleep(1000);
		Adv.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(500);
		Adv.sendKeys(Keys.ENTER);						
		
		WebElement SP = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtSalesPersonName")));			
		SP.sendKeys("BROMLEY ASHLEY");
		Thread.sleep(1000);						
		WebElement MA = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtMainAgencyName")));			
		MA.sendKeys("CLEAR CHANNEL UK LTD");
		Thread.sleep(1000); 
		WebElement Cust = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtCustomerName")));			
		Cust.sendKeys("CLEAR CHANNEL UK LTD");
					
		WebElement Buyer = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtBuyerName")));
		Buyer.sendKeys("Megatron");
		
		Thread.sleep(1000);
		WebElement Creat = wait.until(ExpectedConditions.elementToBeClickable(By.id("CreateOrderButton")));
		Creat.click();
		submitModal();
		
		WebElement OrderCreated = wait.until(ExpectedConditions.elementToBeClickable(By.id("divBookingCreatedText")));
		Assert.assertTrue(OrderCreated.getText().contains("created successfully"));
		return OrderCreated.getText();
	}
	
	public String submitPlayOrder() throws InterruptedException, AWTException
	{
		WebElement NewBooking = wait.until(ExpectedConditions.elementToBeClickable(By.id("ShowCreateBooking")));
		NewBooking.click();
		Thread.sleep(1500);
		
		WebElement Dateslink = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Dates")));
		Dateslink.click();
		
		WebElement BookingDate = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SupermarketBookByPlays1_BookByPlaysFilterScreenUserControl1_txtSearchDates")));
		BookingDate.click();
		BookingDate.clear();
		BookingDate.sendKeys(Constants.oneDay);
		
		WebElement NoOfPlay = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SupermarketBookByPlays1_BookByPlaysFilterScreenUserControl1_txtNumberOfPlays")));
		NoOfPlay.click();
		NoOfPlay.sendKeys("10000");
		
		WebElement StartSearch = wait.until(ExpectedConditions.elementToBeClickable(By.id("StartSearchButton")));
		StartSearch.click();
		
		WebElement CreateOrderDetails = wait.until(ExpectedConditions.elementToBeClickable(By.id("divAdshelPricingCreateOrderDetails")));
		CreateOrderDetails.click();
		
		WebElement Adv = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SupermarketBookByPlays1_ContractHeaderUserControl1_txtAdvertiserName")));
		Adv.sendKeys("APP");
		Thread.sleep(1000);
		Adv.sendKeys(Keys.ARROW_DOWN);
		Thread.sleep(500);
		Adv.sendKeys(Keys.ENTER);						
		
		WebElement SP = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SupermarketBookByPlays1_ContractHeaderUserControl1_txtSalesPersonName")));			
		SP.sendKeys("BROMLEY ASHLEY");
		Thread.sleep(1000);						
		WebElement MA = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SupermarketBookByPlays1_ContractHeaderUserControl1_txtMainAgencyName")));		
		MA.sendKeys("CLEAR CHANNEL UK LTD");
		Thread.sleep(1000); 
		WebElement Cust = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SupermarketBookByPlays1_ContractHeaderUserControl1_txtCustomerName")));			
		Cust.sendKeys("CLEAR CHANNEL UK LTD");
					
		WebElement Buyer = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SupermarketBookByPlays1_ContractHeaderUserControl1_txtBuyerName")));
		Buyer.sendKeys("Lockhead");
		
		Thread.sleep(1000);
		WebElement Creat = wait.until(ExpectedConditions.elementToBeClickable(By.id("divAdshelPricingCreateOrder")));
		Creat.click();
		submitModal();
		
		WebElement OrderCreated = wait.until(ExpectedConditions.elementToBeClickable(By.id("headingCompletedOrder")));
		Assert.assertTrue(OrderCreated.getText().contains("created successfully"));
		return OrderCreated.getText();
		
	}
	
	public String deleteDays() throws AWTException, InterruptedException
	{
		//Used to delete days from ASDA and Sainsbury contract
		WebElement Search = wait.until(ExpectedConditions.elementToBeClickable(By.id("SearchButton")));
		Search.click();
		pleaseWait();
		
		WebElement dates = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='orderlines']/tbody/tr[3]/td[2]")));
		dates.click();
		
		for(int i=5;i<6;i=i+1)
		{
			driver.findElement(By.xpath("//*[@id='orderlines']/tbody/tr["+i+"]/td[10]/i")).click();
			submitModal();					
		}
		
		WebElement GetPrice = wait.until(ExpectedConditions.elementToBeClickable(By.id("divGetPricingButton")));
		GetPrice.click();
		
		WebElement Update = wait.until(ExpectedConditions.elementToBeClickable(By.id("divPricingUpdateBooking")));
		Update.click();
		submitModal();
		
		WebElement UpdateText = wait.until(ExpectedConditions.elementToBeClickable(By.id("divBookingUpdatedText")));
		return UpdateText.getText();
	}
		
	public void changeProduct(String product) 
	{
		
		if(driver.getCurrentUrl().contains("uk.test.clearchannel.io"))
			driver.navigate().to("https://uat.playiq.co.uk/changeproduct");
			
		WebElement currentProduct = driver.findElement(By.id("lblProductName"));
		
		if(!currentProduct.getText().equalsIgnoreCase(product))
		{
		
			driver.navigate().to("https://uat.playiq.co.uk/changeproduct");
			String xpath = "";
			//*[@id='MainContent_divAsda']/div/div/img
			
			switch (product.toUpperCase()) 
			{         
			case "ADSHEL":
				xpath = "//*[@id='MainContent_divAdshel']/div/div/img";
	            break;
			case "ASDA":
	        	xpath = "//*[@id='MainContent_divAsda']/div/div/img";  		
	            break;            
	        case "SAINSBURYS":
	        	xpath = "//*[@id='MainContent_divSainsburys']/div/div/img";    		
	            break;
	        case "STORM":
	        	xpath = "//*[@id='MainContent_divStorm']/div/div/img";    		
	            break;
	        case "WRAP":
	        	xpath = "//*[@id='MainContent_divWrap']/div/div/img";    		
	            break;
	        case "MALLS":
	        	xpath = "//*[@id='MainContent_divMalls']/div/div/img";    		
	            break;
	        case "IRELANDTESCO":
	        	xpath = "//*[@id='MainContent_divIrelandTesco']/div/div/img";    		
	            break;
	               
			 }
			WebElement prod =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));		
			prod.click();
		}
	}
	
	public void pleaseWait()
	{
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("modalPleaseWaitLabel")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='modalPleaseWait']/div/p/img")));
	}
	
	public String runAvailability()
	{
		WebElement Dates = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_AvailabilityUserControl1_txtChargeDates")));
		Dates.clear();
		Dates.sendKeys(Constants.oneDay);
		
		WebElement ViewAvail = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_AvailabilityUserControl1_btnSearchAvail")));
		ViewAvail.click();
		
		WebElement SelectAll = wait.until(ExpectedConditions.elementToBeClickable(By.id("SelectAllButton")));
		SelectAll.click();
		
		WebElement Reset = wait.until(ExpectedConditions.elementToBeClickable(By.id("SelectAllButton")));
		Reset.click();
		
		return driver.findElement(By.xpath("//*[@id='MainContent_AvailabilityUserControl1_tabAvail']/tbody/tr[2]/td[2]")).getText();
		
	} 
	
	public void cancelContract(String ContractNumber, String product) throws AWTException, InterruptedException
	{
		changeProduct(product);
		
		switch (product.toUpperCase()) 
		{
		case "ASDA":
			driver.navigate().to("https://uat.playiq.co.uk/asda/bookings/Options");  		
            break;            
        case "SAINSBURYS":
        	driver.navigate().to("https://uat.playiq.co.uk/sainsburys/bookings/Options");     		
            break;
        case "IRELANDTESCO":
        	driver.navigate().to("https://uat.playiq.co.uk/IrelandTesco/bookings/Options");	    	    		
            break;
		 }
		 
		WebElement contract = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SupermarketViewBookingsUserControl1_txtContractNo")));
		contract.sendKeys(ContractNumber);
		
		WebElement Search = wait.until(ExpectedConditions.elementToBeClickable(By.id("SearchButton")));
		Search.click();
		pleaseWait();
		
		WebElement Cancel = wait.until(ExpectedConditions.elementToBeClickable(By.id("divCancelBooking")));
		Cancel.click();
		submitModal();
		
		WebElement CancelMessage = wait.until(ExpectedConditions.elementToBeClickable(By.id("divBookingCreatedText")));
		Assert.assertTrue(CancelMessage.getText().contentEquals("Contract cancelled successfully"));
		Reporter.log("Cancelled "+product+" Contract - "+ContractNumber);
		
	}
	
}

