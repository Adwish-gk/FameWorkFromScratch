package issuemanagertestcases;

import org.testng.annotations.Test;

import issuemanager.page_objec_tmodel.VmsStagingLoginPageObjects;
import issuemanager.utilities.DataProviders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginPageTestCases extends BaseClass 
{
	 @Test(dataProvider ="LoginData",dataProviderClass =DataProviders.class)
	public void login(String name,String number) throws InterruptedException
	{
	driver.get("https://vms.vapstech.com/#/login/");
		
		logger.info("url opened");
		Thread.sleep(4000);
		
		VmsStagingLoginPageObjects login=new VmsStagingLoginPageObjects(driver);
		
		login.setusername(name);
		logger.info("username entered");
		
		login.setpassword(number);
		logger.info("Password entered");
		
	login.cclick();
	Thread.sleep(4000);
	}

}
