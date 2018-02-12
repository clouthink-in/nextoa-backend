package in.clouthink.synergy.passcode.engine;


import in.clouthink.synergy.passcode.model.PasscodeRequest;

/**
 * @author dz
 */
public interface PasscodeEngine {

	void handlePasscodeRequest(PasscodeRequest request);

}
