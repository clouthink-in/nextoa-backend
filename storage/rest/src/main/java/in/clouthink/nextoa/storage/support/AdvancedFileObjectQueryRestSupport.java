package in.clouthink.nextoa.storage.support;

import in.clouthink.daas.fss.mongodb.model.FileObject;
import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.storage.dto.DefaultFileObjectQueryParameter;
import org.springframework.data.domain.Page;

/**
 */
public interface AdvancedFileObjectQueryRestSupport {

	void deleteById(String id, User user);

	Page<FileObject> listFileObject(DefaultFileObjectQueryParameter queryParameter, User user);

}
