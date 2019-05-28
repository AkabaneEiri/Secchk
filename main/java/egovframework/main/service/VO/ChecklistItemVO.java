package egovframework.main.service.VO;

public class ChecklistItemVO {
	public String ctlg_cd = "";			// 체크리스트 코드
	public String ctlg_itm_cd = "";		// 체크리스트 항목 코드
	public String ctlg_itm_ctnt = "";		// 체크리스트 내용
	public String stdd_yn = "";		// 표준 항목 여부 yes or no
	public int prtcuse_frqc = 0;		// 활용빈도 count
	public String prtcust_frqc_String = "";
	
	public String getCtlg_cd() {
		return ctlg_cd;
	}
	public void setCtlg_cd(String ctlg_cd) {
		this.ctlg_cd = ctlg_cd;
	}
	public String getCtlg_itm_cd() {
		return ctlg_itm_cd;
	}
	public void setCtlg_itm_cd(String ctlg_itm_cd) {
		this.ctlg_itm_cd = ctlg_itm_cd;
	}
	public String getCtlg_itm_ctnt() {
		return ctlg_itm_ctnt;
	}
	public void setCtlg_itm_ctnt(String ctlg_itm_ctnt) {
		this.ctlg_itm_ctnt = ctlg_itm_ctnt;
	}
	public String getstdd_yn() {
		return stdd_yn;
	}
	public void setstdd_yn(String stdd_yn) {
		this.stdd_yn = stdd_yn;
	}
	public int getPrtcuse_frqc() {
		return prtcuse_frqc;
	}
	public int setPrtcuse_frqc(int prtcuse_frqc) {
		return this.prtcuse_frqc = prtcuse_frqc;
	}
	public String getPrtcust_frqc_String() {
		return prtcust_frqc_String;
	}
	public void setPrtcust_frqc_String(String prtcust_frqc_String) {
		this.prtcust_frqc_String = prtcust_frqc_String;
	}
}
