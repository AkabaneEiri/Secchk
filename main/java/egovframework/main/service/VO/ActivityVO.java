package egovframework.main.service.VO;

// 부대 활동 관리
public class ActivityVO {	
	private String incdt_idtf_cd = "";			// 부대식별코드
	private String incdt_actvt_type_cd = "";	// 부대활동유형코드
	
	private String incdt_actvt_type_cd_nm = "";	// 부대활동 명	
	
	private String state_cd = "";				// 상태코드	
	private String actvt_date = "";				// 활동일자	
	private String start_date = "";				// 시작시간
	private String fnsh_date = "";				// 종료시간
	
	private String guidnc_1 = "";				// 지휘관지침1
	private String guidnc_2 = "";				// 지휘관지침2
	private String guidnc_3 = "";				// 지휘관지침3
	private String guidnc_yn_1 = "";			// 지휘관지침1	
	private String guidnc_yn_2 = "";			// 지휘관지침2
	private String guidnc_yn_3 = "";
	
	private String ctlg_itm_cd_1 = "";			// 체크리스트 항목 1...
	private String ctlg_itm_cd_2 = "";	
	private String ctlg_itm_cd_3 = "";
	private String ctlg_itm_cd_4 = "";
	private String ctlg_itm_cd_5 = "";
	private String ctlg_itm_cd_6 = "";
	private String ctlg_itm_cd_7 = "";
	private String ctlg_itm_cd_8 = "";
	private String ctlg_itm_cd_9 = "";
	private String ctlg_itm_cd_10 = "";
	private String ctlg_itm_yn_1 = "";			// 체크리스트 항목 체크여부 1...
	private String ctlg_itm_yn_2 = "";	
	private String ctlg_itm_yn_3 = "";
	private String ctlg_itm_yn_4 = "";
	private String ctlg_itm_yn_5 = "";
	private String ctlg_itm_yn_6 = "";
	private String ctlg_itm_yn_7 = "";
	private String ctlg_itm_yn_8 = "";
	private String ctlg_itm_yn_9 = "";
	private String ctlg_itm_yn_10 = "";	

	private String ctlg_itm_cd_1_nm = "";	// 체크리스트 항목 1 내용...
	private String ctlg_itm_cd_2_nm = "";	
	private String ctlg_itm_cd_3_nm = "";
	private String ctlg_itm_cd_4_nm = "";
	private String ctlg_itm_cd_5_nm = "";
	private String ctlg_itm_cd_6_nm = "";
	private String ctlg_itm_cd_7_nm = "";
	private String ctlg_itm_cd_8_nm = "";
	private String ctlg_itm_cd_9_nm = "";
	private String ctlg_itm_cd_10_nm = "";
	
	// get
	public String getIncdt_idtf_cd() { return incdt_idtf_cd; }	
	public String getIncdt_actvt_type_cd() { return incdt_actvt_type_cd; }		
	public String getState_cd() { return state_cd; }	
	public String getActvt_date() { return actvt_date; }	
	public String getguidnc_1() { return guidnc_1; }
	public String getguidnc_2() { return guidnc_2; }	
	public String getguidnc_3() { return guidnc_3; }	
	public String getStart_date() { return start_date; }
	public String getFnsh_date() { return fnsh_date; }
	
	// set
	public void setIncdt_idtf_cd(String incdt_idtf_cd) { this.incdt_idtf_cd = incdt_idtf_cd; }
	public void setIncdt_actvt_type_cd(String incdt_actvt_type_cd) { this.incdt_actvt_type_cd = incdt_actvt_type_cd; }
	public void setState_cd(String state_cd) { this.state_cd = state_cd; }
	public void setActvt_date(String actvt_date) { this.actvt_date = actvt_date; }
	public void setguidnc_1(String guidnc_1) { this.guidnc_1 = guidnc_1; }
	public void setguidnc_2(String guidnc_2) { this.guidnc_2 = guidnc_2; }
	public void setguidnc_3(String guidnc_3) { this.guidnc_3 = guidnc_3; }
	public void setStart_date(String start_date) { this.start_date = start_date; }
	public void setFnsh_date(String fnsh_date) { this.fnsh_date = fnsh_date; }
	
