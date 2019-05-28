package egovframework.main.service.VO;

public class CodeSearchVO {
	private String search_dvs = "";	// 찾은 코드 분류
	private String search_nm = "";	// 찾을 코드 명

	/** 현재페이지 */
    private int pageIndex = 1;
    
    /** 페이지갯수. 한 화면에 보여질 게시물 갯수 */
    private int pageUnit = 10;
    
    /** 갤러리 게시판용 페이지갯수 */
    private int pageUnitPhoto = 6;
    
    /** 페이지사이즈. 10이 넘으면 '다음' 표시  */
    private int pageSize = 10;

    /** firstIndex */
    private int firstIndex = 1;

    /** lastIndex */
    private int lastIndex = 1;

    /** recordCountPerPage */
    private int recordCountPerPage = 10;
    
    private int start = 10;
    
    private int end = 0;
    
    private int findKey ;
    

    // get
	public String getSearch_dvs() {
		return search_dvs;
	}
	
	public String getSearch_nm() {
		return search_nm;
	}	
	
	public int getPageIndex() {
		return pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public int getPageUnitPhoto() {
		return pageUnitPhoto;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public int getFindKey() {
		return findKey;
	}
	
	
	// set
	public void setSearch_dvs(String search_dvs) {
		this.search_dvs = search_dvs;
	}
	
	public void setSearch_nm(String search_nm) {
		this.search_nm = search_nm;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public void setPageUnitPhoto(int pageUnitPhoto) {
		this.pageUnitPhoto = pageUnitPhoto;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public void setFindKey(int findKey) {
		this.findKey = findKey;
	}
	
	private String search_condition = "";


	public String getSearch_condition() {
		return search_condition;
	}

	public void setSearch_condition(String search_condition) {
		this.search_condition = search_condition;
	}
}
