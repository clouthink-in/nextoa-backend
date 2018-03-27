package in.clouthink.synergy.storage.controller;

import in.clouthink.daas.fss.mongodb.model.FileObject;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.storage.param.DefaultFileObjectSearchParam;
import in.clouthink.synergy.storage.support.AdvancedFileObjectQueryRestSupport;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api(value = "/api/files", description = "查询上传的文件")
@RestController
@RequestMapping(value = "/api/files")
public class AdvancedFileObjectRestController {

    @Autowired
    private AdvancedFileObjectQueryRestSupport advancedFileObjectQueryRestSupport;

    @DeleteMapping(value = "/{id}")
    public void deleteFileById(@PathVariable String id) throws IOException {
        User user = (User) SecurityContexts.getContext().requireUser();
        advancedFileObjectQueryRestSupport.deleteById(id, user);
    }

    @GetMapping()
    public Page<FileObject> listFileObject(DefaultFileObjectSearchParam queryParameter) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return advancedFileObjectQueryRestSupport.listFileObject(queryParameter, user);
    }

}
