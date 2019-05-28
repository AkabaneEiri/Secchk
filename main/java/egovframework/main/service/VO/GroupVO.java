package egovframework.main.service.VO;

public class GroupVO {
	private String ctlg_cd;		//작업 코드
	private String ctlg_nm;	//작업 이름
	private String lrgcls;			//작업 대분류
	private String mdcls;		//작업 소분류
	
	
	public String getCtlg_cd() {
		return ctlg_cd;
	}
	public void setCtlg_cd(String ctlg_cd) {
		this.ctlg_cd = ctlg_cd;
	}
	public String getCtlg_nm() {
		return ctlg_nm;
	}
	public void setCtlg_nm(String ctlg_nm) {
		this.ctlg_nm = ctlg_nm;
	}
	public String getLrgcls() {
		return lrgcls;
	}
	public void setLrgcls(String lrgcls) {
		this.lrgcls = lrgcls;
	}
	public String getMdcls() {
		return mdcls;
	}
	public void setMdcls(String mdcls) {
		this.mdcls = mdcls;
	}

}
