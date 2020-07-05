package com.appium.tests;

import org.testng.annotations.Test;

import com.appium.config.UserBaseTest;
import com.appium.pages.SchedulesPage;

public class SchedulesTest extends UserBaseTest {


	@Test(priority = 1,description="verify schedule shipments search details with regular sized Cargo")
	public void scheduleShipmentRegular() throws InterruptedException {
		SchedulesPage schedule = new SchedulesPage(driver);
		schedule.verifySearchDetails("Chennai", "London", "All", "Regular Sized cargo", "20' Dry Standard");
	}
	
	@Test(priority = 2,description="verify schedule shipments search details with Odd sized Cargo")
	public void scheduleShipmentOdd() throws InterruptedException {
		SchedulesPage schedule = new SchedulesPage(driver);
		schedule.verifySearchDetails("Chennai", "London", "All", "Regular Sized cargo", "20' Dry Standard");
	}
	
	@Test(priority = 2,description="verify schedule shipments search details with Odd sized Cargo")
	public void scheduleShipmentReefer() throws InterruptedException {
		SchedulesPage schedule = new SchedulesPage(driver);
		schedule.verifySearchDetails("Chennai", "London", "Temperature controlled", "reefer equipment", "20' Reefer Standard");
	}

}
