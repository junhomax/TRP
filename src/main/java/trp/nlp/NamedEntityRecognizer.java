package trp.nlp;

/**
 * Created by Junho on 2017. 6. 10..
 */
public class NamedEntityRecognizer {
    private static NamedEntityRecognizer instance = new NamedEntityRecognizer();
    private NamedEntityRecognizer() {

    }

    public static NamedEntityRecognizer getInstance() {
        return instance;
    }


}
