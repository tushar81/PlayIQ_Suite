package Helper;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.xml.XmlSuite;
import org.testng.*;
 
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
 
public class ExtentReporterNG extends TestListenerAdapter implements IReporter 
{
    private ExtentReports extent;      
 
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) 
    {
    	 
    	//extent = new ExtentReports(outputDirectory + File.separator + "PlayIQ_Run_Report.html", true);
    	extent = new ExtentReports(outputDirectory + File.separator + "PlayIQ_Run_Report_"+Constants.today+".html", true); 
    	extent    	
        .addSystemInfo("Environment", "UAT")
        .addSystemInfo("User Name", "Tushar Sathaye");
        extent.loadConfig(new File("C:\\Users\\ukccs1tsa\\Selenium\\PlayIQ_Suite\\src\\Extent-Config.xml"));
        
        for (ISuite suite : suites) 
        {
            Map<String, ISuiteResult> result = suite.getResults();
 
            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }
 
        for (String s : Reporter.getOutput())
        {
            extent.setTestRunnerOutput(s);
        }
        extent.flush();
        extent.close();
    }
 
    private void buildTestNodes(IResultMap tests, LogStatus status) 
    {
        ExtentTest test;
 
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = extent.startTest(result.getMethod().getMethodName());                
                test.getTest().setStartedTime(getTime(result.getStartMillis())) ;
                test.getTest().setEndedTime(getTime(result.getEndMillis()));
                
                for (String group : result.getMethod().getGroups())
                    test.assignCategory(group);
 
                String message = "Test " + status.toString().toLowerCase() + "ed";
 
                if (result.getThrowable() != null)
                    message = result.getThrowable().getMessage();
 
                test.log(status, message); 
                extent.endTest(test);
            }
        }
    }
 
	private Date getTime(long millis) 
	{
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();        
    }


}