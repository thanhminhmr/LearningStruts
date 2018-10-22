package mrmathami.struts2.actions;

import com.opensymphony.xwork2.ActionSupport;
import mrmathami.struts2.model.bean.User;
import mrmathami.struts2.model.dao.Users;
import mrmathami.struts2.utilities.ActionError;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public final class LoginAction extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 1L;
	private Map<String, Object> sessionMap;
	private User user;
	private ActionError error = ActionError.NONE;

	public ActionError getError() {
		return error;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String doLogin() {
		if (sessionMap.get("user") != null) {
			return SUCCESS;
		}

		if (user == null) {
			// direct access
			error = ActionError.NONE;
			return INPUT;
		}

		if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			// LOGIN_EMPTY
			error = ActionError.EMPTY;
			return INPUT;
		}

		User returnUser = Users.getUserByCredential(user);
		if (returnUser == null) {
			// LOGIN_FAILED
			error = ActionError.FAILED;
			return INPUT;
		}

		sessionMap.put("user", returnUser);
		return SUCCESS;
	}

	public String doLogout() {
		sessionMap.remove("user");
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.sessionMap = map;
	}
}
