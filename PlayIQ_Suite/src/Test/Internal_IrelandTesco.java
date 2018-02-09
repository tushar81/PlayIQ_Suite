package Test;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Helper.Constants;
import Helper.PlayIQ_Internal;
 

public class Internal_IrelandTesco extends PlayIQ_Internal
{
	@Test (priority=14)
	public void Internal_IrelandTesco_bookByLocation () throws InterruptedException, AWTException  
	{
		changeProduct("IrelandTesco");					
		driver.navigate().to("https://uat.playiq.co.uk/IrelandTesco/bookings/BookByLocation");
		String orderDetails = submitOrder(Constants.oneWeek);
		Constants.irelandTesco=orderDetails.substring(7,13);
		Assert.assertFalse(orderDetails.isEmpty());
		Reporter.log("IrelandTesco contract created - "+orderDetails);
		 
	}
	
	@Test (priority=14)
	public void Internal_IrelandTesco_bookByPlays () throws InterruptedException, AWTException  
	{
		changeProduct("IrelandTesco");					
		driver.navigate().to("https://uat.playiq.co.uk/IrelandTesco/bookings/BookByPlays");
		String orderDetails = submitPlayOrder();
		Assert.assertFalse(orderDetails.isEmpty());
		System.out.println(orderDetails);
		
	}
	
	@Test (priority=15)
	public void Internal_IrelandTesco_checkOrderValueZero ()   
	{
		
		changeProduct("IrelandTesco");	
		driver.navigate().to("https://uat.playiq.co.uk/IrelandTesco/bookings/Options");
		
		WebElement ContractNo =wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SupermarketViewBookingsUserControl1_txtContractNo")));
		ContractNo.sendKeys(Constants.irelandTesco);
		
		WebElement Search =wait.until(ExpectedConditions.elementToBeClickable(By.id("SearchButton")));
		Search.click();
		
		pleaseWait();
		
		WebElement Header = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SupermarketViewBookingsUserControl1_BookingsUserControl1_lblContractHeader")));
		Assert.assertTrue(Header.getText().contains(Constants.irelandTesco));
		
		WebElement CurrentOrderValue =wait.until(ExpectedConditions.elementToBeClickable(By.id("CurrentOrderValue")));
		Assert.assertTrue(CurrentOrderValue.getText().contains("Saved total order value is £0.00 spread over"));
		
		Reporter.log("Price for Contract - "+Constants.irelandTesco+" is "+CurrentOrderValue.getText());
		
	}
	
	@Test (priority=15)
	public void Internal_IrelandTesco_viewAvailability()
	{
		changeProduct("IrelandTesco");	
		driver.navigate().to("https://uat.playiq.co.uk/IrelandTesco/availability/availdata");
		
		String availability = runAvailability();
		Assert.assertFalse(availability.isEmpty());
		Assert.assertTrue(availability.contains("31 Aug"));
		
		Reporter.log("Tesco Ireland product availability for 1 day returns - "+availability);
		
	}
	
	@Test (priority=15)
	public void Internal_IrelandTesco_savedStoreLists()
	{
		driver.navigate().to("https://uat.playiq.co.uk/savedlists/Lists");
		
		WebElement List = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_grdSavedLists_lblListName_0")));
		Assert.assertFalse(List.getText().isEmpty());
		Assert.assertTrue(List.getText().contains("Autobot"));
		
		Reporter.log("Tesco Ireland product has saved list - "+List);
		
	}
	
}
