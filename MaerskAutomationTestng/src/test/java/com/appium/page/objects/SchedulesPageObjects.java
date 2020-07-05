package com.appium.page.objects;


import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;

public class SchedulesPageObjects {

	@AndroidFindBy(id = "schedulesTab")
	@iOSFindBy(id = "schedulesTab")
	public MobileElement schedulesMenu;

	@AndroidFindBy(id = "fromTextField")
	@iOSFindBy(id = "fromTextField")
	public MobileElement from;

	@AndroidFindBy(id = "search_depature_city")
	@iOSFindBy(id = "search_depature_city")
	public MobileElement searchDepature;

	@AndroidFindBy(id = "select_search_option")
	@iOSFindBy(xpath = "select_search_option")
	public MobileElement selectSearchOption;
	
	@AndroidFindBy(id = "toTextField")
	@iOSFindBy(id = "toTextField")
	public MobileElement to;

	@AndroidFindBy(id = "search_destination_city")
	@iOSFindBy(id = "search_destination_city")
	public MobileElement search_destination_city;
	
	@AndroidFindBy(id = "org.wordpress.android:id/nux_url")
	@iOSFindBy(id = "return")
	public MobileElement return_keyboard;

	@AndroidFindBy(xpath = ".//*[@text='Advanced Search']")
	@iOSFindBy(id = "Advanced Search")
	public MobileElement advancedSearch;
	
	@AndroidFindBy(id = "containerType")
	@iOSFindBy(id = "containerType")
	public MobileElement containerType;

	public MobileElement getSchedulesMenu() {
		return schedulesMenu;
	}
	
	@AndroidFindBy(id = "scheduleDate")
	@iOSFindBy(id = "scheduleDate")
	public MobileElement scheduleDate;

	@AndroidFindBy(id = "containerTypeText")
	@iOSFindBy(id = "containerTypeText")
	public MobileElement containerTypeText;

	@AndroidFindBy(id = "search_btn")
	@iOSFindBy(id = "search_btn")
	public MobileElement searchBtn;
	
	public MobileElement getSearchBtn() {
		return searchBtn;
	}

	public MobileElement getContainerTypeText() {
		return containerTypeText;
	}

	public MobileElement getScheduleDate() {
		return scheduleDate;
	}

	public MobileElement getFrom() {
		return from;
	}

	public MobileElement getSearchDepature() {
		return searchDepature;
	}

	public MobileElement getSelectSearchOption() {
		return selectSearchOption;
	}

	public MobileElement getTo() {
		return to;
	}

	public MobileElement getSearch_destination_city() {
		return search_destination_city;
	}

	public MobileElement getReturn_keyboard() {
		return return_keyboard;
	}

	public MobileElement getAdvancedSearch() {
		return advancedSearch;
	}

	public MobileElement getContainerType() {
		return containerType;
	}
	
	

}
