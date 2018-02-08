package Test;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Helper.Constants;
import Helper.PlayIQ_Internal;

//Storm test cases - Priority 1 to 4
public class Internal_Storm extends PlayIQ_Internal
{		
	
	@Test   (priority=1)
	public void Internal_Storm_runAvailabilityandBook() throws InterruptedException, AWTException   
	{
		changeProduct("STORM");
		driver.navigate().to("https://uat.playiq.co.uk/storm/availability/availdata");
		WebElement location = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_AvailabilityUserControl1_ddlPanel_chosen']/ul/li/input")));
		location.click();
		location.sendKeys("Bill");
		Thread.sleep(500);
		location.sendKeys(Keys.ARROW_DOWN);		
		location.sendKeys(Keys.ENTER);
		
		WebElement update = wait.until(ExpectedConditions.elementToBeClickable(By.id("AvailUpdateButton")));
		update.click();
		
		WebElement selectDay = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_AvailabilityUserControl1_tabAvail']/tbody/tr[2]/td[2]")));
		selectDay.click();
		
		WebElement viewTimes = wait.until(ExpectedConditions.elementToBeClickable(By.id("showSelectionButton")));
		viewTimes.click();
		
		WebElement createOrder1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("CreateOrderLineDiv")));
		createOrder1.click();	
		
		WebElement createOrder2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("divCreateOrder")));
		createOrder2.click();
		
		WebElement Adv = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_AvailabilityUserControl1_ContractHeaderUserControl_txtAdvertiserName")));
		Adv.sendKeys("WARNER");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='autoadvert']/span/span/div/span/div[2]/p")));
		Adv.sendKeys(Keys.ARROW_DOWN);
		Adv.sendKeys(Keys.ARROW_DOWN);
		Adv.sendKeys(Keys.ENTER);
		Adv.sendKeys(Keys.TAB);			
					
		Select Product = new Select(driver.findElement(By.id("MainContent_AvailabilityUserControl1_ContractHeaderUserControl_ddlBrandName")));
		Product.selectByVisibleText("SULLY");
		
		WebElement SP = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_AvailabilityUserControl1_ContractHeaderUserControl_txtSalesPersonName")));
		SP.sendKeys("FISHER JAMIE");
					
		WebElement MA = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_AvailabilityUserControl1_ContractHeaderUserControl_txtMainAgencyName")));
		MA.sendKeys("CLEAR CHANNEL UK LTD");
		
		WebElement Cust = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_AvailabilityUserControl1_ContractHeaderUserControl_txtCustomerName")));
		Cust.sendKeys("CLEAR CHANNEL UK LTD");
		
		WebElement Buyer = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_AvailabilityUserControl1_ContractHeaderUserControl_txtBuyerName")));
		Buyer.sendKeys("Bulkhead");
				
		WebElement Creat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='viewcreateorder']/div/div/div[2]/div[1]/button")));
		Creat.click();		
		submitModal();		
		
		WebElement order = wait.until(ExpectedConditions.elementToBeClickable(By.id("headingCompletedOrder")));
		Assert.assertTrue(order.getText().contains("created successfully"));
		Reporter.log("Storm Availability followed by contract booking with details - "+ order.getText());
		Constants.StormContract[Constants.Counter] = order.getText().substring(7,13);
		Constants.Counter=Constants.Counter+1;		
	}
	
	@Test  (priority=2)
	public void Internal_Storm_createBooking_ThenAddStorm25() throws InterruptedException, AWTException   
	{
		driver.navigate().to("https://uat.playiq.co.uk/storm/bookings/CreateNewBooking");
		
		WebElement Adv = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtAdvertiserName")));
		Adv.sendKeys("WARNER");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='autoadvert']/span/span/div/span/div[2]/p")));
		Adv.sendKeys(Keys.ARROW_DOWN);
		Adv.sendKeys(Keys.ARROW_DOWN);
		Adv.sendKeys(Keys.ENTER);
		Adv.sendKeys(Keys.TAB);			
		Thread.sleep(1000);			
		Select Product = new Select(driver.findElement(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_ddlBrandName")));
		Product.selectByVisibleText("BATMAN");
		
		WebElement SP = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtSalesPersonName")));
		SP.sendKeys("FISHER JAMIE");
					
		WebElement MA = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtMainAgencyName")));
		MA.sendKeys("CLEAR CHANNEL UK LTD");
		
		WebElement Cust = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtCustomerName")));
		Cust.sendKeys("CLEAR CHANNEL UK LTD");
		
		WebElement Buyer = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtBuyerName")));
		Buyer.sendKeys("Bulkhead");
		
		WebElement location = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_BookingsUserControl1_ddlPanel_chosen']/ul")));
		Actions actions = new Actions(driver);
		actions.moveToElement(location);
		actions.click();
		actions.sendKeys("North London Towers");
		actions.sendKeys(Keys.ENTER);
		actions.build().perform();
				
		WebElement ViewAvailability = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_btnSearchAvail")));
		ViewAvailability.click();
		Thread.sleep(500);
		pleaseWait();
		
		WebElement SelectAll = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_BookingsUserControl1_divSelectAllDeselectAllHours']/div/button[1]")));
		SelectAll.click();
		
		WebElement CreateBooking = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='divCreateBooking']/button")));
		CreateBooking.click();
		submitModal();		
		
		WebElement order = wait.until(ExpectedConditions.elementToBeClickable(By.id("divBookingCreatedText")));
		Assert.assertTrue(order.getText().contains("created successfully"));
		Reporter.log("Storm contract created  - "+ order.getText());
		Constants.StormContract[Constants.Counter] = order.getText().substring(7,13);
		Constants.Counter=Constants.Counter+1;
		int cont = Integer.parseInt(order.getText().substring(7,13));
		
		
		// Add Storm 25 Booking in above contract
		
		Select Pricing = new Select(driver.findElement(By.id("MainContent_BookingsUserControl1_ddlSalesModel")));
		Pricing.selectByVisibleText("Storm 25");
		
		location = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_BookingsUserControl1_ddlPanel_chosen']/ul")));
		actions = new Actions(driver);
		actions.moveToElement(location);
		actions.click();
		actions.sendKeys("Nine Elms Lane");
		actions.sendKeys(Keys.ENTER);
		actions.build().perform();
		
		ViewAvailability = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_btnSearchAvail")));
		ViewAvailability.click();
		pleaseWait();
		
		SelectAll = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_BookingsUserControl1_divSelectAllDeselectAllHours']/div/button[1]")));
		SelectAll.click();
		
		WebElement UpdateBooking = wait.until(ExpectedConditions.elementToBeClickable(By.id("divUpdateBooking")));
		UpdateBooking.click();
		Thread.sleep(500);
		submitModal();
		
		order = wait.until(ExpectedConditions.elementToBeClickable(By.id("divBookingCreatedText")));
		
		Assert.assertTrue(order.getText().contains("updated successfully"));
		int cont2 = Integer.parseInt(order.getText().substring(9,15));
		Assert.assertEquals(cont, cont2);
		Reporter.log("Storm contract updated - "+ order.getText());
		
	}
	
	@Test  (priority=2)
	public void Internal_Storm_bookMultiplePackages() throws InterruptedException   
	{
		changeProduct("STORM");
		driver.navigate().to("https://uat.playiq.co.uk/Storm/Bookings/BookByPacks");
		WebElement newbooking = wait.until(ExpectedConditions.elementToBeClickable(By.id("ShowCreateBooking")));
		newbooking.click();
		
		WebElement  dates = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtBookingDates")));
		dates.clear();
		dates.sendKeys("06/08/2018 - 12/08/2018");
		WebElement  apply = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div[3]/div/button[1]")));
		apply.click();
		
		WebElement Adv = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtAdvertiserName")));
		Adv.sendKeys("WARNER");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='autoadvert']/span/span/div/span/div[2]/p")));
		Adv.sendKeys(Keys.ARROW_DOWN);		
		Adv.sendKeys(Keys.ENTER);
		Adv.sendKeys(Keys.TAB);			
		Thread.sleep(1000);			
		
		Select Product = new Select(driver.findElement(By.id("ddlBrandName")));
		Product.selectByVisibleText("TARZAN");
		
		WebElement SP = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSalesPersonName")));
		SP.sendKeys("FISHER JAMIE");
					
		WebElement MA = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtMainAgencyName")));
		MA.sendKeys("CLEAR CHANNEL UK LTD");
		
		WebElement Cust = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtCustomerName")));
		Cust.sendKeys("CLEAR CHANNEL UK LTD");
		
		WebElement Buyer = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtBuyerName")));
		Buyer.sendKeys("Bulkhead");
		
		driver.findElement(By.id("Crom25")).click();
		driver.findElement(By.id("pnlStorm25SalesAreaPacks")).click();
		driver.findElement(By.id("stormNavigation15")).click();
		
		WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSearchAvailability")));
		search.click();
		
		WebElement success1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='progresslines_1_results']/div[1]")));
		Assert.assertTrue(success1.getText().contains("Completed Successfully"));
		WebElement success2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='progresslines_2_results']/div[1]")));
		Assert.assertTrue(success2.getText().contains("Completed Successfully"));
		WebElement success3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='progresslines_3_results']/div[1]")));
		Assert.assertTrue(success3.getText().contains("Completed Successfully"));
		
		WebElement proceed = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnConfirmBooking")));
		proceed.click();
		
		WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(By.id("btnSubmitBooking")));
		submit.click();
		Thread.sleep(1000);
		pleaseWait();
		
		WebElement bookingsuccess = wait.until(ExpectedConditions.elementToBeClickable(By.id("tagBookingCreatedText")));
		bookingsuccess.click();
		Assert.assertTrue(bookingsuccess.getText().contains("created successfully"));
		Constants.StormContract[Constants.Counter] = bookingsuccess.getText().substring(9,15);
		Constants.Counter=Constants.Counter+1;
		Reporter.log("Storm Internal book by package booking created - "+bookingsuccess.getText());		
	}
		
	@Test  (priority=3)
	public void Internal_Storm_bookingCalendarDragAndDrop() throws InterruptedException   
	{
		changeProduct("STORM");
		driver.navigate().to("https://uat.playiq.co.uk/storm/bookings/BookingCalendar");
		Select location = new Select(driver.findElement(By.id("MainContent_ddlPanel")));
		location.selectByVisibleText("North London Towers");					
		WebElement update = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_btnGetOptions")));
		update.click();
		Thread.sleep(2000);
		for(int i =0;i<9;i=i+1)
		{
			if(driver.findElement(By.xpath("//*[@id='calendar']/div[1]/div[3]/h2")).getText().contains("July"))
				break;
			driver.findElement(By.xpath("//*[@id='calendar']/div[1]/div[1]/div/button[2]")).click();
		}
		driver.findElement(By.xpath("//*[@id='calendar']/div[1]/div[2]/div/button[3]")).click(); 
		
		Thread.sleep(2000);
		WebElement booking = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='calendar']/div[2]/div/table/tbody/tr/td/div/div/div[3]/table/tbody/tr/td[2]/div/a[1]/div[1]/div[2]")));
		Reporter.log("Booking intial location is - "+booking.getLocation().toString());
	
		Actions builder = new Actions(driver);		
		builder.dragAndDropBy(booking, 0, 100).build().perform();
		booking = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='calendar']/div[2]/div/table/tbody/tr/td/div/div/div[3]/table/tbody/tr/td[2]/div/a[1]/div[1]/div[2]")));
		Reporter.log("Booking moved to location - "+booking.getLocation().toString());
		builder.dragAndDropBy(booking, 0, -100).build().perform();
	}
	
	@Test  (priority=3)
	public void Internal_Storm_downloadPlaylistScheduleReport()   
	{
		driver.navigate().to("https://uat.playiq.co.uk/storm/Reports/PlaylistSchedule");
		WebElement startdate = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtStartDate")));
		startdate.sendKeys("30/10/2017");
		driver.findElement(By.xpath("//*[@id='Reports']/div/div/div/div[4]/div[1]/button")).click();
		
		WebElement loading = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='divLoadingResults']/div")));
		Assert.assertTrue(loading.isDisplayed());
		Reporter.log("File download started");
	}
	
	@Test  (priority=3)
	public void Internal_Storm_eventCalendarPage() throws InterruptedException   
	{			
		driver.navigate().to("https://uat.playiq.co.uk/Events/EventCalendar");
		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Events")));
		wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Categories")));
		Assert.assertTrue(checkPageTitle("Event Calendar"));
		Assert.assertTrue(Internal_checkPageLogo());
		driver.findElement(By.partialLinkText("Events")).click();
		Assert.assertTrue(driver.findElement(By.partialLinkText("Events")).isDisplayed());
		driver.findElement(By.partialLinkText("Events")).click();
		driver.findElement(By.partialLinkText("Categories")).click();
		Assert.assertTrue(driver.findElement(By.partialLinkText("Categories")).isDisplayed());
		driver.findElement(By.partialLinkText("Categories")).click();
	}
	
	@Test  (priority=3)
	public void Internal_Storm_OverallDiscount() throws InterruptedException, AWTException   
	{
		changeProduct("STORM");
		driver.navigate().to("https://uat.playiq.co.uk/storm/bookings/Options");
		WebElement refnumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_txtContractNo")));		
		String contractNo =  Constants.StormContract[0];		
		refnumber.sendKeys(contractNo);
		driver.findElement(By.id("MainContent_btnContactNo")).click();
		
		WebElement result = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_lblContractHeader")));
		Assert.assertTrue(result.getText().contains(contractNo));
		
		String totalCost = wait.until(ExpectedConditions.elementToBeClickable(By.id("bookingtotalcost"))).getText();
		Reporter.log("Price of contract before discount - "+totalCost);
		totalCost= totalCost.replace("£","");
		totalCost= totalCost.replace(",","");
				
		double cost = Double.parseDouble(totalCost);		
		cost=cost-500;		
		Thread.sleep(1000);
		
		WebElement overallDiscount = wait.until(ExpectedConditions.elementToBeClickable(By.id("divOverallDiscount"))); 
		overallDiscount.click();
		
		driver.switchTo().activeElement();
		
		WebElement discountPrice = wait.until(ExpectedConditions.elementToBeClickable(By.id("txtDiscountPrice")));
		discountPrice.sendKeys(String.valueOf(cost));
		
		WebElement Save = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='discountModal']/div/div/div[3]/button[1]")));
		Save.click();
		
		WebElement discountedPrice = wait.until(ExpectedConditions.elementToBeClickable(By.id("bookingdiscounttotalcost"))); 
		Reporter.log("Price of contract after discount - "+discountedPrice.getText() );
		
		WebElement Update = wait.until(ExpectedConditions.elementToBeClickable(By.id("divUpdateBooking")));
		Update.click();
		Thread.sleep(1000);
		
		submitModal();
		
		WebElement contractUpdated = wait.until(ExpectedConditions.elementToBeClickable(By.id("divBookingCreatedText")));
		Assert.assertTrue(contractUpdated.getText().contains("updated successfully"));
	}
	 
	@Test  (priority=4)
	public void Internal_Storm_viewBookingbyDateandCancel() throws InterruptedException, AWTException   
	{
		driver.navigate().to("https://uat.playiq.co.uk/storm/bookings/Options");
		WebElement searchDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='OptionSearch']/div/div/div/div[3]/div/button")));
		searchDate.click();
		
		Assert.assertTrue(driver.findElement(By.id("MainContent_grdOptions_PagerTable")).getText().contains("Total records found"));
		Reporter.log("Storm Contracts search by date returned - "+driver.findElement(By.id("MainContent_grdOptions_PagerTable")).getText());
		
		Reporter.log("------------------------------------------------------------------------------");
		
		for (int i =0;i<Constants.Counter;i=i+1)
		{
			try
			{
				driver.navigate().to("https://uat.playiq.co.uk/storm/bookings/Options");
				WebElement cNo = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_txtContractNo")));
				cNo.sendKeys(Constants.StormContract[i].trim());
				
				WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_btnContactNo")));
				search.click();
				
				WebElement searchResults = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_lblContractHeader")));
				Assert.assertTrue(searchResults.getText().contains(Constants.StormContract[i].trim()));
				
				WebElement cancel = wait.until(ExpectedConditions.elementToBeClickable(By.id("divCancelBooking")));
				cancel.click();				
				submitModal();
						
				WebElement order = wait.until(ExpectedConditions.elementToBeClickable(By.id("divBookingCreatedText")));
				Assert.assertTrue(order.getText().contains("cancelled successfully"));
				Reporter.log("Searched internal Storm Contract number "+Constants.StormContract[i].trim()+" and Cancelled it");
			}
			catch (NullPointerException e) {				
				Reporter.log("Caught the NullPointerException");
	        }
			 
		}
	} 

}


