package in.clouthink.nextoa.account.domain.request;

import in.clouthink.nextoa.account.domain.model.Gender;

import java.util.Date;

/**
 */
public interface ChangeUserProfileRequest extends AbstractUserRequest {

	String getDisplayName();

	Gender getGender();

	Date getBirthday();

	String getProvince();

	String getCity();

	String getSignature();
}
