package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard_func_page {

	public Dashboard_func_page(WebDriver driver) {
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
	
	@FindBy(xpath = "//*[@class=\"navbar_left\"]")
	public WebElement verifyprofileText;
	
	@FindBy(xpath = "(//p[text()='Live Tracking'])[1]")
	public WebElement clicklivetrackingMenu;
	
	@FindBy(xpath = "//*[text() = 'No Devices Found']")
	public WebElement verifynodevicefoundText;
	
}
	
	