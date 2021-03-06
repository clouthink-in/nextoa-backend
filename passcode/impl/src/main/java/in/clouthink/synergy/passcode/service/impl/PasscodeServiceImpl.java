package in.clouthink.synergy.passcode.service.impl;

import in.clouthink.daas.edm.Edms;
import in.clouthink.synergy.account.exception.PasscodeException;
import in.clouthink.synergy.passcode.model.Passcode;
import in.clouthink.synergy.passcode.model.PasscodeRequest;
import in.clouthink.synergy.passcode.model.Passcodes;
import in.clouthink.synergy.passcode.repository.PasscodeRepository;
import in.clouthink.synergy.passcode.service.PasscodeService;
import in.clouthink.synergy.shared.util.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author dz
 */
@Service
public class PasscodeServiceImpl implements PasscodeService {

	@Autowired
	private PasscodeRepository passcodeRepository;

	@Override
	public void sendPasscode4Register(String telephone) {
		ValidationUtils.validateTelephone(telephone);

		PasscodeRequest passcodeRequest = new PasscodeRequest();
		passcodeRequest.setTelephone(telephone);
		passcodeRequest.setCategory(Passcodes.REGISTER);

		Edms.getEdm(Passcodes.EVENT_GROUP_NAME).dispatch(Passcodes.REGISTER, passcodeRequest);
	}

	@Override
	public void sendPasscode4ForgetPassword(String telephone) {
		ValidationUtils.validateTelephone(telephone);

		PasscodeRequest passcodeRequest = new PasscodeRequest();
		passcodeRequest.setTelephone(telephone);
		passcodeRequest.setCategory(Passcodes.FORGET_PASSWORD);

		Edms.getEdm(Passcodes.EVENT_GROUP_NAME).dispatch(Passcodes.FORGET_PASSWORD, passcodeRequest);
	}

	@Override
	public void validatePasscode4Register(String telephone, String code) {
		ValidationUtils.validateTelephone(telephone);

		if (StringUtils.isEmpty(code)) {
			throw new PasscodeException("请提供验证码");
		}

		Passcode passcode = passcodeRepository.findByTelephoneAndCategory(telephone, Passcodes.REGISTER);
		if (passcode == null) {
			throw new PasscodeException("无效的验证请求");
		}

		if (!passcode.getPasscode().equalsIgnoreCase(code)) {
			throw new PasscodeException("无效的验证码");
		}

		if (passcode.getWhenToExpire() < System.currentTimeMillis()) {
			throw new PasscodeException("验证码已过期");
		}
	}

	@Override
	public void validatePasscode4ForgetPassword(String telephone, String code) {
		ValidationUtils.validateTelephone(telephone);

		if (StringUtils.isEmpty(code)) {
			throw new PasscodeException("请提供验证码");
		}
		Passcode passcode = passcodeRepository.findByTelephoneAndCategory(telephone, Passcodes.FORGET_PASSWORD);
		if (passcode == null) {
			throw new PasscodeException("无效的验证请求");
		}

		if (!passcode.getPasscode().equalsIgnoreCase(code)) {
			throw new PasscodeException("无效的验证码");
		}

		if (passcode.getWhenToExpire() < System.currentTimeMillis()) {
			throw new PasscodeException("验证码已过期");
		}
	}

}
