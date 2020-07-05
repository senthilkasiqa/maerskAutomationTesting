package com.appium.config;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.annotation.values.ElementDescription;
import com.annotation.values.PageName;
import com.appium.manager.AppiumParallelTest;
import com.maersk.common.utilities.MaerskExpectedConditions;
import com.maersk.common.utilities.MaerskMobileElementWailt;
import com.maersk.common.utilities.StringUtility;
import com.relevantcodes.extentreports.LogStatus;
import com.report.factory.ExtentTestManager;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.SwipeElementDirection;
import io.appium.java_client.TouchAction;

public class CommonAppiumTest extends RemoteWebElement {

    public static  AppiumDriver<MobileElement> driver;
    public static final int impliciteTimeOut = 20;
    
    private By by;
    
    public AppiumParallelTest appiumParallelTest = new AppiumParallelTest();
    Logger logger = Logger.getLogger(CommonAppiumTest.class);

	public CommonAppiumTest(AppiumDriver<MobileElement> driver) {
        CommonAppiumTest.driver = driver;
    }

    public boolean waitForPageToLoad(WebElement id) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(id));
		return true;
    }
    
    public void waitForText(MobileElement element, String Value, long... timeout) {
    	
		new MaerskMobileElementWailt(element, timeout).withMessage(
				"Wait Timeout for text of to be '"
						+ Value + "'. Actual Text is : " + element.getText()).until(
								MaerskExpectedConditions.textEqualTo(Value));
	}

	public void waitForNotText(MobileElement element,String Value, long... timeout) {
		new MaerskMobileElementWailt(element, timeout).withMessage(
				"Wait Timeout for text of  not to be '"
						+ Value + "'. Actual Text is : " + element.getText()).until(
								MaerskExpectedConditions.textNotEqualTo(Value));
	}
	
	
	public void waitForPresent(MobileElement element,long... timeout) {
		new MaerskMobileElementWailt(element, timeout).withMessage(
				"Wait Timeout for to be Present.")
				.until(MaerskExpectedConditions.presenteOfElement());
	}

	public void waitForNotPresent(MobileElement element,long... timeout) {
		new MaerskMobileElementWailt(element, timeout).withMessage(
				"Wait Timeout for not to be Present.")
				.until(MaerskExpectedConditions.notpresenteOfElement());
	}

    public void waitForElementToDisAppear(String id) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id(id)));
    }

    public WebElement waitForElement(WebElement arg) {
        waitForPageToLoad(arg);
        WebElement el = arg;
        return el;
    }

    public void swipeRightUntilTextExists(String expected) {
        do {
            swipeRight();
        } while (!driver.getPageSource().contains(expected));
    }

    public void swipeLeftUntilTextExists(String expected) {
        do {
            swipeLeft();
        } while (!driver.getPageSource().contains(expected));
    }

    public void swipeRight() {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.9);
        int endx = (int) (size.width * 0.20);
        int starty = size.height / 2;
        driver.swipe(startx, starty, endx, starty, 1000);
    }

    public void swipeLeft() {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.8);
        int endx = (int) (size.width * 0.20);
        int starty = size.height / 2;
        driver.swipe(startx, starty, endx, starty, 1000);
    }

    public void scrollDirection(MobileElement Id, SwipeElementDirection arg) {
        MobileElement e = Id;
        e.swipe(arg, 1000);
    }

    /**
     * method to set the context to required view.
     *
     * @param context view to be set
     */
    public void setContext(String context) {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
        }
        driver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW_1

        logger.info("Current context" + driver.getContext());
    }

    public void clickBackButton() {
        driver.navigate().back(); //Closes keyboard
        //driver.navigate().back(); //Comes out of edit mode
    }


    public String getCurrentMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    public void logStepIntoExtentReport(String elementDescription, String action,String typeString) {
        ExtentTestManager.getTest().log(LogStatus.INFO, withBoldHTML(action),
            elementDescription + "; " + withBoldHTML("Text") + ": " + typeString);
    }

    public String withBoldHTML(String string) {
        if (!string.trim().isEmpty()) {
            return "<b>" + string + "</b>";
        } else {
            return "";
        }
    }

    public String getPageObjectElemetDescription(Object pageObject, String fieldName) {
        try {
            return this.getClass().getAnnotation(PageName.class).value() + "::" +
                pageObject.getClass().getField(fieldName).getAnnotation(ElementDescription.class)
                .value();
        } catch (NoSuchFieldException e) {

            e.printStackTrace();
        }
        return "";
    }
    
    public boolean isElementPresent(MobileElement locator) {
			if (verifyPresent(locator)) {
				//driver.manage().timeouts().implicitlyWait(impliciteTimeOut, TimeUnit.SECONDS);
				return true;
			} else {
				//driver.manage().timeouts().implicitlyWait(impliciteTimeOut, TimeUnit.SECONDS);
				return false;
			}
		}
    
    
    public void waitForVisible(MobileElement element,long... timeout) {
		new MaerskMobileElementWailt(element, timeout).withMessage(
				"Wait Timeout for  not to be Visible.")
				.until(MaerskExpectedConditions.visibleOfElement());
	}

	public void waitForNotVisible(MobileElement element,long... timeout) {
		new MaerskMobileElementWailt(element, timeout).withMessage(
				"Wait Timeout for not to be Visible.")
				.until(MaerskExpectedConditions.invisibleOfElement());
	}

	public void waitForAttribute(MobileElement element,String AttributeName, String Value,
			long... timeout) {
		new MaerskMobileElementWailt(element, timeout).withMessage(
				"Wait Timeout for " + AttributeName + " value of to be '" + Value + "'. Actual "
						+AttributeName + " value is : "
						+element.getAttribute(AttributeName)).until(
								MaerskExpectedConditions.attributeEqualTo(AttributeName, Value));
	}

	public void waitForNotAttribute(MobileElement element,String AttributeName, String Value,
			long... timeout) {
		new MaerskMobileElementWailt(element, timeout).withMessage(
				"Wait Timeout for " + AttributeName + " value of to be '" + Value + "'. Actual "
						+ AttributeName + " value is : "
						+ element.getAttribute(AttributeName)).until(
							MaerskExpectedConditions.attributeNotEqualTo(AttributeName, Value));
	}

	public void waitForEnable(MobileElement element,long... timeout) {
		new MaerskMobileElementWailt(element, timeout).withMessage(
				"Wait Timeout for to be Enable.").until(
						MaerskExpectedConditions.enabledElement());
	}

	public void waitForDisable(MobileElement element,long... timeout) {
		new MaerskMobileElementWailt(element, timeout).withMessage(
				"Wait Timeout for to be Disable.")
				.until(MaerskExpectedConditions.disableElement());
	}

	public void waitForSelected(MobileElement element,long... timeout) {
		new MaerskMobileElementWailt(element, timeout).withMessage(
				"Wait Timeout for to be Selected.")
				.until(MaerskExpectedConditions.selectedElement());
	}

	public void waitForNotSelected(MobileElement element,long... timeout) {
		new MaerskMobileElementWailt(element, timeout).withMessage(
				"Wait Timeout for to be Selected.")
				.until(MaerskExpectedConditions.notselectedElement());
	}

	public void assertPresent(MobileElement element) {
		if (!verifyPresent(element)) {
			throw new AssertionError("Assertion Error");
		}
	}

	public void assertNotPresent(MobileElement element) {
		if (!verifyNotPresent(element)) {
			throw new AssertionError("Assertion Error");
		}
	}

	public void assertVisible(MobileElement element) {
		if (!verifyVisible(element)) {
			throw new AssertionError("Assertion Error");
		}
	}

