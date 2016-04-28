package com.leaf.common.util;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import com.leaf.common.vo.NavTreeVO;

public class VOUtil {
	public static void convertToNavTree(Map<String, Object> src,NavTreeVO dest){
		dest.setId(((BigInteger)src.get("res_id")).longValue()+"");
		dest.setText((String)src.get("label"));
		Map<String,Object> attributes = new HashMap<String,Object>();
		attributes.put("url",src.get("res_url"));
		attributes.put("oprs",src.get("operations"));
		dest.setAttributes(attributes);
		int leafFlag = ((Integer)src.get("leaf_flag")).intValue();
		if(leafFlag==1){
			dest.setState(NavTreeVO.STATE_OPEN);
		}else{
			dest.setState(NavTreeVO.STATE_CLOSED);
		}
	}
}
