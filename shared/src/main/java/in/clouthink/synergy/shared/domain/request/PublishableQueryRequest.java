package in.clouthink.synergy.shared.domain.request;

/**
 * publish able query request
 */
public interface PublishableQueryRequest extends PageQueryRequest {

	Boolean getPublished();

}
