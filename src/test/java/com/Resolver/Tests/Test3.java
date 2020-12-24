package com.Resolver.Tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.resolver.Pages.HomePage;
import com.resolver.TestBase.TestBase;


public class Test3 extends TestBase {

	HomePage home;

	@BeforeMethod
	public void startUp() {

		initialization();
	}

	@Test
	public void verify_Filter_ComboBox() throws InterruptedException {
		home = new HomePage();
		// Step 1
		boolean flag = home.serach_Table_Example("USA", 2);
		Assert.assertEquals(true, flag);
		System.out.println("Test 3: Step 1: Verify table displays 2 rows with country as USA Successful.");
		// Step 2
		boolean flag1 = home.serach_Table_Example(null, 4);
		Assert.assertEquals(true, flag1);
		System.out.println("Test 3: Step 2: Verify table displays 4 rows Successful.");

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
