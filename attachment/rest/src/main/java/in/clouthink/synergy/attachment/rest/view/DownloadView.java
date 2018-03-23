package in.clouthink.synergy.attachment.rest.view;

import in.clouthink.synergy.attachment.domain.model.AttachmentDownloadHistory;
import io.swagger.annotations.ApiModel;

import java.util.Date;

/**
 */
@ApiModel
public class DownloadView {

	public static DownloadView from(AttachmentDownloadHistory newsReadHistory) {
		DownloadView result = new DownloadView();
		result.setId(newsReadHistory.getId());
		if (newsReadHistory.getDownloadedBy() != null) {
			result.setDownloadById(newsReadHistory.getDownloadedBy().getId());
			result.setDownloadByName(newsReadHistory.getDownloadedBy().getUsername());
		}
		result.setDownloadAt(newsReadHistory.getDownloadedAt());
		result.setDownloadReadAt(newsReadHistory.getLatestDownloadedAt());
		return result;
	}

	private String id;

	private String downloadById;

	private String downloadByName;

	private Date downloadAt;

	private Date downloadReadAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDownloadById() {
		return downloadById;
	}

	public void setDownloadById(String downloadById) {
		this.downloadById = downloadById;
	}

	public String getDownloadByName() {
		return downloadByName;
	}

	public void setDownloadByName(String downloadByName) {
		this.downloadByName = downloadByName;
	}

	public Date getDownloadAt() {
		return downloadAt;
	}

	public void setDownloadAt(Date downloadAt) {
		this.downloadAt = downloadAt;
	}

	public Date getDownloadReadAt() {
		return downloadReadAt;
	}

	public void setDownloadReadAt(Date downloadReadAt) {
		this.downloadReadAt = downloadReadAt;
	}
}
