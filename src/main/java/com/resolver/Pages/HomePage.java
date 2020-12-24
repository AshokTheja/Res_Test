package com.resolver.Pages;

import java.awt.AWTException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.resolver.TestBase.TestBase;
import com.resolver.Utilities.Test_Utils;

public class HomePage extends TestBase {

	// Test1
	@FindBy(css = ".btn.btn-info.btn-lg")
	WebElement open_Modal;

	@FindBy(xpath = "//input[@class='form-control' and @id='name']")
	WebElement modal_Name;

	@FindBy(xpath = "//input[@class='form-control' and @id='city']")
	WebElement modal_City;

	@FindBy(xpath = "//button[@id='enter']")
	WebElement modal_Enter;

	@FindBy(css = ".btn.btn-default")
	WebElement modal_Close;

	@FindBy(xpath = "//span[@id='nameVal']")
	WebElement modal_nameValue;

	@FindBy(xpath = "//span[@id='cityVal']")
	WebElement modal_cityValue;

	// Test2
	@FindBy(css = ".btn.btn-primary.dropdown-toggle")
	WebElement test2_ClickMe;

	@FindBy(xpath = "//ul[@class='dropdown-menu show']//input[@id='myInput']")
	WebElement test2_DD_SearchField;

	// Test3
	@FindBy(xpath = "//input[@id='searchMe']")
	WebElement table_SearchField;

	// Test4
	@FindBy(xpath = "//br//following-sibling::button[@id='drag1']")
	WebElement drag_From;

	@FindBy(css = "#div1")
	WebElement drop_At;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verify_Open_Modal(String name, String city) throws InterruptedException {

		open_Modal.click();
		Test_Utils.sync(3000);
		modal_Name.sendKeys(name);
		modal_City.sendKeys(city);
		modal_Enter.click();
		Test_Utils.sync(2000);
		modal_Close.click();

		String foundValues = modal_nameValue.getText();
		foundValues = foundValues + " " + modal_cityValue.getText();

		return foundValues;

	}

	@SuppressWarnings("unused")
	public boolean verify_Print_AvailableValues(String avail_Options) {

		Boolean flag = false;
		List<String> availableOptions_List = new ArrayList<String>();

		String[] availableOptions = avail_Options.split(",");

		test2_ClickMe.click();

		List<WebElement> wE = driver.findElements(By.xpath("//ul[@class='dropdown-menu show']//li"));

		if (wE.size() == availableOptions.length) {
			for (int i = 0; i < availableOptions.length; i++) {
				if (avail_Options.contains(wE.get(i).getText().trim())) {

					System.out.println("verified available option: " + wE.get(i).getText().trim());
					flag = true;

				} else {
					flag = false;
					break;
				}
			}

		}
		test2_ClickMe.click();
		return flag;
	}

	public boolean verify_required_Options(String wanted, String required, int count) throws InterruptedException {

		Test_Utils.sync(3000);

		test2_ClickMe.click();
		test2_DD_SearchField.clear();
		test2_DD_SearchField.sendKeys(wanted);

		boolean flag = false;

		String[] req = required.split(",");

		List<WebElement> wE = driver
				.findElements(By.xpath("//ul[@class='dropdown-menu show']//li[not(@style='display: none;')]"));

		if (wE.size() == count) {
			if (count > 0) {
				for (int i = 0; i < req.length; i++) {

					if (required.contains(wE.get(i).getText().trim())) {
						System.out.println("verified required option: " + wE.get(i).getText().trim());
						flag = true;

					} else {
						flag = false;
						System.out.println("Required option not found");
						break;
					}

				}
			} else if (count == 0) {
				System.out.println("As expected verified that no option is vibile for the text: " + wanted);
				flag = true;
			}
		} else {
			System.out.println("Step failed because expected count of options are not available");
		}

		test2_ClickMe.click();
		return flag;
	}

	public boolean serach_Table_Example(String country, int rows_Count) throws InterruptedException {

		Test_Utils.sync(2000);
		boolean flag = false;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0]. scrollIntoView(true);", table_SearchField);
		table_SearchField.clear();
		table_SearchField.sendKeys(Keys.ENTER);
		if (country != null)
			table_SearchField.sendKeys(country);
		Test_Utils.sync(1000);

		List<WebElement> wE = driver
				.findElements(By.xpath("//tbody[@id='someTable']//tr[not(@style='display: none;')]"));

		if (wE.size() == rows_Count) {

			if (country != null) {

				for (WebElement w : wE) {
					if (w.findElement(By.xpath("//td[3]")).getText().trim().equals(country))
						flag = true;
					else {
						flag = false;
						System.out
								.println("Step failed because expected row with Country as: " + country + " not found");
						break;
					}
				}
			} else
				flag = true;

		}

		return flag;
	}

	public boolean verify_DragandDrop() throws AWTException, InterruptedException {

		boolean flag = false;
		Test_Utils.sync(3000);
		Test_Utils.JS_ScrollIntoView(drag_From);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("function createEvent(typeOfEvent) {\n" + "var event =document.createEvent(\"CustomEvent\");\n"
				+ "event.initCustomEvent(typeOfEvent,true, true, null);\n" + "event.dataTransfer = {\n" + "data: {},\n"
				+ "setData: function (key, value) {\n" + "this.data[key] = value;\n" + "},\n"
				+ "getData: function (key) {\n" + "return this.data[key];\n" + "}\n" + "};\n" + "return event;\n"
				+ "}\n" + "\n" + "function dispatchEvent(element, event,transferData) {\n"
				+ "if (transferData !== undefined) {\n" + "event.dataTransfer = transferData;\n" + "}\n"
				+ "if (element.dispatchEvent) {\n" + "element.dispatchEvent(event);\n"
				+ "} else if (element.fireEvent) {\n" + "element.fireEvent(\"on\" + event.type, event);\n" + "}\n"
				+ "}\n" + "\n" + "function simulateHTML5DragAndDrop(element, destination) {\n"
				+ "var dragStartEvent =createEvent('dragstart');\n" + "dispatchEvent(element, dragStartEvent);\n"
				+ "var dropEvent = createEvent('drop');\n"
				+ "dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);\n"
				+ "var dragEndEvent = createEvent('dragend');\n"
				+ "dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);\n" + "}\n" + "\n"
				+ "var source = arguments[0];\n" + "var destination = arguments[1];\n"
				+ "simulateHTML5DragAndDrop(source,destination);", drag_From, drop_At);

		List<WebElement> isElementAvail_Check = driver
				.findElements((By.xpath("//br//following-sibling::button[@id='drag1']")));

		if (isElementAvail_Check.size() == 0)
			return flag = true;

		return flag;

	}
}
