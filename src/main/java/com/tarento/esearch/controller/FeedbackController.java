package com.tarento.esearch.controller;

import java.util.List;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tarento.esearch.exception.NoNodeAvailableException;
import com.tarento.esearch.model.ResponseStatus;
import com.tarento.esearch.service.EsearchService;
import com.tarento.esearch.service.EsearchServiceImpl;
import com.tarento.esearch.utils.EsearchUtils;

@RestController
public class FeedbackController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FeedbackController.class);
	
	@RequestMapping(value = "/rating/{index}", method = RequestMethod.GET)
	public String getOrder(@PathVariable String index) throws JSONException {
		LOGGER.info("Start: FeedbackController getOrder event");
		JSONObject jsonObject = null;
		List<Map<String,Object>> esData = null;
		SearchResponse response = null;
		try {
			jsonObject = new JSONObject();
			EsearchService serviceImpl = new EsearchServiceImpl();
			response = serviceImpl.getAvailableDocuments(index);
			esData = EsearchUtils.getAllDocuments(response);
			if(null == esData || esData.size() == 0) {
				jsonObject.put("404", "No data Found");
				return jsonObject.toString();
			}
			jsonObject = EsearchUtils.prepareRatingJson(esData, response);
		} catch (NoNodeAvailableException expObj) {
			LOGGER.error("Error in FeedbackController getOrder : ", expObj);
			jsonObject.put("500", "NoNodeAvailableException");
			expObj.printStackTrace();
			return jsonObject.toString();
		} catch (Exception expObj) {
			LOGGER.error("Error in FeedbackController getOrder : ", expObj);
			ResponseStatus status = new ResponseStatus(500, "Internal Error");
			expObj.printStackTrace();
			return status.toString();
		} finally {
			// transportClient.close();
		}
		LOGGER.info("End: FeedbackController getOrder event");
		return jsonObject.toString();
	}
	
}
