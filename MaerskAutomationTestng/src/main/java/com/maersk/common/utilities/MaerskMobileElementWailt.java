package com.maersk.common.utilities;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.Clock;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.SystemClock;

import com.google.common.collect.ImmutableList;
import com.thoughtworks.selenium.SeleniumException;

import io.appium.java_client.MobileElement;

public class MaerskMobileElementWailt extends FluentWait<MobileElement> {
	
	/**
	 * Default constructor1
	 * @param m 
	 * @param l 
	 * @param systemSleeper 
	 * @param systemClock 
	 */

	public MaerskMobileElementWailt(MobileElement input) {
		super(input);
	}

	/**
	 * Default constructor2
	 */
	
	public MaerskMobileElementWailt(MobileElement element,
			long... timeOutInMiliSeconds) {
		this(element, new SystemClock(), Sleeper.SYSTEM_SLEEPER,
				getTimeout(timeOutInMiliSeconds), getInterval());
	}

	/**
	 * Default constructor3
	 */

	@SuppressWarnings("unchecked")
	public MaerskMobileElementWailt(MobileElement element, Clock clock,
			Sleeper sleeper, long timeOutInMiliSeconds, long sleepTimeOut) {
		super(element, clock, sleeper);
		withTimeout(timeOutInMiliSeconds, TimeUnit.MILLISECONDS);
		pollingEvery(sleepTimeOut, TimeUnit.MILLISECONDS);
		ignore(new Class[] { NoSuchElementException.class,
				StaleElementReferenceException.class, SeleniumException.class });
	}

	/**
	 * This Method will ignore exceptions that you provide as parameter
	 */

	public MaerskMobileElementWailt ignore(
			@SuppressWarnings("unchecked") Class<? extends RuntimeException>... exceptionType) {
		return (MaerskMobileElementWailt) ignoreAll(ImmutableList.copyOf(exceptionType));
	}

	/**
	 * This Method will return wait timeout.
	 */

	private static long getTimeout(long... timeout) {
		if ((timeout == null) || (timeout.length < 1) || (timeout[0] <= 0L)) {
			
			return Integer.parseInt(getTimeout());
			//return getTimeout();
		}
		return timeout[0];
	}

	/**
	 * This Method will return default wait timeout.
	 */

	private static String getTimeout() {
		
		return PropertyHandler.getKeyValues("configuration.properties","default_wait_timeout");
		//return PropertyManager.getResourceBundle().getLong("default_wait_timeout");
	}

	/**
	 * This Method will return default interval
	 */

	private static long getInterval() {
		return 10;
	}


}
