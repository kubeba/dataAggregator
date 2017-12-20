import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IOOperations {

	private static final String FILE_NAME = "/Users/Shared/testData/checkedVehicles.xlsx";

	static void writeArrayListToExcel(final String file_name, ArrayList<CarDealerSelector> finalJavaObject,
			String nameSheet) throws EncryptedDocumentException, IOException {
		XSSFWorkbook workbook = null;
		XSSFSheet sheet = null;
		int lastRow = 0;
		File excelFile = new File(file_name);
		if (excelFile.exists()) {
			try {
				FileInputStream inputStream = new FileInputStream(new File(file_name));
				workbook = (XSSFWorkbook) WorkbookFactory.create(inputStream);
				if (workbook.getSheet(nameSheet) == null) {
					sheet = workbook.createSheet(nameSheet);
				} else {
					sheet = workbook.getSheet(nameSheet);
					lastRow = sheet.getPhysicalNumberOfRows();
				}
			} catch (InvalidFormatException e) {
				e.printStackTrace();
			}
		} else {
			workbook = new XSSFWorkbook();
			sheet = workbook.createSheet(nameSheet);
		}
		
		int rowNum = lastRow;

		for (CarDealerSelector objectFromList : finalJavaObject) {

			Row row = sheet.createRow(rowNum++);
			int colNum = 0;
			Cell cell;
			cell = row.createCell(colNum++);
			if (objectFromList.getDealerName() instanceof String) {
				cell.setCellValue(objectFromList.getDealerName());
			}
			cell = row.createCell(colNum++);
			if (objectFromList.getDealerZip() instanceof String) {
				cell.setCellValue(objectFromList.getDealerZip());
			}
			cell = row.createCell(colNum++);
			if (objectFromList.getDealerCity() instanceof String) {
				cell.setCellValue(objectFromList.getDealerCity());
			}
			cell = row.createCell(colNum++);
			if (objectFromList.getDealerKey() instanceof String) {
				cell.setCellValue(objectFromList.getDealerKey());
			}
			cell = row.createCell(colNum++);
			if (objectFromList.getDealerHref() instanceof String) {
				cell.setCellValue(objectFromList.getDealerHref());
			}
			cell = row.createCell(colNum++);
			if (objectFromList.getCarId() instanceof String) {
				cell.setCellValue(objectFromList.getCarId());
			}
			cell = row.createCell(colNum++);
			if (objectFromList.getCommissionId() instanceof String) {
				cell.setCellValue(objectFromList.getCommissionId());
			}
			cell = row.createCell(colNum++);
			if (objectFromList.getVin() instanceof String) {
				cell.setCellValue(objectFromList.getVin());
			}
			cell = row.createCell(colNum++);
			if (objectFromList.getBuyOnline() instanceof String) {
				cell.setCellValue(objectFromList.getBuyOnline());
			}
			cell = row.createCell(colNum++);
			if (objectFromList.getCarDealerUrl() instanceof String) {
				cell.setCellValue(objectFromList.getCarDealerUrl());
			} 
			cell = row.createCell(colNum++);
			if (objectFromList.getLinkToCarById() instanceof String) {
				cell.setCellValue(objectFromList.getLinkToCarById());
			} 
			cell = row.createCell(colNum++);
			if (objectFromList.getFirstUsage() instanceof String) {
				cell.setCellValue(objectFromList.getFirstUsage());
			} 
			cell = row.createCell(colNum++);
			if (objectFromList.getPreuse() instanceof String) {
				cell.setCellValue(objectFromList.getPreuse());
			} else {
				cell.setCellValue("Non-string input");
				System.out.println("somehow some non-string value appeared.");
			}
		}

		try {
			FileOutputStream outputStream = new FileOutputStream(file_name);
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}

	public static List<String> readFromExcel() {

		List<String> carsChecked = new ArrayList<String>();
		try {

			FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Iterator<Row> iterator = datatypeSheet.iterator();

			while (iterator.hasNext()) {

				Row currentRow = iterator.next();
				Iterator<Cell> cellIterator = currentRow.iterator();

				while (cellIterator.hasNext()) {

					Cell currentCell = cellIterator.next();
					if (currentCell.getStringCellValue() instanceof String) {
						carsChecked.add(currentCell.getStringCellValue());
					} else {
						carsChecked.add("Non-String value");
					}

				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return carsChecked;
	}
}
