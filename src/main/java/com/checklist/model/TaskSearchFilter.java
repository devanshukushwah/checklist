package com.checklist.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskSearchFilter {
	private Date createdFrom;
	private Date createdTo;
	private Boolean status;
}
