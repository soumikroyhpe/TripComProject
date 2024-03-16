package com.poc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import lombok.val;

public class ExcelUtils {

	private String path;
	private File file;
	private FileInputStream fis;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private final Map<String, Integer> map= new HashMap<String, Integer>();

	public ExcelUtils(String path) {
		this.path = path;
	}

	public ConcurrentHashMap<String, String> fetchTestDataMap(ConcurrentHashMap<String, String> dataMap,
			String sheetName, String tcName) throws IOException {
		sheetName = sheetName.replaceAll("$", "");
		dataMap = getCommonData(sheetName, tcName);

		try {
			if (null == dataMap || dataMap.isEmpty()) {
				System.err.println(tcName + " : TestName is not found; please check the datasheet.");
				Assert.fail("Exception while fetching TestData");
			} else {

			}

		} catch (Exception e) {
			System.out.println("Inside datamap null block");
		}

		return dataMap;

	}

	public ConcurrentHashMap<String, String> getCommonData(String sheetName, String tcName)
			throws IOException {

		file = new File(path);
		
		
		ConcurrentHashMap<String, String> dataval = new ConcurrentHashMap<String, String>();

		try {

			fis = new FileInputStream(file);
			workbook = (XSSFWorkbook) WorkbookFactory.create(fis);
			sheet = workbook.getSheet(sheetName);

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				
				if (sheet.getRow(i).getCell(0).getStringCellValue().equalsIgnoreCase(tcName)) {
					
					for (int j = 0; j <= sheet.getRow(i).getLastCellNum(); j++) {
						
						String key = sheet.getRow(0).getCell(j).getStringCellValue().trim();
						
						String value = "";
						
						
						try {
							Cell cell = sheet.getRow(i).getCell(j);

							if (cell.getCellType() == CellType.NUMERIC) {

								DataFormatter dataFormatter = new DataFormatter();
								value = dataFormatter.formatCellValue(cell);
								
							} else if (cell.getCellType() == CellType.STRING) {
								value = cell.getStringCellValue();

							}

						} catch (Exception e) {

						}
						dataval.put(key, value);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Could not find the datasheet. Please verify!! ");
			System.exit(0);

		} catch (Exception e) {

		}
		workbook.close();
		fis.close();

		return dataval;

	}
	
	
	public void printMap(ConcurrentHashMap<String, String> dataMap) {

		System.out.println("\n----------Print Map------------");
		for (Map.Entry<String, String> set : dataMap.entrySet()) {

			System.out.println(set.getKey() + ":" + set.getValue());
		}
		System.out.println("------------------------------\n");
	}

}
