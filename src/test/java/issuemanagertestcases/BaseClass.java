package issuemanagertestcases;



import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.logging.log4j.LogManager;

public class BaseClass 
{
	public static WebDriver driver;
	public static Logger logger;
	
	
	@BeforeClass
	@Parameters({"os","browser"})
	public void setup(String os,String br)
	{
		switch(br.toLowerCase())
		{
		case "chrome":driver=new ChromeDriver();break;
		case "firefox":driver=new FirefoxDriver();
		case "edge":driver=new EdgeDriver();
		default:System.out.print("Invalid Browser Name");return;
		
		}
		logger=LogManager.getLogger(this.getClass());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	@AfterClass
	public void tear()
	{
		//driver.close();
	}
	
	public String captureScreen(String name) throws IOException{
	{
		
		String time = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot take=(TakesScreenshot)driver;
		File sourceFile=take.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir"+".\\ToStoreScreenshots\\"+name+time+".png");
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile)	;
		return targetFilePath;
	}

}}
