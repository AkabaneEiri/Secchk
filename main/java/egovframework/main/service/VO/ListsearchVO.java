package egovframework.main.service.VO;

public class ListsearchVO { // 부여된 과업에 따른 체크리스트 검색용 vo
	private String findCode = "";	// form 에서 입력받아온 검색할 코드
	
	public String getFindCode() { return findCode; }
	public void setFindCode(String findCode) { this.findCode = findCode; }

	private String incdt_idtf_cd = "";	// 부대식별코드
	
	public String getIncdt_idtf_cd() { return incdt_idtf_cd; }
	public void setIncdt_idtf_cd(String incdt_idtf_cd) { this.incdt_idtf_cd = incdt_idtf_cd; }
	
	private String srvno = "";	// 로그인한 유저의 군번

	public String getSrvno() { return srvno; }
	public void setSrvno(String srvno) { this.srvno = srvno; }
	
	private String date = "";	// 일자 검색용

	public String getDate() { return date; }
	public void setDate(String date) { this.date = date; }	
	
	private String task = "";

	public String getTask() { return task; }
	public void setTask(String task) { this.task = task; }
	
	private String cmdNum = "";	// 지휘관 지침 클리어 용

	public String getCmdNum() { return cmdNum; }
	public void setCmdNum(String cmdNum) { this.cmdNum = cmdNum; }
	
	private String seq = "";	// 번호

	public String getSeq() { return seq; }
	public void setSeq(String seq) { this.seq = seq; }
	
	private String actId = ""; // 실 부대활동 ID

	public String getActId() { return actId; }
	public void setActId(String actId) { this.actId = actId; }	
	
	private String after_rqst_data_seq = "";

	public String getAfter_rqst_data_seq() {
		return after_rqst_data_seq;
	}
	public void setAfter_rqst_data_seq(String after_rqst_data_seq) {
		this.after_rqst_data_seq = after_rqst_data_seq;
	}
	
	private String state_cd = "";

	public String getState_cd() {
		return state_cd;
	}
	public void setState_cd(String state_cd) {
		this.state_cd = state_cd;
	}	
	
	private String search_by_name = "";	// 이름으로 검색

	public String getSearch_by_name() {
		return search_by_name;
	}
	public void setSearch_by_name(String search_by_name) {
		this.search_by_name = search_by_name;
	}	
}
