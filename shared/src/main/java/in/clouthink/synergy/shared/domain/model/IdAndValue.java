package in.clouthink.synergy.shared.domain.model;

/**
 * @auther dz
 */
public class IdAndValue extends StringIdentifier {

    public static IdAndValue from(String value) {
        return new IdAndValue(value);
    }

    public IdAndValue() {
    }

    public IdAndValue(String id) {
        super(id);
    }
}
