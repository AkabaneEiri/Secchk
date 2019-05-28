package egovframework.main.service.VO;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TaskVO {
	public String incdt_idtf_cd;		//부대식별코드
	public String incdt_actvt_type_cd;	//부대활동유형코드
	public String actvt_date;			//활동일자
	public String task;					//과업
	public String task_psnchnrg_srvno;	//과업담당자군번
	public String state_cd;				//상태코드
	public String ctlg_cd;				//체크리스트 코드
	public String ctlg_nm;				//체크리스트 이름
	public String lrgcls;				//체크리스트 대분류
	public String lrgcls_nm;			//체크리스트 대분류명
	public String mdcls;				//체크리스트 중분류
	public String mdcls_nm;				//체크리스트 중분류명
	public String cd;					//코드명
	public String cd_nm;				//코드이름
	public String getLrgcls_nm() {
		return lrgcls_nm;
	}
	public void setLrgcls_nm(String lrgcls_nm) {
		this.lrgcls_nm = lrgcls_nm;
	}
	public String getMdcls_nm() {
		return mdcls_nm;
	}
	public void setMdcls_nm(String mdcls_nm) {
		this.mdcls_nm = mdcls_nm;
	}

	public String rmrk;					//코드비고
	public String task_psnchnrg_rnk;
	public String getCtlg_cd() {
		return ctlg_cd;
	}
	public void setCtlg_cd(String ctlg_cd) {
		this.ctlg_cd = ctlg_cd;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getCd_nm() {
		return cd_nm;
	}
	public void setCd_nm(String cd_nm) {
		this.cd_nm = cd_nm;
	}
	public String getRmrk() {
		return rmrk;
	}
	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
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
	public String getIncdt_idtf_cd() {
		return incdt_idtf_cd;
	}
	public void setIncdt_idtf_cd(String incdt_idtf_cd) {
		this.incdt_idtf_cd = incdt_idtf_cd;
	}
	public String getIncdt_actvt_type_cd() {
		return incdt_actvt_type_cd;
	}
	public void setIncdt_actvt_type_cd(String incdt_actvy_type_cd) {
		this.incdt_actvt_type_cd = incdt_actvy_type_cd;
	}
	public String getActvt_date() {
		return actvt_date;
	}
	public void setActvt_date(String actvt_date) {
		this.actvt_date = actvt_date;
	}
	public String getTask() {
		return task;
	}
	public void setTask(String task) {
		this.task = task;
	}
	public String getTask_psnchnrg_srvno() {
		return task_psnchnrg_srvno;
	}
	public void setTask_psnchnrg_srvno(String task_psnchnrg_srvno) {
		this.task_psnchnrg_srvno = task_psnchnrg_srvno;
	}
	public String getState_cd() {
		return state_cd;
	}

	public void setState_cd(String state_cd) {
		this.state_cd = state_cd;
	}

	private String start_date = ""; // 현재 상태 코드
	private String fnsh_date = ""; // 시작시간

	public String getStart_date() {
		return start_date;
	}

	public String getFnsh_date() {
		return fnsh_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public void setFnsh_date(String fnsh_date) {
		this.fnsh_date = fnsh_date;
	}

	private String task_psnchnrg_nm = ""; // 과업 담당자 이름

	public String getTask_psnchnrg_nm() {
		return task_psnchnrg_nm;
	}

	public void setTask_psnchnrg_nm(String task_psnchnrg_nm) {
		this.task_psnchnrg_nm = task_psnchnrg_nm;
	}

	private String incdt_actvt_type_cd_nm = ""; // 활동명

	public String getIncdt_actvt_type_cd_nm() {
		return incdt_actvt_type_cd_nm;
	}

	public void setIncdt_actvt_type_cd_nm(String incdt_actvt_type_cd_nm) {
		this.incdt_actvt_type_cd_nm = incdt_actvt_type_cd_nm;
	}
    public String toStringsimple() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }  
    
    // add 190328
    private String seq = "";
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	public String getTask_psnchnrg_rnk() {
		return task_psnchnrg_rnk;
	}
	public void setTask_psnchnrg_rnk(String task_psnchnrg_rnk) {
		this.task_psnchnrg_rnk = task_psnchnrg_rnk;
	}
	
	// add 190412 //
	private String id = "";
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
