package in.clouthink.synergy.account.domain.request;

import in.clouthink.synergy.account.domain.model.Gender;

import java.util.Date;

/**
 */
public interface SaveUserRequest extends AbstractUserRequest {

	String getUsername();

	String getTelephone();

	Gender getGender();

	String getAvatarId();

	Date getBirthday();

	String getPassword();

}
