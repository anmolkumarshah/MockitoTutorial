package com.in28minutes.business;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;

public class ListTest {

	@Test
	public void testing_list_size_method() {
		List mockedList = mock(List.class);
		when(mockedList.size()).thenReturn(2);
		assertEquals(mockedList.size(), 2);
	}
	
	@Test
	public void testing_list_returning_multiple_values() {
		List mockedList = mock(List.class);
		when(mockedList.size()).thenReturn(2).thenReturn(3);
		assertEquals(mockedList.size(), 2);
		assertEquals(mockedList.size(), 3);
	}
	
	@Test
	public void testing_list_get() {
		List mockedList = mock(List.class);
		when(mockedList.get(0)).thenReturn("Anmol");
		assertEquals(mockedList.get(0), "Anmol");
	}
	
	@Test
	public void testing_list_get_BDD() {
		// given 
		List<Integer> mockedList = mock(List.class);
		given(mockedList.get(0)).willReturn(100);
		
		// when 
		Integer value = mockedList.get(0);
		
		// then
		assertThat(value, is(100));
	}
	
	@Test
	public void testing_list_get_default_values() {
		List mockedList = mock(List.class);
		assertEquals(mockedList.get(100), null);
		assertEquals(mockedList.get(10), null);
	}
	
	@Test
	public void testing_list_get_argument_matchers() {
		List mockedList = mock(List.class);
		when(mockedList.get(anyInt())).thenReturn("Anmol");
		
		assertEquals(mockedList.get(100), "Anmol");
		assertEquals(mockedList.get(10), "Anmol");
	}
	
	@Test(expected = RuntimeException.class)
	public void testing_list_throw_exception() {
		List mockedList = mock(List.class);
		when(mockedList.get(anyInt())).thenThrow(new RuntimeException("Some exception"));
		
		mockedList.get(100);
	}
	
	@Test
	public void testing_list_combine_matcher_with_hardcoaded_value() {
		List mockedList = mock(List.class);
		List<String> expectedArray = Arrays.asList("Anmol");
		
		when(mockedList.subList(anyInt(), 5) ).thenReturn(expectedArray);
		assertEquals(mockedList.subList(1,5).size(), 1);
	}

}
