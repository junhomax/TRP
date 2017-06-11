package trp.nlp;

/**
 * Created by Junho on 2017. 6. 10..
 */
public class MorphemeAnalyzer {
    private static MorphemeAnalyzer instance = new MorphemeAnalyzer();

    private MorphemeAnalyzer() {

    }

    public static MorphemeAnalyzer getInstance() {
        return instance;
    }


}
