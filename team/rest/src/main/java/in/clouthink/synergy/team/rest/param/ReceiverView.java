package in.clouthink.synergy.team.rest.param;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.Receiver;
import io.swagger.annotations.ApiModel;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
@ApiModel
public class ReceiverView {

	public static List<ReceiverView> fromUsers(List<User> users) {
		if (users == null) {
			return Collections.unmodifiableList(Collections.EMPTY_LIST);
		}

		return users.stream().map(ReceiverView::from).collect(Collectors.toList());
	}

	public static List<ReceiverView> from(List<Receiver> receivers) {
		if (receivers == null) {
			return Collections.unmodifiableList(Collections.EMPTY_LIST);
		}

		return receivers.stream().map(ReceiverView::from).collect(Collectors.toList());
	}

	public static ReceiverView from(Receiver receiver) {
		if (receiver == null || receiver.getUser() == null) {
			return null;
		}
		User user = receiver.getUser();
		ReceiverView result = new ReceiverView();
		result.setUserId(user.getId());
		result.setUsername(user.getUsername());
//		if (user.getOrganizations() != null) {
//			result.setOrganizations(user.getOrganizations()
//										.stream()
//										.map(SimpleOrganization::from)
//										.collect(Collectors.toList()));
//		}
		return result;
	}

	public static ReceiverView from(User user) {
		if (user == null) {
			return null;
		}
		ReceiverView result = new ReceiverView();
		result.setUserId(user.getId());
		result.setUsername(user.getUsername());
//		if (user.getOrganizations() != null) {
//			result.setOrganizations(user.getOrganizations()
//										.stream()
//										.map(SimpleOrganization::from)
//										.collect(Collectors.toList()));
//		}
		return result;
	}

	private String userId;

	private String username;

//	private List<SimpleOrganization> organizations;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

//	public List<SimpleOrganization> getOrganizations() {
//		return organizations;
//	}
//
//	public void setOrganizations(List<SimpleOrganization> organizations) {
//		this.organizations = organizations;
//	}
}
