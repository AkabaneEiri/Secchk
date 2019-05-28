package egovframework.main.service.VO;

public class MemberlistVO {
		private	String 	srvno;			//군번
		private	String 	pw;				//비밀번호
		private	String 	rnkcd;			//계급코드
		private	String	stmt;			//성명
		private	String	rspofc_nm;		//직책
		private	String	athrt;		//권한코드
		private	String	fnl_login_date;	//최종로그인일자
		private	String	prtbl_telno;	//휴대전화번호
		private	String	login_err_rtrv;	//로그인오류횟수
		private	String	chng_date;	//비밀번호변경일자
		private	String	incdt_idtf_cd;	//부대식별코드
		private	String	montr;	//모니터링권한
		private	String	acc_state_info;		//계정상태정보
		
		public String getSrvno() {
			return srvno;
		}
		public void setSrvno(String srvno) {
			this.srvno = srvno;
		}
		public String getPw() {
			return pw;
		}
		public void setPw(String pw) {
			this.pw = pw;
		}
		public String getRnkcd() {
			return rnkcd;
		}
		public void setRnkcd(String rnkcd) {
			this.rnkcd = rnkcd;
		}
		public String getStmt() {
			return stmt;
		}
		public void setStmt(String stmt) {
			this.stmt = stmt;
		}
		public String getRspofc_nm() {
			return rspofc_nm;
		}
		public void setRspofc_nm(String rspofc_nm) {
			this.rspofc_nm = rspofc_nm;
		}
		public String getathrt() {
			return athrt;
		}
		public void setathrt(String athrt) {
			this.athrt = athrt;
		}
		public String getFnl_login_date() {
			return fnl_login_date;
		}
		public void setFnl_login_date(String fnl_login_date) {
			this.fnl_login_date = fnl_login_date;
		}
		public String getPrtbl_telno() {
			return prtbl_telno;
		}
		public void setPrtbl_telno(String prtbl_telno) {
			this.prtbl_telno = prtbl_telno;
		}
		public String getLogin_err_rtrv() {
			return login_err_rtrv;
		}
		public void setLogin_err_rtrv(String login_err_rtrv) {
			this.login_err_rtrv = login_err_rtrv;
		}
		public String getchng_date() {
			return chng_date;
		}
		public void setchng_date(String chng_date) {
			this.chng_date = chng_date;
		}
		public String getIncdt_idtf_cd() {
			return incdt_idtf_cd;
		}
		public void setIncdt_idtf_cd(String incdt_idtf_cd) {
			this.incdt_idtf_cd = incdt_idtf_cd;
		}
		public String getmontr() {
			return montr;
		}
		public void setmontr(String montr) {
			this.montr = montr;
		}
		public String getacc_state_info() {
			return acc_state_info;
		}
		public void setacc_state_info(String acc_state_info) {
			this.acc_state_info = acc_state_info;
		}
		
	}
