package trp.nlp;

import org.apache.commons.lang.StringUtils;
import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.bitbucket.eunjeon.seunjeon.LNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import trp.utils.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Junho on 2017. 6. 10..
 */
public class MorphemeAnalyzer {
    private static Logger logger = LoggerFactory.getLogger(MorphemeAnalyzer.class);
    private static MorphemeAnalyzer instance = new MorphemeAnalyzer();
    private MorphemeAnalyzer() {}
    public static MorphemeAnalyzer getInstance() {
        return instance;
    }
    public synchronized boolean initialize( String dictionaryPath ) {
        String userDicPath = dictionaryPath + "/" + Constants.MA_USER_DICTIONARY;
    	File f = new File(userDicPath);
        if(f.exists() && !f.isDirectory())
        	loadUserDictionary(userDicPath);
    	
    	boolean isSuccess = false;
        return isSuccess;
    }
    
    private void loadUserDictionary(String dictionaryPath){
    	logger.info("Loading MorphemeAnalyzer User Dictionary.");
    	ArrayList<String> userWords = new ArrayList<String>(50);
    	try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryPath), "UTF8"));
			String line = "";
			while( (line = br.readLine()) != null){
				userWords.add(line.trim());
			}
			logger.trace("Number of words in MorphemeAnalyzer User Dictionary:\t" + userWords.size());
			
			br.close();
		} catch (UnsupportedEncodingException e) {
			logger.error("Encoding mismatch error", e);
		} catch (FileNotFoundException e) {
			logger.error("Cannot find user dictionary", e);
		} catch (IOException e) {
			logger.error("IOException", e);
		}
    	
    	//try{
    	//Analyzer.setUser(...);
    	//}
    	logger.info("Successfully loaded MorphmeAnalyzer User Dictionary.");
    }

    public List<String> getMorphList(String input) {
        return getMorphList(input, null, false);
    }

    public List<String> getMorphList(String input, boolean withPOSTag) {
        return getMorphList(input, null, withPOSTag);
    }

    public String getAnalyzedString(String input) {
        List<String> tmpResult = getMorphList(input, true);
        if( tmpResult == null )
            return null;
        return StringUtils.join(tmpResult, " ");
    }

    public List<String> getMorphList(String input, String filterPOS, boolean withPOSTag) {
        List<String> tmpResult = new ArrayList<String>();
        List<LNode> lNodeList = Analyzer.parseJava(input);

        for(LNode lnode : lNodeList ) {
            String morph = lnode.morpheme().surface();
            String pos = lnode.morpheme().feature().head();
            if( withPOSTag ) {
                tmpResult.add(morph + '/' + pos);
            } else {
                tmpResult.add(morph);
            }
        }

        return tmpResult;
    }



}
