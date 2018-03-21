package in.clouthink.synergy.storage.mock.spiImpl;

import in.clouthink.daas.fss.core.FileObject;
import in.clouthink.daas.fss.core.FileStorage;
import in.clouthink.daas.fss.core.FileStorageRequest;
import in.clouthink.daas.fss.spi.FileStorageService;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Map;

/**
 * @auther dz
 */
@Component
public class FileStorageServiceImpl implements FileStorageService {
    @Override
    public FileStorage findById(String id) {
        return null;
    }

    @Override
    public FileStorage findByFilename(String filename) {
        return null;
    }

    @Override
    public Map<String, Object> buildExtraAttributes(FileObject fileObject) {
        return null;
    }

    @Override
    public FileStorage store(InputStream inputStream, FileStorageRequest request) {
        return null;
    }

    @Override
    public FileStorage restore(String previousId, InputStream inputStream, FileStorageRequest request) {
        return null;
    }
}
