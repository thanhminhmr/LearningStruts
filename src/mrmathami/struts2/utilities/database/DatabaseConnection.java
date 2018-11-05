package mrmathami.struts2.utilities.database;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/*
public class DatabaseConnection {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/learning_servlet?setUnicode=true&characterEncoding=UTF-8";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
/*/

public class DatabaseConnection {
	private static final String resource = "mybatis.xml";
	private static final SqlSessionFactory sqlSessionFactory;

	static {
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = inputStream != null ? new SqlSessionFactoryBuilder().build(inputStream) : null;
	}

	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
}

//*/