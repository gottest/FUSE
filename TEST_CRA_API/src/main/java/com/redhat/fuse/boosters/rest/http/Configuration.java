package com.redhat.fuse.boosters.rest.http;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;


public class Configuration {

    private Properties m_propApp = null;
    private Properties m_propVersion = null;
    private Properties m_propUI = null;
    
    private static Configuration conf = null;
    private static final String APP_PROP = "prop/app.properties";
    private static final String VER_PROP = "prop/version.properties";   

    private Configuration(){
    	
    }
    
    public static Configuration getInstance() {
        if (conf == null) {
            conf = new Configuration();
            conf.init();
        }
        return conf;
    }

    public void init() {
    	initAppConfig();
    	initVersionConfig();
    }

    private boolean initAppConfig(){
    	boolean flag = false;
    	
    	m_propApp = new Properties();

        URL settingsUrl = Thread.currentThread().getContextClassLoader().getResource(APP_PROP);

        if (settingsUrl == null) {
            throw new IllegalStateException(APP_PROP +" missing");
        }

        try {
        	m_propApp.load(settingsUrl.openStream());
            
        } catch (IOException e) {
            throw new RuntimeException("Could not load "+APP_PROP + e);
        }
        
        return true;
    }
    
    private boolean initVersionConfig(){
    	boolean flag = false;
    	
    	m_propVersion = new Properties();

        URL settingsUrl = Thread.currentThread().getContextClassLoader().getResource(VER_PROP);

        if (settingsUrl == null) {
            throw new IllegalStateException(VER_PROP +" missing");
        }

        try {
        	m_propVersion.load(settingsUrl.openStream());
            
        } catch (IOException e) {
            throw new RuntimeException("Could not load "+VER_PROP + e);
        }
        
        return true;
    }
    
    public String getValue(String key, String Default) {
    	if(m_propApp == null) return Default;
        return m_propApp.getProperty(key, Default);
    }
    
    public String getVersion(String key, String Default) {
    	if(m_propVersion == null) return Default;
        return m_propVersion.getProperty(key, Default);
    }
    
    public String getUI(String key, String Default) {
    	if(m_propUI == null) return Default;
        return m_propUI.getProperty(key, Default);
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
    	return null;
    }
}