package com.cbt.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cbt.utilities.Driver;

public class SignInPage {

	public SignInPage() {
	PageFactory.initElements(Driver.getDriver(), this);
		
	}
	
	@FindBy(xpath="//input[@name='email']")
	public WebElement signInEmail;
	
	@FindBy(xpath="//input[@name='password']")
	public WebElement signInPassword;
	
	@FindBy(xpath="//button[@className='button is-dark']")
	public WebElement signInButton;
	
}

