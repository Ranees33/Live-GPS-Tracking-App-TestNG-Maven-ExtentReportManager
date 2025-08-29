package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Subscription_page {
	
	public Subscription_page(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "(//*[@class=\"subscription_btn__8DjFz  \"])[1]")
	public WebElement premiumBuyNowButton;

	@FindBy(className = "subscription_icon")
	public WebElement viewSubscription;
	

}
