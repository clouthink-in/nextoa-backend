package in.clouthink.synergy.shared.domain.model;

/**
 * @auther dz
 */
public class IdValuePair extends StringIdentifier {

    public static IdValuePair from(String value) {
        return new IdValuePair(value);
    }

    public IdValuePair() {
    }

    public IdValuePair(String id) {
        super(id);
    }
}
