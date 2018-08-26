package com.cbt.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.cbt.utilities.Driver;

public class MapPage {

	public MapPage() {
		PageFactory.initElements(Driver.getDriver(), this);	
	}
	
	@FindBy(linkText="hunt")
	public WebElement huntMenuButton;
	
	@FindBy(linkText="schedule")
	public WebElement scheduleMenuButton;
	
	@FindBy(xpath="//a[@class='navbar-link']")
	public WebElement myMenuButton;
	
	@FindBy(id="room-211")
	public WebElement googleRoom;
	
	@FindBy(id="room-212")
	public WebElement appleRoom;
	
	@FindBy(id="room-213")
	public WebElement microsoftRoom;
	
	@FindBy(id="room-215")
	public WebElement amazonRoom;
	
	@FindBy(id="room-216")
	public WebElement teslaRoom;
	
	@FindBy(id="room-217")
	public WebElement facebookRoom;
	
	@FindBy(className="title")
	public WebElement locationBtn;
}
