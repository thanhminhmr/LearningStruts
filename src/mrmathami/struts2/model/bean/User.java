package mrmathami.struts2.model.bean;

import java.util.EnumMap;

public final class User {
	private final EnumMap<UserFields, Object> map = new EnumMap<>(UserFields.class);

	public Integer getUserId() {
		return (Integer) map.get(UserFields.USER_ID);
	}

	public void setUserId(Integer userId) {
		map.put(UserFields.USER_ID, userId);
	}

	public String getUsername() {
		return (String) map.get(UserFields.USERNAME);
	}

	public void setUsername(String username) {
		map.put(UserFields.USERNAME, username);
	}

	public String getPassword() {
		return (String) map.get(UserFields.PASSWORD);
	}

	public void setPassword(String password) {
		map.put(UserFields.PASSWORD, password);
	}

	public boolean has(boolean... booleans) {
		if (booleans.length != UserFields.size()) throw new IllegalArgumentException();
		for (int i = 0; i < booleans.length; i++) {
			if (booleans[i] && map.get(UserFields.value(i)) == null) return false;
		}
		return true;
	}

	public static String[] names() {
		String[] names = new String[UserFields.size()];
		for (int i = 0; i < names.length; i++) {
			names[i] = UserFields.values[i].toString();
		}
		return names;
	}

	public Object[] values() {
		return map.values().toArray();
	}

	private enum UserFields {
		USER_ID("userId"),
		USERNAME("username"),
		PASSWORD("password");

		public static final UserFields[] values = values();
		private final String string;

		UserFields(String string) {
			this.string = string;
		}

		public static UserFields value(int i) {
			return values[i];
		}

		public static int size() {
			return values.length;
		}

		@Override
		public String toString() {
			return string;
		}
	}
}
