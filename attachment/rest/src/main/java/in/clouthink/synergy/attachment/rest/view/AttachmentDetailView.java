package in.clouthink.synergy.attachment.rest.view;

import in.clouthink.synergy.attachment.domain.model.Attachment;
import io.swagger.annotations.ApiModel;

/**
 *
 */
@ApiModel
public class AttachmentDetailView extends AttachmentView {

	public static AttachmentDetailView from(Attachment attachment, Object fileObject) {
		if (attachment == null) {
			return null;
		}
		AttachmentDetailView result = new AttachmentDetailView();
		convert(attachment, result);
		result.setFileObject(fileObject);
		return result;
	}

	//附件关联的存储对象,例如daas fss object
	private Object fileObject;

	public Object getFileObject() {
		return fileObject;
	}

	public void setFileObject(Object fileObject) {
		this.fileObject = fileObject;
	}

}
