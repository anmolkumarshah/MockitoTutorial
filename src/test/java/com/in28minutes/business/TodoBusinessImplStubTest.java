package com.in28minutes.business;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.in28minutes.data.api.ToDoServiceStub;
import com.in28minutes.data.api.TodoService;

public class TodoBusinessImplStubTest {

	@Test
	public void usingAStub() {
		TodoService todoService = new ToDoServiceStub();
		TodoBusinessImpl todoBusinessImpl = new TodoBusinessImpl(todoService);
		List<String> todos = todoBusinessImpl
				.retrieveTodosRelatedToSpring("Ranga");
		assertEquals(2, todos.size());
	}
}