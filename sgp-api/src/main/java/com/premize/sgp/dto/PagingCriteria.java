/**
 * 
 */
package com.premize.sgp.dto;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-web
 * @class PagingCriteria
 * @since 21/01/2014
 *
 */
public final class PagingCriteria {

	private final String search;
	private final List<Search> searchFields;
    private final Integer displayStart;
    private final Integer displaySize;
    private final Integer pageNumber;
    private final List<SortField> sortFields;

    public PagingCriteria(String search, List<Search> searchFields, Integer displayStart, Integer displaySize,
                    Integer pageNumber, List<SortField> sortFields) {
    	    this.search = search;
            this.searchFields = searchFields;
            this.displayStart = displayStart;
            this.displaySize = displaySize;
            this.pageNumber = pageNumber;
            this.sortFields = sortFields;
    }
    
    

	public List<Search> getSearchFields() {
		return Collections.unmodifiableList(searchFields);
    }

    public Integer getDisplayStart() {
            return displayStart;
    }

    public Integer getDisplaySize() {
            return displaySize;
    }

    public Integer getPageNumber() {
            return pageNumber;
    }

    public List<SortField> getSortFields() {
            return Collections.unmodifiableList(sortFields);
    }

	public String getSearch() {
		return search;
	}

}
