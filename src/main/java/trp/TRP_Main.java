package trp;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.log4j.PropertyConfigurator;

import trp.configurator.Configurator;

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
    	setLogger(Configurator.getInstance().getServer_logLevel(), Configurator.getInstance().getServer_logPath());
    }


    /**
     * Setting Log configuration for Dialog Manager
     * @param logLevel_
     * @param logFilePath_
     */
    public static void setLogger( String logLevel_, String logFilePath_ ) {
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
        properties.setProperty( "log4j.appender.stdout.Threshold", logLevel_ );
        properties.setProperty( "log4j.appender.rolling.File", logFilePath_ );
        PropertyConfigurator.configure( properties );
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
