package in.clouthink.synergy.storage.mock.spiImpl;

import in.clouthink.daas.fss.core.FileObject;
import in.clouthink.daas.fss.core.FileObjectHistory;
import in.clouthink.daas.fss.spi.FileObjectService;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class FileObjectServiceImpl implements FileObjectService {
    @Override
    public FileObject findById(String id) {
        return null;
    }

    @Override
    public FileObject findByFinalFilename(String finalFileName) {
        return null;
    }

    @Override
    public List<FileObjectHistory> findHistoryById(String id) {
        return null;
    }
}
