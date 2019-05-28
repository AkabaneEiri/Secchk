package egovframework.main.service.VO;
 
public class MemberVO {

		private String srvno;
		private String pw;
		private String rnkcd;
		private String cd_nm;
		private String stmt;
		private String rspofc_nm;
		private String athrt;
		private String fnl_login_date;
		private String prtbl_telno;
		private String login_err_rtrv;
		private String chng_date;
		private String incdt_idtf_cd;
		private String montr;
		private String acc_state_info;
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
		public String getCd_nm() {
			return cd_nm;
		}
		public void setCd_nm(String cd_nm) {
			this.cd_nm = cd_nm;
		}
		
		protected String unscript(String data){
		if(data == null || data.trim().equals("")){
		return "";
		}
		
		String ret = data;
		ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&It;script");
		ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&It;/script");
		ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&It;object");
		ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&It;/object");
		ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&It;applet");
		ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&It;/applet");
		ret = ret.replaceAll("<(E|e)(M|m)(B|b)(E|e)(D|d)","&It;embed");
		ret = ret.replaceAll("</(E|e)(M|m)(B|b)(E|e)(D|d)","&It;/embed");
		return ret;
		
		}
		
		private String ip = "";
		public String getIp() {
			return ip;
		}
		public void setIp(String ip) {
			this.ip = ip;
		}
		
}