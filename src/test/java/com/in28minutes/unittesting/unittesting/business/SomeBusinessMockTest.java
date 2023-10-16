package com.in28minutes.unittesting.unittesting.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.in28minutes.unittesting.unittesting.data.SomeDataService;

@RunWith(MockitoJUnitRunner.class)
public class SomeBusinessMockTest {

	@InjectMocks
	SomeBusinessImpl business;

	@Mock
	SomeDataService dataServiceMock;

//	If I'm using @InjectMocks and @Mock then no need to write the below code also no need to write the
//	@Before function, this is automatically done by the Mockito
//	SomeBusinessImpl business = new SomeBusinessImpl();
//	SomeDataService dataServiceMock = mock(SomeDataService.class);
//
//	@Before
//	public void before() {
//		business.setSomeDataService(dataServiceMock);
//	}

	@Test
	public void calculateSumUsingDataService_basic() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 1, 2, 3 });
		assertEquals(6, business.calculateSumUsingDataService());
	}

	@Test
	public void calculateSumUsingDataService_empty() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
		assertEquals(0, business.calculateSumUsingDataService());
	}

	@Test
	public void calculateSumUsingDataService_oneValue() {
		when(dataServiceMock.retrieveAllData()).thenReturn(new int[] { 5 });
		assertEquals(5, business.calculateSumUsingDataService());
	}

}