	// 추가 get set 함수
	public String getguidnc_yn_1() { return guidnc_yn_1; }
	public void setguidnc_yn_1(String guidnc_yn_1) { this.guidnc_yn_1 = guidnc_yn_1; }
	public String getguidnc_yn_2() { return guidnc_yn_2; }
	public void setguidnc_yn_2(String guidnc_yn_2) { this.guidnc_yn_2 = guidnc_yn_2; }
	public String getguidnc_yn_3() { return guidnc_yn_3; }
	public void setguidnc_yn_3(String guidnc_yn_3) { this.guidnc_yn_3 = guidnc_yn_3; }
	public String getCtlg_itm_cd_1() { return ctlg_itm_cd_1; }
	public void setCtlg_itm_cd_1(String ctlg_itm_cd_1) { this.ctlg_itm_cd_1 = ctlg_itm_cd_1; }
	public String getCtlg_itm_cd_2() { return ctlg_itm_cd_2; }
	public void setCtlg_itm_cd_2(String ctlg_itm_cd_2) { this.ctlg_itm_cd_2 = ctlg_itm_cd_2; }
	public String getCtlg_itm_cd_3() { return ctlg_itm_cd_3; }
	public void setCtlg_itm_cd_3(String ctlg_itm_cd_3) { this.ctlg_itm_cd_3 = ctlg_itm_cd_3; }
	public String getCtlg_itm_cd_4() { return ctlg_itm_cd_4; }
	public void setCtlg_itm_cd_4(String ctlg_itm_cd_4) { this.ctlg_itm_cd_4 = ctlg_itm_cd_4; }
	public String getCtlg_itm_cd_5() { return ctlg_itm_cd_5; }
	public void setCtlg_itm_cd_5(String ctlg_itm_cd_5) { this.ctlg_itm_cd_5 = ctlg_itm_cd_5; }
	public String getCtlg_itm_cd_6() { return ctlg_itm_cd_6; }
	public void setCtlg_itm_cd_6(String ctlg_itm_cd_6) { this.ctlg_itm_cd_6 = ctlg_itm_cd_6; }
	public String getCtlg_itm_cd_7() { return ctlg_itm_cd_7; }
	public void setCtlg_itm_cd_7(String ctlg_itm_cd_7) { this.ctlg_itm_cd_7 = ctlg_itm_cd_7; }
	public String getCtlg_itm_cd_8() { return ctlg_itm_cd_8; }
	public void setCtlg_itm_cd_8(String ctlg_itm_cd_8) { this.ctlg_itm_cd_8 = ctlg_itm_cd_8; }
	public String getCtlg_itm_cd_9() { return ctlg_itm_cd_9; }
	public void setCtlg_itm_cd_9(String ctlg_itm_cd_9) { this.ctlg_itm_cd_9 = ctlg_itm_cd_9; }
	public String getCtlg_itm_cd_10() { return ctlg_itm_cd_10; }
	public void setCtlg_itm_cd_10(String ctlg_itm_cd_10) { this.ctlg_itm_cd_10 = ctlg_itm_cd_10; }
	public String getCtlg_itm_yn_1() { return ctlg_itm_yn_1; }
	public void setCtlg_itm_yn_1(String ctlg_itm_yn_1) { this.ctlg_itm_yn_1 = ctlg_itm_yn_1; }
	public String getCtlg_itm_yn_2() { return ctlg_itm_yn_2; }
	public void setCtlg_itm_yn_2(String ctlg_itm_yn_2) { this.ctlg_itm_yn_2 = ctlg_itm_yn_2; }
	public String getCtlg_itm_yn_3() { return ctlg_itm_yn_3; }
	public void setCtlg_itm_yn_3(String ctlg_itm_yn_3) { this.ctlg_itm_yn_3 = ctlg_itm_yn_3; }
	public String getCtlg_itm_yn_4() { return ctlg_itm_yn_4; }
	public void setCtlg_itm_yn_4(String ctlg_itm_yn_4) { this.ctlg_itm_yn_4 = ctlg_itm_yn_4; }
	public String getCtlg_itm_yn_5() { return ctlg_itm_yn_5; }
	public void setCtlg_itm_yn_5(String ctlg_itm_yn_5) { this.ctlg_itm_yn_5 = ctlg_itm_yn_5; }
	public String getCtlg_itm_yn_6() { return ctlg_itm_yn_6; }
	public void setCtlg_itm_yn_6(String ctlg_itm_yn_6) { this.ctlg_itm_yn_6 = ctlg_itm_yn_6; }
	public String getCtlg_itm_yn_7() { return ctlg_itm_yn_7; }
	public void setCtlg_itm_yn_7(String ctlg_itm_yn_7) { this.ctlg_itm_yn_7 = ctlg_itm_yn_7; }
	public String getCtlg_itm_yn_8() { return ctlg_itm_yn_8; }
	public void setCtlg_itm_yn_8(String ctlg_itm_yn_8) { this.ctlg_itm_yn_8 = ctlg_itm_yn_8; }
	public String getCtlg_itm_yn_9() { return ctlg_itm_yn_9; }
	public void setCtlg_itm_yn_9(String ctlg_itm_yn_9) { this.ctlg_itm_yn_9 = ctlg_itm_yn_9; }
	public String getCtlg_itm_yn_10() { return ctlg_itm_yn_10; }
	public void setCtlg_itm_yn_10(String ctlg_itm_yn_10) { this.ctlg_itm_yn_10 = ctlg_itm_yn_10; }
	
