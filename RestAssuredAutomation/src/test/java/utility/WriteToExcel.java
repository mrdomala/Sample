//package utility;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//public class WriteToExcel {
//
//	public void writetoexcel(String apiname, String api, int statuscode, String sec, String date, String time)
//			throws IOException {
//		String path = ".//TestData/Api_Details.xlsx";
//
//		FileInputStream fis = new FileInputStream(path);
//
//		Workbook workbook = new XSSFWorkbook(fis);
//
//		Sheet sheet = workbook.getSheetAt(0);
//		int lastRow = sheet.getLastRowNum();
//
//		sheet.createRow(lastRow + 1).createCell(0).setCellValue(apiname);
//		sheet.getRow(lastRow + 1).createCell(1).setCellValue(api);
//		sheet.getRow(lastRow + 1).createCell(2).setCellValue(statuscode);
//		sheet.getRow(lastRow + 1).createCell(3).setCellValue(sec);
//		sheet.getRow(lastRow + 1).createCell(4).setCellValue(date);
//		sheet.getRow(lastRow + 1).createCell(5).setCellValue(time);
//
//		FileOutputStream fos = new FileOutputStream(path);
//
//		workbook.write(fos);
//
//		fos.close();
//	}
//
//}

package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteToExcel {

	public void writetoexcel(String apiname, String api, int statuscode, String sec, String date, String time)
			throws IOException {
		String path = ".//TestData/Api_Details.xlsx";

		FileInputStream fis = new FileInputStream(path);

		@SuppressWarnings("resource")
		Workbook workbook = new XSSFWorkbook(fis);

		Sheet sheet = workbook.getSheetAt(0);
		int lastRow = sheet.getLastRowNum();

		sheet.createRow(lastRow + 1).createCell(0).setCellValue(apiname);
		sheet.getRow(lastRow + 1).createCell(1).setCellValue(api);
		sheet.getRow(lastRow + 1).createCell(2).setCellValue(statuscode);
		sheet.getRow(lastRow + 1).createCell(3).setCellValue(sec);
		sheet.getRow(lastRow + 1).createCell(4).setCellValue(date);
		sheet.getRow(lastRow + 1).createCell(5).setCellValue(time);

		FileOutputStream fos = new FileOutputStream(path);

		workbook.write(fos);

		fos.close();
	}

}
