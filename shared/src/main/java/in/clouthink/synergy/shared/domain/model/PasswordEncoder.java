package in.clouthink.synergy.shared.domain.model;

/**
 */
public interface PasswordEncoder {

	String encode(String rawPassword, String salt);

}
