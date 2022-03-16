package com.redhat.fuse.boosters.rest.http;

import org.apache.camel.json.simple.JsonObject;

/**
 * Service interface for name service.
 * 
 */
public interface CRAService {

    JsonObject getVersion();
    JsonObject purchases(String ref, String id);
    JsonObject BahtTextAlphabet(String amount);
    JsonObject eformRequest(String formType);
    
}