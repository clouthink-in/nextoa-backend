package in.clouthink.synergy.storage.mock.controller;

import in.clouthink.daas.fss.mongodb.model.FileObject;
import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.security.SecurityContexts;
import in.clouthink.synergy.storage.mock.dto.DefaultFileObjectQueryParameter;
import in.clouthink.synergy.storage.mock.support.AdvancedFileObjectQueryRestSupport;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Api("查询上传的文件")
@RestController
@RequestMapping(value = "/api")
public class AdvancedFileObjectRestController {

    @Autowired
    private AdvancedFileObjectQueryRestSupport advancedFileObjectQueryRestSupport;

    @DeleteMapping(value = "/files/{id}")
    public void deleteFileBId(@PathVariable String id) throws IOException {
        User user = (User) SecurityContexts.getContext().requireUser();
        advancedFileObjectQueryRestSupport.deleteById(id, user);
    }

    @GetMapping(value = "/files")
    public Page<FileObject> listFileObject(DefaultFileObjectQueryParameter queryParameter) {
        User user = (User) SecurityContexts.getContext().requireUser();
        return advancedFileObjectQueryRestSupport.listFileObject(queryParameter, user);
    }

}
