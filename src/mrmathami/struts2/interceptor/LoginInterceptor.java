package mrmathami.struts2.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import mrmathami.struts2.actions.LoginAction;

import java.util.Map;

public final class LoginInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// is logging in
		if (invocation.getAction().getClass().equals(LoginAction.class)) {
			return invocation.invoke();
		}

		final Map<String, Object> sessionMap = invocation.getInvocationContext().getSession();

		// Is there a "user" object stored in the user's HttpSession?
		if (sessionMap != null && sessionMap.get("user") != null) {
			return invocation.invoke();
		}

		return ActionSupport.LOGIN;
	}
}
