package egovframework.main.service.VO;

public class CtlgCodeVO {

	private String ctlg_cd;
	private String ctlg_nm;
	private String lrgcls;
	
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
	private String mdcls;
	
	
	private String lrgcls_nm = "";
	private String mdcls_nm = "";

	public String getLrgcls_nm() {
		return lrgcls_nm;
	}
	public String getMdcls_nm() {
		return mdcls_nm;
	}
	public void setLrgcls_nm(String lrgcls_nm) {
		this.lrgcls_nm = lrgcls_nm;
	}
	public void setMdcls_nm(String mdcls_nm) {
		this.mdcls_nm = mdcls_nm;
	}
}
