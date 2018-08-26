package com.cbt.stepDefinitions;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.openqa.selenium.Keys;

import com.cbt.pages.MapPage;
import com.cbt.pages.SignInPage;
import com.cbt.utilities.ConfigReader;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class VisibiltyOfLocation {

	SignInPage signinPage = new SignInPage();
	MapPage mapPage = new MapPage();
	
	@Given("user is on the main page")
	public void user_is_on_the_main_page() {
		signinPage.signInEmail.sendKeys(ConfigReader.getProperties("leader_username"));
		signinPage.signInPassword.sendKeys(ConfigReader.getProperties("leader_password") +Keys.ENTER);
	   
	}

	@Then("user should be able to see the location on the top left")
	public void user_should_be_able_to_see_the_location_on_the_top_left() {
	   
		Assert.assertTrue(mapPage.locationBtn.isDisplayed());
	
	}
}
