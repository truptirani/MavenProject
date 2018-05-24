package config;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;

public class CommonMethods extends BaseTestSetting {

  //=============This method is used to select value from the weblist============
	public void selectWeblist(WebElement WebList, String visibleText) 
	{
		try {
	       Select s1=new Select(WebList);
	       s1.selectByVisibleText(visibleText);	
	       
			}catch(NoSuchElementException e) {
				System.out.println(visibleText+" Option value not find in dropdown");
			}
	 }
  //=================End Method=======================================================
//============== This method is used to select the option in dropdown based on the value=========
	public void selectWeblist(WebElement WebList, int index) 
	{
		try {
	       Select s1=new Select(WebList);
	       s1.selectByIndex(index);
	       
			}catch(NoSuchElementException e) {
				System.out.println(index+" is not present");
			}
	 }
//=============================================================================================
	// ----------------Select the CheckBox-----------------------------------------------------
//=============================================================================================
	public void Select_The_Checkbox(WebElement WebCheckbox) {
		try {
            if (WebCheckbox.isSelected()) {
               System.out.println("Checkbox: " + WebCheckbox + "is already selected");
            } else {
            	// Select the checkbox
            	WebCheckbox.click();
            }
        } catch (Exception e) {
        	System.out.println("Unable to select the checkbox: " + WebCheckbox);
        }
		
	}
	//=============================================================================================
	//Below method is used to De-select a Checkbox, if it is selected already
	//=============================================================================================
	public void DeSelect_The_Checkbox(WebElement WebCheckbox) {
		try {
            if (WebCheckbox.isSelected()) {
            	//De-select the checkbox
            	WebCheckbox.click();
            } else {
            	System.out.println("Checkbox: "+WebCheckbox+"is already deselected");
            }
        } catch (Exception e) {
        	System.out.println("Unable to deselect checkbox: "+WebCheckbox);
        }
    }
	//=============================================================================================	
	//Below method is used to select the checkbox with the specified value from multiple checkboxes.
	//=============================================================================================
	public void Select_The_CheckBox_from_List(WebElement WebCheckbox, String valueToSelect) {
		List<WebElement> allOptions = WebCheckbox.findElements(By.tagName("input"));
		for (WebElement option : allOptions) {
			   System.out.println("Option value "+option.getText());
			        if (valueToSelect.equals(option.getText())) {
			            option.click();
			            break;
			        }
			    }
	}
	//=============================================================================================
	//Return element present or not
	//=============================================================================================
		
		public boolean isElementExist(WebElement Ele) {
			WebDriverWait wait = new WebDriverWait(driver,5);
			wait.until(ExpectedConditions.visibilityOf(Ele));
				if (Ele.isDisplayed()) {
					return true;
				}else {
					System.out.println(Ele+" is not displayed in this page");
					logger.log(LogStatus.INFO,Ele+" is not displayed in this page");
					return false;
				}
		}
//=============================================================================================
//Return element present or not
//=============================================================================================
	
