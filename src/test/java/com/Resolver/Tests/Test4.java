package com.Resolver.Tests;

import java.awt.AWTException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.resolver.Pages.HomePage;
import com.resolver.TestBase.TestBase;


public class Test4 extends TestBase {

	HomePage home;

	@BeforeMethod
	public void startUp() {

		initialization();
	}

	@Test
	public void verify_Filter_ComboBox() throws InterruptedException, AWTException {
		home = new HomePage();
		// Step 1
		boolean flag = false;
		flag = home.verify_DragandDrop();
		Assert.assertEquals(true, flag);
		System.out.println("Test 4: verificatoin of Drag & Drop successful.");

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}

}
