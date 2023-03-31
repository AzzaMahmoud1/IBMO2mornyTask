package testData;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;

import base.TestBase;

public class TestData extends TestBase{
	 private static final String EXCEL_FILE_PATH = "path/to/excel/file.xlsx";
	    private static By[] byElements;

	    public static void readExcelFile() throws IOException {
	        FileInputStream inputStream = new FileInputStream(EXCEL_FILE_PATH);
	        Workbook workbook = new XSSFWorkbook(inputStream);
	        Sheet sheet = workbook.getSheetAt(0);

	        int numberOfRows = sheet.getLastRowNum() + 1;
	        String[][] elements = new String[numberOfRows][2];
	        for (int i = 0; i < numberOfRows; i++) {
	            Row row = sheet.getRow(i);
	            Cell firstCell = row.getCell(0);
	            Cell secondCell = row.getCell(1);
	            if (firstCell != null && secondCell != null) {
	                elements[i][0] = firstCell.getStringCellValue();
	                elements[i][1] = secondCell.getStringCellValue();
	            }
	        }

	        workbook.close();
	        inputStream.close();

	        byElements = new By[elements.length];
	        for (int i = 1; i < elements.length; i++) {
	            byElements[i] = By.xpath(elements[i][1]);
	        }
	    }

	    public static By[] getByElements() {
	        return byElements;
	    }
	}
