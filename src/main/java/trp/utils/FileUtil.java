package trp.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Junho on 2017. 6. 10..
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger( FileUtil.class );
    /*
        Singleton instance
     */
    private FileUtil() {}
    private static class Singleton {
        private static final FileUtil instance = new FileUtil();
    }
    public static FileUtil getInstance() {
        return Singleton.instance;
    }

    /**
     * readFile by line
     * @param fileName
     * @return
     * @throws IOException
     */
    public List<String> readFileByLine( String fileName ) throws IOException {
        List<String> retList                = new ArrayList<String>();
        FileInputStream fileInputStream     = new FileInputStream( fileName );
        InputStreamReader inputStreamReader = new InputStreamReader( fileInputStream, UtilOption.UTF8 );
        BufferedReader bufferedReader       = new BufferedReader( inputStreamReader );
        String line;

        while ( ( line = bufferedReader.readLine() ) != null ) retList.add( line );
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();

        return retList;
    }

    /**
     * readFile by chunk(whole stream)
     * @param fileName
     * @return
     * @throws IOException
     */
    public String readFileByChunk( String fileName ) throws IOException {
        FileInputStream fileInputStream     = new FileInputStream( fileName );
        InputStreamReader inputStreamReader = new InputStreamReader( fileInputStream, UtilOption.UTF8 );
        BufferedReader bufferedReader       = new BufferedReader( inputStreamReader );
        StringBuilder sb                    = new StringBuilder();
        int c;
        while ( ( c = bufferedReader.read() ) != -1 ) sb.append( (char)c );
        bufferedReader.close();
        inputStreamReader.close();
        fileInputStream.close();

        return sb.toString();
    }

    /**
     *
     * Read resource file by line from resource directory
     * @param fileName relative path based on the root of resource directory
     * @return the content of target file by lines
     * @throws IOException
     */
    public List<String> readResourceByLine( String fileName ) throws IOException {
        List<String> retList                = new ArrayList<String>();
        InputStream inputStream             = this.getClass().getClassLoader().getResourceAsStream(fileName);
        BufferedReader bufferedReader       = new BufferedReader( new InputStreamReader(inputStream,"UTF-8"));
        String line;

        while ( ( line = bufferedReader.readLine() ) != null ) retList.add( line );
        bufferedReader.close();
        inputStream.close();

        return retList;
    }

    /**
     * Write file (Overloaded)
     * @param line
     * @param fileName
     * @throws IOException
     */
    public void writeFile( String line, String fileName ) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter( fileName ));
        try {
            bufferedWriter.write( line );
            bufferedWriter.flush();
        } catch( IOException e ) {
            e.printStackTrace();
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Write file (Overloaded)
     * @param lineList
     * @param fileName
     * @throws IOException
     */
    public void writeFile( List<String> lineList, String fileName ) throws IOException {
        BufferedWriter  bufferedWriter  = new BufferedWriter( new FileWriter( fileName ) );
        StringBuilder strBuf            = new StringBuilder();
        int paramListSize               = lineList.size();
        for ( int i = 0; i < paramListSize; i++ )
            strBuf.append( lineList.get(i) + "\n" );

        try {
            bufferedWriter.write( strBuf.toString() );
            bufferedWriter.flush();
        } catch ( IOException e ) {
            e.printStackTrace();
        } finally {
            if ( bufferedWriter != null ) {
                try {
                    bufferedWriter.close();
                } catch( IOException e ) {
                    e.printStackTrace();
                }
            }
        }
    }
}
