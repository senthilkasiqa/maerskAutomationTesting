package com.appium.config;

import com.appium.pages.SchedulesPage;

public interface DeviceInterface {

	public void scheduleShipment(SchedulesPage shipment, String depature,String destination,String option,String containerSize,String containerOption);

	
}
