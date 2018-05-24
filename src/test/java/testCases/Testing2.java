package testCases;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import config.BaseTestSetting;
import page.Allmediany_SignUpPage;

public class Testing2 extends BaseTestSetting {
	
	public String TestCaseName = "Testing2";
	public String TestSheet = "Allmediany";
	Allmediany_SignUpPage AllmedianyPage;

//	ExtentReports extent;
//	ExtentTest logger;	
	 //===================================================================================	
	@BeforeTest
	public void startTest() throws IOException {
		Report = CreateExtentReport(TestCaseName);
	}
	//==========================================
	@DataProvider
    public Object[][] GetData() throws Exception{
		 //Object data = null;
		 Object[][] data = null;
         data = GetData(TestCaseName,TestSheet);
         return data;
	}
	//==========================================
	 @Test(dataProvider="GetData")
	 public void SampleTest(Hashtable<String, String> data) throws IOException {
	    logger = Report.startTest(TestCaseName, TestCaseName+" Execution Started");
	    if (!data.get("Execute").equalsIgnoreCase("Y") || (!isExecutableFlag(TestCaseName))){
	    	logger.log(LogStatus.SKIP,TestCaseName+" is skipped as skip mode is off");
	    	throw new SkipException(TestCaseName+" is skipped as Runmode is not Y in DataSheet");
	    }else {
	    	//-------------BODY-----------BODY-------------BODY----------BODY---------------------------------
	    	//PageFactory.initElements(driver, AllmedianyPage);
	    	OpenBrowser(data.get("Browser"),data.get("URL"),20);
	    	AllmedianyPage = new Allmediany_SignUpPage(driver,logger);
	    	AllmedianyPage.SignUp(data);
	    	logger.log(LogStatus.PASS, TestCaseName+" PASSED");
	    	driver.close();
	    	//-------------END BODY----------- END BODY-------------END BODY----------END BODY----------------
	    }
	 }
	 //===================================================================================	 
	 @AfterMethod
	 public void getResult(ITestResult result){
	 if(result.getStatus() == ITestResult.FAILURE){
	    logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
	    logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
	 }else if(result.getStatus() == ITestResult.SKIP){
	    logger.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
	 }else {
		logger.log(LogStatus.PASS, "Test Case PASSED is "+result.getName());
	 }
	 // ending test
	 //endTest(logger) : It ends the current test and prepares to create HTML report
	 if(driver!=null) {
   		  driver.quit();
	 }
	 Report.endTest(logger);
	 }
	 //===================================================================================
	 @AfterTest
	 public void endReport(){
	 // writing everything to document
	 //flush() - to write or update test information to your report.
		  if (Report != null) {
			  Report.endTest(logger);
			  Report.flush();
			  Report.close();
		  }
		  try {
			  if (driver != null) {
				  driver.quit();
			  }
		  }catch (Exception e) {
			  System.out.println("All browser already closed");
		  }            
	    }
	 //===================================================================================	 	
}
