package issuemanager.page_objec_tmodel;

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
	@FindBy(id="form2") private WebElement username;
	@FindBy(name="password") private WebElement password;
	@FindBy(xpath="(//button[@type=\"submit\"])[1]") private WebElement signinbtn;
	
	public void setusername(String loginusername) 
	{
		username.sendKeys(loginusername);
	}
	public void setpassword(String loginpassword) 
	{
		password.sendKeys(loginpassword);
	}
	public void cclick()
	{
		signinbtn.click();
	}
}
