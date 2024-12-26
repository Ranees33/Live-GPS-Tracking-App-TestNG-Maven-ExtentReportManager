package testCases;

import java.io.File;
import java.io.IOException;

import org.testng.annotations.DataProvider;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Excel_data_code {
	
	String[][] data = null;
	
	@DataProvider(name="logintestData1")
	public Object[][] loginfirsttestDataProvider() throws BiffException, IOException {
		
		data = readfirstExcelData();
		return data;
	}

	public String[][] readfirstExcelData() throws BiffException, IOException {

		// Load the Excel File
		File excelData = new File("C:\\Users\\Ranees\\Desktop\\test Data login scenario for VM Tracker app.xls");
		Workbook workbook = Workbook.getWorkbook(excelData);
		Sheet sheet = workbook.getSheet(0);

		int rowCount = sheet.getRows();
		int columnCount = sheet.getColumns();

		String[][] loginData = new String[rowCount - 1][columnCount];

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				loginData[i - 1][j] = sheet.getCell(j, i).getContents();

			}

		}

		return loginData;
	}
	
	

	@DataProvider(name = "logintestData2")
	public Object[][] loginsecondtestDataProvider() throws BiffException, IOException {

		data = readsecondExcelData();
		return data;
	}

	public String[][] readsecondExcelData() throws BiffException, IOException {

		// Load the Excel File
		File excelData = new File("C:\\Users\\Ranees\\Desktop\\test Data login scenario for VM Tracker app.xls");
		Workbook workbook = Workbook.getWorkbook(excelData);
		Sheet sheet = workbook.getSheet(1);

		int rowCount = sheet.getRows();
		int columnCount = sheet.getColumns();

		String[][] loginData = new String[rowCount - 1][columnCount];

		for (int i = 1; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				loginData[i - 1][j] = sheet.getCell(j, i).getContents();

			}

		}

		return loginData;
	}

}
