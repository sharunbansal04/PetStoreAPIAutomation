package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {

	public static String workBookPath = System.getProperty("user.dir") + "//src//test//resources//UserTestData.xlsx";

	/**
	 * This method will read and return String array containing all data from sheet
	 * 
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String[][] getAllData(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(workBookPath);

		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum(); // counts from 0
		int colCount = sheet.getRow(1).getLastCellNum(); // counts from 1

		String[][] userData = new String[rowCount][colCount];
		DataFormatter formatter = new DataFormatter();

		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				userData[i][j] = formatter.formatCellValue(sheet.getRow(i + 1).getCell(j));
			}
		}
		wb.close();
		fis.close();
		return userData;

	}

	/**
	 * This method will read and returns String array containing all values in a
	 * particular cell
	 * 
	 * @param sheetName
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String[] getCellValues(String sheetName, int cellNumber) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(workBookPath);

		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum(); // counts from 0

		String[] userData = new String[rowCount];

		for (int i = 0; i < rowCount; i++) {
			userData[i] = sheet.getRow(i + 1).getCell(cellNumber).getStringCellValue();
		}
		wb.close();
		fis.close();
		return userData;

	}

	public int getRowCount(String sheetName) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream(workBookPath);

		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(sheetName);

		int rowCount = sheet.getLastRowNum(); // counts from 0

		wb.close();
		fis.close();
		return rowCount;

	}

}
