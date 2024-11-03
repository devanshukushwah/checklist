package com.checklist.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.checklist.DAO.TaskDAO;
import com.checklist.model.PageRequest;
import com.checklist.model.PageResponse;
import com.checklist.model.Task;
import com.checklist.model.TaskHistory;
import com.checklist.model.TaskSearchFilter;

public class TaskServiceImplTest {
	
	@InjectMocks
	TaskServiceImpl taskServiceImpl;
	
	@Mock
	TaskDAO taskDAO;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	private static final int USER_ID = 1;

	@Test
	@DisplayName("should return list of task for home screen")
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
	
	@Test
	@DisplayName("should update status when changing to true for id")
	public void testUpdateStatus() {
		// Given
		int id = 1;
		boolean newStatus = true;
		boolean result = true;
		
		// When
		when(taskDAO.updateStatus(id, newStatus)).thenReturn(result);
		
		// Then
		boolean updateStatus = taskServiceImpl.updateStatus(id, newStatus);
		assertNotNull(updateStatus);
		assertEquals(updateStatus, result);
	}
	
	@Test
	@DisplayName("should add task for user id")
	public void testAddTask() {
		// Given
		Task task = new Task();
		
		// When
		when(taskDAO.addTask(USER_ID, task)).thenReturn(true);
		
		// Then
		boolean res = taskServiceImpl.addTask(USER_ID, task);
		assertNotNull(res);
		assertEquals(res, true);
		
	}
	
	@Test
	@DisplayName("should get task based on filter")
	public void testGetTask() {
		// Given
		TaskSearchFilter taskSearchFilter = new TaskSearchFilter();
		
		// When
		when(taskDAO.getTask(taskSearchFilter, USER_ID)).thenReturn(Arrays.asList(new Task(), new Task()));
		
		// Then
		List<Task> taskList = taskServiceImpl.getTask(taskSearchFilter, USER_ID);
		assertNotNull(taskList);
		assertEquals(taskList.size(), 2);
	}

	@Test
	@DisplayName("should return task history list for particular page request")
	public void testGetTaskHistory() {
		// Given
		PageRequest pageRequest = new PageRequest();
		PageResponse<List<TaskHistory>> pageResponse = new PageResponse<>();
		List<TaskHistory> arrayList = Arrays.asList(new TaskHistory(), new TaskHistory());
		pageResponse.setData(arrayList);
		
		// When
		when(taskDAO.getTaskHistory(USER_ID, pageRequest)).thenReturn(pageResponse);
		
		// Then
		PageResponse<List<TaskHistory>> taskHistory = taskServiceImpl.getTaskHistory(USER_ID, pageRequest);
		assertNotNull(taskHistory);
		assertEquals(taskHistory.getData().size(), 2);
	}
	
	@Test
	@DisplayName("should return task history total count for particular page request")
	public void testGetTaskHistoryTotalCount() {
		// Given
		int totalCount = 100;
		PageRequest pageRequest = new PageRequest();
		PageResponse<List<TaskHistory>> pageResponse = new PageResponse<>();
		pageResponse.setTotalCount(totalCount);
		
		// When
		when(taskDAO.getTaskHistory(USER_ID, pageRequest)).thenReturn(pageResponse);
		
		// Then
		PageResponse<List<TaskHistory>> taskHistory = taskServiceImpl.getTaskHistory(USER_ID, pageRequest);
		assertNotNull(taskHistory);
		assertEquals(taskHistory.getTotalCount(), totalCount);
	}
	
}
