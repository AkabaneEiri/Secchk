package egovframework.main.service.VO;

public class GroupCodeVO {
	private String cd = "";			// 그룹 코드
	private String cd_nm = "";		// 코드명
	private String rmrk = "";		// 비고
	
	private String chng_cd_nm = "";	// 바꿀 코드 명	
	private String chng_rmrk = "";	// 바꿀 비고
	
	public String getCd() {
		return cd;
	}
	public String getCd_nm() {
		return cd_nm;
	}
	public String getRmrk() {
		return rmrk;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public void setCd_nm(String cd_nm) {
		this.cd_nm = cd_nm;
	}
	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
	}
	
	public String getChng_cd_nm() {
		return chng_cd_nm;
	}
	public String getChng_rmrk() {
		return chng_rmrk;
	}
	public void setChng_cd_nm(String chng_cd_nm) {
		this.chng_cd_nm = chng_cd_nm;
	}
	public void setChng_rmrk(String chng_rmrk) {
		this.chng_rmrk = chng_rmrk;
	}
}
