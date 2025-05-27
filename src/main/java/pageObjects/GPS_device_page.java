package pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GPS_device_page {
	
	public GPS_device_page(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[text() = 'GPS Devices']")
	public WebElement navigategpsPage;
	
	@FindBy(xpath = "//*[text() = 'Add Device']")
	public WebElement visibleadd_devicebtnEle;
	
	@FindBy(xpath = "//*[text() = 'Add Device']")
	public WebElement clickaddeviceBtn;
	
	@FindBy(xpath = "//*[@class='input_container']")
	public List<WebElement> printelementsInfo;
	
	@FindBy(xpath = "//*[text() = 'Cancel']")
	public WebElement clickcancelBtn;

}
