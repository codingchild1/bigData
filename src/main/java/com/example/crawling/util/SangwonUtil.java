package com.example.crawling.util;

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

}
