package trp.configurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author Junhwan
 *
 */
public class Configurator {
	
	private String server_home;
	private static String server_logPath;
	private static String server_logLevel;
	private String server_knowledgePath;
	
	private static Configurator instance = new Configurator();
	private Configurator () {
	}
	public static Configurator getInstance () {
		return instance;
	}
	
	public void init(String configPath) throws Exception{
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(configPath + "/server.properties");
			prop.load(input);
			server_home = prop.getProperty("server.home");
			server_logLevel = prop.getProperty("server.loglevel");
			
			server_logPath = setPathProperties(prop.getProperty("server.logpath"), server_logPath);
			server_knowledgePath = setPathProperties(prop.getProperty("server.knowledgepath"), server_knowledgePath);
			
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					throw e;
				} catch (Exception e){
					throw e;
				}
			}
		}

		//create all paths
		createRequiredDirectories(server_logPath);
		createRequiredDirectories(server_knowledgePath);
		
		setLogger();
	}
	
	public void createRequiredDirectories(String path){
		File f = new File(path);
		String abPath = f.getAbsolutePath();
		if(!f.exists()){
			try{
				boolean result = f.mkdirs(); 
				if(result)
					System.out.println("Successfully created [ " + abPath + " ]");
				else
					System.out.println("Failed to create [" + abPath + " ]");
			} catch (Exception e){
				System.out.println("Exception occurred while making [ " + abPath + " ]");
			}
		}
	}
	
	public String setPathProperties(String temp, String targetOption){
		if(temp.contains(server_home.replace(".", "")))
			targetOption = temp;
		else
			targetOption = server_home + temp; 
		return targetOption;
	}
	
	public String getServer_home() {
		return server_home;
	}
	public void setServer_home(String server_home) {
		this.server_home = server_home;
	}
	public String getServer_logPath() {
		return server_logPath;
	}
	public void setServer_logPath(String server_logPath) {
		this.server_logPath = server_logPath;
	}
	public String getServer_logLevel() {
		return server_logLevel;
	}
	public void setServer_logLevel(String server_logLevel) {
		this.server_logLevel = server_logLevel;
	}
	
	public String getServer_knowledgePath() {
		return server_knowledgePath;
	}
	public void setServer_knowledgePath(String server_knowledgePath) {
		this.server_knowledgePath = server_knowledgePath;
	}
	
	 /**
     * Setting Log configuration for Dialog Manager
     * @param logLevel_
     * @param logFilePath_
     */
    private static void setLogger() {
        Properties properties = new Properties();
        /*
            Default log settings
         */
        properties.setProperty( "log4j.rootLogger", "TRACE, stdout, rolling" );
        properties.setProperty( "log4j.appender.stdout", "org.apache.log4j.ConsoleAppender" );
        properties.setProperty( "log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout" );
        properties.setProperty( "log4j.appender.stdout.layout.ConversionPattern", "%t> [%d{yyyy-MM-dd HH:mm:ss}] [%c{1}] [%L] [%p] %m %n" );
        properties.setProperty( "log4j.appender.rolling", "org.apache.log4j.DailyRollingFileAppender" );
        properties.setProperty( "log4j.appender.rolling.encoding", "UTF-8" );
        properties.setProperty( "log4j.appender.rolling.layout", "org.apache.log4j.PatternLayout" );
        properties.setProperty( "log4j.appender.rolling.layout.ConversionPattern", "%t> [%d{yyyy-MM-dd HH:mm:ss}] [%c{1}] [%L] [%p] %m %n" );
        properties.setProperty( "log4j.appender.rolling.DatePattern", "'-'yyyy-MM-dd'.log'" );

        /**
         * Change second parameter
         */
        properties.setProperty( "log4j.appender.stdout.Threshold", server_logLevel );
        properties.setProperty( "log4j.appender.rolling.File", server_logPath + "/trp");
        PropertyConfigurator.configure( properties );
    }
	
}
