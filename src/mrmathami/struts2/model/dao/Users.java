package mrmathami.struts2.model.dao;

import mrmathami.struts2.model.bean.User;
import mrmathami.struts2.utilities.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public final class Users {
	public static User getUserByCredential(User user) {
		Connection connection = DatabaseConnection.getConnection();
		if (user == null || !user.has(false, true, true)) return null;

		try (PreparedStatement statement = connection.prepareStatement("SELECT userId, username, password FROM users WHERE username = ? AND password = ?")) {
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static User getUserById(User user) {
		Connection connection = DatabaseConnection.getConnection();
		if (user == null || !user.has(true, false, false)) return null;

		try (PreparedStatement statement = connection.prepareStatement("SELECT userId, username, password FROM users WHERE userId = ?")) {
			statement.setInt(1, user.getUserId());

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return new User(
						(Integer) resultSet.getObject("userId"),
						resultSet.getString("username"),
						resultSet.getString("password")
				);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static ArrayList<User> getUsers() {
		Connection connection = DatabaseConnection.getConnection();

		try (PreparedStatement statement = connection.prepareStatement("SELECT userId, username, password FROM users")) {
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.isBeforeFirst()) {
				ArrayList<User> users = new ArrayList<>();
				while (resultSet.next()) {
					users.add(new User(
							(Integer) resultSet.getObject("userId"),
							resultSet.getString("username"),
							resultSet.getString("password")
					));
				}
				return users;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static User addUser(User user) {
		Connection connection = DatabaseConnection.getConnection();
		if (user == null || !user.has(false, true, true)) return null;

		try (PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS)) {
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

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static boolean updateUserById(User user) {
		Connection connection = DatabaseConnection.getConnection();
		if (user == null || !user.has(true, true, true)) return false;

		try (PreparedStatement statement = connection.prepareStatement("UPDATE users SET username = ?, password = ? WHERE userId = ?")) {
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setInt(3, user.getUserId());

			if (statement.executeUpdate() != 0) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteUserById(User user) {
		Connection connection = DatabaseConnection.getConnection();
		if (user == null || !user.has(true, false, false)) return false;

		try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE userId = ?")) {
			statement.setInt(1, user.getUserId());

			return statement.executeUpdate() != 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
