package in.clouthink.synergy.sms.history.rest.controller;

import in.clouthink.synergy.sms.history.rest.dto.SmsHistoriesSearchParam;
import in.clouthink.synergy.sms.history.rest.dto.SmsHistorySummary;
import in.clouthink.synergy.sms.history.rest.support.SmsHistorySupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "/sms-histories", description = "短信发送记录")
@RestController
@RequestMapping("/api/sms-histories")
public class SmsHistoryRestController {

    @Autowired
    private SmsHistorySupport smsHistorySupport;

    @ApiOperation(value = "获取短信发送记录（分页）")
    @GetMapping()
    public Page<SmsHistorySummary> findPage(SmsHistoriesSearchParam parameter) {
        return smsHistorySupport.findPage(parameter);
    }

}
