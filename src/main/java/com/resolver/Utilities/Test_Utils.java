package com.resolver.Utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.resolver.TestBase.TestBase;

public class Test_Utils extends TestBase {

	@SuppressWarnings({ "rawtypes", "incomplete-switch", "unchecked" })
	public List Provide_Data(String Test_Name) throws IOException, InterruptedException {
		List data = new ArrayList();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\ashok\\eclipse-workspace-1\\Res_Test\\src\\main\\java\\com\\resolver\\TestData\\TestData_Resolver.xlsx");
		XSSFWorkbook wbk = new XSSFWorkbook(fis);
		XSSFSheet sheet = wbk.getSheetAt(0);

		Iterator row = sheet.iterator();
		while (row.hasNext()) {
			Row rowitr = (Row) row.next();

			Iterator cell = rowitr.cellIterator();
			while (cell.hasNext()) {
				Cell cellitr = (Cell) cell.next();

				switch (cellitr.getCellType()) {

				case STRING:
					data.add(cellitr.getStringCellValue());
					break;

				case NUMERIC:
					data.add(cellitr.getNumericCellValue());
					break;

				case BOOLEAN:
					data.add(cellitr.getBooleanCellValue());
					break;

				case FORMULA:
					data.add(cellitr.getArrayFormulaRange());
					break;
				}

			}
		}

		wbk.close();
		int start_Index = 0;
		int end_Index = 0;
		for (int i = 0; i <= data.size() - 1; i++) {
			if (data.get(i).equals(Test_Name)) {
				start_Index = i;

			} else if (data.get(i).equals(Test_Name + "_End")) {
				end_Index = i;
				break;
			}
		}

		data = data.subList(start_Index + 1, end_Index);
		return data;
	}

	public static void sync(int s) throws InterruptedException {
		Thread.sleep(s);
	}

	public static void JS_ScrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0]. scrollIntoView(true);", element);
	}
}
