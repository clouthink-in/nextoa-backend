package in.clouthink.synergy.team.rest.view;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.ActivityAction;
import in.clouthink.synergy.team.domain.model.Receiver;
import in.clouthink.synergy.team.rest.param.ReceiverView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
public class TaskParticipantView {

	public static TaskParticipantView from(User fromUser, User toUser, ActivityAction action) {
		if (action == null) {
			return null;
		}
		TaskParticipantView result = new TaskParticipantView();
		List<User> to = new ArrayList<>();
		to.add(fromUser);
		to.addAll(action.getToReceivers()
						.stream()
						.filter(receiver -> skipSender(fromUser, receiver))
						.map(receiver -> receiver.getUser())
						.filter(user -> !user.getId().equalsIgnoreCase(toUser.getId()))
						.collect(Collectors.toList()));

		result.setTo(ReceiverView.fromUsers(to));

		List<User> cc = new ArrayList<>();
		cc.addAll(action.getCcReceivers()
						.stream()
						.filter(receiver -> skipSender(fromUser, receiver))
						.map(receiver -> receiver.getUser())
						.filter(user -> !user.getId().equalsIgnoreCase(toUser.getId()))
						.collect(Collectors.toList()));

		result.setCc(ReceiverView.fromUsers(cc));
		return result;
	}

	private static boolean skipSender(User sender, Receiver receiver) {
		if (receiver.getUser() == null) {
			return false;
		}
		if (receiver.getUser().getId().equalsIgnoreCase(sender.getId())) {
			return false;
		}
		return true;
	}

	private List<ReceiverView> to;

	private List<ReceiverView> cc;

	public List<ReceiverView> getTo() {
		return to;
	}

	public void setTo(List<ReceiverView> to) {
		this.to = to;
	}

	public List<ReceiverView> getCc() {
		return cc;
	}

	public void setCc(List<ReceiverView> cc) {
		this.cc = cc;
	}
}
