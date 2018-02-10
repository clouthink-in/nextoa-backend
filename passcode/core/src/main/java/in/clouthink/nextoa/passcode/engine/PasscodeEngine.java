package in.clouthink.nextoa.passcode.engine;


import in.clouthink.nextoa.passcode.model.PasscodeRequest;

/**
 * @author dz
 */
public interface PasscodeEngine {

	void handlePasscodeRequest(PasscodeRequest request);

}
