package egovframework.main.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ScriptWriter {
	private PrintWriter out = null;
	
	private ScriptWriter() {
	}	
	
	private static class LazyHolder {
		public static final ScriptWriter INSTANCE = new ScriptWriter();
	}
	
	public static ScriptWriter getInstance() {
		return LazyHolder.INSTANCE;
	}
	
	public void printScript(HttpServletResponse response, String scriptStr, boolean doFlush) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
        out = response.getWriter();
		out.println(scriptStr);
		
		if(doFlush)
			out.flush(); 
	}
	
	public void flush() {
		out.flush();
	}
}
