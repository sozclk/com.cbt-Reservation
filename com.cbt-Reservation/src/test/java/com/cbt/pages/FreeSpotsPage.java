package com.cbt.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cbt.utilities.Driver;

public class FreeSpotsPage {

	public FreeSpotsPage() {
		PageFactory.initElements(Driver.getDriver(), this);	
	}
	
	@FindBy(xpath="//button[@class='button is-transparent is-white']")
	public WebElement firstBookButton;
}
