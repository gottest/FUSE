package com.redhat.fuse.boosters.rest.http;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.QueryParam;

import org.apache.camel.json.simple.JsonObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.camel.Header;
import org.springframework.stereotype.Service;

import util.NumberUtil;

@Service("CRAService")
public class CRAServiceImpl implements CRAService {

    private static final Logger logger = LogManager.getLogger(CRAServiceImpl.class);
    
    public JsonObject getVersion() {
        String status = Configuration.getInstance().getVersion("app.api.version.status", "1.0");
		String update = Configuration.getInstance().getVersion("app.api.version.update", "2021-10-27");
        
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("name", "CRA_API");
		jsonObject.put("version", status);
		jsonObject.put("update", update);
        System.out.println(">>>> "+jsonObject);
        return jsonObject;
    }

    public JsonObject purchases(
        @Header("ref") String ref
        , @Header("id") String id
    ) {
        logger.info("Call Service purchases");
		logger.info("call time = "+new Date());
		logger.info("ref = "+ref);
		logger.info("id = "+id);
		
        Map<String, Object> r = new LinkedHashMap<String, Object>();
		try {
			r = new LinkedHashMap<String, Object>();
			r.put("message", "purchases");
			r.put("ref", ref);
			r.put("id", id);
			
            System.out.println(r);
		} catch (Exception e) {
			logger.error(e,e);
			e.printStackTrace();
		}

        JsonObject jsonObject = new JsonObject(r);
        return jsonObject;
    }

    public JsonObject BahtTextAlphabet(@Header("amount") String amount) {
        logger.info("Call Service BahtTextAlphabet");
		logger.info("call time = "+new Date());
		logger.info("amount = "+amount);
		
        Map<String, Object> r = new LinkedHashMap<String, Object>();
		try {
			amount = amount.replace(",", "");
			double amountDouble = new Double(amount);
			r.put("result", NumberUtil.getBahtText(String.valueOf(amountDouble)));
			r.put("message", "BahtText");
			
            System.out.println(r);
		} catch (Exception e) {
			logger.error(e,e);
			r.put("result", "error");
			r.put("message", e.getMessage());
		}

        JsonObject jsonObject = new JsonObject(r);
        return jsonObject;
    }

    public JsonObject eformRequest(
        @Header("formType") String formType
    ) {
        logger.info("Call Service eformRequest");
		logger.info("call time = "+new Date());
		logger.info("formType = "+formType);
		
        Map<String, Object> r = new LinkedHashMap<String, Object>();
		try {
			r = new LinkedHashMap<String, Object>();
			r.put("message", "eformRequest");
			r.put("formType", formType);
			
            System.out.println(r);
		} catch (Exception e) {
			logger.error(e,e);
			e.printStackTrace();
		}

        JsonObject jsonObject = new JsonObject(r);
        return jsonObject;
    }



    public static void main(String[] args) {
        System.out.println("hello");
        CRAServiceImpl app = new CRAServiceImpl();
        app.BahtTextAlphabet("10000.00");
    }
}