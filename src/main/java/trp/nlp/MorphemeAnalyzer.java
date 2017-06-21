package trp.nlp;

import org.apache.commons.lang.StringUtils;
import org.bitbucket.eunjeon.seunjeon.Analyzer;
import org.bitbucket.eunjeon.seunjeon.LNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Junho on 2017. 6. 10..
 */
public class MorphemeAnalyzer {
    private static Logger logger = LoggerFactory.getLogger( MorphemeAnalyzer.class );
    private static MorphemeAnalyzer instance = new MorphemeAnalyzer();
    private MorphemeAnalyzer() {}
    public static MorphemeAnalyzer getInstance() {
        return instance;
    }
    public synchronized boolean initialize( String dicionaryPath ) {
        boolean isSuccess = false;
        return isSuccess;
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
