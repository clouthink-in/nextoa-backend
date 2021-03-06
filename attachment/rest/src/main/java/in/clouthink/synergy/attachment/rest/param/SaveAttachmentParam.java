package in.clouthink.synergy.attachment.rest.param;

import in.clouthink.synergy.attachment.domain.request.SaveAttachmentRequest;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class SaveAttachmentParam implements SaveAttachmentRequest {

	private String title;

	private String category;

	private String summary;

	private String fileObjectId;

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Override
	public String getFileObjectId() {
		return fileObjectId;
	}

	public void setFileObjectId(String fileObjectId) {
		this.fileObjectId = fileObjectId;
	}
}
