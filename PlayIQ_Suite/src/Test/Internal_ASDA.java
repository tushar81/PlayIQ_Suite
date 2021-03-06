package Test;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import Helper.Constants;
import Helper.PlayIQ_Internal;

public class Internal_ASDA extends PlayIQ_Internal

{
		
				// ASDA Product Tests		
				@Test (priority=9)
				public void Internal_ASDA_bookByLocation () throws InterruptedException, AWTException  
				{
					changeProduct("ASDA");					
					driver.navigate().to("https://uat.playiq.co.uk/asda/bookings/BookByLocation");
					String orderDetails = submitOrder(Constants.oneWeek);
					Constants.asdaContract=orderDetails.substring(7,13); 
					Reporter.log("ASDA contract created - "+orderDetails);					 
				}
				
				@Test (priority=9)
				public void Internal_ASDA_bookByPlays () throws InterruptedException, AWTException  
				{
					changeProduct("ASDA");					
					driver.navigate().to("https://uat.playiq.co.uk/asda/bookings/BookByPlays");
					String orderDetails = submitPlayOrder(Constants.oneWeek);
					Assert.assertFalse(orderDetails.isEmpty());
					System.out.println(orderDetails);
					
				}
				
				@Test (priority=9)
				public void Internal_ASDA_CheckDaylightSaving() throws InterruptedException, AWTException
				{
					changeProduct("ASDA");	
					
					driver.navigate().to("https://uat.playiq.co.uk/asda/bookings/BookByLocation");
					String orderDetails = submitOrder("24/03/2018 - 26/03/2018");					
					Reporter.log(orderDetails);
					Assert.assertTrue(orderDetails.contains("created successfully"));
					
					cancelContract(orderDetails.substring(7,13),"ASDA");
					
					driver.navigate().to("https://uat.playiq.co.uk/asda/bookings/BookByPlays");
					orderDetails = submitPlayOrder("24/03/2018 - 26/03/2018");
					Reporter.log(orderDetails);
					Assert.assertTrue(orderDetails.contains("created successfully"));
					
					cancelContract(orderDetails.substring(7,13),"ASDA");
					
					driver.navigate().to("https://uat.playiq.co.uk/asda/bookings/BookByLocation");
					orderDetails = submitOrder("27/10/2018 - 29/10/2018");
					Reporter.log(orderDetails);
					Assert.assertTrue(orderDetails.contains("created successfully"));
					
					cancelContract(orderDetails.substring(7,13),"ASDA");
					
					driver.navigate().to("https://uat.playiq.co.uk/asda/bookings/BookByPlays");
					orderDetails = submitPlayOrder("27/10/2018 - 29/10/2018");
					Reporter.log(orderDetails);
					Assert.assertTrue(orderDetails.contains("created successfully"));
					
					cancelContract(orderDetails.substring(7,13),"ASDA");
					
					
				}
				
				@Test (priority=10)
				public void Internal_ASDA_viewAvailability()
				{
					changeProduct("ASDA");	
					driver.navigate().to("https://uat.playiq.co.uk/asda/availability/availdata");
					String availability = runAvailability();
					Assert.assertFalse(availability.isEmpty());
					Assert.assertTrue(availability.contains("31 Aug"));
					
				}
				
				
				
				
				
					
}
