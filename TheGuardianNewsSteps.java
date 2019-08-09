package com.cucumber.framework.theGuardian.stepdefinition;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.cucumber.framework.PageObject.TheGuardianNewsPage;
import com.cucumber.framework.helper.Logger.LoggerHelper;
import com.cucumber.framework.helper.TestBase.TestBase;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TheGuardianNewsSteps {
	private final Logger log = LoggerHelper.getLogger(TheGuardianNewsSteps.class);
	TheGuardianNewsPage theGuardianNews;
	String newsText;
	Boolean flag;
	@Given("^user navigates to application$")
	public void user_navigates_to_application() throws Throwable {
		log.info("User navigates to the Guardian Application");
		theGuardianNews = new TheGuardianNewsPage(TestBase.driver);
	}

	@When("^user gets the text of first news$")
	public void user_gets_the_text_of_first_news() throws Throwable {
		log.info("User retrieves the link of first news from the Guardian Webpage.");
		newsText = theGuardianNews.returnNewsText();
		log.info("First news : " + newsText);
	}

	@Then("^navigate to google to validate the news text$")
	public void navigate_to_google_to_validate_the_news_text() throws Throwable {
		Boolean flag=theGuardianNews.verifyNewsOnGoogle(newsText);
		log.info(flag);
		Assert.assertTrue(flag);
		log.info("Asserting that news is valid!");
		
	}
	
	@Then("^navigate to Times of India to validate the news text$")
	public void navigate_to_Times_of_India_to_validate_the_news_text() throws Throwable {
		Boolean flag=theGuardianNews.verifyNewsOnTimesOfIndia(newsText);
		log.info(flag);
		Assert.assertTrue(flag);
		log.info("Asserting that news is valid!");
		
	}


	

}
