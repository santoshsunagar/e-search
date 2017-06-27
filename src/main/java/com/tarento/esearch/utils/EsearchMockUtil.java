package com.tarento.esearch.utils;

import java.util.HashMap;
import java.util.Map;

public class EsearchMockUtil {
	public static Map<String, String> prepareESJson() throws Exception {
		Map<String, String> mapObj = new HashMap<String, String>();
		try {
			mapObj.put("orderId", "1");
			mapObj.put("orderName", "test Order");
			mapObj.put("orderDesc", "test Order Description");
			mapObj.put("orderCreated", "Today");
		} catch (Exception expObj) {
			expObj.printStackTrace();
		}
		return mapObj;
	}
}
