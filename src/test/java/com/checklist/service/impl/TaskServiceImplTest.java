package com.checklist.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.checklist.DAO.TaskDAO;
import com.checklist.model.Task;

public class TaskServiceImplTest {
	
	@InjectMocks
	TaskServiceImpl taskServiceImpl;
	
	@Mock
	TaskDAO taskDAO;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetHomeTask() {
		// Given
		List<Task> taskList = Arrays.asList(new Task(), new Task());
		
		// When
		when(taskDAO.getHomeTask(1)).thenReturn(taskList);
		
		// Then
		List<Task> homeTask = taskServiceImpl.getHomeTask(1);
		assertNotNull(homeTask);
		assertEquals(homeTask.size(), 2);
	}

}
