package page;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import config.BaseTestSetting;
import config.CommonMethods;
import config.LocatorRepository;

public class Allmediany_SignUpPage extends BaseTestSetting {

	@FindBy(xpath = LocatorRepository.UserName)
	WebElement userName;

	@FindBy(xpath = LocatorRepository.Password)
	WebElement password;

	@FindBy(xpath = LocatorRepository.ConfirmPassword)
	WebElement Confirmpassword;

	@FindBy(xpath = LocatorRepository.FirstName)
	WebElement firstname;

	@FindBy(xpath = LocatorRepository.LastName)
	WebElement lastname;

	@FindBy(xpath = LocatorRepository.Email)
	WebElement email;

	@FindBy(xpath = LocatorRepository.DOBMonth)
	WebElement dobMonth;

	@FindBy(xpath = LocatorRepository.DOBDate)
	WebElement dobDate;

	@FindBy(xpath = LocatorRepository.DOBYear)
	WebElement dobYear;

	@FindBy(xpath = LocatorRepository.Address)
	WebElement address;

	@FindBy(xpath = LocatorRepository.Country)
	WebElement country;

	@FindBy(xpath = LocatorRepository.State)
	WebElement state;

	@FindBy(xpath = LocatorRepository.City)
	WebElement city;

	@FindBy(xpath = LocatorRepository.Submit)
	WebElement submit;

	// Allmediany_SignUpPage AllmedianyPage = PageFactory.initElements(driver,
	// Allmediany_SignUpPage.class);

	WebDriver driver;

	@SuppressWarnings("static-access")
	public Allmediany_SignUpPage(WebDriver driver, ExtentTest logger) {
		this.driver = driver;
		this.logger = logger;
		PageFactory.initElements(driver, this);
	}

	public void SignUp(Hashtable<String, String> data) throws IOException {
		CommonMethods CM = new CommonMethods();
		userName.sendKeys(data.get("username"));
		password.sendKeys(data.get("Password"));
		Confirmpassword.sendKeys(data.get("ConfirmPassword"));
		CM.selectWeblist(dobMonth, data.get("Month of Dob"));
		CM.selectWeblist(dobDate, data.get("Date of Dob"));
		CM.selectWeblist(dobYear, data.get("Year of Dob"));
		logger.log(LogStatus.INFO, "All details filled");
		TakeScreenShot();
		// submit.click();
	}

}
