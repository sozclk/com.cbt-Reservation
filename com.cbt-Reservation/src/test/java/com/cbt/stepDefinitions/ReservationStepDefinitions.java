package com.cbt.stepDefinitions;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
import com.cbt.pages.SignInPage;
import com.cbt.utilities.ConfigReader;
import com.cbt.utilities.Driver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ReservationStepDefinitions {
	SignInPage signinPage = new SignInPage();
	MapPage mapPage = new MapPage();
	HuntPage huntPage = new HuntPage();
	FreeSpotsPage freeSpotPage = new FreeSpotsPage();

	WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);

	@Given("a teacher is signed in with valid username and password")
	public void a_teacher_is_signed_in_with_valid_username_and_password() {
		signinPage.signInEmail.sendKeys(ConfigReader.getProperties("teacher_username"));
		signinPage.signInPassword.sendKeys(ConfigReader.getProperties("teacher_password") + Keys.ENTER);
	}

	@When("a teacher clicks on hunt button")
	public void a_teacher_clicks_on_hunt_button() {
		wait.until(ExpectedConditions.elementToBeClickable(mapPage.googleRoom));
		mapPage.huntMenuButton.click();
	}

	@Then("the teacher should be able to reserve a room")
	public void the_teacher_should_be_able_to_reserve_a_room() throws InterruptedException {
		Select select = new Select(huntPage.from);
		huntPage.date.click();
		huntPage.date.sendKeys("19"); // date for the day after tomorrow
		select.selectByVisibleText("10:00am");
		select = new Select(huntPage.to);
		select.selectByIndex(3);
		huntPage.search.click();
		wait.until(ExpectedConditions.elementToBeClickable(freeSpotPage.firstBookButton));
		freeSpotPage.firstBookButton.click();
		BookingConfirmationPage confirmPage = new BookingConfirmationPage();
		wait.until(ExpectedConditions.elementToBeClickable(confirmPage.confirmButton));
		confirmPage.confirmButton.click();
		WebElement grayBookedBox = Driver.getDriver().findElement(By.xpath(
				"/html/body/app-root/div/app-my-schedule-page/div/div/div/div/app-weekly-schedule/div/table/tbody/tr[7]/td[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(grayBookedBox));
		assertTrue(grayBookedBox.isDisplayed());
	}

	@Given("a team leader is signed in  with valid username and password")
	public void a_team_leader_is_signed_in_with_valid_username_and_password() {
		signinPage.signInEmail.sendKeys(ConfigReader.getProperties("leader_username"));
		signinPage.signInPassword.sendKeys(ConfigReader.getProperties("leader_password") + Keys.ENTER);
	}

	@When("a team leader clicks on hunt button")
	public void a_team_leader_clicks_on_hunt_button() {
		wait.until(ExpectedConditions.elementToBeClickable(mapPage.googleRoom));
		mapPage.huntMenuButton.click();
	}

	@Then("the team leader should be able to reserve a room")
	public void the_team_leader_should_be_able_to_reserve_a_room() {
		Select select = new Select(huntPage.from);
		huntPage.date.click();
		huntPage.date.sendKeys("20"); // three days later
		select.selectByVisibleText("1:00pm");
		select = new Select(huntPage.to);
		select.selectByIndex(3);
		huntPage.search.click();
		wait.until(ExpectedConditions.elementToBeClickable(freeSpotPage.firstBookButton));
		freeSpotPage.firstBookButton.click();
		BookingConfirmationPage confirmPage = new BookingConfirmationPage();
		wait.until(ExpectedConditions.elementToBeClickable(confirmPage.confirmButton));
		confirmPage.confirmButton.click();
		WebElement grayBookedBox = Driver.getDriver().findElement(By.xpath(
				"/html/body/app-root/div/app-my-schedule-page/div/div/div/div/app-weekly-schedule/div/table/tbody/tr[13]/td[4]"));
		wait.until(ExpectedConditions.elementToBeClickable(grayBookedBox));
		assertTrue(grayBookedBox.isDisplayed());
	}

	@Given("a student is signed in with valid username and password")
	public void a_student_is_signed_in_with_valid_username_and_password() {
		signinPage.signInEmail.sendKeys(ConfigReader.getProperties("student_username"));
		signinPage.signInPassword.sendKeys(ConfigReader.getProperties("student_password") + Keys.ENTER);
	}

	@When("a student clicks on hunt button")
	public void a_student_clicks_on_hunt_button() {
		wait.until(ExpectedConditions.elementToBeClickable(mapPage.googleRoom));
		mapPage.huntMenuButton.click();
	}

	@Then("the student should NOT be able to reserve a room")
	public void the_student_should_NOT_be_able_to_reserve_a_room() {
		Select select = new Select(huntPage.from);
		huntPage.date.click();
		huntPage.date.sendKeys("21");
		select.selectByVisibleText("1:00pm");
		select = new Select(huntPage.to);
		select.selectByIndex(3);
		huntPage.search.click();
		FreeSpotsPage freeSpotPage = new FreeSpotsPage();
		wait.until(ExpectedConditions.visibilityOfAllElements(freeSpotPage.firstRoomBox));
		assertFalse(freeSpotPage.firstRoomBox.isDisplayed());
	}

	@Given("a team leader reserved a room")
	public void a_team_leader_reserved_a_room() {
		signinPage.signInEmail.sendKeys(ConfigReader.getProperties("leader_username"));
		signinPage.signInPassword.sendKeys(ConfigReader.getProperties("leader_password") + Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(mapPage.googleRoom));
		mapPage.huntMenuButton.click();
		Select select = new Select(huntPage.from);
		huntPage.date.click();
		huntPage.date.sendKeys("21"); // three days later
		select.selectByVisibleText("10:00pm");
		select = new Select(huntPage.to);
		select.selectByIndex(3);
		huntPage.search.click();
		wait.until(ExpectedConditions.elementToBeClickable(freeSpotPage.firstBookButton));
		freeSpotPage.firstBookButton.click();
		BookingConfirmationPage confirmPage = new BookingConfirmationPage();
		wait.until(ExpectedConditions.elementToBeClickable(confirmPage.confirmButton));
		confirmPage.confirmButton.click();
	}

	@Then("team leader should be able to see the reservation")
	public void team_leader_should_be_able_to_see_the_reservation() {
		String xpathReservation = "/html/body/app-root/div/app-my-schedule-page/div/div/div/div/app-weekly-schedule/div/table/tbody/tr[7]/td[4]";
		WebElement reservation = Driver.getDriver().findElement(By.xpath(xpathReservation));
		wait.until(ExpectedConditions.visibilityOf(reservation));
		assertTrue(reservation.isDisplayed());
		Driver.closeDriver();

	}

	@Then("student from the same team should be able to see the reservation")
	public void student_from_the_same_team_should_be_able_to_see_the_reservation() {
		Driver.getDriver().get(ConfigReader.getProperties("url"));
		signinPage.signInEmail.sendKeys(ConfigReader.getProperties("student_username"));
		signinPage.signInPassword.sendKeys(ConfigReader.getProperties("student_password") + Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(mapPage.googleRoom));
		mapPage.googleRoom.click();
		WebElement firstLineSchedule = Driver.getDriver()
				.findElement(By.xpath("//div[@class='heading is-7 cell-top']"));
		wait.until(ExpectedConditions.visibilityOf(firstLineSchedule));
		String xpathReservation = "/html/body/app-root/div/app-my-schedule-page/div/div/div/div/app-weekly-schedule/div/table/tbody/tr[7]/td[4]";
		WebElement reservation = Driver.getDriver().findElement(By.xpath(xpathReservation));
		assertTrue(reservation.isDisplayed());
		Driver.closeDriver();

	}

	@Then("student from different team should be able to see the reservation")
	public void student_from_different_team_should_be_able_to_see_the_reservation() {
		Driver.getDriver().get(ConfigReader.getProperties("url"));
		signinPage.signInEmail.sendKeys(ConfigReader.getProperties("student_other_username"));
		signinPage.signInPassword.sendKeys(ConfigReader.getProperties("student_other_password") + Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(mapPage.googleRoom));
		mapPage.googleRoom.click();
		WebElement firstLineSchedule = Driver.getDriver()
				.findElement(By.xpath("//div[@class='heading is-7 cell-top']"));
		wait.until(ExpectedConditions.visibilityOf(firstLineSchedule));
		String xpathReservation = "/html/body/app-root/div/app-my-schedule-page/div/div/div/div/app-weekly-schedule/div/table/tbody/tr[7]/td[4]";
		WebElement reservation = Driver.getDriver().findElement(By.xpath(xpathReservation));
		assertTrue(reservation.isDisplayed());
		Driver.closeDriver();
	}

}
