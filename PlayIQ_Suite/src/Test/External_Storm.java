package Test;

import java.awt.AWTException;
import java.text.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import Helper.Constants;
import Helper.PlayIQ_External;

public class External_Storm extends PlayIQ_External
{

	@Test
	public void External_allLandingPages () throws InterruptedException  

	{
		Assert.assertTrue(checkPageTitle("https://uk.test.clearchannel.io/Storm/StormHome", "Storm"));		
		Assert.assertTrue(checkPageTitle("https://uk.test.clearchannel.io/Storm/CreateBooking", "Create Booking"));		
		Assert.assertTrue(checkPageTitle("https://uk.test.clearchannel.io/Storm/ViewBookings", "View Bookings"));		
	}

	@Test   
	public void External_viewBookingByDate() throws InterruptedException  
	{
		driver.navigate().to("https://uk.test.clearchannel.io/Storm/ViewBookings");
		WebElement DateTxt = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_txtSearchDates")));
		DateTxt.click();
		DateTxt.clear();
		DateTxt.sendKeys("25/12/2017 - 30/06/2018");
		
		Select droplist = new Select(driver.findElement(By.id("MainContent_ddlAgency")));   
		droplist.selectByVisibleText(Constants.Agency);
		Thread.sleep(1200);
		WebElement Search2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchButton2")));
		Search2.click();
 		
 		WebElement result = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_contractListView']/div[1]/h4"))); 
 		Assert.assertTrue(result.isDisplayed());					
		String msg = driver.findElement(By.xpath("//*[@id='MainContent_grdOptions_PagerRow']/td[2]/span")).getText();
		if(msg.contains("Total records found"))
			Reporter.log("Search Results given with info - "+msg);		
	}
	
	@Test   
	public void External_bookStormSkyline() throws InterruptedException  
	{
		//BOOKED FOR 30-06-2018 
		gotoPackages();		
		//Next page
		WebElement Skyline = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='pnlStorm25SalesAreaPacks']/div[2]/div/div/div[3]/a/div")));		
		Skyline.click();		
		
		WebElement BookingDate = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_txtSearchDates")));
		Reporter.log("Default booking period - "+BookingDate.getAttribute("value"));
		BookingDate.click();
		
