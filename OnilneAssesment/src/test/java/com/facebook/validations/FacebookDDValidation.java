package com.facebook.validations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FacebookDDValidation {
	WebDriver driver;

	List <String> optionList;
	Set<String> optionSet;

	@BeforeTest
	public void setUp()
	{
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.facebook.com");
	}

	@Test
	public void verify_Dd_Duplicate_Options()    //******Verify Dropdown options are not duplicate*******
	{
		Select month=new Select(driver.findElement(By.id("month")));
		optionList=new ArrayList<String>();
		optionSet=new HashSet<String>();

		for(WebElement e:month.getOptions()) 
		{
			optionList.add(e.getText());
			optionSet.add(e.getText());
		}

		System.out.println("Elements in List:"+optionList);
		System.out.println("Elements in Set:"+optionSet);

		Assert.assertEquals(optionList.size(),optionSet.size());
	}

	@Test
	public void verify_DD_sorted()      //******Verify Dropdown options are sorted*******
	{
		List<String> tempList = new ArrayList<String>();
		Select month=new Select(driver.findElement(By.id("month")));
		optionList=new ArrayList<String>();

		for(WebElement e:month.getOptions()) 
		{
			optionList.add(e.getText());
			tempList.add(e.getText());	
		}
		Collections.sort(tempList);
		System.out.println("List After Sorting:"+tempList);
		Assert.assertEquals(optionList, tempList);
	}

	@Test
	public void verify_DD_ContainsAll()    //******Verify Dropdown contains All Options********
	{
		String []arr= {"Month", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov", "Dec"};
		Select month=new Select(driver.findElement(By.id("month")));
		List<WebElement> dropdownValue = month.getOptions();
		for(int i=0;i<dropdownValue.size();i++)
		{
			Assert.assertEquals(arr[i], dropdownValue.get(i).getText());
		}
		System.out.println("List contains all the Options");

		
	}

	@AfterTest
	public void tearDown()
	{
		driver.close();
	}
}
