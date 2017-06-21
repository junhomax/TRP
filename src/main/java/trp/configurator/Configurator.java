package trp.configurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author Junhwan
 *
 */
public class Configurator {
	
	private String server_home;
	private String server_logPath;
	private String server_logLevel;
	
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
			server_logPath = prop.getProperty("server.logpath");
			server_logLevel = prop.getProperty("server.loglevel");
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
	
	
	
}
