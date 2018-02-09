package Test;

import java.awt.AWTException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import Helper.Constants;
import Helper.PlayIQ_Internal;

public class Internal_Sainsbury extends PlayIQ_Internal

{
		 	
				@Test (priority=10)
				public void Internal_Sainsbury_bookByLocation () throws InterruptedException, AWTException  
				{
					changeProduct("SAINSBURYS");
					
					driver.navigate().to("https://uat.playiq.co.uk/sainsburys/bookings/BookByLocation");
					String orderDetails = submitOrder(Constants.oneWeek);
					Constants.sainsburyContract=orderDetails.substring(7,13); 
					Reporter.log("SAINSBURY contract created - "+orderDetails);
				}
					
}
