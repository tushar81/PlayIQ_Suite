package Test;

import java.awt.AWTException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Helper.PlayIQ_Internal;
import Helper.Constants;
 

public class Internal_Wrap extends PlayIQ_Internal

{
				
				@Test (priority=11)
				public void Internal_Wrap_bookByLocation () throws InterruptedException, AWTException  
				{
					changeProduct("WRAP"); 
					driver.navigate().to("https://uat.playiq.co.uk/wrap/bookings/CreateNewBooking");
					

					WebElement BookingDate = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookByLocationUserControl1_BookingsUserControl1_txtChargeDates")));
					BookingDate.clear();
					BookingDate.sendKeys(Constants.oneDay);
										
					WebElement ViewAvail = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookByLocationUserControl1_BookingsUserControl1_btnSearchAvail")));
					ViewAvail.click();
					
					Thread.sleep(1000);
					pleaseWait();
					
					WebElement SelectAll =  wait.until(ExpectedConditions.elementToBeClickable(By.id("SelectAllCells")));
					SelectAll.click();
					
					WebElement GetPricing =  wait.until(ExpectedConditions.elementToBeClickable(By.id("divGetPricingButton")));
					GetPricing.click();
					
					WebElement CreateOrder =  wait.until(ExpectedConditions.elementToBeClickable(By.id("divPricingCreateOrder")));
					CreateOrder.click();
					
					WebElement Adv = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookByLocationUserControl1_BookingsUserControl1_ContractHeaderUserControl_txtAdvertiserName")));
					Adv.sendKeys("APP");
					Thread.sleep(1000);
					Adv.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(500);
					Adv.sendKeys(Keys.ENTER);						
					
					WebElement SP = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookByLocationUserControl1_BookingsUserControl1_ContractHeaderUserControl_txtSalesPersonName")));			
					SP.sendKeys("TYLER DAVID");
					Thread.sleep(1000);						
					WebElement MA = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookByLocationUserControl1_BookingsUserControl1_ContractHeaderUserControl_txtMainAgencyName")));			
					MA.sendKeys("CLEAR CHANNEL UK LTD");
					Thread.sleep(1000); 
					WebElement Cust = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookByLocationUserControl1_BookingsUserControl1_ContractHeaderUserControl_txtCustomerName")));			
					Cust.sendKeys("CLEAR CHANNEL UK LTD");
								
					WebElement Buyer = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookByLocationUserControl1_BookingsUserControl1_ContractHeaderUserControl_txtBuyerName")));
					Buyer.sendKeys("Megatron");
					
					Thread.sleep(1000);
					WebElement Creat = wait.until(ExpectedConditions.elementToBeClickable(By.id("CreateOrderButton")));
					Creat.click();
					submitModal();
					
					WebElement OrderCreated = wait.until(ExpectedConditions.elementToBeClickable(By.id("divBookingCreatedText")));
					Assert.assertTrue(OrderCreated.getText().contains("created successfully"));
					Reporter.log("Wrap contract created - "+OrderCreated.getText());
					Constants.wrapContract[0] = OrderCreated.getText().substring(7, 13);
					
				}
				
