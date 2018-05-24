package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTestSetting {
	public static String ConfigPropertyPath = System.getProperty("user.dir") + "\\TestData\\ConfigDetail.properties";
	public static WebDriver driver;
	public static ExtentTest logger;
	public static ExtentReports Report;

	// =============================================================================================
	public static ExtentReports CreateExtentReport(String TestCaseName) throws IOException {
		String ResultFolder = getPropertiesFileData(ConfigPropertyPath, "ResultFolder");
		ExtentReports extent = new ExtentReports(ResultFolder + "//" + TestCaseName + "_" + fn_GetTimeStamp() + ".html",
				true);
		extent.addSystemInfo("Host Name", "Testing").addSystemInfo("Environment", "Automation Testing")
				.addSystemInfo("User Name", "Selenium");
		// loading the external xml file (i.e., extent-config.xml) which was placed
		// under the base directory
		// You could find the xml file below. Create xml file in your project and copy
		// past the code mentioned below
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		return extent;
	}

	// =============================================================================================
	public void TakeScreenShot() throws IOException {
		String ResultFolder = getPropertiesFileData(ConfigPropertyPath, "ResultFolder");
		String screenShotPath = ResultFolder + "\\ScreenShot\\" + fn_GetTimeStamp() + ".png";
		// test.log(LogStatus.FAIL, result.getThrowable());
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File dest = new File(screenShotPath);
		try {
			FileUtils.copyFile(SrcFile, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.log(LogStatus.INFO, logger.addScreenCapture(screenShotPath));
	}

	// =============================================================================================
	public Object[][] GetData(String TestCaseName, String TestSheet) throws IOException {
		String DataSheetPath = getPropertiesFileData(ConfigPropertyPath, "TestDataPath");
		xlsxReader xlOperation = new xlsxReader(System.getProperty("user.dir") + DataSheetPath);
		int MatchingTestCaseRowInDataSheet = xlOperation.getCellRowNum(TestSheet, "TestCaseName", TestCaseName);
		int TotalColumnNumber = xlOperation.getColumnCount(TestSheet);
		String TestCaseNameExist = xlOperation.getCellData(TestSheet, 0, MatchingTestCaseRowInDataSheet - 1);
		try {
			if (TestCaseNameExist.equalsIgnoreCase("TestCaseName")) {
				System.out.println("The Testcase found and start with row number = " + MatchingTestCaseRowInDataSheet);
			}
		} catch (Exception e) {
			System.out.println(
					"The excel sheet data format is not correct, Testcase's Name should be under 'TestCaseName'");
		}
		String NextTestCaseName = xlOperation.getCellData(TestSheet, 0, MatchingTestCaseRowInDataSheet);
		int NextTestCaseRow = MatchingTestCaseRowInDataSheet;
		int TestCaseRowCount = MatchingTestCaseRowInDataSheet;
		Hashtable<String, String> CollectData = null;
		int row = 0;
		int TotalMatchTestCaseCount = 0;
		while (NextTestCaseName.equalsIgnoreCase(TestCaseName)) {
			TotalMatchTestCaseCount++;
			TestCaseRowCount++;
			NextTestCaseName = xlOperation.getCellData(TestSheet, 0, TestCaseRowCount);
		}
		System.out.println("Total number of test case found is = " + TotalMatchTestCaseCount);
		// Define the size of the Object from 'TotalMatchTestCaseCount'
		Object[][] data = new Object[TotalMatchTestCaseCount][1];

		for (int i = 0; i < TotalMatchTestCaseCount; i++) {
			CollectData = new Hashtable<String, String>();
			for (int j = 0; j < TotalColumnNumber; j++) {
				String Key = xlOperation.getCellData(TestSheet, j, MatchingTestCaseRowInDataSheet - 1);
				String Value = xlOperation.getCellData(TestSheet, j, NextTestCaseRow);
				if (Key.equals(null) || Key.equals("")) {
					break;
				}
				CollectData.put(Key, Value);
			} // for
			data[row][0] = CollectData;
			row++;
			NextTestCaseRow++;
		}
		// xlOperation.closeExcel();
		return data;
	}

	// =============================================================================================
	// This method will open corresponding bRowser as per the user INput
	// Examaple for InternetExplorere = IE
	// Mozilla = Mozilla
	// Chrome = Chrome
	@SuppressWarnings("deprecation")
	public void OpenBrowser(String BrowserType, String Url, int impWaitTime) {
		// driver=null;
		if (BrowserType.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\BrowserDriver\\" + "chromedriver.exe");
			driver = new ChromeDriver();
		} else if (BrowserType.equalsIgnoreCase("Mozilla")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\BrowserDriver\\" + "geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
		} else if (BrowserType.equalsIgnoreCase("IE")) {
			driver = new InternetExplorerDriver();
		}
		driver.manage().window().maximize();
		driver.get(Url);
		driver.manage().timeouts().implicitlyWait(impWaitTime, TimeUnit.SECONDS);
		// return driver;
	}
	// ====================================================================================================

	// This method will return True if Executable value is Y in data sheet, else
	// return False
	public boolean isExecutableFlag(String TestCaseName) throws IOException {
		String DataSheetPath = getPropertiesFileData(ConfigPropertyPath, "TestDataPath");
		xlsxReader xlOperation = new xlsxReader(System.getProperty("user.dir") + DataSheetPath);
		int MatchingTestCaseRow = xlOperation.getCellRowNum("TestCase", "TestCaseName", TestCaseName);
		String WillExecute = xlOperation.getCellData("TestCase", "WillExecute", MatchingTestCaseRow);
		if (WillExecute.contains("Y")) {
			return true;
		} else {
			return false;
		}
	}

	// =============================================================================================
	// This method will return the property value by taking input PropertyName, and
	// the key
	public static String getPropertiesFileData(String PropertyFilePath, String Key) throws IOException {
		Properties config = new Properties();
		File file = new File(PropertyFilePath);
		FileInputStream Fi = new FileInputStream(file);
		config.load(Fi);
		String Value = config.getProperty(Key);
		return Value;
	}

	// =============================================================================================
	public void Update_Results(String TestCaseName, String Description) throws IOException {

		String DataSheetPath = getPropertiesFileData(ConfigPropertyPath, "TestDataPath");
		xlsxReader xlOperation = new xlsxReader(System.getProperty("user.dir") + DataSheetPath);
		int MatchingTestCaseRow = xlOperation.getCellRowNum("TestCase", "TestCaseName", TestCaseName);
		MatchingTestCaseRow = MatchingTestCaseRow + 1;
		xlOperation.setCellData("TestCase", "Result", MatchingTestCaseRow, Description);
	}

	// =============================================================================================
	public static String fn_GetTimeStamp() {
		DateFormat DF = DateFormat.getDateTimeInstance();
		Date dte = new Date();
		String DateValue = DF.format(dte);
		DateValue = DateValue.replaceAll(":", "_");
		DateValue = DateValue.replaceAll(",", "");
		return DateValue;
	}
}
