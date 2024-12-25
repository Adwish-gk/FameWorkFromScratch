package issuemanagertestcases;

import org.testng.annotations.Test;

import issuemanager.page_objec_tmodel.VmsStagingLoginPageObjects;
import issuemanager.utilities.DataProviders;

import static org.testng.Assert.fail;

import java.util.List;
import java.util.Scanner;

import javax.security.auth.spi.LoginModule;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;


public class LoginPageTestCases extends BaseClass 
{
	//dataProvider ="LoginData",dataProviderClass =DataProviders.class
	
	
	
	@Test()
	public void login() throws InterruptedException
	{
	driver.get("https://app.readyassist.in/book-service");
		
		logger.info("url opened");
		VmsStagingLoginPageObjects login=new VmsStagingLoginPageObjects(driver);
		GeneralClass genric=new GeneralClass(driver);
		System.out.println("done");
		genric.waitForVisibility(login.mobilenumbertxtbox, 10);
		login.setNumber("8073750772");
		logger.info("Mobile number Entered");
		login.clickproceed();
		logger.info("OTP sent");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the OTP received: ");
		String otp = scanner.next(); // Read OTP as a string
		scanner.close();  // Close the scanner after reading input

		// Assert OTP length is 4 digits
		assert otp.length() == 4 : "Invalid OTP! OTP should be exactly 4 digits.";
		        

		List<WebElement>otpp= login.otptextfield;
		 int i=0;
		        for(WebElement e:otpp) 
		        {
		        	e.sendKeys(String.valueOf(otp.charAt(i)));
		        	i++;
		       }
		        Thread.sleep(4000);
		        
		        genric.waitForVisibility(login.verifyNow, 10);
		        
		        login.clickverifyNowbtn();
		        
		        

		        
		    }


        } 
        
