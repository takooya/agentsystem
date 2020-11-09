package cn.kgc.agentsystem.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


//单项加密方法,加密后无法解密
public class MD5 {

	public static String MD5Encode(String origin){
		
		String resultString="";
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			resultString=byteArrayToHexString(md.digest(origin.getBytes()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return resultString;
	}

	
	private static String byteArrayToHexString(byte[] digest) {
		//byte -128-127
		//128位digest 转为16进制32位字符串
		StringBuffer result=new StringBuffer();
		for(int i=0;i<digest.length;i++)
			result.append(byteToHexString(digest[i]));
		return result.toString();
	}


	private final static String[] hexDigits={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
	/*
	 *字节转16进制字符串
	 */
	private static String byteToHexString(byte b) {
		int n=b;
		if(n<0)
			n=n+256;
		int d1=n/16;
		int d2=n%16;
		return hexDigits[d1]+hexDigits[d2];
	}
}
