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

public class Internal_Adshel extends PlayIQ_Internal
{
			// Adshel Product Tests	- Priority 5 to 8	
			@Test (priority=5)
			public void Internal_Adshel_bookByLocation () throws InterruptedException, AWTException  
			{
				changeProduct("ADSHEL");
				
				driver.navigate().to("https://uat.playiq.co.uk/adshel/bookings/CreateNewBooking");
				WebElement TVradio =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='collapseAvail']/div/div/div[15]/div/input[3]")));
				TVradio.click();
				
				WebElement TVa =wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_BookingsUserControl1_ddlTvArea_chosen']/ul/li/input")));
				TVa.click();
				TVa.sendKeys("GRANADA");
				TVa.sendKeys(Keys.ENTER);
				Thread.sleep(2000);		
				WebElement viewAvail = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_btnSearchAvail")));
				viewAvail.click();
				Thread.sleep(1000);
				pleaseWait();			
							
				WebElement selectall = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_BookingsUserControl1_divSelectHours']/div[1]/div/button[1]")));
				selectall.click();
				
				WebElement getpricing = wait.until(ExpectedConditions.elementToBeClickable(By.id("divGetPricingButton")));
				getpricing.click();
				
				WebElement createOrder = wait.until(ExpectedConditions.elementToBeClickable(By.id("divAdshelPricingCreateOrder")));
				createOrder.click();
				
				WebElement Adv = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_BookingsUserControl1_ContractHeaderUserControl_txtAdvertiserName']")));
				Adv.sendKeys("APP");
				Thread.sleep(1000);
				Adv.sendKeys(Keys.ARROW_DOWN);
				Thread.sleep(500);
				Adv.sendKeys(Keys.ENTER);						
				
				WebElement SP = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtSalesPersonName")));			
				SP.sendKeys("PAYNE STEVE");
				Thread.sleep(1000);						
				WebElement MA = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtMainAgencyName")));			
				MA.sendKeys("CLEAR CHANNEL UK LTD");
				Thread.sleep(1000); 
				WebElement Cust = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtCustomerName")));			
				Cust.sendKeys("CLEAR CHANNEL UK LTD");
							
				WebElement Buyer = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtBuyerName")));
				Buyer.sendKeys("Optimus Prime");
				
