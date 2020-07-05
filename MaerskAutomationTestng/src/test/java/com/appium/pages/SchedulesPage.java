package com.appium.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.appium.config.CommonAppiumTest;
import com.appium.config.DeviceInterface;
import com.appium.config.ViewFactory;
import com.appium.page.objects.SchedulesPageObjects;
import com.maersk.common.utilities.StringUtility;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;



public class SchedulesPage extends CommonAppiumTest {
	public ViewFactory viewFactory = new ViewFactory(driver);
	public DeviceInterface runnerInfo;
	public SchedulesPageObjects schedulePageObjects = new SchedulesPageObjects();

	public SchedulesPage(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator(driver), schedulePageObjects);
		runnerInfo = viewFactory.getMobilePlatform(driver.toString().split(":")[0].toString());
	}

	public List<String> scheduleContainer(String depature,String destination,String option,String containerSize,String containerOption) {
		
		List<String> scheduleDetails=new ArrayList<String>();
		schedulePageObjects.from.click();
		schedulePageObjects.getSearch_destination_city().sendKeys(depature);
		schedulePageObjects.selectSearchOption.click();
		scheduleDetails.add(schedulePageObjects.from.getText().trim());
		schedulePageObjects.to.click();
		schedulePageObjects.getSearch_destination_city().sendKeys(destination);
		schedulePageObjects.selectSearchOption.click();
		scheduleDetails.add(schedulePageObjects.to.getText().trim());
		schedulePageObjects.advancedSearch.click();
		scheduleDetails.add(selectDate());
		driver.findElement(MobileBy.id("4"));
		selectContainerType(option, containerSize, containerOption);
		scheduleDetails.add(schedulePageObjects.containerTypeText.getText().trim());
		schedulePageObjects.searchBtn.click();
		
		return scheduleDetails;
		
	}
	

	public void verifySearchDetails(String depature,String destination,String option,String containerSize,String containerOption){
		
		List<String> expectedText=scheduleContainer(depature, destination, option, containerSize, containerOption);
		List<String> actualTxt=new ArrayList<String>();
		for(String txt:expectedText) {
			actualTxt.add(driver.findElement(MobileBy.xpath("//*[@text='"+txt+"']")).getText().trim());
		}
		
		Assert.assertEquals(expectedText, actualTxt);
		
		if(driver.findElement(MobileBy.xpath("//*[contains(@text,'couldn't find any schedule')]")).getText().contains("couldn't find any schedule")){
		
			System.out.println("No search results found");
		}
		else {
			int scheduleSearchResultSize=driver.findElements(MobileBy.xpath("xpath")).size();
		}
		
	}
	
	public String selectDate() {
		String dateText=StringUtility.generateCurrentDateAndTime();
		String[] getDate=dateText.split("/");
		for(String txt:getDate) {
			driver.findElement(MobileBy.xpath("//*[@text='"+txt+"']"));
		}
		
		return schedulePageObjects.scheduleDate.getText().trim();
		
	}
	
	public void selectContainerType(String option,String containerSize,String containerOption) {
		
		if(option.contains("All")){
			driver.findElement(MobileBy.xpath("//*[@text="+containerSize+"]/following-sibling::div[@text='"+containerOption+"]"));
		}
		else {
			driver.findElement(MobileBy.xpath("//*[@text='Reeferequipment']/following-sibling::div[@text='"+containerOption+"]"));
		}
		
	}

}
