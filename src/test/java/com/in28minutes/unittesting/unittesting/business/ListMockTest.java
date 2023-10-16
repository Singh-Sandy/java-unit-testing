package com.in28minutes.unittesting.unittesting.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mockito.ArgumentCaptor;

public class ListMockTest {

	List<String> mock = mock(List.class);

	@Test
	public void size_basic() {

		when(mock.size()).thenReturn(5);
		assertEquals(5, mock.size());
	}

	@Test
	public void returnDifferentValues() {
		when(mock.size()).thenReturn(5).thenReturn(10);
		assertEquals(5, mock.size());
		assertEquals(10, mock.size());
	}

	@Test
	public void returnWithParameters() {
		when(mock.get(0)).thenReturn("Sandeep Singh");
		assertEquals("Sandeep Singh", mock.get(0));
		assertEquals(null, mock.get(1));
	}

	@Test
	public void returnWithGenericParameters() {
		when(mock.get(anyInt())).thenReturn("Sandeep Singh");
		assertEquals("Sandeep Singh", mock.get(0));
		assertEquals("Sandeep Singh", mock.get(1));
	}

	@Test
	public void verificationBasics() {
		// SUT
		String value1 = mock.get(0);
		String value2 = mock.get(1);

		// Verify whether the function is called or not
		verify(mock).get(0);
		verify(mock, times(2)).get(anyInt());
		verify(mock, atLeast(1)).get(anyInt());
		verify(mock, atLeastOnce()).get(anyInt());
		verify(mock, atMost(2)).get(anyInt());
		verify(mock, never()).get(2);

	}

	@Test
	public void argumentCapturing() {
		// SUT
		mock.add("SomeString");

		// Verification what the argument is (argument captor)
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mock).add(captor.capture());

//		System.out.println(captor.getValue());

		assertEquals("SomeString", captor.getValue());

	}

	@Test
	public void multipleArgumentCapturing() {
		// SUT
		mock.add("SomeString1");
		mock.add("SomeString2");

		// Verification what the argument is (argument captor)
		ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
		verify(mock, times(2)).add(captor.capture());

//		System.out.println(captor.getValue());

		List<String> allValues = captor.getAllValues();
		assertEquals("SomeString1", allValues.get(0));
		assertEquals("SomeString2", allValues.get(1));

	}

	@Test
	public void mocking() {

		// A mock does not retain the behavior of the original class

		ArrayList arrayListMock = mock(ArrayList.class);
		System.out.println(arrayListMock.get(0));// null
		System.out.println(arrayListMock.size());// 0
		arrayListMock.add("Test");
		arrayListMock.add("Test2");
		System.out.println(arrayListMock.size());// 0
		when(arrayListMock.size()).thenReturn(5);
		System.out.println(arrayListMock.size());// 5

	}

	@Test
	public void spying() {

		// A spy, by default, retain behavior of the original class

		ArrayList arrayListSpy = spy(ArrayList.class);
		arrayListSpy.add("Test0");
		System.out.println(arrayListSpy.get(0));// Test0
		System.out.println(arrayListSpy.size());// 1
		arrayListSpy.add("Test1");
		arrayListSpy.add("Test2");
		System.out.println(arrayListSpy.size());// 3
		when(arrayListSpy.size()).thenReturn(5);
		System.out.println(arrayListSpy.size());// 5

		arrayListSpy.add("Test3");
		System.out.println(arrayListSpy.size());// 5

		verify(arrayListSpy).add("Test3");

	}

}
