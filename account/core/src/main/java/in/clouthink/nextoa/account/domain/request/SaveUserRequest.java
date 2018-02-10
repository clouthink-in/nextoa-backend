package in.clouthink.nextoa.account.domain.request;

import in.clouthink.nextoa.account.domain.model.Gender;

import java.util.Date;

/**
 */
public interface SaveUserRequest extends AbstractUserRequest {

	String getUsername();

	String getCellphone();

	Gender getGender();

	String getAvatarId();

	Date getBirthday();

	String getPassword();

}