				@Test (priority=12)
				public void Internal_Wrap_bookByPlays () throws InterruptedException, AWTException  
				{
					driver.navigate().to("https://uat.playiq.co.uk/wrap/bookings/BookByPlays");
					
					WebElement CreateBooking = wait.until(ExpectedConditions.elementToBeClickable(By.id("ShowCreateBooking")));
					CreateBooking.click(); 
					
					WebElement NumberOfPlays = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_WrapBookByPlaysUserControl1_BookByPlaysFilterScreenUserControl_txtNumberOfPlays")));
					NumberOfPlays.sendKeys("6000");
					
					/*driver.findElement(By.partialLinkText("Dates")).click();
					
					WebElement Date = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_WrapBookByPlaysUserControl1_BookByPlaysFilterScreenUserControl_txtSearchDates")));
					Date.click();				
					Date.clear();
					Date.sendKeys(Constants.oneDay);
					Thread.sleep(1100);*/
					
					WebElement Search = wait.until(ExpectedConditions.elementToBeClickable(By.id("StartSearchButton")));
					Search.click(); 
					
					WebElement CreateOrder = wait.until(ExpectedConditions.elementToBeClickable(By.id("divWrapPricingCreateOrderDetails")));
					CreateOrder.click(); 
					
					WebElement Adv = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_WrapBookByPlaysUserControl1_ContractHeaderUserControl_txtAdvertiserName")));
					Adv.sendKeys("Deb");
					Thread.sleep(1000);
					Adv.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(500);
					Adv.sendKeys(Keys.ENTER);						
					
					WebElement SP = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_WrapBookByPlaysUserControl1_ContractHeaderUserControl_txtSalesPersonName")));			
					SP.sendKeys("TYLER DAVID");
					Thread.sleep(1000);						
					WebElement MA = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_WrapBookByPlaysUserControl1_ContractHeaderUserControl_txtMainAgencyName")));			
					MA.sendKeys("CLEAR CHANNEL UK LTD");
					Thread.sleep(1000); 
					WebElement Cust = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_WrapBookByPlaysUserControl1_ContractHeaderUserControl_txtCustomerName")));			
					Cust.sendKeys("CLEAR CHANNEL UK LTD");
								
					WebElement Buyer = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_WrapBookByPlaysUserControl1_ContractHeaderUserControl_txtBuyerName")));
					Buyer.sendKeys("Megatron");
					
					Thread.sleep(1000);
					WebElement Creat = wait.until(ExpectedConditions.elementToBeClickable(By.id("divWrapPricingCreateOrder")));
					Creat.click();
					
					submitModal();
					
					WebElement OrderCreated = wait.until(ExpectedConditions.elementToBeClickable(By.id("CreateUpDateOrderHeaderText")));
					Assert.assertTrue(OrderCreated.getText().contains("New Order Created"));
					Reporter.log("Wrap contract created - "+OrderCreated.getText());
					Constants.wrapContract[1] = OrderCreated.getText().substring(20);
					System.out.println(OrderCreated.getText().substring(20));					
				}
											
				@Test (priority=13)
				public void Internal_Wrap_Availability()
				{
					driver.navigate().to("https://uat.playiq.co.uk/wrap/availability/availdata");
					WebElement viewAvailability = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_AvailabilityUserControl1_btnSearchAvail")));
					viewAvailability.click();
					
					
					
					WebElement availabilityKey = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tetrisgrid']/div[1]/div/div[1]/h4")));
					Assert.assertTrue(availabilityKey.getText().contains("Availability Key"));
					Reporter.log("Wrap Availability returned table with - "+availabilityKey.getText()); 
					
					WebElement selectAll = wait.until(ExpectedConditions.elementToBeClickable(By.id("SelectAllButton")));
					selectAll.click();
					
					WebElement Reset = wait.until(ExpectedConditions.elementToBeClickable(By.id("clearSelections")));
					Reset.click();
					Assert.assertTrue(selectAll.isDisplayed()&&Reset.isDisplayed());
					
				}
								
				@Test (priority=13)
				public void Internal_Wrap_savedLists()
				{
					driver.navigate().to("https://uat.playiq.co.uk/wrap/savedlists/Lists");
					WebElement yourLists = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SavedListUserControl1_Label9")));
					Assert.assertTrue(yourLists.getText().contains("Your Lists"));
					
					WebElement List1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SavedListUserControl1_grdSavedLists_lblListName_0")));
										
					WebElement List1_1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SavedListUserControl1_grdSavedLists_lblCreatedBy_0")));
					WebElement List1_2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SavedListUserControl1_grdSavedLists_lnkSavedListPanels_0")));
					
					Assert.assertTrue(List1.isDisplayed()&&List1_1.isDisplayed()&&List1_2.isDisplayed());
					
					Reporter.log("First List in Saved List is having" );
					Reporter.log("List Name - "+List1.getText());
					Reporter.log("Created By - "+List1_1.getText());		
					
					List1_2.click();
					WebElement Screens  = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SavedListUserControl1_PanelSearchUserControl1_lblLocationTitle")));
					Assert.assertTrue(Screens.getText().contains("Screens in"));
					
				}
				
