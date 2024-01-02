package com.example.crawling.util;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

public class SangwonUtil {

	public String getHostAddress(){
		String hostAddress = "";

		try{
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		}catch(Exception e){
			e.printStackTrace();
		}

		return hostAddress;
	}

	public static String getServletPath(HttpServletRequest request){
		String sp = request.getServletPath();
		String SERVLET_PATH = sp.substring(1);

		request.setAttribute("SERVLET_PATH", SERVLET_PATH);

		return sp;
	}

}
