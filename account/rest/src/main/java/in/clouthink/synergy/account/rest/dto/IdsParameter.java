package in.clouthink.synergy.account.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import in.clouthink.synergy.account.domain.request.IdsRequest;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("id数组类请求")
public class IdsParameter implements IdsRequest {

    private String[] ids = ArrayUtils.EMPTY_STRING_ARRAY;

    @Override
    public String[] getIds() {
        return ids;
    }

    public void setIds(String[] ids) {
        this.ids = ids;
    }
}
