package api.utilities;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider
	public String[][] getAllUserData() throws EncryptedDocumentException, IOException {
		ExcelFileUtility eUtil = new ExcelFileUtility();
		String[][] allData = eUtil.getAllData("UserModel");

		return allData;
	}

	@DataProvider
	public String[] getAllUsernames() throws EncryptedDocumentException, IOException {
		ExcelFileUtility eUtil = new ExcelFileUtility();
		String[] allUsernames = eUtil.getCellValues("UserModel", 1);

		return allUsernames;
	}

}
