package com.Resolver.Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.resolver.Pages.HomePage;
import com.resolver.TestBase.TestBase;
import com.resolver.Utilities.Test_Utils;

public class Test1 extends TestBase {

	HomePage home;
	Test_Utils utils;

	@BeforeMethod
	public void startUp() throws IOException, InterruptedException {
		initialization();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void open_Modal_Test() throws InterruptedException, IOException {

		utils = new Test_Utils();
		home = new HomePage();

		List<String> data = new ArrayList<String>();
		data = utils.Provide_Data("Test1");
		String name = data.get(0);
		String city = data.get(1);

		// Step 1
		String[] verify = home.verify_Open_Modal(name, city).split(" ");
		// Step 2
		Assert.assertEquals(name, verify[0].trim());
		Assert.assertEquals(city, verify[1].trim());
		System.out.println("Name and City verified Successfully" + "\r\n" + "Test 1 - Successful");

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
