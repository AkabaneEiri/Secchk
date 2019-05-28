package egovframework.main.service.VO;

public class RequestActivityVO {
	private String seq									="";		//인덱스
	private String rqst_date 						= "";		// 요청 일자
	private String rqstr_srvno				 	= "";		// 요청자 군번
	private String actvt_date 					= "";		// 활동일자
	private String rsn 									= "";		// 사유
	public String state_cd 						= "";		// 상태코드
	private String incdt_actvt_type_cd	 	= "";		// 부대활동유형코드
	private String opn							="";		// 검토 의견
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	private String ctlg_cd = "";				// 체크리스트 코드 형식으로 입력되었을 경우 사용	
	private String incdt_actvt_type_cd_nm = "";	// 부대활동 명
		
		// get
	public String getRqst_date() { return rqst_date; }
	public String getRqstr_srvno() { return rqstr_srvno; }
	public String getActvt_date() { return actvt_date; }
	public String getRsn() { return rsn; }
	public String getState_cd() { return state_cd; }
	public String getIncdt_actvt_type_cd() { return incdt_actvt_type_cd; }
	
	public String getCtlg_cd() { return ctlg_cd; }
	public String getIncdt_actvt_type_cd_nm() { return incdt_actvt_type_cd_nm; }
	
	// set
	public void setRqst_date(String rqst_date) { this.rqst_date = rqst_date; }
	public void setRqstr_srvno(String rqstr_srvno) { this.rqstr_srvno = rqstr_srvno; }
	public void setActvt_date(String actvt_date) { this.actvt_date = actvt_date; }
	public void setRsn(String rsn) { this.rsn = rsn; }
	public void setState_cd(String state_cd) { this.state_cd = state_cd; }
	public void setIncdt_actvt_type_cd(String incdt_actvt_type_cd) { this.incdt_actvt_type_cd = incdt_actvt_type_cd; }
	
	public void setCtlg_cd(String ctlg_cd) { this.ctlg_cd = ctlg_cd; }
	public void setIncdt_actvt_type_cd_nm(String incdt_actvt_type_cd_nm) { this.incdt_actvt_type_cd_nm = incdt_actvt_type_cd_nm; }

	public String getopn() {
		return opn;
	}
	public void setopn(String opn) {
		this.opn = opn;
	}
	
	// add 190325
	private String rqstr_nm = "";
	private String rqstr_rnk = "";

	public String getRqstr_rnk() {
		return rqstr_rnk;
	}
	public void setRqstr_rnk(String rqstr_rnk) {
		this.rqstr_rnk = rqstr_rnk;
	}
	public String getRqstr_nm() {
		return rqstr_nm;
	}
	public void setRqstr_nm(String rqstr_nm) {
		this.rqstr_nm = rqstr_nm;
	}	
	
	private String incdt_idtf_cd = "";	// 부대 식별코드

	public String getIncdt_idtf_cd() {
		return incdt_idtf_cd;
	}
	public void setIncdt_idtf_cd(String incdt_idtf_cd) {
		this.incdt_idtf_cd = incdt_idtf_cd;
	}
	
	
}
