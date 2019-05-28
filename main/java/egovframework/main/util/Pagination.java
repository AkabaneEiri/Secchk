package egovframework.main.util;

import java.util.List;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

public class Pagination {
	private PaginationInfo paginationInfo;
	
	private int currentPage = 1;
	
	private int defaultPage = 1;
	
	private int countPerPage = 10;		// item cnt in 1 page
	
	private int pageSize = 10;			// view page total cnt
	
	private int totalRecordCount = 0;	// total cnt
	
	private int start = 0;
	private int end = 0;
	
	public Pagination() {
		paginationInfo = new PaginationInfo();
	}
	
	// set page view size
	public void setCountPerPage(int _countPerPage) { countPerPage = _countPerPage; }
	public void setPageSize(int _pageSize) { pageSize = _pageSize; }
	
	public void setPaginationInfo(List<?> _obj, int _currentPage) {
		totalRecordCount = _obj.size();
		currentPage = _currentPage;
		
		paginationInfo.setTotalRecordCount(totalRecordCount);
		paginationInfo.setRecordCountPerPage(countPerPage);
		paginationInfo.setPageSize(pageSize);
		
		paginationInfo.setCurrentPageNo(currentPage);
		
		setStartToEnd();
	}
	
	private void setStartToEnd() {
		start = (currentPage - 1) * 10;
		end = start + countPerPage;
		if (end > totalRecordCount)
			end = totalRecordCount;
	}
	
	public List<?> getListPerPage(List<?> _obj) {
		return _obj.subList(start, end);
	}
	
	public PaginationInfo getPaginationInfo() {
		return paginationInfo;
	}
}
