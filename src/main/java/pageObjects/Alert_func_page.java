package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Alert_func_page {
	
	public Alert_func_page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[text() = 'Alerts']")
	public WebElement clickalertpageLink;
	
	@FindBy(xpath = "(//*[@id='dropDownSelect'])[1]")
	public WebElement alertypeleClick;
	
	@FindBy(xpath = "//*[@class='filterdropdown ']")
	public List<WebElement> alertypefuncEle;
	
	@FindBy(xpath = "//p[text()='Select Device']")
	public WebElement selectdeviceEle;
	
	@FindBy(xpath = "//*[@class='filterdropdown ']")
	public List<WebElement> selectdeviceleValues;
	
}
