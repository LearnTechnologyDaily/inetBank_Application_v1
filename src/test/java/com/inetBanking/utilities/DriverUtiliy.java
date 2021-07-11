package com.inetBanking.utilities;

import org.openqa.selenium.NoAlertPresentException;

import com.inetBanking.testCases.BaseClass;

public class DriverUtiliy extends BaseClass {
		
	public static boolean isAlertPresent() //user defined method created to check alert is present or not
	{
		try
		{
		driver.switchTo().alert();
		
		return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}
		
	}
}
