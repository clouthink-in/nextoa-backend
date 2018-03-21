package in.clouthink.synergy.storage.mock.support.mock;

import in.clouthink.daas.fss.mongodb.model.FileObject;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.storage.mock.dto.DefaultFileObjectQueryParameter;
import in.clouthink.synergy.storage.mock.support.AdvancedFileObjectQueryRestSupport;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * AdvancedFileObjectQueryRestSupport mocker
 *
 * @author dz
 */
@Component
public class AdvancedFileObjectQueryRestSupportMockImpl implements AdvancedFileObjectQueryRestSupport {

    @Override
    public void deleteById(String id, User user) {

    }

    @Override
    public Page<FileObject> listFileObject(DefaultFileObjectQueryParameter queryParameter, User user) {
        return null;
    }

}
