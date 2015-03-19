package io.github.celebes.hazelcast.test.samples;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class GettingStartedTest extends TestCase {

	public GettingStartedTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(GettingStartedTest.class);
	}

	public void testGettingStarted() {
		assertTrue(true);
	}
}
