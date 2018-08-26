package com.cbt.stepDefinitions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cbt.pages.BookingConfirmationPage;
import com.cbt.pages.FreeSpotsPage;
import com.cbt.pages.HuntPage;
import com.cbt.pages.MapPage;
import com.cbt.pages.SchedulePage;
import com.cbt.pages.SignInPage;
import com.cbt.utilities.ConfigReader;
import com.cbt.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CancellationOfReservedRoomGuldana {
	
	
	SignInPage signinPage = new SignInPage();
	MapPage mapPage = new MapPage();
	HuntPage huntPage = new HuntPage();
	WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);
	FreeSpotsPage freeSpotPage = new FreeSpotsPage(); 
	SchedulePage schedulePage = new SchedulePage();
	
	WebElement room = Driver.getDriver().findElement(By.xpath("//p[@class='title is-size-4']"));
	String roomName = room.getText();
	
	@Given("user on reservation page")
	public void user_on_reservation_page() {
		signinPage.signInEmail.sendKeys(ConfigReader.getProperties("leader_username"));
		signinPage.signInPassword.sendKeys(ConfigReader.getProperties("leader_password") +Keys.ENTER);
	}

	@When("user clicks on reservation box")
	public void user_clicks_on_reservation_box() {
	  
		wait.until(ExpectedConditions.elementToBeClickable(mapPage.googleRoom));
		mapPage.huntMenuButton.click();
		Select select = new Select(huntPage.from);
		huntPage.date.click();
		huntPage.date.sendKeys("27"); // three days later
		select.selectByVisibleText("7:00am");
		select = new Select(huntPage.to);
		select.selectByIndex(3);
		huntPage.search.click();
		wait.until(ExpectedConditions.elementToBeClickable(freeSpotPage.firstBookButton));
		room = Driver.getDriver().findElement(By.xpath("//p[@class='title is-size-4']"));
		roomName = room.getText();
		freeSpotPage.firstBookButton.click();
		
	}

	@Then("user should able to see pop up window")
	public void user_should_able_to_see_pop_up_window() {
		BookingConfirmationPage confirmPage = new BookingConfirmationPage();
		wait.until(ExpectedConditions.elementToBeClickable(confirmPage.confirmButton));
		confirmPage.confirmButton.click();
		System.out.println("you have booked " + roomName);
		String xpath = "/html/body/app-root/div/app-my-schedule-page/div/div/div/div/app-weekly-schedule/div/table/tbody/tr[1]/td[1]";
		WebElement firstSlot = Driver.getDriver().findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(firstSlot));
		
		
	}

	@Then("user should able to click on cancel conference button")
	public void user_should_able_to_click_on_cancel_conference_button() {
		schedulePage.cancelConferenceBtn.click(); 
		//added comments
	}
}
