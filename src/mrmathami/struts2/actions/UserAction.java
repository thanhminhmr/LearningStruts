package mrmathami.struts2.actions;

import com.opensymphony.xwork2.ActionSupport;
import mrmathami.struts2.model.bean.User;
import mrmathami.struts2.model.dao.Users;
import mrmathami.struts2.utilities.ActionError;

import java.util.ArrayList;

public final class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private User user;
	private ArrayList<User> users;
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

	public ArrayList<User> getUsers() {
		return users;
	}

	public String doCreate() {
		if (user == null) {
			// direct access
			error = ActionError.NONE;
			return INPUT;
		}

		if (!user.has(false, true, true)
				|| user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			// error empty
			error = ActionError.EMPTY;
			return INPUT;
		}

		User returnUser = Users.addUser(user);
		if (returnUser == null) {
			// error failed
			error = ActionError.FAILED;
			return INPUT;
		}
		return SUCCESS;
	}

	public String doList() {
		users = Users.getUsers();
		return SUCCESS;
	}

	public String doEdit() {
		if (user == null) {
			// not normal
			return SUCCESS;
		}

		User returnUser = Users.getUserById(user);
		if (returnUser == null) {
			// not normal
			return SUCCESS;
		}

		if (user.getUsername() == null || user.getPassword() == null) {
			// direct access
			user = returnUser;
			error = ActionError.NONE;
			return INPUT;
		}

		if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
			// error empty
			user = returnUser;
			error = ActionError.EMPTY;
			return INPUT;
		}

		if (!Users.updateUserById(user)) {
			// error failed
			error = ActionError.FAILED;
			return INPUT;
		}
		return SUCCESS;
	}

	public String doDelete() {
		if (user == null) {
			// not normal
			return SUCCESS;
		}

		User returnUser = Users.getUserById(user);
		if (returnUser == null) {
			// not normal
			return SUCCESS;
		}

		if (user.getUsername() == null || user.getPassword() == null) {
			// direct access
			user = returnUser;
			error = ActionError.NONE;
			return INPUT;
		}

		if (!user.getUsername().equals(returnUser.getUsername())
				|| !user.getPassword().equals(returnUser.getPassword())
				|| !Users.deleteUserById(user)) {
			// error failed
			error = ActionError.FAILED;
			return INPUT;
		}

		return SUCCESS;
	}

}