				Thread.sleep(1000);
				WebElement Creat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='CreateOrderButton']/div/button")));
				Creat.click();
				submitModal();
				
				WebElement order = wait.until(ExpectedConditions.elementToBeClickable(By.id("divBookingCreatedText")));
				Assert.assertTrue(order.getText().contains("created successfully"));
				Reporter.log("Adshel contract booked by location details - "+ order.getText());				
				Constants.adshelContract = order.getText().substring(7,13);				
			}
		
			@Test (priority=6)
			public void Internal_Adshel_bookByPlays () throws InterruptedException  
			{
								
				driver.navigate().to("https://uat.playiq.co.uk/adshel/bookings/BookByPlays");
				WebElement Create = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='ShowCreateBooking']/button")));
				Create.click();
				
				driver.findElement(By.partialLinkText("Dates -")).click();
				
				WebElement Date = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookByPlaysFilterScreenUserControl_txtSearchDates")));;
				Date.clear();
				Date.sendKeys(Constants.oneDay);
				
				WebElement NoOfPlay = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookByPlaysFilterScreenUserControl_txtNumberOfPlays")));
				WebElement Search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='StartSearchButton']/button")));
				NoOfPlay.sendKeys("5000");
				Search.click();
				
				WebElement CreateOrder = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='divAdshelPricingCreateOrderDetails']/button")));
				CreateOrder.click();
							
				WebElement Adv = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='MainContent_ContractHeaderUserControl_txtAdvertiserName']")));
				Adv.sendKeys("WARNER");
				Thread.sleep(1000);
				Adv.sendKeys(Keys.ARROW_DOWN);
				Adv.sendKeys(Keys.ARROW_DOWN);
				Adv.sendKeys(Keys.ENTER);
				Adv.sendKeys(Keys.TAB);			
							
				Select Product = new Select(driver.findElement(By.id("MainContent_ContractHeaderUserControl_ddlBrandName")));
				Product.selectByVisibleText("SUPERMAN");
					
				WebElement SP = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_ContractHeaderUserControl_txtSalesPersonName")));
				SP.sendKeys("PAYNE STEVE");
							
				WebElement MA = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_ContractHeaderUserControl_txtMainAgencyName")));
				MA.sendKeys("CLEAR CHANNEL UK LTD");
				
				WebElement Cust = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_ContractHeaderUserControl_txtCustomerName")));
				Cust.sendKeys("CLEAR CHANNEL UK LTD");
				
				WebElement Buyer = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_ContractHeaderUserControl_txtBuyerName")));
				Buyer.sendKeys("Bumblebee");
						
				WebElement Creat = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='divAdshelPricingCreateOrder']/button")));
				Creat.click();
				Thread.sleep(1000);			
				driver.switchTo().activeElement();    
				driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[1]")).click();
							
				WebElement NoOfPlay1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("NoOfExistingPlays")));
				Assert.assertTrue(NoOfPlay1.getText().contains("plays in this booking"));						
				WebElement OrderNumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("CreateUpDateOrderHeaderText"))); 
				Assert.assertTrue(OrderNumber.getText().contains("New Order Created"));
				Reporter.log(OrderNumber.getText()+" \n "+NoOfPlay1.getText());				
				Constants.adshelContractToCancel = OrderNumber.getText().substring(20);				
			} 
							
			@Test (priority=7)
			public void Internal_Adshel_addPlaysToExistingContract () throws InterruptedException  
			{
				String contract = Constants.adshelContractToCancel;
				if(!contract.equals("0"))
				{
					driver.navigate().to("https://uat.playiq.co.uk/adshel/bookings/BookByPlays");
					WebElement addToExisting = wait.until(ExpectedConditions.elementToBeClickable(By.id("AddToExistingBooking")));
					addToExisting.click();
					
					WebElement existingContract= wait.until(ExpectedConditions.elementToBeClickable(By.id("txtSearchContractNo")));
					existingContract.sendKeys(Constants.adshelContractToCancel.toString());
					
					WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='collapseExisting']/div/div[1]/div[2]/button")));
					search.click();				
					WebElement contractDtls = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("SearchContractDetails")));				
					Reporter.log("Existing Contract Details are - "+contractDtls.getText());
					
					WebElement cont = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='btnContinueExisting']/button")));
					cont.click();
					
					WebElement searAvl = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='showSearchAvailButton']/button")));
					searAvl.click();
							
					WebElement bbPlays = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_BookByPlaysFilterScreenUserControl_txtNumberOfPlays")));
					bbPlays.sendKeys("5000");
					
					WebElement searAvl2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='StartSearchButton']/button")));
					searAvl2.click();
					
					WebElement update = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='divAdshelPricingCreateOrder']/button")));
					update.click();
					Thread.sleep(1000);			
					driver.switchTo().activeElement();    
					driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[1]")).click();
					Thread.sleep(1000);
					WebElement updatedOrder = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("CreateUpDateOrderHeaderText")));				
					Reporter.log(updatedOrder.getText());
					WebElement updatedPlay = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("NoOfExistingPlays")));
					Reporter.log(updatedPlay.getText());
					Assert.assertTrue(updatedOrder.getText().contains(contract));	
				}
				else				
					{
						Reporter.log("Error in retriving contract");
						Assert.assertTrue(false);				
					}
					
				}
		
			@Test  (priority=7)
			public void Internal_Adshel_navigateAvailabilityMenu() throws Exception 
			{		 
					
				 driver.navigate().to("https://uat.playiq.co.uk/adshel/AdshelHome");
				 Thread.sleep(500);
				 Actions builder = new Actions(driver);
				 WebElement availability = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[4]/div[2]/header/div[1]/div[1]/div/div[2]/ul[1]/li[2]/a")));
				 builder.moveToElement(availability).click().perform();
				 WebElement AB = driver.findElement(By.id("lnkAvailability"));
				 builder.moveToElement(AB).click().build().perform();		 		 
				 Assert.assertTrue(checkPageTitle("Availability"));
				 availability = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[4]/div[2]/header/div[1]/div[1]/div/div[2]/ul[1]/li[2]/a")));		 
				 builder = new Actions(driver);
				 builder.moveToElement(availability).click().perform();
				 WebElement aCalc = driver.findElement(By.id("lnkPlayCalculator"));
				 builder.moveToElement(aCalc).click().build().perform();		 
				 Assert.assertTrue(checkPageTitle("Available Plays Calculator"));
				 
			}
	
			@Test (priority=7)
			public void Internal_Adshel_navigateBookingMenu() throws Exception 
			{		 
				 driver.navigate().to("https://uat.playiq.co.uk/adshel/AdshelHome");
				 Thread.sleep(500);
				 Actions builder = new Actions(driver);
				 WebElement booking = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='main-container']/div[2]/header/div[1]/div[1]/div/div[2]/ul[1]/li[4]/a")));
				 builder.moveToElement(booking).click().perform();
				 WebElement BO = wait.until(ExpectedConditions.elementToBeClickable(By.id("lnkOptions")));
				 builder.moveToElement(BO).click().build().perform();		 		 
				 Assert.assertTrue(checkPageTitle("View Bookings and Options"));
				 
				 booking = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='main-container']/div[2]/header/div[1]/div[1]/div/div[2]/ul[1]/li[4]/a")));
				 builder = new Actions(driver);
				 builder.moveToElement(booking).click().perform();
				 WebElement BbP = wait.until(ExpectedConditions.elementToBeClickable(By.id("lnkBookByPlays")));
				 builder.moveToElement(BbP).click().build().perform();		 
				 Assert.assertTrue(checkPageTitle("Book By Plays"));
				 
				 booking = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='main-container']/div[2]/header/div[1]/div[1]/div/div[2]/ul[1]/li[4]/a")));
				 builder = new Actions(driver);
				 builder.moveToElement(booking).click().perform();
				 WebElement BbL = wait.until(ExpectedConditions.elementToBeClickable(By.id("lnkBookByLocation")));
				 builder.moveToElement(BbL).click().build().perform();		 
				 Assert.assertTrue(checkPageTitle("Book By Location"));
				 
				 booking = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='main-container']/div[2]/header/div[1]/div[1]/div/div[2]/ul[1]/li[4]/a")));
				 builder = new Actions(driver);
				 builder.moveToElement(booking).click().perform();
				 WebElement BC = wait.until(ExpectedConditions.elementToBeClickable(By.id("lnkBookingCalendar")));
				 builder.moveToElement(BC).click().build().perform();		 
				 Assert.assertTrue(checkPageTitle("Booking Calendar"));
				 
				 booking = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='main-container']/div[2]/header/div[1]/div[1]/div/div[2]/ul[1]/li[4]/a")));
				 builder = new Actions(driver);
				 builder.moveToElement(booking).click().perform();
				 WebElement LS = wait.until(ExpectedConditions.elementToBeClickable(By.id("lnkLoopSpaces")));
				 builder.moveToElement(LS).click().build().perform();		 
				 Assert.assertTrue(checkPageTitle("Manage Loop Space"));				 
				 
			}
			
			@Test  (priority=7)
			public void Internal_Adshel_navigateCampaignPerformaceMenu() throws Exception 
			{	
				 driver.navigate().to("https://uat.playiq.co.uk/adshel/AdshelHome");
				 Thread.sleep(500);
				 Actions builder = new Actions(driver);
				 WebElement reporting  = wait.until(ExpectedConditions.elementToBeClickable(By.id("CampaignPerformanceMenu")));
				 builder.moveToElement(reporting).click().perform();
				 WebElement PoP = driver.findElement(By.id("lnkCampaignPerformance"));
				 Reporter.log("Check if Submenu - "+PoP.getText() + " is working as expected");
				 builder.moveToElement(PoP).click().build().perform();
				 Assert.assertTrue(checkPageTitle("Campaign Performance"));				 
				 
				 reporting  = wait.until(ExpectedConditions.elementToBeClickable(By.id("CampaignPerformanceMenu")));
				 builder.moveToElement(reporting).click().perform();
				 PoP = driver.findElement(By.id("lnkProofOfPlay"));
				 Reporter.log("Check if Submenu - "+PoP.getText() + " is working as expected");
				 builder.moveToElement(PoP).click().build().perform();
				 Assert.assertTrue(checkPageTitle("Proof of Play Report"));				 
				 
				 reporting  = wait.until(ExpectedConditions.elementToBeClickable(By.id("CampaignPerformanceMenu")));
				 builder.moveToElement(reporting).click().perform();
				 PoP = driver.findElement(By.id("lnkMultiOption"));
				 Reporter.log("Check if Submenu - "+PoP.getText() + " is working as expected");
				 builder.moveToElement(PoP).click().build().perform();
				 Assert.assertTrue(checkPageTitle("PoP Download Requests")); 
				 
				 reporting  = wait.until(ExpectedConditions.elementToBeClickable(By.id("CampaignPerformanceMenu")));
				 builder.moveToElement(reporting).click().perform();
				 PoP = driver.findElement(By.xpath("//*[@id='CampaignPerformanceMenu']/ul/li[4]/a"));
				 Reporter.log("Check if Submenu - "+PoP.getText() + " is working as expected");
				 builder.moveToElement(PoP).click().build().perform();
				 Assert.assertTrue(checkPageTitle("PoP - Contract Performance"));
				 
				 /*
				 reporting  = wait.until(ExpectedConditions.elementToBeClickable(By.id("CampaignPerformanceMenu")));
				 builder.moveToElement(reporting).click().perform();
				 PoP = driver.findElement(By.xpath("//*[@id='CampaignPerformanceMenu']/ul/li[5]/a"));
				 builder.moveToElement(PoP).click().build().perform();
				 Assert.assertTrue(checkPageTitle("PoP - Contract Performance"));*/
			}
			
			@Test  (priority=7)
			public void Internal_Adshel_checkCMSMenu() throws Exception 
			{
				driver.navigate().to("https://uat.playiq.co.uk/adshel/AdshelHome");
				WebElement CMS = wait.until(ExpectedConditions.elementToBeClickable(By.id("lnkCMSMenu")));
				Assert.assertTrue(CMS.isDisplayed()&&CMS.isEnabled());
				Reporter.log("Menu to launch CMS - "+CMS.getText());
			}
			
			@Test  (priority=7)
			public void Internal_Adshel_navigateSetupMenu() throws Exception 
			{	
				 driver.navigate().to("https://uat.playiq.co.uk/adshel/AdshelHome");
				 Thread.sleep(500);
				 Actions builder = new Actions(driver);
				 WebElement reporting  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='setupmenu']/a")));
				 builder.moveToElement(reporting).click().perform();
				 WebElement NS = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='setupmenu']/ul/li[4]/a")));
				 builder.moveToElement(NS).click().build().perform();
				 Thread.sleep(1500);				 
				 Assert.assertTrue(checkPageTitle("Network Setup"));
				 
				 reporting  = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='setupmenu']/a")));
				 builder.moveToElement(reporting).click().perform();
				 WebElement EC = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='setupmenu']/ul/li[5]/a")));
				 builder.moveToElement(EC).click().build().perform();
				 Thread.sleep(1500);
				 Assert.assertTrue(checkPageTitle("External Companies"));
				 driver.navigate().to("https://uat.playiq.co.uk/adshel/AdshelHome");
				 
			}
			
			@Test  (priority=7)
			public void Internal_Adshel_adshelAvailability() throws Exception 
			{		 
				 driver.navigate().to("https://uat.playiq.co.uk/adshel/Availability/AvailData");
				 Thread.sleep(500);
				 WebElement datePicker = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_AvailabilityUserControl1_txtChargeDates")));
				 datePicker.clear();
				 datePicker.sendKeys(Constants.oneDay);
				 
				 WebElement location = driver.findElement(By.className("chosen-choices"));
				 WebElement btnUpdate = driver.findElement(By.id("AvailUpdateButton"));
				 WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lblMasterTitle")));
				 Assert.assertEquals(title.getText(),"Availability");
				 btnUpdate.click();		 
				 Assert.assertTrue(datePicker.isDisplayed() && location.isDisplayed() && btnUpdate.isDisplayed());		  		 
				 WebElement row = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/form/div[4]/div[2]/div[1]/div[1]/div[1]/div[3]/div[5]/div/table/tbody/tr[3]/td[1]")));		 
				 Reporter.log("Availability Table shown with first row - "+row.getText());
				 Assert.assertTrue(row.isDisplayed());
			}
			
			@Test  (priority=7)
			public void Internal_Adshel_adshelAvailCalculator() throws Exception 
			{		 
				 driver.navigate().to("https://uat.playiq.co.uk/adshel/Bookings/PlaysCalculator");
				 Thread.sleep(500);
				 WebElement title = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lblMasterTitle")));
				 Assert.assertEquals(title.getText(),"Available Plays Calculator");
				 
				 driver.findElement(By.partialLinkText("Dates")).click();
				 
				 WebElement dates = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MainContent_BookByPlaysFilterScreenUserControl_txtSearchDates")));
				 dates.clear();
				 dates.sendKeys(Constants.oneDay);
				 
				
				 driver.findElement(By.id("SearchButton")).click();
				 Thread.sleep(1500);
				 
				 WebElement newtitle = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_Label1")));
				 Assert.assertEquals(newtitle.getText(),"Availability Search Progress");
				 
				 WebElement searchagain = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/form/div[4]/div[2]/div[1]/div/div[4]/div[2]/div/button")));
				 Assert.assertTrue(searchagain.isDisplayed());			 
				 Assert.assertTrue(driver.findElement(By.id("MainContent_Label2")).isDisplayed());
				 Assert.assertTrue(driver.findElement(By.id("MainContent_Label5")).isDisplayed());			 
				 
				 Reporter.log("Page refreshed and Availability given with button - "+ searchagain.getText());
				 
				 
			}
			
			@Test  (priority=8 , enabled=false)
			public void Internal_Adshel_EditBookingAndRemoveDay() throws Exception 
			{
				changeProduct("ADSHEL");
				driver.navigate().to("https://uat.playiq.co.uk/adshel/bookings/Options");
				WebElement Cno = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_txtContractNo")));
				
				if(!Constants.adshelContract.isEmpty())
				{
					Cno.sendKeys(Constants.adshelContract);
					
					WebElement SearchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("SearchButton")));
					SearchButton.click();				
					Thread.sleep(1000);
					
					pleaseWait();
					driver.findElement(By.partialLinkText("Contract")).click();
					driver.findElement(By.partialLinkText("Search")).click();
					
					WebElement OrderLine = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='orderlines']/tbody/tr[3]/td[1]")));
					
					OrderLine.click();
					
						for(int i=4;i<5;i=i+1)
						{
							driver.findElement(By.xpath("//*[@id='orderlines']/tbody/tr["+i+"]/td[10]/i")).click();
							submitModal();					
						}
					
					WebElement GetPricing = wait.until(ExpectedConditions.elementToBeClickable(By.id("divGetPricingButton")));
					GetPricing.click();
					
					WebElement Update = wait.until(ExpectedConditions.elementToBeClickable(By.id("divAdshelPricingUpdateBooking")));
					Update.click();				
					submitModal();			
					 
					WebElement confirmation  = wait.until(ExpectedConditions.elementToBeClickable(By.id("divBookingUpdatedText")));
					Assert.assertTrue(confirmation.getText().contains("updated successfully"));
					Reporter.log(confirmation.getText()+" - Days deleted from orderlines");
					
					// Cancel the entire contract after removing the days
					
					WebElement Cancel = wait.until(ExpectedConditions.elementToBeClickable(By.id("divCancelBooking")));
					Cancel.click();
					submitModal();
				}
				else
				{
					Reporter.log("Adshel contract unavailable to edit");
					Assert.assertTrue(false);
					
				}
			}
						
			@Test (priority=8)
			public void Internal_Adshel_viewContractDetailsAndCancel () throws InterruptedException, AWTException  
			{
				driver.navigate().to("https://uat.playiq.co.uk/adshel/bookings/Options");
				WebElement ConNumber = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_txtContractNo")));
				
				if(!Constants.adshelContractToCancel.isEmpty())
				{
					ConNumber.sendKeys(Constants.adshelContractToCancel.toString());				
					WebElement Searchbtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("SearchButton")));
					Searchbtn.click();
					
					WebElement Cancel = wait.until(ExpectedConditions.elementToBeClickable(By.id("divCancelBooking")));
					Reporter.log("Order details for - "+driver.findElement(By.partialLinkText("Contract")).getText()+" are as follows");
					Reporter.log("In Charge Dates  - "+ driver.findElement(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtBookingDates")).getAttribute("value"));
					Reporter.log("Advertiser - "+ driver.findElement(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtAdvertiserName")).getAttribute("value"));
					Reporter.log("Buyer - "+ driver.findElement(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_txtBuyerName")).getAttribute("value"));		
					Reporter.log("Product - "+ driver.findElement(By.id("MainContent_BookingsUserControl1_ContractHeaderUserControl_ddlBrandName")).getAttribute("value"));
					
					Cancel.click();
					submitModal();
					WebElement message =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("divBookingCreatedText")));
					Reporter.log(Constants.adshelContractToCancel.toString()+" - "+message.getText());
					Assert.assertTrue(message.getText().contains("Contract cancelled successfully"));
				}
				else
				{
					Reporter.log("Adshel Contract not available to cancel");
					Assert.assertTrue(false);
				}
				
			}
			
}
