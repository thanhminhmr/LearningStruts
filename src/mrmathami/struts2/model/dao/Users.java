package mrmathami.struts2.model.dao;

import mrmathami.struts2.model.bean.User;
import mrmathami.struts2.utilities.database.DatabaseConnection;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

//*
public final class Users {
	public static User getUserByCredential(User user) {
		if (user == null || !user.has(false, true, true)) return null;
		SqlSession sqlSession = DatabaseConnection.getSqlSession();
		User resultUser = sqlSession.selectOne("Users.getUserByCredential", user);
		sqlSession.close();
		return resultUser;
	}

	public static User getUserById(User user) {
		if (user == null || !user.has(true, false, false)) return null;
		SqlSession sqlSession = DatabaseConnection.getSqlSession();
		User resultUser = sqlSession.selectOne("Users.getUserById", user);
		sqlSession.close();
		return resultUser;
	}

	public static List<User> getUsers() {
		SqlSession sqlSession = DatabaseConnection.getSqlSession();
		List<User> resultUsers = sqlSession.selectList("Users.getUsers");
		sqlSession.close();
		return resultUsers;
	}

	public static User addUser(User user) {
		if (user == null || !user.has(false, true, true)) return null;
		SqlSession sqlSession = DatabaseConnection.getSqlSession();
		int ret = sqlSession.insert("Users.addUser", user);
		sqlSession.commit();
		sqlSession.close();
		if (ret == 0 || !user.has(true, true, true)) return null;
		return user;
	}

	public static boolean updateUserById(User user) {
		if (user == null || !user.has(true, true, true)) return false;
		SqlSession sqlSession = DatabaseConnection.getSqlSession();
		int ret = sqlSession.update("Users.updateUserById", user);
		sqlSession.commit();
		sqlSession.close();
		return ret != 0;
	}

	public static boolean deleteUserById(User user) {
		if (user == null || !user.has(true, false, false)) return false;
		SqlSession sqlSession = DatabaseConnection.getSqlSession();
		int ret = sqlSession.delete("Users.deleteUserById", user);
		sqlSession.commit();
		sqlSession.close();
		return ret != 0;
	}
}
/*/
public final class Users {
	public static User getUserByCredential(User user) throws SQLException {
		if (user == null || !user.has(false, true, true)) return null;

		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT userId, username, password FROM users WHERE username = ? AND password = ?");
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new User(
					(Integer) resultSet.getObject("userId"),
					resultSet.getString("username"),
					resultSet.getString("password")
			);
		}

		return null;
	}

	public static User getUserById(User user) throws SQLException {
		if (user == null || !user.has(true, false, false)) return null;

		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT userId, username, password FROM users WHERE userId = ?");
		statement.setInt(1, user.getUserId());

		ResultSet resultSet = statement.executeQuery();
		if (resultSet.next()) {
			return new User(
					(Integer) resultSet.getObject("userId"),
					resultSet.getString("username"),
					resultSet.getString("password")
			);
		}
		return null;
	}

	public static List<User> getUsers() throws SQLException {
		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement("SELECT userId, username, password FROM users");
		ResultSet resultSet = statement.executeQuery();
		if (resultSet.isBeforeFirst()) {
			List<User> users = new ArrayList<>();
			while (resultSet.next()) {
				users.add(new User(
						(Integer) resultSet.getObject("userId"),
						resultSet.getString("username"),
						resultSet.getString("password")
				));
			}
			return users;
		}
		return null;
	}

	public static User addUser(User user) throws SQLException {
		if (user == null || !user.has(false, true, true)) return null;

		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());

		if (statement.executeUpdate() != 0) {
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.next()) {
				return new User(
						resultSet.getInt(1),
						user.getUsername(),
						user.getPassword()
				);
			}
		}
		return null;
	}

	public static boolean updateUserById(User user) throws SQLException {
		if (user == null || !user.has(true, true, true)) return false;

		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement("UPDATE users SET username = ?, password = ? WHERE userId = ?");
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword());
		statement.setInt(3, user.getUserId());

		return statement.executeUpdate() != 0;
	}

	public static boolean deleteUserById(User user) throws SQLException {
		if (user == null || !user.has(true, false, false)) return false;

		Connection connection = DatabaseConnection.getConnection();
		PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE userId = ?");
		statement.setInt(1, user.getUserId());

		return statement.executeUpdate() != 0;
	}
}
// */