	public boolean isElementPresent(By Xpath) {
		 driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
         int elementCount = driver.findElements(Xpath).size();
         driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
                if (elementCount > 0) {
                    return true;
                }else {
                    return false;
      			}
		}
//=============================================================================================	
//=====================Do mouse over==================================
//=============================================================================================
	public static void MouseOver(WebElement Element){
		Actions actObj=new Actions(driver);
		actObj.moveToElement(Element).build().perform();
		}
	//============================================================================================
	//----Switch between window-------------------
	//============================================================================================
		@SuppressWarnings({ "rawtypes", "unused" })
		public static void switchToNewWindow() {
		Set s = driver.getWindowHandles();
		Iterator itr = s.iterator();
		String w1 = (String) itr.next();
		String w2 = (String) itr.next();
		driver.switchTo().window(w2);
		}
	//============================================================================================
		@SuppressWarnings({ "rawtypes", "unused" })
		public static void switchToOldWindow() {
		Set s = driver.getWindowHandles();
		Iterator itr = s.iterator();
		String w1 = (String) itr.next();
		String w2 = (String) itr.next();
		driver.switchTo().window(w1);
		}
		//============================================================================================
		public static void switchToParentWindow() {
		driver.switchTo().defaultContent();
		}
		//============================================================================================
		public static void waitForElementClickable(WebElement element,int seconds) {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.elementToBeClickable(element));
		}
		//============================================================================================
		public static void waitTillElementFound(WebElement ElementTobeFound,int seconds) {
			WebDriverWait wait = new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOf(ElementTobeFound));
		}
		//============================================================================================
		//---------------------Click WebElement-------------------------------------------------------
		//============================================================================================
		public void ClickElement(WebElement element) {
			try {
			boolean elementIsClickable = element.isEnabled();
			while (elementIsClickable) {
			element.click();
			}
			} catch (Exception e) {
			System.out.println("Element is not enabled");
			e.printStackTrace();
			}
			}
		//============================================================================================
		//---------------------Double Click WebElement-------------------------------------------------------
		//============================================================================================
		public static void doubleClickWebelement(WebElement doubleclickonWebElement)
				throws InterruptedException {
				Actions builder = new Actions(driver);
				builder.doubleClick(doubleclickonWebElement).perform();
				Thread.sleep(2000);
		}
		//============================================================================================
		//---------------------HighLight Element------------------------------------------------------
		//============================================================================================
		public static void highlightelement(WebElement element) {
			for (int i = 0; i < 4; i++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(
			"arguments[0].setAttribute('style', arguments[1]);",
			element, "color: solid red; border: 6px solid yellow;");
			js.executeScript(
			"arguments[0].setAttribute(‘style’, arguments[1]);",
			element, "");
			}
		}
		//============================================================================================
		//---------------------Accept alert------------------------------------------------------
		//============================================================================================
		public static boolean checkAlert_Accept() {
			try {
			Alert a = driver.switchTo().alert();
			String str = a.getText();
			System.out.println(str);

			a.accept();
			return true;

			} catch (Exception e) {

			System.out.println("no alert ");
			return false;
			}
			}
		
		//============================================================================================
		//---------------------Scroll to Element for better screenshot================================
		//============================================================================================
		public static void scrolltoElement(WebElement ScrolltoThisElement) {
			Coordinates coordinate = ((Locatable) ScrolltoThisElement)
			.getCoordinates();
			coordinate.onPage();
			coordinate.inViewPort();
		}
		//============================================================================================
		//---------------------Select the radiobutton=================================================
		//============================================================================================
		public static void radiobutton_Select(WebElement Radio) {
			boolean checkstatus;
			checkstatus = Radio.isSelected();
			if (checkstatus == true) {
			System.out.println("RadioButton is already checked");
			} else {
			Radio.click();
			System.out.println("Selected the Radiobutton");
			}
		}
		//============================================================================================
		//---------------------DeSelect the radiobutton=================================================
		//============================================================================================
		public static void radioButton_Deselect(WebElement Radio) {
			boolean checkstatus;
			checkstatus = Radio.isSelected();
			if (checkstatus == true) {
			Radio.click();
			System.out.println("Radio Button is deselected");
			} else {
			System.out.println("Radio Button was already Deselected");
			}
			}
		//============================================================================================
		//---------------------Drag and drop element--------------------------------------------------
		//============================================================================================
		public static void dragAndDrop(WebElement fromWebElement,
		WebElement toWebElement) {
		Actions builder = new Actions(driver);
		builder.dragAndDrop(fromWebElement, toWebElement);
		}
		
		//Method 2 if other not work
		public static void dragAndDrop_Method2(WebElement fromWebElement,
		WebElement toWebElement) {
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(fromWebElement)
		.moveToElement(toWebElement).release(toWebElement).build();
		dragAndDrop.perform();
		}
		
		//Method 3 if other not work
		public static void dragAndDrop_Method3(WebElement fromWebElement,
		WebElement toWebElement) throws InterruptedException {
		Actions builder = new Actions(driver);
		builder.clickAndHold(fromWebElement).moveToElement(toWebElement)
		.perform();
		Thread.sleep(2000);
		builder.release(toWebElement).build().perform();
		}
		//============================================================================================
}
	
