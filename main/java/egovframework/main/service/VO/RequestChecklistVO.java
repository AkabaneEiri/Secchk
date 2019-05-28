package egovframework.main.service.VO;

public class RequestChecklistVO {
	private String seq="";
	private String rqst_date = "";				// 요청일자
	private String rqstr_srvno = "";			// 요청자 군번
	private String ctlg_cd = "";					// 체크리스트 코드
	private String ctlg_itm_cd_1 = "";		// 변경전 코드
	private String ctlg_itm_cd_2 = "";		// 변경후 코드
	public String state_cd = "";				// 현재 상태	
	private String rqst_ctnt = "";				// 건의 내용	
	private String rsn = "";							// 요청 사유
	private String new_yn = "";					// 신규여부
	private String ctlg_nm = "";					// 체크리스트 활동 명	
	private String rvw_opn = "";				//검토의견
	private String opn= "";
	
	// add 190325 //////////////////////////
	private String ctlg_itm_nm = "";				// 항목 내용
	private String rqstr_nm = "";					// 요청자 이름
	
	public String getRqstr_nm() {
		return rqstr_nm;
	}
	public void setRqstr_nm(String rqstr_nm) {
		this.rqstr_nm = rqstr_nm;
	}
	public String getCtlg_itm_nm() {
		return ctlg_itm_nm;
	}
	public void setCtlg_itm_nm(String ctlg_itm_nm) {
		this.ctlg_itm_nm = ctlg_itm_nm;
	}	
	
	private String rqstr_rnk = "";

	public String getRqstr_rnk() {
		return rqstr_rnk;
	}
	public void setRqstr_rnk(String rqstr_rnk) {
		this.rqstr_rnk = rqstr_rnk;
	}
	////////////////////////////////////////
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getRqst_date() {
		return rqst_date;
	}
	public String getRqstr_srvno() {
		return rqstr_srvno;
	}
	public String getCtlg_cd() {
		return ctlg_cd;
	}
	public String getCtlg_itm_cd_1() {
		return ctlg_itm_cd_1;
	}
	public String getCtlg_itm_cd_2() {
		return ctlg_itm_cd_2;
	}
	public void setCtlg_itm_cd_1(String ctlg_itm_cd_1) {
		this.ctlg_itm_cd_1 = ctlg_itm_cd_1;
	}

	public String getState_cd() {
		return state_cd;
	}
	public String getRqst_ctnt() {
		return rqst_ctnt;
	}
	public String getRsn() {
		return rsn;
	}
	public String getNew_yn() {
		return new_yn;
	}
	public String getCtlg_nm() {
		return ctlg_nm;
	}
	
	
	
	// set
	public void setRqst_date(String rqst_date) {
		this.rqst_date = rqst_date;
	}
	public void setRqstr_srvno(String rqstr_srvno) {
		this.rqstr_srvno = rqstr_srvno;
	}
	public void setCtlg_cd(String ctlg_cd) {
		this.ctlg_cd = ctlg_cd;
	}
	public void setCtlg_itm_cd_2(String ctlg_itm_cd_2) {
		this.ctlg_itm_cd_2 = ctlg_itm_cd_2;
	}
	public void setState_cd(String state_cd) {
		this.state_cd = state_cd;
	}
	public void setNew_yn(String new_yn) {
		this.new_yn = new_yn;
	}	
	public void setCtlg_nm(String ctlg_nm) {
		this.ctlg_nm = ctlg_nm;
	}
	public void setRqst_ctnt(String rqst_ctnt) {
		this.rqst_ctnt = rqst_ctnt;
	}
	public void setRsn(String rsn) {
		this.rsn = rsn;
	}
	public String getRvw_opn() {
		return rvw_opn;
	}
	public void setRvw_opn(String rvw_opn) {
		this.rvw_opn = rvw_opn;
	}

	private String incdt_idtf_cd = "";		// 부대 식별 코드

	public String getIncdt_idtf_cd() {
		return incdt_idtf_cd;
	}
	public void setIncdt_idtf_cd(String incdt_idtf_cd) {
		this.incdt_idtf_cd = incdt_idtf_cd;
	}
	public String getOpn() {
		return opn;
	}
	public void setOpn(String opn) {
		this.opn = opn;
	}	
}
