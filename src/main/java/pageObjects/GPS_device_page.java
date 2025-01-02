package pageObjects;

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
	
	@FindBy(id = "AddDeviceIconButton")
	public WebElement visibleadd_devicebtnEle;

}