	// 부대 활동 명
	public String getIncdt_actvt_type_cd_nm() {
		return incdt_actvt_type_cd_nm;
	}
	public void setIncdt_actvt_type_cd_nm(String incdt_actvt_type_cd_nm) {
		this.incdt_actvt_type_cd_nm = incdt_actvt_type_cd_nm;
	}
	
	// 항목 이름
	public String getCtlg_itm_cd_1_nm() {
		return ctlg_itm_cd_1_nm;
	}
	public void setCtlg_itm_cd_1_nm(String ctlg_itm_cd_1_nm) {
		this.ctlg_itm_cd_1_nm = ctlg_itm_cd_1_nm;
	}
	public String getCtlg_itm_cd_2_nm() {
		return ctlg_itm_cd_2_nm;
	}
	public void setCtlg_itm_cd_2_nm(String ctlg_itm_cd_2_nm) {
		this.ctlg_itm_cd_2_nm = ctlg_itm_cd_2_nm;
	}
	public String getCtlg_itm_cd_3_nm() {
		return ctlg_itm_cd_3_nm;
	}
	public void setCtlg_itm_cd_3_nm(String ctlg_itm_cd_3_nm) {
		this.ctlg_itm_cd_3_nm = ctlg_itm_cd_3_nm;
	}
	public String getCtlg_itm_cd_4_nm() {
		return ctlg_itm_cd_4_nm;
	}
	public void setCtlg_itm_cd_4_nm(String ctlg_itm_cd_4_nm) {
		this.ctlg_itm_cd_4_nm = ctlg_itm_cd_4_nm;
	}
	public String getCtlg_itm_cd_5_nm() {
		return ctlg_itm_cd_5_nm;
	}
	public void setCtlg_itm_cd_5_nm(String ctlg_itm_cd_5_nm) {
		this.ctlg_itm_cd_5_nm = ctlg_itm_cd_5_nm;
	}
	public String getCtlg_itm_cd_6_nm() {
		return ctlg_itm_cd_6_nm;
	}
	public void setCtlg_itm_cd_6_nm(String ctlg_itm_cd_6_nm) {
		this.ctlg_itm_cd_6_nm = ctlg_itm_cd_6_nm;
	}
	public String getCtlg_itm_cd_7_nm() {
		return ctlg_itm_cd_7_nm;
	}
	public void setCtlg_itm_cd_7_nm(String ctlg_itm_cd_7_nm) {
		this.ctlg_itm_cd_7_nm = ctlg_itm_cd_7_nm;
	}
	public String getCtlg_itm_cd_8_nm() {
		return ctlg_itm_cd_8_nm;
	}
	public void setCtlg_itm_cd_8_nm(String ctlg_itm_cd_8_nm) {
		this.ctlg_itm_cd_8_nm = ctlg_itm_cd_8_nm;
	}
	public String getCtlg_itm_cd_9_nm() {
		return ctlg_itm_cd_9_nm;
	}
	public void setCtlg_itm_cd_9_nm(String ctlg_itm_cd_9_nm) {
		this.ctlg_itm_cd_9_nm = ctlg_itm_cd_9_nm;
	}
	public String getCtlg_itm_cd_10_nm() {
		return ctlg_itm_cd_10_nm;
	}
	public void setCtlg_itm_cd_10_nm(String ctlg_itm_cd_10_nm) {
		this.ctlg_itm_cd_10_nm = ctlg_itm_cd_10_nm;
	}
	
	// add 190412
	private String id = "";

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
