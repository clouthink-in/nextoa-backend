package in.clouthink.synergy.passcode.service;

/**
 * @author dz
 */
public interface PasscodeService {

	void sendPasscode4Register(String telephone);

	void sendPasscode4ForgetPassword(String telephone);

	void validatePasscode4Register(String telephone, String passcode);

	void validatePasscode4ForgetPassword(String telephone, String passcode);

}
