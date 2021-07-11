package com.inetBanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetBanking.pageObject.LoginPage;
import com.inetBanking.utilities.DriverUtiliy;
import com.inetBanking.utilities.XLUtils;

public class Login_TC_001 extends BaseClass {

	/*  */
	/*
	 * This Method will Login using UserName AND Password Parameter - Null
	 */

	@Test
	public void loginTest() throws IOException {
		LoginPage lp = new LoginPage(driver);
		logger.info("URL is Opened");

		lp.setUserName(rc.userName());
		logger.info("Entered the UserName");

		lp.setPassword(rc.password());
		logger.info("Entered the Password");

		lp.clickLogin();
		logger.info("Clicked on Login Button");

		// assert the Result and print it in the log
		if (driver.getTitle().equalsIgnoreCase(rc.getTitle())) {
			System.out.println("THE TITLE: " + driver.getTitle());
			Assert.assertTrue(true);
			logger.info("Login TestCase Passed Worked as Expected");
			
			lp.clickLogout();
			logger.info("Clicked Logout Button for Passed Login Scenario");
			
			if (DriverUtiliy.isAlertPresent() == true) {
				driver.switchTo().alert().accept();// close
				driver.switchTo().defaultContent();
				logger.info("Logout Alert CLosed");
				Assert.assertTrue(true);
			}
			else {
				logger.info("Logout Alert is not displayed");
				Assert.assertTrue(false);
			}
			
			try {
				Thread.sleep(3000);
			} // THIS Theard is Used to Halt the Test for 2Secs
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			logger.info("Login TestCase Failed is not Working as Expected");
			CaptureScreenshot(driver, "loginTest");
			
			lp.clickLogout();
			logger.info("Clicked Logout Button for Failed Excel Login Scenario");
			
			Assert.assertTrue(false);
			
		}
	}

	@Test(dataProvider = "Login_TestData")
	public void loginTest2(String user, String pwd) throws IOException, InterruptedException {

		logger.info("Url is Navigated for Excel Login Scenario");
		LoginPage lp = new LoginPage(driver);

		lp.setUserName(user);
		logger.info("UserName is Entered using Excel data Sheet");

		lp.setPassword(pwd);
		logger.info("Password is Entered using Excel data Sheet");

		lp.clickLogin();
		logger.info("Clicked Login Button for Excel Login Scenario");

		Thread.sleep(3000);

		if (DriverUtiliy.isAlertPresent() == true) {
			
			driver.switchTo().alert().accept();// close
			driver.switchTo().defaultContent();
			
			logger.info("Login failed due to Incorrect Cerdentials or UnExcepted Alert Present");
			CaptureScreenshot(driver, "loginTest_ExcelScenario");
			
			Assert.assertTrue(false);

		} else {
			Assert.assertTrue(true);
			logger.info("Logout Alert is Persent");
			lp.clickLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();// close logout alert
			driver.switchTo().defaultContent();
		}

	}

	@DataProvider(name = "Login_TestData")
	String[][] getmethod12() throws IOException {

		String xlpath = rc.getXLPath();
		String sheet_Name = rc.getSheetName();
		int rowCount = XLUtils.getRowCount(xlpath, sheet_Name);
		int colCount = XLUtils.getCellCount(xlpath, sheet_Name, 1);

		String logindata[][] = new String[rowCount][colCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				logindata[i - 1][j] = XLUtils.getCellData(xlpath, sheet_Name, i, j); // 1 0
			}

		}
		return logindata;
	}

}
