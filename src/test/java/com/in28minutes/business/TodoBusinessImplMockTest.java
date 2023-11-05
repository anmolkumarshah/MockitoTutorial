package com.in28minutes.business;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.core.Is;
import org.junit.Test;
import org.mockito.Mockito;

import com.in28minutes.data.api.TodoService;

public class TodoBusinessImplMockTest {

	@Test
	public void usingAMock() {
		TodoService todoServiceMock = mock(TodoService.class);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		List<String> expectedArray = Arrays.asList("Spring core", "Spring Season", "Spring Onion");
		when(todoServiceMock.retrieveTodos("dummy")).thenReturn(expectedArray);

		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");

		assertEquals(3, todos.size());
	}

	@Test
	public void usingAMock_withEmptyList() {
		TodoService todoServiceMock = mock(TodoService.class);

		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);

		List<String> expectedArray = Arrays.asList();
		when(todoServiceMock.retrieveTodos("dummy")).thenReturn(expectedArray);

		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");

		assertEquals(0, todos.size());
	}

	@Test
	public void usingAMock_using_BDD() {

		// Given
		TodoService todoServiceMock = mock(TodoService.class);
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		List<String> expectedArray = Arrays.asList("Spring core", "Spring Season", "Spring Onion");
		
		given(todoServiceMock.retrieveTodos("dummy")).willReturn(expectedArray);

		// When
		List<String> todos = todoBusinessImpl.retrieveTodosRelatedToSpring("dummy");

		// Then
		assertThat(todos.size(), is(3));
	}
	
	@Test
	public void delete_todos_not_related_to_spring() {
		// given
		TodoService todoServiceMock = mock(TodoService.class);		
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoServiceMock);
		
		List<String> expectedArray = Arrays.asList("Spring core", "Spring Season", "Learn to Dance");
		given(todoServiceMock.retrieveTodos("dummy")).willReturn(expectedArray);
		
		// when
		todoBusinessImpl.deleteTodosNotRelatedToSpring("dummy");
		
		// then
		verify(todoServiceMock).deleteTodo("Learn to Dance");
		
//		verify(todoServiceMock).deleteTodo("Spring core"); --> error = different argument
		
		verify(todoServiceMock,never()).deleteTodo("Spring core");
		
		verify(todoServiceMock,times(1)).deleteTodo("Learn to Dance");
		
		verify(todoServiceMock,atLeast(1)).deleteTodo("Learn to Dance");
	}

}