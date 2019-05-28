package egovframework.main.service.VO;

public class CheckCodeVO {
	private String ctlg_cd;			//체크리스트 코드
	private String ctlg_nm;		//체크리스트 이름
	private String lrgcls;			//체크리스트 대분류
	private String mdcls;				//체크리스트 중분류
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
