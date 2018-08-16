package com.cbt.pages;

import org.openqa.selenium.support.PageFactory;

import com.cbt.utilities.Driver;

public class MapPage {

	public MapPage() {
	PageFactory.initElements(Driver.getDriver(), this);
		
	}
}
