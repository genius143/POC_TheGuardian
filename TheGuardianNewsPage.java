package com.cucumber.framework.PageObject;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.cucumber.framework.configreader.ObjectRepo;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.Wait.WaitHelper;

public class TheGuardianNewsPage {
	String newsLink;
	String newsText;
	String googleURL="https://www.google.com/";
	String TimesOfIndiaURL="https://timesofindia.indiatimes.com/";
	
	WebDriver driver;
	private final Logger log = LoggerHelper.getLogger(TheGuardianNewsPage.class);
	WaitHelper waitHelper;

	@FindBy(xpath = "//time[@class='fc-date-headline']")
	WebElement newsSection;

	@FindBy(xpath = "//time[@class='fc-date-headline']//following::h3[1]/a/span[2]/span")
	WebElement firstNewsText;

	@FindBy(xpath = "//time[@class='fc-date-headline']//following::h3[1]/a")
	WebElement firstNewsLink;

	@FindBy(name = "q")
	WebElement submitButton;
	
	@FindBy(xpath = "//*[contains(text(),'OK with that')]")
	WebElement okWithThatButton;
	
	

	public TheGuardianNewsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		driver.get(ObjectRepo.reader.getWebsite());
		waitHelper.waitForElement(driver, newsSection, ObjectRepo.reader.getExplicitWait());
	}

	public String returnNewsText() {
		log.info("Getting text of first news to be validated in Google");
		if(okWithThatButton.isDisplayed()){
			okWithThatButton.click();
		}
		waitHelper.waitForElement(driver, firstNewsLink, ObjectRepo.reader.getExplicitWait());
		newsLink = firstNewsLink.getAttribute("href");
		//newsText = firstNewsText.getText();
		log.info("News Text is : " + newsLink);
		return newsLink;
	}
	
/* 
 * Functionality to validate news on Google.com
 * 
 */
	public Boolean verifyNewsOnGoogle(String newsLink) {
		boolean result = false;
		log.info("Getting text of first news to be validated in Google");
		driver.get(googleURL);
		driver.navigate().to(googleURL);
		WebElement submit_button = driver.findElement(By.name("q"));
		submit_button.sendKeys(newsLink);
		submit_button.submit();
		List<WebElement> my_list = driver.findElements(By.tagName("a"));
		System.out.println("The list of links are : ");
		for (WebElement element : my_list) {

			System.out.println(element.getAttribute("href"));

			if (element.getAttribute("href") != null && (element.getAttribute("href").contains(newsLink))) {
				log.info("News Found on Google!");
				result = true;
				
				break;
				

			}
		}
		return result;
	}
	
	/* 
	 * Functionality to validate news on Times of India site
	 * 
	 */
		public Boolean verifyNewsOnTimesOfIndia(String newsLink) {
			boolean result = false;
			log.info("Getting text of first news to be validated in Google");
			driver.get(TimesOfIndiaURL);
			driver.navigate().to(TimesOfIndiaURL);
			WebElement search_button = driver.findElement(By.xpath("//*[text()=' + ']"));
			search_button.click();
			WebElement search_bar = driver.findElement(By.xpath("//*input[@name='query']"));
			
		search_bar.sendKeys(newsLink);
			search_bar.submit();
			List<WebElement> my_list = driver.findElements(By.tagName("a"));
			System.out.println("The list of links are : ");
			for (WebElement element : my_list) {

				System.out.println(element.getAttribute("href"));

				if (element.getAttribute("href") != null && (element.getAttribute("href").contains(newsLink))) {
					log.info("News Found on Google!");
					result = true;					
					break;				

				}
			}
			return result;
		}
}
