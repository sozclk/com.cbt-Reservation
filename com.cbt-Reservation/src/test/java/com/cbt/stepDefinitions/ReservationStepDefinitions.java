package com.cbt.stepDefinitions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cbt.pages.*;
import com.cbt.utilities.ConfigReader;
import com.cbt.utilities.Driver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ReservationStepDefinitions {
	
	WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5000);

//	@Given("a teacher is signed in with valid username {string} and password {string}")
//	public void a_teacher_is_signed_in_with_valid_username_and_password(String username, String password) {
//		
//	}

	@Given("a teacher is signed in with valid username and password")
	public void a_teacher_is_signed_in_with_valid_username_and_password() {
		SignInPage signinPage = new SignInPage();
		  signinPage.signInEmail.sendKeys(ConfigReader.getProperties("teacher_username"));
		  signinPage.signInPassword.sendKeys(ConfigReader.getProperties("teacher_password") + Keys.ENTER);
	}
	
	@When("a teacher clicks on hunt button")
	public void a_teacher_clicks_on_hunt_button() {
	   
	}

	@Then("the teacher should be able to reserve a room")
	public void the_teacher_should_be_able_to_reserve_a_room() {
	   HuntPage huntPage = new HuntPage();
	   //Select select = new Select(huntPage.date);
	  
	   
	}
}
