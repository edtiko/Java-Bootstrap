package com.premize.sgp.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.premize.sgp.dto.PagingCriteria;
import com.premize.sgp.dto.Search;
import com.premize.sgp.dto.SortField;
import com.premize.sgp.dto.TableParam;


/**
 * 
 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
 * @project sgp-api
 * @class TableParamArgumentResolver
 * @since 27/03/2014
 *
 */
@EnableWebMvc
public class TableParamArgumentResolver implements WebArgumentResolver {
	
	private static final String S_ECHO = "sEcho";
	private static final String I_DISPLAY_START = "iDisplayStart";
	private static final String I_DISPLAY_LENGTH = "iDisplayLength";
	private static final String I_SORTING_COLS = "iSortingCols";
	
	private static final String I_SORT_COLS = "iSortCol_";
	private static final String S_SORT_DIR = "sSortDir_";
	private static final String S_DATA_PROP = "mDataProp_";
	private static final String S_SEARCH = "sSearch";
	private static final String S_SEARCHES = "sSearch_";
	
	private static final String I_COLUMNS = "iColumns";
	
	/**
	 * 
	 * @author <a href="mailto:edwin.gomez@premize.com">Edwin Gómez</a>
	 * @since 27/03/2014
	 * @see org.springframework.web.bind.support.WebArgumentResolver#resolveArgument(org.springframework.core.MethodParameter, org.springframework.web.context.request.NativeWebRequest)
	 */
	public Object resolveArgument(MethodParameter param, NativeWebRequest request) throws Exception {
		TableParam tableParamAnnotation = param.getParameterAnnotation(TableParam.class);
		
		if (tableParamAnnotation != null) {
			HttpServletRequest httpRequest = (HttpServletRequest) request.getNativeRequest();
			
			String sEcho = httpRequest.getParameter(S_ECHO);
			String sDisplayStart = httpRequest.getParameter(I_DISPLAY_START);
			String sDisplayLength = httpRequest.getParameter(I_DISPLAY_LENGTH);
			String sSortingCols = httpRequest.getParameter(I_SORTING_COLS);
			String sColumns = httpRequest.getParameter(I_COLUMNS);
			String sSearch = httpRequest.getParameter(S_SEARCH);
			
			Integer iEcho = Integer.parseInt(sEcho);
			Integer iDisplayStart = Integer.parseInt(sDisplayStart);
			Integer iDisplayLength = Integer.parseInt(sDisplayLength);
			Integer iSortingCols = Integer.parseInt(sSortingCols);
			Integer iColumns = Integer.parseInt(sColumns);
			
			List<SortField> sortFields = new ArrayList<SortField>();
			
			for (int colCount = 0; colCount < iSortingCols; colCount++) {
				String sSortCol = httpRequest.getParameter(I_SORT_COLS + colCount);
				String sSortDir = httpRequest.getParameter(S_SORT_DIR + colCount);
				String sColName = httpRequest.getParameter(S_DATA_PROP + sSortCol);
				sortFields.add(new SortField(sColName, sSortDir));
			}
			
			List<Search> listSearchs = new ArrayList<Search>();
			for (int i = 0; i < iColumns; i++) {
				String sColName = httpRequest.getParameter(S_DATA_PROP + i);
				String sSearchi = httpRequest.getParameter(S_SEARCHES + i);
				listSearchs.add(new Search(sColName, sSearchi));
			}
			
			PagingCriteria pc = new PagingCriteria(sSearch, listSearchs, iDisplayStart, iDisplayLength, iEcho,
					sortFields);
			
			return pc;
		}
		
		return WebArgumentResolver.UNRESOLVED;
	}
}