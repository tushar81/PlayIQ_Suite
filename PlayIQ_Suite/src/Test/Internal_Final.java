package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Helper.Constants;
import Helper.PlayIQ_Internal;

public class Internal_Final extends PlayIQ_Internal 
{

	@Test (priority=21)
	public void Internal_RemoveDayAsda() throws Exception 
	{
		//Remove Day Asda
		changeProduct("ASDA");
		driver.navigate().to("https://uat.playiq.co.uk/asda/bookings/Options");
		WebElement contract = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SupermarketViewBookingsUserControl1_txtContractNo")));
		contract.sendKeys(Constants.asdaContract);		
		String UpdateText = deleteDays();
		
		Assert.assertTrue(UpdateText.contains("updated successfully"));
		Assert.assertTrue(UpdateText.contains(Constants.asdaContract));		
		Reporter.log("Days removed from Asda Contract - "+Constants.asdaContract);
	}
	
	@Test (priority=22,enabled=false)
	public void Internal_RemoveDaySainsbury() throws Exception 
	{
		//Remove Day Asda
		changeProduct("SAINSBURYS");
		driver.navigate().to("https://uat.playiq.co.uk/sainsburys/bookings/Options");
		WebElement contract = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_SupermarketViewBookingsUserControl1_txtContractNo")));
		contract.sendKeys(Constants.sainsburyContract);		
		String UpdateText = deleteDays();
		
		Assert.assertTrue(UpdateText.contains("updated successfully"));
		Assert.assertTrue(UpdateText.contains(Constants.sainsburyContract));		
		Reporter.log("Days removed from Sainsburys Contract - "+Constants.sainsburyContract);
	}
	
	@Test (priority=23)
	public void Internal_CancelAsdaSainsburyIrelandTesco() throws Exception 
		{
			//Cancel Asda
				if(!Constants.asdaContract.isEmpty())
					cancelContract(Constants.asdaContract,"ASDA");
				else
					Reporter.log("ASDA Contract not available to cancel"); 
				
			// Cancel IrelandTesco	
				
				if(!Constants.irelandTesco.isEmpty())
					cancelContract(Constants.irelandTesco,"irelandTesco");
				else
					Reporter.log("IrelandTesco Contract not available to cancel"); 
				
			/*/ Cancel Sainsbury - Uncomment after the fix on Sainsbury in Oasis DB	
				
				if(!Constants.sainsburyContract.isEmpty())
					cancelContract(Constants.sainsburyContract,"SAINSBURYS");
				else
					Reporter.log("SainsburyContract Contract not available to cancel"); */
		 	 
		}
	
	@Test (priority=24)
	public void Internal_CancelWrap() throws Exception 
		{
			//Cancel Wrap
			changeProduct("WRAP");
			
			for (String str: Constants.wrapContract)
				{			
				
				driver.navigate().to("https://uat.playiq.co.uk/wrap/bookings/Options");
				WebElement contract = wait.until(ExpectedConditions.elementToBeClickable(By.id("MainContent_ViewBookingsUserControl1_txtContractNo")));
				contract.sendKeys(str);
				
				WebElement Search = wait.until(ExpectedConditions.elementToBeClickable(By.id("SearchButton")));
				Search.click();
				pleaseWait();
				
				WebElement Cancel = wait.until(ExpectedConditions.elementToBeClickable(By.id("divCancelBooking")));
				Cancel.click();
				submitModal();
				
				WebElement CancelMessage = wait.until(ExpectedConditions.elementToBeClickable(By.id("divBookingCreatedText")));
				Assert.assertTrue(CancelMessage.getText().contentEquals("Contract cancelled successfully"));
				Reporter.log("Cancelled Wrap Contract - "+str);
				}
			
				
		}

}
