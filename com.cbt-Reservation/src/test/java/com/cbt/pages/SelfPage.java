package com.cbt.pages;

import org.openqa.selenium.support.PageFactory;

import com.cbt.utilities.Driver;

public class SelfPage {

	public SelfPage() {
		PageFactory.initElements(Driver.getDriver(), this);
			
		}
}
