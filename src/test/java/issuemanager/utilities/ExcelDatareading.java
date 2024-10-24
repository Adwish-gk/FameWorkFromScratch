package issuemanager.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDatareading {
	FileInputStream fi;
	FileOutputStream fo;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	String path;
	ExcelDatareading(String path) 
	{
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws InvalidFormatException, IOException 
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
				sheet=workbook.getSheet(sheetName);
				int rowcount=sheet.getLastRowNum();
				workbook.close();
				fi.close();
		return rowcount;
	}
	public int getCellCount(String sheetName,int rownum) throws InvalidFormatException, IOException 
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
				sheet=workbook.getSheet(sheetName);
				row=sheet.getRow(rownum);
				int cellCount=row.getLastCellNum();
				workbook.close();
				fi.close();
		return cellCount;
	}
	public String getCellData(String sheetName,int rownum,int column) throws InvalidFormatException, IOException 
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
				sheet=workbook.getSheet(sheetName);
				row=sheet.getRow(rownum);
				  cell=row.getCell(column);
				  DataFormatter format=new DataFormatter();
				  String data;
				  try {
				  data=format.formatCellValue(cell);
				  }catch(Exception e) 
				  {
					  data=" ";
				  }
				
				workbook.close();
				fi.close();
		return data;
	}
	

}
