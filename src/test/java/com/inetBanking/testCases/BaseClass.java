package com.inetBanking.testCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetBanking.utilities.ReadConfig;

public class BaseClass {

	public static WebDriver driver;
	public static Logger logger;
	public String downloadPath = System.getProperty("user.dir");

	ReadConfig rc = new ReadConfig();

	@Parameters("browser")
	@BeforeClass
	public void initialSetUp(String browser) {

		// logger Configuration
		logger = Logger.getLogger("eBanking");
		PropertyConfigurator.configure("Log4j.properties");
		// String browser = "chrome";
		// driver properties
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", rc.getChromePath());
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) { 
			System.setProperty("webdriver.firefox.driver", downloadPath + "\\Drivers\\firefoxdriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("iebrowser")) {
			System.setProperty("webdriver.chrome.driver", downloadPath + "\\Drivers\\internetexplorer.exe");
			driver = new ChromeDriver();
		}

		// setting the global wait
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(rc.getApplicationURL());
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public void CaptureScreenshot(WebDriver driver, String tc_Name) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tc_Name + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot Captured");

	}
	
	public String randomestring()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(8);
		return(generatedstring);
	}
	
	public static String randomeNum() {
		String generatedString2 = RandomStringUtils.randomNumeric(4);
		return (generatedString2);
	}

}
