package testCases;

import java.awt.AWTException;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import config.BaseTestSetting;
import config.CommonMethods;

public class FlipkartBuy2 extends BaseTestSetting
{
	public static String TestCaseName = "Register_Mercury_Tours";
	public String TestSheet = "MercuryTours";
	
  @Test
  public void OpenBrowserTesting() throws IOException, InterruptedException, AWTException {
	  
	    CommonMethods CM = new CommonMethods();
	    String NoteURLPro = "https://www.flipkart.com/redmi-note-5-black-64-gb/p/itmf2fc3phdkgahv?pid=MOBF28FTXZYZ6UYJ&srno=s_1_4&otracker=search&lid=LSTMOBF28FTXZYZ6UYJSQJJLU&fm=SEARCH&iid=c1fafea3-186e-4217-bdce-786b5357a659.MOBF28FTXZYZ6UYJ.SEARCH&ppt=Search%20Page&ppn=Search%20Page&ssid=kraovjkgg00000001522218356914&qH=8c551d5a4e9ae744";
	    OpenBrowser("Chrome",NoteURLPro,20);
	    
	    driver.findElement(By.xpath("//a[@class='_2k0gmP']")).click();
	    driver.findElement(By.xpath("//input[@class='_2zrpKA']")).sendKeys("");
	    driver.findElement(By.xpath("//input[@class='_2zrpKA _3v41xv']")).sendKeys("");
	    driver.findElement(By.xpath("//button[@class='_2AkmmA _1LctnI _7UHT_c']")).click();
	    //driver.findElement(By.xpath("//div[contains(text(),'Coming Soon')])")).click();
	    
	    
	    
	    Thread.sleep(60000);
	    
	    //driver.manage().timeouts().implicitlyWait(1,TimeUnit.SECONDS);
	    //boolean ComingSoon = driver.findElement(By.xpath("//div[contains(text(),'Coming Soon')])")).isDisplayed();
	    boolean Buy = CM.isElementPresent(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2kuvG8 _7UHT_c']"));
	    
	    
	    while(!Buy){
	    	driver.navigate().refresh();
	    	Buy = CM.isElementPresent(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2kuvG8 _7UHT_c']"));
	    }
	    //buy now
	    boolean Continue = CM.isElementPresent(By.xpath("//button[@class='_2AkmmA _2Q4i61 _7UHT_c']"));
	    while(!Continue){
	    	//Click BuyNow
	    	driver.findElement(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2kuvG8 _7UHT_c']")).click();
	    	Thread.sleep(500);
	    	Continue = CM.isElementPresent(By.xpath("//button[@class='_2AkmmA _2Q4i61 _7UHT_c']"));
	    }
	    //driver.manage().timeouts().implicitlyWait(120,TimeUnit.SECONDS);
	    
	    //continue
	    driver.findElement(By.xpath("//button[@class='_2AkmmA _2Q4i61 _7UHT_c']")).click();
	    //cvv
	    driver.findElement(By.xpath("//input[@name='cvv']")).sendKeys("390");
	    //pay
	   driver.findElement(By.xpath("//button[@class='_2AkmmA Hy6dyh _7UHT_c']")).click();
	    //give details manually
	    
	    
	    
	   
	    
  }
}
