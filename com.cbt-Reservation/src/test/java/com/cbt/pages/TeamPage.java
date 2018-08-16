package com.cbt.pages;

import org.openqa.selenium.support.PageFactory;

import com.cbt.utilities.Driver;

public class TeamPage {

	public TeamPage() {
		PageFactory.initElements(Driver.getDriver(), this);
			
		}
}
