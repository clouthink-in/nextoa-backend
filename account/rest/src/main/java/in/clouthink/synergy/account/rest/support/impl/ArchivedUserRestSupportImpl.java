package in.clouthink.synergy.account.rest.support.impl;

import in.clouthink.synergy.account.domain.model.User;
import in.clouthink.synergy.account.rest.view.UserDetailView;
import in.clouthink.synergy.account.rest.param.UserSearchParam;
import in.clouthink.synergy.account.rest.view.UserView;
import in.clouthink.synergy.account.rest.support.ArchivedUserRestSupport;
import in.clouthink.synergy.account.service.AccountService;
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
	public Page<UserView> listArchivedUsers(UserSearchParam queryRequest) {
		queryRequest.setEnabled(null);
		Page<User> userPage = accountService.listArchivedUsers(queryRequest);
		return new PageImpl<>(userPage.getContent().stream().map(UserView::from).collect(Collectors.toList()),
							  new PageRequest(queryRequest.getStart(), queryRequest.getLimit()),
							  userPage.getTotalElements());
	}

	@Override
	public UserDetailView getArchivedUser(String id) {
		User user = accountService.findById(id);
		return UserDetailView.from(user);
	}

}
