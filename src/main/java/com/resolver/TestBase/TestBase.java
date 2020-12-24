package com.resolver.TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {

	public static WebDriver driver;
	public static WebDriverWait wait;

	public static void initialization() {

		System.setProperty("webdriver.chrome.driver", "./Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 10);
		driver.get("C:/Users/ashok/eclipse-workspace-1/Resolver/Application/index.html");

	}

}
