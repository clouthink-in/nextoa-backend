package in.clouthink.synergy.team.rest.support;

import in.clouthink.synergy.team.domain.model.Receiver;
import in.clouthink.synergy.team.rest.param.ReceiverParam;

import java.util.List;

/**
 *
 */
public interface ReceiverBuilder {

	Receiver buildReceiver(ReceiverParam parameter);

	List<Receiver> buildReceivers(List<ReceiverParam> parameter);

}
