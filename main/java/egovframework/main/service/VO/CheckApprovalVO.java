package egovframework.main.service.VO;

public class CheckApprovalVO {
	private String rqst_date;
	private String rqstr_srvno;
	private String incdt_actvt_type_cd;
	private String actvt_date;
	private String ctlg_cd;
	private String ctlg_itm_cd;
	private String rsn;
	private String state_cd;
	private String state;
	private String seq;
	private String opn;
	
	// add 190325 //////////////////
	private String incdt_idtf_cd = "";
	private String rqstr_nm = "";	
	private String incdt_actvt_type_cd_nm = "";	
	private String ctlg_itm_nm = "";

	public String getCtlg_itm_nm() {
		return ctlg_itm_nm;
	}
	public void setCtlg_itm_nm(String ctlg_itm_nm) {
		this.ctlg_itm_nm = ctlg_itm_nm;
	}
	public String getIncdt_idtf_cd() {
		return incdt_idtf_cd;
	}
	public void setIncdt_idtf_cd(String incdt_idtf_cd) {
		this.incdt_idtf_cd = incdt_idtf_cd;
	}
	public String getRqstr_nm() {
		return rqstr_nm;
	}
	public String getIncdt_actvt_type_cd_nm() {
		return incdt_actvt_type_cd_nm;
	}
	public void setRqstr_nm(String rqstr_nm) {
		this.rqstr_nm = rqstr_nm;
	}
	public void setIncdt_actvt_type_cd_nm(String incdt_actvt_type_cd_nm) {
		this.incdt_actvt_type_cd_nm = incdt_actvt_type_cd_nm;
	}
	
	private String rqstr_rnk = "";

	public String getRqstr_rnk() {
		return rqstr_rnk;
	}
	public void setRqstr_rnk(String rqstr_rnk) {
		this.rqstr_rnk = rqstr_rnk;
	}
	////////////////////////////////
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getopn() {
		return opn;
	}
	public void setopn(String opn) {
		this.opn = opn;
	}
	public String getRqst_date() {
		return rqst_date;
	}
	public void setRqst_date(String rqst_date) {
		this.rqst_date = rqst_date;
	}
	public String getRqstr_srvno() {
		return rqstr_srvno;
	}
	public void setRqstr_srvno(String rqstr_srvno) {
		this.rqstr_srvno = rqstr_srvno;
	}
	public String getIncdt_actvt_type_cd() {
		return incdt_actvt_type_cd;
	}
	public void setIncdt_actvt_type_cd(String incdt_actvt_type_cd) {
		this.incdt_actvt_type_cd = incdt_actvt_type_cd;
	}
	public String getActvt_date() {
		return actvt_date;
	}
	public void setActvt_date(String actvt_date) {
		this.actvt_date = actvt_date;
	}
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
	public String getRsn() {
		return rsn;
	}
	public void setRsn(String rsn) {
		this.rsn = rsn;
	}
	public String getState_cd() {
		return state_cd;
	}
	public void setState_cd(String state_cd) {
		this.state_cd = state_cd;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	private String taskDataSeq = "";

	public String getTaskDataSeq() {
		return taskDataSeq;
	}
	public void setTaskDataSeq(String taskDataSeq) {
		this.taskDataSeq = taskDataSeq;
	}
	
	private String id = "";

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
