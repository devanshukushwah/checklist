package com.checklist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A generic class representing a paginated response.
 * 
 * @param <T> the type of data contained in the response
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {
    
    /**
     * The total count of items in the paginated response.
     */
    private int totalCount;

    /**
     * The data of the current page.
     */
    private T data;
}
