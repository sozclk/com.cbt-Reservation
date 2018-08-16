package com.cbt.pages;

import org.openqa.selenium.support.PageFactory;

import com.cbt.utilities.Driver;

public class HuntPage {

	public HuntPage() {
		PageFactory.initElements(Driver.getDriver(), this);
			
		}
}
