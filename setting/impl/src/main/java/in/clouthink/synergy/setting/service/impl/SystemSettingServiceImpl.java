package in.clouthink.synergy.setting.service.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.setting.domain.model.SystemSetting;
import in.clouthink.synergy.setting.domain.request.SaveSystemSettingRequest;
import in.clouthink.synergy.setting.exception.SystemSettingException;
import in.clouthink.synergy.setting.repository.SystemSettingRepository;
import in.clouthink.synergy.setting.service.SystemSettingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author dz
 */
@Service
public class SystemSettingServiceImpl implements SystemSettingService {

	@Autowired
	private SystemSettingRepository systemSettingRepository;

	@Override
	public SystemSetting getSystemSetting() {
		return ((List<SystemSetting>) systemSettingRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC,
																							  "createdAt")))).stream()
																											 .findFirst()
																											 .orElse(null);
	}

	@Override
	public void saveSystemSetting(SaveSystemSettingRequest request, User byWho) {
		if (StringUtils.isEmpty(request.getName())) {
			throw new SystemSettingException("系统名称不能为空");
		}

		SystemSetting existedSystemSetting = getSystemSetting();
		if (existedSystemSetting == null) {
			existedSystemSetting = new SystemSetting();
			SystemSetting.onCreate(existedSystemSetting, byWho);
		}

		BeanUtils.copyProperties(request, existedSystemSetting);
		SystemSetting.onModify(existedSystemSetting, byWho);

		systemSettingRepository.save(existedSystemSetting);
	}


}
