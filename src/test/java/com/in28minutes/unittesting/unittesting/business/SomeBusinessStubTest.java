package com.in28minutes.unittesting.unittesting.business;

import static org.junit.Assert.*;

import org.junit.Test;

import com.in28minutes.unittesting.unittesting.data.SomeDataService;

class SomeDataServiceStub implements SomeDataService {

	@Override
	public int[] retrieveAllData() {
		return new int[] { 1, 2, 3 };
	}

}
class SomeDataServiceEmptyStub implements SomeDataService {
	
	@Override
	public int[] retrieveAllData() {
		return new int[] { };
	}
	
}
class SomeDataServiceOneElementStub implements SomeDataService {
	
	@Override
	public int[] retrieveAllData() {
		return new int[] { 5 };
	}
	
}

public class SomeBusinessStubTest {

	@Test
	public void calculateSumUsingDataService_basic() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceStub());
		int actualResult = business.calculateSumUsingDataService();
		int expectedResult = 6;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateSumUsingDataService_empty() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceEmptyStub());
		int actualResult = business.calculateSumUsingDataService();
//		int actualResult = business.calculateSum(new int[] {});
		int expectedResult = 0;
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void calculateSumUsingDataService_oneValue() {
		SomeBusinessImpl business = new SomeBusinessImpl();
		business.setSomeDataService(new SomeDataServiceOneElementStub());
		int actualResult = business.calculateSumUsingDataService();
//		int actualResult = business.calculateSum(new int[] { 5 });
		int expectedResult = 5;
		assertEquals(expectedResult, actualResult);
	}

}
