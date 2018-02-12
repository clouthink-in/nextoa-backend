package in.clouthink.synergy.team.domain.request;


import in.clouthink.synergy.team.domain.model.Receiver;

import java.util.List;

/**
 */
public interface AbstractActivityRequest {

    /**
     * 协作请求正文内容(对于允许编辑的,可以修改正文内容后再进入下一步)
     *
     * @return
     */
    String getActivityContent();

    /**
     * 主送
     */
    List<Receiver> getToReceivers();

    /**
     * 抄送
     *
     * @return
     */
    List<Receiver> getCcReceivers();

}
