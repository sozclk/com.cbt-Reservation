package com.cbt.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cbt.utilities.Driver;

public class BookingConfirmationPage {

	public BookingConfirmationPage() {
		PageFactory.initElements(Driver.getDriver(), this);	
	}
	//
	@FindBy(xpath="//button")
	public WebElement confirmButton;
}
