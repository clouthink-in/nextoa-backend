package in.clouthink.synergy.shared.domain.request;

/**
 * publish able query request
 */
public interface PublishableSearchRequest extends PageSearchRequest {

	Boolean getPublished();

}
