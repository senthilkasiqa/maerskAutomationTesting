package com.appium.screen.flows;

import com.appium.config.CommonAppiumTest;
import com.appium.config.DeviceInterface;
import com.appium.pages.SchedulesPage;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AndroidFlow extends CommonAppiumTest implements DeviceInterface {

    public AndroidFlow(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

	public void scheduleShipment(SchedulesPage shipment, String depature, String destination, String option,
			String containerSize, String containerOption) {
		shipment.verifySearchDetails(depature, destination, option, containerSize, containerOption);
		
	}


}
