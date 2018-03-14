package in.clouthink.synergy.team.rest.support;

import in.clouthink.synergy.team.domain.model.Receiver;
import in.clouthink.synergy.team.rest.dto.ReceiverParameter;

import java.util.List;

/**
 *
 */
public interface ReceiverBuilder {

	Receiver buildReceiver(ReceiverParameter parameter);

	List<Receiver> buildReceivers(List<ReceiverParameter> parameter);

}
