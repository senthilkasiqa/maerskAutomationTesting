package com.appium.tests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.appium.manager.ParallelThread;

public class Runner {

	@Test
	public void testRunner() throws Exception {
		ParallelThread parallelThread = new ParallelThread();
		List<String> test = new ArrayList<String>();
//		test.add("LoginTest");
//		test.add("MyFilesTest");
		parallelThread.runner("com.appium.tests");
	}

}
