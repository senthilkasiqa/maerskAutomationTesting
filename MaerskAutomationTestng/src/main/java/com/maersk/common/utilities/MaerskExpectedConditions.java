package com.maersk.common.utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MaerskExpectedConditions {
	
	public static ExpectedCondition<AppiumDriver<MobileElement>, Boolean> titleContains(
			final String title) {

		return new ExpectedCondition<AppiumDriver<MobileElement>, Boolean>() {
			private String currentTitle = "";

			public Boolean apply(AppiumDriver<MobileElement> driver) {
				currentTitle = driver.getTitle();
				return currentTitle != null && currentTitle.contains(title);
			}
		};
	}

	public static ExpectedCondition<AppiumDriver<MobileElement>, Boolean> titleNotContains(
			final String title) {

		return new ExpectedCondition<AppiumDriver<MobileElement>, Boolean>() {
			private String currentTitle = "";

			public Boolean apply(AppiumDriver<MobileElement> driver) {
				currentTitle = driver.getTitle();
				return !(currentTitle != null && currentTitle.contains(title));
			}
		};
	}

	public static ExpectedCondition<MobileElement, Boolean> visibleOfElement() {
		return new ExpectedCondition<MobileElement, Boolean>() {

			public Boolean apply(MobileElement element) {
				element.setId("-1");
				return element.isDisplayed();
			}
		};
	}

	public static ExpectedCondition<MobileElement, Boolean> invisibleOfElement() {
		return new ExpectedCondition<MobileElement, Boolean>() {

			public Boolean apply(MobileElement element) {
				element.setId("-1");
				return !element.isDisplayed();
			}
		};
	}

	public static ExpectedCondition<MobileElement, Boolean> presenteOfElement() {
		return new ExpectedCondition<MobileElement, Boolean>() {

			public Boolean apply(MobileElement element) {
				element.setId("-1");
				return element != null && element.isEnabled() || element.isDisplayed();
			}
		};
	}

	public static ExpectedCondition<MobileElement, Boolean> notpresenteOfElement() {
		 return new ExpectedCondition<MobileElement, Boolean>() {

			public Boolean apply(MobileElement element) {
				element.setId("-1");
				return !(element != null)&& !element.isDisplayed() || !element.isEnabled();
			}
		};
	}

	public static ExpectedCondition<MobileElement, Boolean> selectedElement() {
		return new ExpectedCondition<MobileElement, Boolean>() {

			public Boolean apply(MobileElement element) {
				return element.isSelected();
			}
		};
	}

	public static ExpectedCondition<MobileElement, Boolean> notselectedElement() {
		return new ExpectedCondition<MobileElement, Boolean>() {

			public Boolean apply(MobileElement element) {
				return !element.isSelected();
			}
		};
	}

	public static ExpectedCondition<MobileElement, Boolean> enabledElement() {
		return new ExpectedCondition<MobileElement, Boolean>() {

			public Boolean apply(MobileElement element) {
				return element.isEnabled();
			}
		};
	}

	public static ExpectedCondition<MobileElement, Boolean> disableElement() {
		return new ExpectedCondition<MobileElement, Boolean>() {

			public Boolean apply(MobileElement element) {
				return !element.isEnabled();
			}
		};
	}

	public static ExpectedCondition<MobileElement, Boolean> attributeEqualTo(
			final String AtributeName, final String value) {
		return new ExpectedCondition<MobileElement, Boolean>() {
			public Boolean apply(MobileElement element) {
				try {
					return element.getAttribute(AtributeName).equalsIgnoreCase(
							value);
				} catch (Exception e) {
					return false;
				}
			}
		};
	}

	public static ExpectedCondition<MobileElement, Boolean> attributeNotEqualTo(
			final String AtributeName, final String value) {
		return new ExpectedCondition<MobileElement, Boolean>() {
			public Boolean apply(MobileElement element) {
				return !element.getAttribute(AtributeName).equalsIgnoreCase(
						value);
			}
		};
	}

	public static ExpectedCondition<MobileElement, Boolean> textEqualTo(
			final String value) {
		return new ExpectedCondition<MobileElement, Boolean>() {
			public Boolean apply(MobileElement element) {
				return element.getText().contains(value);
			}
		};
	}

	public static ExpectedCondition<MobileElement, Boolean> textNotEqualTo(
			final String value) {
		return new ExpectedCondition<MobileElement, Boolean>() {
			public Boolean apply(MobileElement element) {
				return !element.getText().contains(value);
			}
		};
	}

}
