package in.clouthink.synergy.storage.support.impl;

import in.clouthink.daas.fss.mongodb.model.FileObject;
import in.clouthink.daas.fss.mongodb.repository.FileObjectRepository;
import in.clouthink.daas.fss.spi.FileObjectService;
import in.clouthink.daas.fss.spi.FileStorageService;
import in.clouthink.synergy.account.domain.model.Roles;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.storage.param.DefaultFileObjectSearchParam;
import in.clouthink.synergy.storage.exception.FileException;
import in.clouthink.synergy.storage.support.AdvancedFileObjectQueryRestSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 */
@Component
public class AdvancedFileObjectQueryRestSupportImpl implements AdvancedFileObjectQueryRestSupport {

    @Autowired
    private FileObjectService fileObjectService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private FileObjectRepository fileObjectRepository;

    @Override
    public void deleteById(String id, User user) {
        in.clouthink.daas.fss.core.FileObject fileObject = fileObjectService.findById(id);
        if (fileObject == null) {
            return;
        }

        if (user.getAuthorities().contains(Roles.ROLE_ADMIN) || user.getAuthorities().contains(Roles.ROLE_MGR)) {
            try {
                fileObjectRepository.delete(id);
            } catch (Exception e) {
            }
            return;
        }

        if (!user.getUsername().equalsIgnoreCase(fileObject.getUploadedBy())) {
            throw new FileException("只能删除自己上传的附件.");
        }

        try {
            fileObjectRepository.delete(id);
        } catch (Exception e) {
        }
    }

    @Override
    public Page<FileObject> listFileObject(DefaultFileObjectSearchParam queryParameter, User user) {
        String sortedBy = queryParameter.getSortedBy();
        if (StringUtils.isEmpty(sortedBy)) {
            sortedBy = "uploadedAt";
        }
        return fileObjectRepository.findPage(queryParameter,
                                             new PageRequest(queryParameter.getStart(),
                                                             queryParameter.getLimit(),
                                                             new Sort(Sort.Direction.ASC, sortedBy)));
    }
}
