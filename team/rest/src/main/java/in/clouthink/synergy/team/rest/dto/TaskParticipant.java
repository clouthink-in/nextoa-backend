package in.clouthink.synergy.team.rest.dto;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.team.domain.model.ActivityAction;
import in.clouthink.synergy.team.domain.model.Receiver;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 */
public class TaskParticipant {

	public static TaskParticipant from(User fromUser, User toUser, ActivityAction action) {
		if (action == null) {
			return null;
		}
		TaskParticipant result = new TaskParticipant();
		List<User> to = new ArrayList<>();
		to.add(fromUser);
		to.addAll(action.getToReceivers()
						.stream()
						.filter(receiver -> skipSender(fromUser, receiver))
						.map(receiver -> receiver.getUser())
						.filter(user -> !user.getId().equalsIgnoreCase(toUser.getId()))
						.collect(Collectors.toList()));

		result.setTo(ReceiverSummary.fromUsers(to));

		List<User> cc = new ArrayList<>();
		cc.addAll(action.getCcReceivers()
						.stream()
						.filter(receiver -> skipSender(fromUser, receiver))
						.map(receiver -> receiver.getUser())
						.filter(user -> !user.getId().equalsIgnoreCase(toUser.getId()))
						.collect(Collectors.toList()));

		result.setCc(ReceiverSummary.fromUsers(cc));
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

	private List<ReceiverSummary> to;

	private List<ReceiverSummary> cc;

	public List<ReceiverSummary> getTo() {
		return to;
	}

	public void setTo(List<ReceiverSummary> to) {
		this.to = to;
	}

	public List<ReceiverSummary> getCc() {
		return cc;
	}

	public void setCc(List<ReceiverSummary> cc) {
		this.cc = cc;
	}
}
