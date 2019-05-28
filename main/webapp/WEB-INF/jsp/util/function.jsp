<%!

public String getReqParam(String Req,String ifNulltoReplace ) {
	if ( Req == null || Req.equals("")) return ifNulltoReplace;
	else return Req.trim();
}

//sqlinjection, XSS 방지
String Filter(String str, String ifNulltoReplace, String avatag) {

	if(str == null || str.equals("")) 
	{
		return ifNulltoReplace;
	}
	else
	{	
		str = str.replaceAll("&", "&amp");
		str = str.replaceAll("--","");
		str = str.replaceAll("<","&lt;");
		str = str.replaceAll(">","&gt;");
		str = str.replaceAll("\0","");
	
		if (!avatag.equals("")) {
			avatag.replaceAll(" ","");
			
			String [] st = avatag.split(",");
		
			//허용할 태그를 존재 여부를 검사하여 원상태로 변환
			for(int x = 0; x < st.length; x++ ) {
				str = str.replaceAll("&lt;"+st[x]+" ", "<"+st[x]+" ");
				str = str.replaceAll("&lt;"+st[x]+">", "<"+st[x]+">");
				str = str.replaceAll("&lt;/"+st[x], "</"+st[x]);
			}
    	}
	
	}	
	return (str.trim());
}

String checkpath(String fname, String dn_path) {

	if(dn_path.equals("") &&  fname.equals(""))
	{
		return "error";
	}	 
	
	if((dn_path.indexOf("..\\") != -1) || (dn_path.indexOf("../") != -1) || (dn_path.indexOf("..") != -1)) {
		return "error";
	}

	if((fname.indexOf("..\\") != -1) || (fname.indexOf("../") != -1) || (fname.indexOf("..") != -1) || (fname.indexOf("/") != -1)) {
		return "error";
	}

	if (!dn_path.equals("") && !fname.equals("")) 
	{
		dn_path = dn_path + "/";
	}
	
	String filepath = dn_path + fname;
	
	return filepath;
}
%>