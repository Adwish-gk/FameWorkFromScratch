  package issuemanager.page_objec_tmodel;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VmsStagingLoginPageObjects 
{
	WebDriver vdriver;
	
	public VmsStagingLoginPageObjects(WebDriver driver) 
	{
		
		vdriver=driver;
		PageFactory.initElements(vdriver,this);
	}
	//@FindBy(id="landing_bookbtn") private WebElement BookServicebtn;
	@FindBy(xpath="/html/body/app-root/app-order-book-page/div/div/div[1]/app-book-service-v2/div/div/div/div/div/div/div[3]/input") public WebElement mobilenumbertxtbox;
	@FindBy(xpath="/html/body/app-root/app-order-book-page/div/div/div[1]/app-book-service-v2/div/div/div/div/div/div/button") private WebElement Proceedbtn;
	@FindBy(xpath="/html/body/app-root/app-order-book-page/div/div/div[1]/app-book-service-v2/div/div/div/div/div/div[3]/input") public List<WebElement> otptextfield;
	@FindBy(xpath="/html/body/app-root/app-order-book-page/div/div/div[1]/app-book-service-v2/div/div/div/div/div/button") public WebElement verifyNow;
	
	public void setNumber(String number) 
	{
	    mobilenumbertxtbox.sendKeys(number); // Convert int to String
	    
	}
   public void clickproceed()
	{
		Proceedbtn.click();
	}
   
   public void clickverifyNowbtn() 
	{
	   verifyNow.click();
	}
}
  
