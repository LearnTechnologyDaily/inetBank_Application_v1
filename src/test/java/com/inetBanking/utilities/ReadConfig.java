package com.inetBanking.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig {

	Properties pro;

	public ReadConfig() {
		try {
			File src = new File("./Configuration//CommonConfig.properties");

			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getApplicationURL() 
	{
		return pro.getProperty("Application_Url");
	}

	public String userName() 
	{
		return pro.getProperty("UserName");
	}

	public String password() 
	{
		return pro.getProperty("Password");
	}
	
	public String getTitle() 
	{
		return pro.getProperty("TitleOfPage");
	}

	public String getChromePath() 
	{
		return pro.getProperty("chrome");
	}
	
	public String getXLPath() 
	{
		return pro.getProperty("xlPath");
	}
	
	public String getSheetName() 
	{
		return pro.getProperty("sheetname");
	}


}
