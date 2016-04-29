package com.leaf.base.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

	/**
	 * 获取当前用户权限
	 * @return
	 */
	public static Authentication getUserAuthentication(){
		return SecurityContextHolder.getContext()
			    .getAuthentication();
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getCurrentUser(Class<T> t){
		Authentication authen =  getUserAuthentication();
		if(authen!=null){
			return (T)authen.getPrincipal();
		}
		return null;
	}
	
	/**
	 * MD5 32位 加密
	 * @param sourceStr
	 * @return
	 */
    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }
}
