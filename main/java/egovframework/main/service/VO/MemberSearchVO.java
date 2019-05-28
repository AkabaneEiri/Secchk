package egovframework.main.service.VO;
 
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class MemberSearchVO {
	/** 검색어  */
    private String searchKeyword = "";
    private String searchKeyword2 = "";
    
    /** 검색조건 */
    private String searchCondition1 = "";
	private String searchCondition2 = "";
    private String searchCondition3 = "";
    private String searchCondition = "";
    private String searchConditionLarge = "";
    private String searchConditionMiddle = "";
    
    // 190520 add by seungwon ////////////
    private String searchAthrt = "";
    
    public String getSearchAthrt() { return searchAthrt; }
    public void setSearchAthrt(String searchAthrt) { this.searchAthrt = searchAthrt; }
    //////////////////////////////////////
	
  
	/** 검색사용여부 */
    private String searchUseYn = "";
    
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
    
    /** 관리자가 여러 건의 게시물을 한번에 삭제할 때 사용 */
    private List<String> delnum;
    
    private int totCnt;
 
    /** 게시물 번호를 String 값으로 전달하고자 할 때 사용 */
    private String selectWrtno;
    
    private String clsfct_cd;
    
    private String pw = "";
    
    private String searchCode;
    
    public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getSearchKeyword() {
		return searchKeyword;
	}

	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	public String getSearchKeyword2() {
		return searchKeyword2;
	}

	public void setSearchKeyword2(String searchKeyword2) {
		this.searchKeyword2 = searchKeyword2;
	}
	
	public String getSearchUseYn() {
		return searchUseYn;
	}

	public void setSearchUseYn(String searchUseYn) {
		this.searchUseYn = searchUseYn;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageUnit() {
		return pageUnit;
	}

	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getFirstIndex() {
		return firstIndex;
	}

	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	public int getLastIndex() {
		return lastIndex;
	}

	public void setLastIndex(int lastIndex) {
		this.lastIndex = lastIndex;
	}

	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}

	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getFindKey() {
		return findKey;
	}

	public void setFindKey(int findKey) {
		this.findKey = findKey;
	}

	public List<String> getDelnum() {
		return delnum;
	}

	public void setDelnum(List<String> delnum) {
		this.delnum = delnum;
	}

	public int getTotCnt() {
		return totCnt;
	}

	public void setTotCnt(int totCnt) {
		this.totCnt = totCnt;
	}

	public String getSelectWrtno() {
		return selectWrtno;
	}

	public void setSelectWrtno(String selectWrtno) {
		this.selectWrtno = selectWrtno;
	}

	public String getClsfct_cd() {
		return clsfct_cd;
	}

	public void setClsfct_cd(String clsfct_cd) {
		this.clsfct_cd = clsfct_cd;
	}
	public int getPageUnitPhoto() {
		return pageUnitPhoto;
	}
	public void setPageUnitPhoto(int pageUnitPhoto) {
		this.pageUnitPhoto = pageUnitPhoto;
	}
	public String getSearchCode() {
		return searchCode;
	}
	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}
    public String getSearchCondition1() {
		return searchCondition1;
	}
	public void setSearchCondition1(String searchCondition1) {
		this.searchCondition1 = searchCondition1;
	}
	public String getSearchCondition2() {
		return searchCondition2;
	}
	public void setSearchCondition2(String searchCondition2) {
		this.searchCondition2 = searchCondition2;
	}
	public String getSearchCondition3() {
		return searchCondition3;
	}
	public void setSearchCondition3(String searchCondition3) {
		this.searchCondition3 = searchCondition3;
	}
	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public void setSearchConditionLarge(String searchConditionLarge){
		this.searchConditionLarge = searchConditionLarge;
	}
	public String getSearchConditionLarge(){
		return searchConditionLarge;
	}
	public void setSearchConditionMiddle(String searchConditionMiddle){
		this.searchConditionMiddle = searchConditionMiddle;
	}
	public String getSearchConditionMiddle(){
		return searchConditionMiddle;
	}
	
}
