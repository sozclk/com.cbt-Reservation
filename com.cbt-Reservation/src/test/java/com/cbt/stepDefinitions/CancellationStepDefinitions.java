package com.cbt.stepDefinitions;

import static org.junit.Assert.assertFalse;

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

public class CancellationStepDefinitions {
	
	SignInPage signinPage = new SignInPage();
	MapPage mapPage = new MapPage();
	HuntPage huntPage = new HuntPage();
	FreeSpotsPage freeSpotPage = new FreeSpotsPage();
	SchedulePage schedulePage = new SchedulePage();

	WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);

	@Given("a team leader deletes the reservation")
	public void a_team_leader_deletes_the_reservation() throws InterruptedException {
		signinPage.signInEmail.sendKeys(ConfigReader.getProperties("leader_username"));
		signinPage.signInPassword.sendKeys(ConfigReader.getProperties("leader_password") + Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(mapPage.googleRoom));
		mapPage.huntMenuButton.click();
		Select select = new Select(huntPage.from);
		huntPage.date.click();
		huntPage.date.sendKeys("19"); // three days later
		select.selectByVisibleText("7:00am");
		select = new Select(huntPage.to);
		select.selectByIndex(3);
		huntPage.search.click();
		wait.until(ExpectedConditions.elementToBeClickable(freeSpotPage.firstBookButton));
		
		WebElement room = Driver.getDriver().findElement(By.xpath("//p[@class='title is-size-4']"));
		String roomName = room.getText();
		freeSpotPage.firstBookButton.click();
		BookingConfirmationPage confirmPage = new BookingConfirmationPage();
		wait.until(ExpectedConditions.elementToBeClickable(confirmPage.confirmButton));
		confirmPage.confirmButton.click();
		System.out.println("you have booked " + roomName);
		String xpath = "/html/body/app-root/div/app-my-schedule-page/div/div/div/div/app-weekly-schedule/div/table/tbody/tr[1]/td[1]";
		WebElement firstSlot = Driver.getDriver().findElement(By.xpath(xpath));
		wait.until(ExpectedConditions.visibilityOf(firstSlot));
		schedulePage.cancelConferenceButton.click();
		
	}

	@Then("the team leader should not be able to see the reservation")
	public void the_team_leader_should_not_be_able_to_see_the_reservation() {
		String xpath = "/html/body/app-root/div/app-my-schedule-page/div/div/div/div/app-weekly-schedule/div/table/tbody/tr[1]/td[1]";
		WebElement firstSlot = Driver.getDriver().findElement(By.xpath(xpath));
		assertFalse(firstSlot.isDisplayed());		
		Driver.closeDriver();
	}

	@Then("student from the same team should not be able to see the reservation")
	public void student_from_the_same_team_should_not_be_able_to_see_the_reservation() {
		signinPage.signInEmail.sendKeys(ConfigReader.getProperties("student_username"));
		signinPage.signInPassword.sendKeys(ConfigReader.getProperties("student_password") + Keys.ENTER);
		String xpath = "/html/body/app-root/div/app-my-schedule-page/div/div/div/div/app-weekly-schedule/div/table/tbody/tr[1]/td[1]";
		WebElement firstSlot = Driver.getDriver().findElement(By.xpath(xpath));
		mapPage.googleRoom.click();
		assertFalse(firstSlot.isDisplayed());
		Driver.closeDriver();
	}

	@Then("student from different team should not be able to see the reservation")
	public void student_from_different_team_should_not_be_able_to_see_the_reservation() {
		signinPage.signInEmail.sendKeys(ConfigReader.getProperties("student_other_username"));
		signinPage.signInPassword.sendKeys(ConfigReader.getProperties("student_other_password") + Keys.ENTER);
		String xpath = "/html/body/app-root/div/app-my-schedule-page/div/div/div/div/app-weekly-schedule/div/table/tbody/tr[1]/td[1]";
		WebElement firstSlot = Driver.getDriver().findElement(By.xpath(xpath));
		mapPage.googleRoom.click();
		assertFalse(firstSlot.isDisplayed());
		Driver.closeDriver();
	}
}
