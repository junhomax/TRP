package trp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.log4j.PropertyConfigurator;

import trp.configurator.Configurator;
import trp.nlp.MorphemeAnalyzer;

import java.io.IOException;
import java.util.Properties;

import javax.security.auth.login.Configuration;

/**
 * Created by Junho on 2017. 6. 10..
 */
public class TRP_Main {
    /*
        DEFAULT Commandline Options
     */
    public static final String ARG_CONFIG   = "config";
    public static final String ARG_PID      = "pid";

    public static void main( String[] args ) {
    	try{
    		System.out.println("Initializing configurator...");
    		Configurator.getInstance().init(args[0]);
    	} catch (IOException e){
    		System.out.println("Cannot find config file in " + args[0] + ". IOException occurred.");
    	} catch (Exception e){
    		System.out.println("Unhandled exception occuured");
    		e.printStackTrace();
    	}
    	
    	System.out.println("Server Home:\t" + Configurator.getInstance().getServer_home());
    	System.out.println("Server LogPath:\t" + Configurator.getInstance().getServer_logPath());
    	System.out.println("Server LogLevel:\t" + Configurator.getInstance().getServer_logLevel());
    	MorphemeAnalyzer.getInstance().initialize(Configurator.getInstance().getServer_knowledgePath());
    	
    	
    }


   

    /**
     * Check whethere there is config option in executable parameter or not.
     * @param cmdLine_
     * @return boolean
     */
    private static boolean isEssentialOptionMissing( CommandLine cmdLine_ ) {
        return !cmdLine_.hasOption( ARG_CONFIG );
    }

    /**
     * Create options for Dialog Manager
     * @return Options
     */
    private static Options createOptions() {
        Options options = new Options();
        options.addOption( ARG_CONFIG, true, "Config properties path" )
                .addOption( ARG_PID, true, "PID file" );
        return options;
    }
}
