package in.clouthink.synergy.audit.rest.param;


import in.clouthink.synergy.audit.domain.request.AuthEventSearchRequest;
import in.clouthink.synergy.shared.domain.request.impl.DateRangedSearchParam;
import io.swagger.annotations.ApiModel;

@ApiModel
public class AuthEventSearchParam extends DateRangedSearchParam implements AuthEventSearchRequest {

	private String realm;

	private String username;

	private Boolean succeed;

	@Override
	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public Boolean getSucceed() {
		return succeed;
	}

	public void setSucceed(Boolean succeed) {
		this.succeed = succeed;
	}
}
