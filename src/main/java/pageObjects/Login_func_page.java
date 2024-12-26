package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_func_page {

	public Login_func_page(WebDriver driver) {
	PageFactory.initElements(driver, this);
}
	@FindBy(xpath = "(//a[@class='nav-links'])[2]")
	public WebElement clickloginEle;
	
	@FindBy(id = "email")
	public WebElement enterEmailid;
	
	@FindBy(id = "pass")
	public WebElement enterPsword;
	
	@FindBy(xpath = "//*[@id = 'loginSubmit']")
	public WebElement submitloginBtn;
	
	@FindBy(xpath = "(//*[text()='arkranees'])[2]")
	public WebElement clickuserProfile;
	
	@FindBy(xpath = "//p[text()='Log Out']")
	public WebElement clicklogoutEle;
	
	
	
}