		for(int i=6;i>0;i=i-1)
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/table/thead/tr[1]/th[3]/i")).click();	
		//Change as per last day of the 6 Month window
		WebElement day = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.lastDay)));	
		if(day.isEnabled())
			day.click();
		Thread.sleep(1100);
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/button[1]")).click();
		Reporter.log("Selected booking period for Storm Skyline- "+BookingDate.getAttribute("value"));
		
		bookStorm();		
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='tblOrderLines']/tbody/tr[9]/td[6]")).getText(),"£20,000.00");
	}
		
	@Test 
	public void External_bookStormCromination() throws InterruptedException  
	{
		
		gotoPackages();		
		
		WebElement Cromination = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Crom25")));
		Cromination.click();		
		
		WebElement BookingDate = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_txtSearchDates")));
		BookingDate.click();
		for(int i=6;i>0;i=i-1)
			driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/table/thead/tr[1]/th[3]/i")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/table/tbody/tr[2]/td[1]")).click();		
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/button[1]")).click();
		
		Reporter.log("Selected booking period for Storm Cromination- "+BookingDate.getAttribute("value"));
		
		bookStorm();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='tblOrderLines']/tbody/tr[4]/td[6]")).getText(),"£59,000.00");
	}
	
	@Test 
	public void External_bookStormNavigate5() throws InterruptedException  
	{
		//Book for 17-12-2017
		gotoPackages();
		//Next page
		WebElement Navigate5 = wait.until(ExpectedConditions.elementToBeClickable(By.id("stormNavigation5")));
		Navigate5.click();		
		
		selectLastDay();
		
		bookStorm();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='tblOrderLines']/tbody/tr[8]/td[6]")).getText(),"£6,480.00");
	}
	
	@Test 
	public void External_bookStormNavigate10() throws InterruptedException  
	{
		//Book for 16-12-2017
		gotoPackages();		
		//Next page
		WebElement Navigate10 = wait.until(ExpectedConditions.elementToBeClickable(By.id("stormNavigation10")));
		Navigate10.click();		
		
		selectLastDay();
		
		bookStorm();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='tblOrderLines']/tbody/tr[13]/td[6]")).getText(),"£10,800.00");
	}
	
	@Test 
	public void External_bookStormNavigate15() throws InterruptedException  
	{
		//Book for 15-12-2017
		gotoPackages();		
		//Next page
		WebElement Navigate15 = wait.until(ExpectedConditions.elementToBeClickable(By.id("stormNavigation15")));
		Navigate15.click();		
		
		selectLastDay();
		
		bookStorm();
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='tblOrderLines']/tbody/tr[18]/td[6]")).getText(),"£14,256.00");
	}
		
	@Test 
	public void External_bookStormDominationPackages() throws InterruptedException, AWTException  
	{
		gotoPackages();
		
		WebElement Shoreditch = wait.until(ExpectedConditions.elementToBeClickable(By.id("stormShoreditch")));
		Shoreditch.click();		
		
		WebElement Cromination = wait.until(ExpectedConditions.elementToBeClickable(By.id("CromDom")));
		Cromination.click();
		
		WebElement BookingDate = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_txtSearchDates")));
		BookingDate.click();		
		for(int i=6;i>0;i=i-1)
			driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/table/thead/tr[1]/th[3]/i")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/table/tbody/tr[2]/td[1]")).click();
		driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/button[1]")).click();
		
		WebElement CromDates = wait.until(ExpectedConditions.elementToBeClickable(By.id("CD_Dates")));
		CromDates.click();
		
		driver.switchTo().activeElement();
		driver.findElement(By.id("txtPackSearchDates")).click();
		
		driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/table/tbody/tr[3]/td[1]")).click();
		driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/button[1]")).click();
		driver.findElement(By.xpath("//*[@id='modalPackageDates']/div/div/div[3]/button[1]")).click();
		
		bookStorm();
		Reporter.log("Contract Type - "+driver.findElement(By.xpath("//*[@id='tblOrderLines']/tbody/tr[4]/td[2]")).getText());	
		Reporter.log("Contract Date - "+driver.findElement(By.xpath("//*[@id='tblOrderLines']/tbody/tr[4]/td[3]")).getText());		
		Reporter.log("Contract Price - "+driver.findElement(By.xpath("//*[@id='tblOrderLines']/tbody/tr[4]/td[6]")).getText());	
		 
		Assert.assertEquals(driver.findElement(By.xpath("//*[@id='tblOrderLines']/tbody/tr[6]/td[6]")).getText(),"£204,000.00");
		
	}
	
	@Test 
	public void External_checkRollingSixMonths() throws InterruptedException, AWTException, ParseException  
	{
		//Check the rolling 6 months availability
		gotoPackages();
				
		WebElement Navigate5 = wait.until(ExpectedConditions.elementToBeClickable(By.id("stormNavigation5")));
		Navigate5.click();
		
		WebElement BookingDate = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_txtSearchDates")));
		BookingDate.click();
		
		WebElement CurrentMonth = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/table/thead/tr[1]/th[2]")));
		Reporter.log("Current month is - "+CurrentMonth.getText());
		
		WebElement Arrow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/table/thead/tr[1]/th[3]/i")));
		Assert.assertTrue(Arrow.isDisplayed());
		Assert.assertFalse(driver.findElements(By.xpath("/html/body/div[2]/div[2]/div/table/thead/tr[1]/th[3]/i")).isEmpty());
		for(int i =0;i<7;i=i+1)
			driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/table/thead/tr[1]/th[3]/i")).click();		
		Assert.assertTrue(driver.findElements(By.xpath("/html/body/div[2]/div[2]/div/table/thead/tr[1]/th[3]/i")).isEmpty());
		
		CurrentMonth = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[2]/div[2]/div/table/thead/tr[1]/th[2]")));
		Reporter.log("Booking cannot be done for - "+CurrentMonth.getText());
	}	
	
	@Test   (priority=1)
	public void External_viewBookingByBookingReference() throws InterruptedException 
	{
		driver.navigate().to("https://uk.test.clearchannel.io/Storm/ViewBookings");
		WebElement refnumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_txtContractNo")));		
		refnumber.sendKeys(Constants.StormContract[0]);		
		driver.findElement(By.id("btnSearchByNumber")).click();
		
		WebElement BookingDetails = wait.until(ExpectedConditions.elementToBeClickable(By.id("lblBookingDetails")));
		WebElement OrderLines = wait.until(ExpectedConditions.elementToBeClickable(By.id("lblOrderLines")));
		OrderLines.click();
		Assert.assertTrue(BookingDetails.isDisplayed() && OrderLines.isDisplayed());		
		Reporter.log("View Booking page has booking details");
		Reporter.log("In Charge Dates  - "+ driver.findElement(By.id("MainContent_txtBookingDates")).getAttribute("value"));
		Reporter.log("Advertiser - "+ driver.findElement(By.id("MainContent_txtAdvertiserName")).getAttribute("value"));
		Reporter.log("Buyer - "+ driver.findElement(By.id("MainContent_txtBuyerName")).getAttribute("value"));		
		Reporter.log("Product - "+ driver.findElement(By.id("MainContent_txtBrandName")).getAttribute("value"));
		OrderLines.click();		
	}
	
	private void gotoPackages() 
	{
		driver.navigate().to("https://uk.test.clearchannel.io/Storm/CreateBooking");
		WebElement Continue = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnContinue")));
		
		Select Agency = new Select(driver.findElement(By.id("ddlAgencyName")));
		Agency.selectByVisibleText(Constants.Agency);
		
		Select Advertiser = new Select(driver.findElement(By.id("ddlAdvertiserName")));
		Advertiser.selectByVisibleText(Constants.Advertiser);
		
		Select Product = new Select(driver.findElement(By.id("ddlBrandName")));
		Product.selectByVisibleText(Constants.ProductName);
		Continue.click();
	}

	private void selectLastDay () throws InterruptedException
	{
		WebElement BookingDate = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_txtSearchDates")));
		BookingDate.click();		
		for(int i=6;i>0;i=i-1)
			driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/table/thead/tr[1]/th[3]/i")).click();	
		//Change in Constant CSS selector for Last day
			WebElement day = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(Constants.lastDay)));	
			if(day.isEnabled())
				day.click();
			Thread.sleep(1100);
			driver.findElement(By.xpath("/html/body/div[2]/div[3]/div/button[1]")).click();
			Reporter.log("Selected booking period  - "+BookingDate.getAttribute("value"));
	}
	
	private void bookStorm() throws InterruptedException 
	{
		WebElement SearchAvai = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnSearchAvailability")));
		SearchAvai.click();
		
		WebElement Proceed = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("btnConfirmBooking")));
		Proceed.click();
		
		WebElement comments = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_txtComments")));
		Thread.sleep(1100);
		comments.sendKeys( " -- THIS IS A TEST Contract --");
		
		
		WebElement Submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSubmitBooking")));
		Submit.click();
		
		WebElement bookingref = wait.until(ExpectedConditions.elementToBeClickable(By.id("lblBookingReference")));		
		
		Constants.StormContract[Constants.Counter] = bookingref.getText();
		Constants.Counter=Constants.Counter+1;	
		
		Reporter.log("Contract Type - "+driver.findElement(By.xpath("//*[@id='tblOrderLines']/tbody/tr[2]/td[2]")).getText());	
		Reporter.log("Contract Date - "+driver.findElement(By.xpath("//*[@id='tblOrderLines']/tbody/tr[2]/td[3]")).getText());		
		Reporter.log("Contract Price - "+driver.findElement(By.xpath("//*[@id='tblOrderLines']/tbody/tr[2]/td[6]")).getText());	
	
		Assert.assertTrue(bookingref.getText().length()>0);	
		Reporter.log("Contract Booking Reference Number - "+bookingref.getText());
		
	}
	
}

