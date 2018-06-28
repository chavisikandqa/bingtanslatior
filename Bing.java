package qainfotech;

import static org.testng.Assert.assertEquals;

import java.io.ObjectInputStream.GetField;
import java.util.Scanner;

import javax.print.DocFlavor.INPUT_STREAM;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Bing {
	WebDriver wd;

	@BeforeTest
	
	public void launchTheWebpage() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\chavisikand\\eclipse-workspace\\qainfotech\\driver\\chromedriver.exe");
		wd = new ChromeDriver();
		wd.get("https://www.bing.com/translator");
	}

	@Test(priority=0)

	public void englishToGerman() throws InterruptedException 
			
	{
		Select s = new Select(wd.findElement(By.id("t_sl")));
		s.selectByValue("en");
	
		wd.findElement(By.id("t_sv")).sendKeys("My name is Chavi");
		Select o = new Select(wd.findElement(By.id("t_tl")));
		o.selectByValue("de");
		Thread.sleep(5000);
		
		JavascriptExecutor t=(JavascriptExecutor)wd;
	       String p=(String) t.executeScript("return document.querySelector('textArea#t_tv').value");
	       System.out.println(p);
		Assert.assertEquals("Mein Name ist Chavi", p);
	}
	

	@Test(priority=1)
	public void reverseButton() {
		
		Select o = new Select(wd.findElement(By.id("t_tl")));
		o.selectByValue("de");
		
		wd.findElement(By.id("t_revIcon")).click();
		
		JavascriptExecutor q=(JavascriptExecutor)wd;
		 String v=(String) q.executeScript("return document.querySelector('select#t_sl').value");
		System.out.println(v);
		
		Assert.assertEquals("de",v);
	
	}
	@Test(priority=2)
	public void suggestionBox() {
		Select l = new Select(wd.findElement(By.id("t_sl")));
		l.selectByValue("sm");
		Select k = new Select(wd.findElement(By.id("t_sl")));
		k.selectByValue("ru");
		String g=wd.findElement(By.className("ttl_histbtn")).getAttribute("value");
		System.out.println(g);
		Assert.assertEquals("Samoan", g);
		
	}
	@AfterTest
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(10000);
		wd.quit();
	}
}


