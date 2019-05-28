package egovframework.main.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
//import java.applet;

import javax.servlet.http.HttpServletRequest;

public class NetworkState {
	private String macAddr = "";
	
	public String getMacAddress() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			
			String ipAddr = addr.getHostAddress();
			String hostName = addr.getHostName();
			
			NetworkInterface netIn = NetworkInterface.getByInetAddress(addr);
			if (netIn != null) {
				byte[] mac = netIn.getHardwareAddress();
				if (mac != null) {
					for (int i = 0; i < mac.length; i++) {
						macAddr += String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : "");
					}
				}
				else {
					System.out.println("addr doesn't exist");
				}
			}
			else {
				System.out.println("addr is not found");
			}
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (SocketException e) {
			e.printStackTrace();
		}	
		
		return macAddr;
	}
	
	public String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		System.out.println("X-Forwarded-For : " + ip);
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			System.out.println("Proxy-Client-IP : " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			System.out.println("WL-Proxy-Client-IP : " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			System.out.println("HTTP_CLIENT_IP : " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			System.out.println("HTTP_X_FORWARDED_FOR : " + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			System.out.println("getRemoteAddr() : " + ip);
		}
		return ip;
	}
}
