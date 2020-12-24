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


public class Test2 extends TestBase {

	HomePage home;
	Test_Utils utils;

	@BeforeMethod
	public void startUp() {

		initialization();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void verify_Filter_ComboBox() throws InterruptedException, IOException {

		utils = new Test_Utils();
		home = new HomePage();

		List<String> data = new ArrayList<String>();

		data = utils.Provide_Data("Test2");

		String avail_Options = data.get(0);
		String Step2_Keyword = data.get(1);
		String Step2_verify_Keywords = data.get(2);
		String step3_keyword = data.get(3);
		String step3_Verify_keyword = data.get(4);

		// Step 1
		boolean flag = home.verify_Print_AvailableValues(avail_Options);
		Assert.assertEquals(true, flag);
		System.out.println("Test 2: Step 1: Verification of available Filter combo box options successful");
		// Step 2
		boolean flag1 = home.verify_required_Options(Step2_Keyword, Step2_verify_Keywords, 2);
		Assert.assertEquals(true, flag1);
		System.out.println(
				"Test 2: Step 2: Verification of two visible options \"Angular\" and \"Angular 2\" successful.");
		// Step 3
		boolean flag2 = home.verify_required_Options(step3_keyword, step3_Verify_keyword, 0);
		Assert.assertEquals(true, flag2);
		System.out.println("Test 2: Step 3: Verification of 0 visible options for test \"ReactJS\" successful.");
	}

	@AfterMethod
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
	}
}