				@Test (priority=13)
				public void Internal_Wrap_bookingCalendar() throws InterruptedException
				{
					driver.navigate().to("https://uat.playiq.co.uk/wrap/bookings/BookingCalendar");
					WebElement Screen = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingCalendarUserControl1_txtOptionSearchPanel")));
					Screen.click();
					Screen.clear();
					Screen.sendKeys("CROYDON");
					Thread.sleep(1000);
					Screen.sendKeys(Keys.ARROW_DOWN);
					Thread.sleep(500);
					Screen.sendKeys(Keys.ENTER);	
					
					WebElement Update = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingCalendarUserControl1_btnGetOptions")));
					Update.click();
					
					WebElement Month = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='calendar']/div[1]/div[3]/h2")));
					Reporter.log("Booking Calendar given for - "+Month.getText());
					Assert.assertFalse(Month.getText().isEmpty());
					
				}
				
				@Test (priority=13)
				public void Internal_Wrap_availablePlaysCalculator()
				{
					driver.navigate().to("https://uat.playiq.co.uk/wrap/bookings/PlaysCalculator");
					
					WebElement Search = wait.until(ExpectedConditions.elementToBeClickable(By.id("SearchButton")));
					Search.click();
					
					WebElement calculationResult = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_PlaysCalculatorUserControl1_Label5")));
					Assert.assertTrue(calculationResult.getText().contains("Calculation Result"));
					
					WebElement TotalPlaysAvailable = wait.until(ExpectedConditions.elementToBeClickable(By.id("lblTotalPlays")));
					Assert.assertTrue(TotalPlaysAvailable.isDisplayed());
					Reporter.log("Total Plays Available are - "+TotalPlaysAvailable.getText());
					calculationResult.click();
					
					WebElement DistributionOfPlays = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_PlaysCalculatorUserControl1_Label2")));
					Assert.assertTrue(DistributionOfPlays.getText().contains("Distribution Of Plays"));
					DistributionOfPlays.click();
					calculationResult.click();
					DistributionOfPlays.click();
				}
				
				@Test (priority=14)
				public void Internal_Wrap_SearchByDate()
				{
					driver.navigate().to("https://uat.playiq.co.uk/wrap/bookings/Options");
					
					WebElement ContractNumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_ViewBookingsUserControl1_txtContractNo")));
					ContractNumber.click();
					ContractNumber.sendKeys(Constants.wrapContract[0]);
					
					WebElement Search = wait.until(ExpectedConditions.elementToBeClickable(By.id("SearchButton")));
					Search.click();
					
					WebElement BookingDetails = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_ViewBookingsUserControl1_BookingsUserControl1_lblContractHeader")));
					Assert.assertTrue(BookingDetails.isDisplayed());
					Reporter.log("Booking Details for Contract - "+Constants.wrapContract[0]+" are given");
					
					driver.navigate().to("https://uat.playiq.co.uk/wrap/bookings/Options");
					
					WebElement SearchByDate = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_ViewBookingsUserControl1_txtSearchDates")));
					SearchByDate.click();
					SearchByDate.clear();
					SearchByDate.sendKeys("01/03/2018 - 31/03/2018");
					
					
					WebElement Search2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("SearchButton2")));
					Search2.click();
					
					pleaseWait();
					
					WebElement records = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_ViewBookingsUserControl1_grdOptions']/tbody/tr[2]/td[1]")));
					Assert.assertTrue(records.isDisplayed());
					Reporter.log("Search by Date returned table with - "+records.getText()); 
					
				}


}