//	public void assertNotVisible(MobileElement element) {
//		if (!verifyNotVisible(element)) {
//			throw new AssertionError("Assertion Error");
//		}
//	}
//
//	public void assertAttribute(MobileElement element,String AttributeName, String Value) {
//		if (!verifyAttribute(element,AttributeName, Value)) {
//			throw new AssertionError("Assertion Error");
//		}
//	}
//
//	public void assertNotAttribute(MobileElement element,String AttributeName, String Value) {
//		if (!verifyNotAttribute(element,AttributeName, Value)) {
//			throw new AssertionError("Assertion Error");
//		}
//	}

	public void assertText(MobileElement element,String Value) {
		if (!verifyText(element,Value)) {
			throw new AssertionError("Assertion Error");
		}
	}

	public void assertNotText(MobileElement element,String Value) {
		if (!verifyNotText(element,Value)) {
			throw new AssertionError("Assertion Error");
		}
	}

	public void assertSelected(MobileElement element) {
		if (!verifySelected(element)) {
			throw new AssertionError("Assertion Error");
		}
	}

	public void assertNotSelected(MobileElement element) {
		if (!verifyNotSelected(element)) {
			throw new AssertionError("Assertion Error");
		}
	}

	public void assertEnabled(MobileElement element) {
		if (!verifyEnabled(element)) {
			throw new AssertionError("Assertion Error");
		}
	}

	public void assertNotEnabled(MobileElement element) {
		if (!verifyNotEnabled(element)) {
			throw new AssertionError("Assertion Error");
		}
	}

	public boolean verifyPresent(MobileElement element) {
		try {
			waitForPresent(element);
		} catch (Exception e) {
			logger.error("Wait Timeout - Present for "
					+ e.getMessage());
		}
//		boolean present =isElementPresent(element);
//		if (present) {
//			logger.info("Expected element should be present");
//			return true;
//		} else {
//			logger.info("Actual is Not Present.");
//			return false;
//		}
		return true;
	}

	public boolean verifyNotPresent(MobileElement element) {
		try {
			waitForNotPresent(element);
		} catch (Exception e) {
			logger.error("Wait Timeout - Not Present for "
					+ e.getMessage());
		}
//		boolean present = !isElementPresent(element);
//		if (present) {
//			logger.info("Expected  Should not be present. Actual is not Present.");
//			return true;
//		} else {
//			logger.info("Expected  Should not be present. Actual is not Present.");
//			return false;
//		}
		return true;
	}

	public boolean verifyVisible(MobileElement element) {
		try {
			waitForVisible(element);
		} catch (Exception e) {
			logger.error("Wait Timeout - visible for " 
					+ e.getMessage());
		}

		boolean present = !isElementPresent(element);
		if (present) {
			logger.info("Expected  Should not be present. Actual is not Present.");
			return true;
		} else {
			logger.info("Expected  Should not be present. Actual is not Present.");
			return false;
		}
	}

	public boolean verifyText(MobileElement element,String value) {
		try {
			waitForPresent(element);
		} catch (Exception e) {
			logger.info("Expected should be present. Actual is not present.");
			logger.error("Wait Timeout - Text for "
					+ e.getMessage());
		}
		String text = element.getText().trim();
		if (text.contains(value.trim())) {
			logger.info("Expected should be present. Actual is not present.");
			return true;
		} else {
			logger.error("Expected should be present. Actual is not present.");
			return false;
		}
	}

	public boolean verifyNotText(MobileElement element,String value) {
		try {
			waitForNotText(element,value);
		} catch (Exception e) {
			logger.error("Wait Timeout - Not text for "
					+ e.getMessage());
		}
		String text = element.getText();
		if (!text.contains(value)) {
			logger.info("Expected should be present. Actual is not present.");
			return true;
		} else {
			logger.error("Expected should be present. Actual is not present.");
			return false;
		}
	}

	public boolean verifySelected(MobileElement element) {
		try {
			waitForSelected(element);
		} catch (Exception e) {
			logger.error("Wait Timeout - Selected for "
					+ e.getMessage());
		}
		boolean selected = element.isSelected();
		if (selected) {
			logger.info("Expected should be present. Actual is not present.");
			return true;
		} else {
			logger.info("Expected should be present. Actual is not present.");
			return false;
		}
	}

	public boolean verifyNotSelected(MobileElement element) {
		try {
			waitForNotSelected(element);
		} catch (Exception e) {
			logger.error("Wait Timeout - Not Selected for "
					+ e.getMessage());
		}
		boolean selected = element.isSelected();
		if (!selected) {
			logger.info("Expected should be present. Actual is not present.");
			return true;
		} else {
			logger.info("Expected should be present. Actual is not present.");
			return false;
		}
	}

	public boolean verifyEnabled(MobileElement element) {
		try {
			waitForSelected(element);
		} catch (Exception e) {
			logger.error("Wait Timeout - Enabled for"
					+ e.getMessage());
		}
		boolean selected = element.isEnabled();
		if (selected) {
			logger.info("Expected should be present. Actual is not present.");
			return true;
		} else {
			logger.info("Expected should be present. Actual is not present.");
			return false;
		}
	}

	public boolean verifyNotEnabled(MobileElement element) {
		try {
			waitForSelected(element);
		} catch (Exception e) {
			logger.error("Wait Timeout - Not Enabled for"
					+ e.getMessage());
		}
		boolean enabled = element.isEnabled();
		if (!enabled) {
			logger.info("Expected should be present. Actual is not present.");
			return true;
		} else {
			logger.info("Expected should be present. Actual is not present.");;
			return false;
		}
	}
	
	public boolean isPresent(MobileElement element) {
		
		try {

			List<MobileElement> eles;
			if (element != null) {
				if (!this.isPresent(element)) {
					return false;
				}
				eles = CommonAppiumTest.driver.findElements(this.by);
			} else {
				eles = getWrappedDriver().findElements(this.by);
			}
			if ((eles != null) && (eles.size() > 0)) {
				if (StringUtility.isNullOrEmpty(this.id)) {
					this.id = eles.get(0).getId();
				}
				return true;
			}
			return false;
		} catch (Exception e) {
		}
		return false;
	}
	public enum SWIPE_DIRECTION_CUSTOM {
		RIGHT, LEFT;
	}
	
	public static void swipeDirection(SWIPE_DIRECTION_CUSTOM direction) {
		
		int height = driver.findElementByClassName("UIAWindow").getSize().getHeight();
		int width = driver.findElementByClassName("UIAWindow").getSize().getWidth();

		
		try {
			
			 if (direction == SWIPE_DIRECTION_CUSTOM.LEFT) {
				 
				 new TouchAction((MobileDriver<MobileElement>) driver).press(width, height/2).waitAction(1000)

		            .moveTo(width/4, height/2).release().perform();
			 
			 } else if (direction == SWIPE_DIRECTION_CUSTOM.RIGHT) {
				 
				 new TouchAction((MobileDriver<MobileElement>) driver).press(width/4, height/2).waitAction(1000)

		            .moveTo(width, height/2).release().perform();
			 
			 }
		} catch (Exception ex) {

			ex.printStackTrace();

		}
	}
	
	
	public static boolean verifyTrue(boolean condition, String SuccessMessage, String FailureMessage) {
		if (condition) {
			return true;
		} else {
			return false;
		}
	}
	
	public void enterTextInAppFieldIos(String sText) throws Exception {
		try {
			driver.executeScript("target.frontMostApp().keyboard().keys()['26'].touchAndHold(10.0)");
			driver.executeScript("target.frontMostApp().keyboard().typeString('" + sText + "')");	
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}
	
	public static void pause(long timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void scrollTo() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");
		js.executeScript("mobile: scroll", scrollObject);
	}

    
    
}
