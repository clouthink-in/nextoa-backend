package in.clouthink.nextoa.account.rest.support.impl;

import in.clouthink.nextoa.account.domain.model.User;
import in.clouthink.nextoa.account.rest.dto.UserDetail;
import in.clouthink.nextoa.account.rest.dto.UserQueryParameter;
import in.clouthink.nextoa.account.rest.dto.UserSummary;
import in.clouthink.nextoa.account.rest.support.ArchivedUserRestSupport;
import in.clouthink.nextoa.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ArchivedUserRestSupportImpl implements ArchivedUserRestSupport {

	@Autowired
	private AccountService accountService;

	@Override
	public Page<UserSummary> listArchivedUsers(UserQueryParameter queryRequest) {
		queryRequest.setEnabled(null);
		Page<User> userPage = accountService.listArchivedUsers(queryRequest);
		return new PageImpl<>(userPage.getContent().stream().map(UserSummary::from).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  userPage.getTotalElements());
	}

	@Override
	public UserDetail getArchivedUser(String id) {
		User user = accountService.findById(id);
		return UserDetail.from(user);
	}

}
