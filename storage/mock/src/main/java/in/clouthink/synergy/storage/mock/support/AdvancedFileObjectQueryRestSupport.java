package in.clouthink.synergy.storage.mock.support;

import in.clouthink.daas.fss.mongodb.model.FileObject;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.storage.mock.dto.DefaultFileObjectQueryParameter;
import org.springframework.data.domain.Page;

/**
 */
public interface AdvancedFileObjectQueryRestSupport {

    void deleteById(String id, User user);

    Page<FileObject> listFileObject(DefaultFileObjectQueryParameter queryParameter, User user);

}
