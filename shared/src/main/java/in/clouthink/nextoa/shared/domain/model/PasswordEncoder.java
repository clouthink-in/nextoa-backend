package in.clouthink.nextoa.shared.domain.model;

/**
 */
public interface PasswordEncoder {

	String encode(String rawPassword, String salt);

}
