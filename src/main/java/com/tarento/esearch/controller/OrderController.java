package com.tarento.esearch.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tarento.esearch.model.ResponseStatus;
import com.tarento.esearch.service.EsearchService;
import com.tarento.esearch.service.EsearchServiceImpl;

@RestController
public class OrderController {

	@RequestMapping(value = "/{index}/{type}/{searchId}", method = RequestMethod.GET)
	public Map<String, Object> getOrder(@PathVariable String index,
			@PathVariable String type, @PathVariable String searchId) {
		Map<String, Object> map = null;
		try {
			map = new HashMap<String, Object>();
			EsearchService serviceImpl = new EsearchServiceImpl();
			map = serviceImpl.getDocument(index, type, searchId);
			if(null ==  map || map.size() == 0) {
				map = new HashMap<String, Object>();
				map.put("404", "No data Found");
				return map;
			}
		} catch (Exception expObj) {
			expObj.printStackTrace();
		} finally {
			// transportClient.close();
		}
		return map;
	}

	@RequestMapping(value = "/{index}/{type}/{searchId}", method = RequestMethod.POST)
	public ResponseStatus createDocument(@PathVariable String index,
			@PathVariable String type, @PathVariable String searchId, @RequestBody String requestData) {
		ResponseStatus status = null;
		try {
			status = new ResponseStatus(201, "Created");
			EsearchService serviceImpl = new EsearchServiceImpl();
			serviceImpl.createDocument(index, type, searchId, requestData);
		} catch (Exception expObj) {
			status = new ResponseStatus(500, "Internal Error");
			expObj.printStackTrace();
		} finally {
			// transportClient.close();
		}
		return status;
	}

	@RequestMapping(value = "/{index}/{type}/{searchId}", method = RequestMethod.PUT)
	public ResponseStatus updateDocument(@PathVariable String index,
			@PathVariable String type, @PathVariable String searchId, @RequestBody String requestData) {
		ResponseStatus status = null;
		try {
			status = new ResponseStatus(201, "Created");
			EsearchService serviceImpl = new EsearchServiceImpl();
			serviceImpl.updateDocument(index, type, searchId, null, null, requestData);
		} catch (Exception expObj) {
			status = new ResponseStatus(500, "Internal Error");
			expObj.printStackTrace();
		} finally {
			// transportClient.close();
		}
		return status;
	}

	@RequestMapping(value = "/{index}/{type}/{searchId}", method = RequestMethod.DELETE)
	public ResponseStatus removeDocument(@PathVariable String index, @PathVariable String type, @PathVariable String searchId) {
		ResponseStatus status = new ResponseStatus();
		try {
			EsearchService serviceImpl = new EsearchServiceImpl();
			serviceImpl.removeDocument(index, type, searchId);
			status.setStatus(201);
			status.setStatusMessage("Removed");
		} catch (Exception expObj) {
			status = new ResponseStatus(500, "Internal Error");
			expObj.printStackTrace();
		} finally {
			// transportClient.close();
		}
		return status;
	}

}
