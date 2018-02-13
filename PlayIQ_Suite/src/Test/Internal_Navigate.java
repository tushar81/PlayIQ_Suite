package Test;

 
import java.io.FileReader;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.opencsv.CSVReader;

import Helper.PlayIQ_Internal;

public class Internal_Navigate extends PlayIQ_Internal 
{
	
	@Test (priority=19)
	public void Internal_allProductsLandingPages() throws Exception 
	{
		
		Assert.assertTrue(checkPageTitle("https://uat.playiq.co.uk/malls/mallshome", "Malls"));
		Assert.assertTrue(checkPageTitle("https://uat.playiq.co.uk/wrap/wraphome", "Wrap"));
		Assert.assertTrue(checkPageTitle("https://uat.playiq.co.uk/storm/stormhome", "Storm"));
		Assert.assertTrue(checkPageTitle("https://uat.playiq.co.uk/sainsburys/sainsburyshome", "Sainsbury's Live"));
		Assert.assertTrue(checkPageTitle("https://uat.playiq.co.uk/asda/asdahome", "Asda Live"));
		Assert.assertTrue(checkPageTitle("https://uat.playiq.co.uk/adshel/adshelhome", "Adshel Live"));
		Assert.assertTrue(checkPageTitle("https://uat.playiq.co.uk/NI/NIhome", "Adshel Live Northern Ireland"));
		Assert.assertTrue(checkPageTitle("https://uat.playiq.co.uk/IrelandTesco/IrelandTescohome", "Ireland Tesco Live"));
		driver.navigate().to("https://uat.playiq.co.uk/changeproduct");
		Assert.assertTrue(checkPageTitle("Select a Product"));		
	}
	
	//Data driven page navigator	
	@Test (priority=20)
	public void Internal_checkTitle_allPages() throws Exception 
		{
			
			@SuppressWarnings("resource")
			CSVReader reader = new CSVReader(new FileReader("C://Users//ukccs1tsa//Selenium//PlayIQ_Suite//src//Pages.csv"));
			  String [] csvCell;
			  
			  while ((csvCell = reader.readNext()) != null) 
			  {   
			   String Link = csvCell[0];
			   String Title = csvCell[1];
			   Assert.assertTrue(checkPageTitle(Link.trim(),Title.trim()));		   
			  }
		}
	}

