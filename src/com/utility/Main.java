package com.utility;

import java.io.File;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

import org.apache.log4j.Appender;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.SimpleLayout;
import org.apache.log4j.xml.DOMConfigurator;
 
public class Main
{
	static final String CONFIG_XML = "config.xml";
	static final String FILE_LOG = "mainApplication.log";
    static Logger logger = Logger.getLogger(Main.class);
    static Appender fh = null;
    
    public static void main(String[] args) 
    {
    	try
    	{
    		// WARNING: no appenders could be found for logger
    		BasicConfigurator.configure();
    		
    		init();
   		 
	        // Log in console
	        logger.debug("Log4j console appender configuration is successful !!");
    	}
    	catch (Exception e) {
 			System.out.println("main: exception: " + e.getMessage());
 		}    	
    }
    
    private static void init() {
    	try
    	{
    		// PropertiesConfigurator is used to configure logger from properties file
    		String path = GetJarExecutionPath();
    		String fPath = path + File.separator + CONFIG_XML;
    		File f = new File(fPath);
	    	
	    	if(f.exists()) 
	    	{
	    		// xml file
	    		DOMConfigurator.configure(fPath);
	    		// property file
		        //PropertyConfigurator.configure(fPath);
	    		DefaultLoggerInitializer.initSyslog();
	    		
	    		// File Logger
	    		InitFileLogger();
	    	}
	    	else
	    	{
	    		System.out.println("main: could not find configuration file: " + f.getPath());	
	    	}
    	}
    	catch (Exception e) {
 			System.out.println("main: exception: " + e.getMessage());
 		}    	
    }
    
	private static String GetJarExecutionPath() 
	{
		java.security.CodeSource codeSource = Main.class.getProtectionDomain().getCodeSource();
		java.io.File jarFile = null;
		try {
			jarFile = new java.io.File(codeSource.getLocation().toURI().getPath());
		} catch (URISyntaxException e) {
			System.out.println("main: failed to obtain execution path");
		}
		return jarFile.getParentFile().getPath();
	}
	
	private static void InitFileLogger() {
		
		try {
			
			// This block configure the logger with handler and formatter
			String path = GetJarExecutionPath();
			String fPath = path + File.separator + FILE_LOG;
    		File f = new File(fPath);
	    	
	    	if(f.exists()) 
	    	{
		        fh = new FileAppender(new SimpleLayout(), fPath);
		        logger.addAppender(fh);
	    	}
	    	else
	    	{
	    		System.out.println("main: could not find configuration file: " + f.getPath());	
	    	}
		} 
		catch (Exception e) 
		{
			System.out.println("main: failed to obtain execution path");
		}
	}
}