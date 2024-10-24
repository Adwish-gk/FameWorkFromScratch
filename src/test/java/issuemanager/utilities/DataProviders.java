package issuemanager.utilities;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;

public class DataProviders 
{
@DataProvider(name="LoginData")
public String[][] getData() throws InvalidFormatException, IOException
{
String path="C:\\Users\\DELL\\eclipse-workspace\\FrameWorkFromScratch\\testdata\\ExcelDatareading.xlsx";
ExcelDatareading xlUtil=new ExcelDatareading(path);
int rows=xlUtil.getRowCount("Sheet1");
int cols=xlUtil.getCellCount("Sheet1", 1);
String loginData[][]=new String[rows][cols];
for(int i=1;i<=rows;i++) 
{
for(int j=0;j<cols;j++) 
{
	loginData[i-1][j]=xlUtil.getCellData("Sheet1",i,j);
}	

}
return loginData;

}
}
