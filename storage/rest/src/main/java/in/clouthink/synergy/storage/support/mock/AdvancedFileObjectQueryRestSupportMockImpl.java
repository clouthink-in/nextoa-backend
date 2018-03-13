package in.clouthink.synergy.storage.support.mock;

import in.clouthink.daas.fss.mongodb.model.FileObject;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.storage.dto.DefaultFileObjectQueryParameter;
import in.clouthink.synergy.storage.support.AdvancedFileObjectQueryRestSupport;
import org.springframework.data.domain.Page;

/**
 * AdvancedFileObjectQueryRestSupport mocker
 *
 * @author dz
 */
public class AdvancedFileObjectQueryRestSupportMockImpl implements AdvancedFileObjectQueryRestSupport {
	@Override
	public void deleteById(String id, User user) {

	}

	@Override
	public Page<FileObject> listFileObject(DefaultFileObjectQueryParameter queryParameter, User user) {
		return null;
	}
}
