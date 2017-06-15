package trp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by Junho on 2017. 6. 10..
 */
public class SerializationUtil {
    private static Logger logger = LoggerFactory.getLogger( SerializationUtil.class );

    public static boolean marshalling( Object targetObj, String fileName ) throws IOException {
        OutputStream file       = null;
        OutputStream buffer     = null;
        ObjectOutput output     = null;
        boolean isMarshalled    = true;

        try {
            file    = new FileOutputStream( fileName );
            buffer  = new BufferedOutputStream( file );
            output  = new ObjectOutputStream( buffer );
            output.writeObject( targetObj );
        } catch (IOException e) {
            logger.warn( "Cannot perform marshalling target object!" );
            isMarshalled = false;
            e.printStackTrace();
        } finally {
            if ( output != null ) output.close();
            if ( buffer != null ) buffer.close();
            if ( file   != null ) file.close();
        }

        return isMarshalled;
    }

    public static Object unmarshalling( String fileName ) throws IOException {
        Object result           = null;
        InputStream file        = null;
        InputStream buffer      = null;
        ObjectInput input       = null;

        try {
            file    = new FileInputStream( fileName );
            buffer  = new BufferedInputStream( file );
            input   = new ObjectInputStream( buffer );
            result  = input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if ( input  != null ) input.close();
            if ( buffer != null ) buffer.close();
            if ( file   != null ) file.close();
        }
        return result;
    }
}
