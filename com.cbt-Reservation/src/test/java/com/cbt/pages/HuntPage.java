package com.cbt.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cbt.utilities.Driver;

public class HuntPage {

	public HuntPage() {
		PageFactory.initElements(Driver.getDriver(), this);	
	}
	
	@FindBy(id="date")
	public WebElement date;
	
	@FindBy(id="timelineStart")
	public WebElement from;
	
	@FindBy(id="timelineFinish")
	public WebElement to;
	
	@FindBy(xpath="//p[@class='control']//button[@type='submit']")
	public WebElement search;
	
	
}
