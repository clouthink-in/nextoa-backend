package in.clouthink.synergy.team.openapi.support;

import in.clouthink.synergy.team.domain.model.Receiver;
import in.clouthink.synergy.team.openapi.dto.ReceiverParameter;

import java.util.List;

/**
 *
 */
public interface ReceiverBuilder {

	Receiver buildReceiver(ReceiverParameter parameter);

	List<Receiver> buildReceivers(List<ReceiverParameter> parameter);

}
