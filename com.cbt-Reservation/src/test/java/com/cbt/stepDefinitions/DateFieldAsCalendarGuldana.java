package com.cbt.stepDefinitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cbt.pages.HuntPage;
import com.cbt.pages.MapPage;
import com.cbt.pages.SignInPage;
import com.cbt.utilities.ConfigReader;
import com.cbt.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class DateFieldAsCalendarGuldana {
	
	
	SignInPage signinPage = new SignInPage();
	MapPage mapPage = new MapPage();
	HuntPage huntPage = new HuntPage();
	WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
	
	
	@Given("user on the main page")
	public void user_on_the_main_page() {
		//Driver.getDriver().get("url");
		signinPage.signInEmail.sendKeys(ConfigReader.getProperties("leader_username"));
		signinPage.signInPassword.sendKeys(ConfigReader.getProperties("leader_password") +Keys.ENTER);
		//signinPage.signInButton.click();
	}

	@When("user clicks on the hunt button")
	public void user_clicks_on_the_hunt_button() {
	   wait.until(ExpectedConditions.elementToBeClickable(mapPage.huntMenuButton));
		 mapPage.huntMenuButton.click();
		
	}

	@Then("hunt for spot page opens")
	public void hunt_for_spot_page_opens() {
	    huntPage.date.isDisplayed();
	}

	@Then("user clicks on dropdown button on the date box")
	public void user_clicks_on_dropdown_button_on_the_date_box() {
		Select select = new Select(huntPage.date);
		huntPage.date.click();
	}

	@Then("user should see the calendar")
	public void user_should_see_the_calendar() {
		Select select = new Select(huntPage.date);
		huntPage.date.isDisplayed();
	}

}
