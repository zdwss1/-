package utils;
import java.sql.*;

public class DbUtil {
  //声明连接数据库的信息，例如数据库URL、用户名及密码

	private static final String URL = "jdbc:mysql://127.0.0.1:3306/exam?user=root&password=123456&useUnicode=true&characterEncoding=utf-8";
	//声明JDBC的相关对象
	protected static Statement s = null;
	protected static ResultSet rs = null;
	protected static boolean rsd = false;
	protected static Connection conn = null;
	//创建数据库连接	单个线程访问
	public static synchronized Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	//执行INSERT、UPDATE、DELETE语句 sql SQL语句，字符串类型 执行结果int类型
	public static int executeUpdate(String sql) {
		int result = 0;
		try {
			s = getConnection().createStatement();
			result = s.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	//执行SELECT语句 sql SQL语句，字符串类型 return ResultSet结果集
	public static ResultSet executeQuery(String sql) {
		try {
			s = getConnection().createStatement();
			rs = s.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return rs;
	}
	
	/*
	 * 执行动态SQL语句
	 * sql含有参数的动态SQL语句
	 * return 返回PreparedStatement对象
	 * */
	public static PreparedStatement executePreparedStatement(String sql) {
		PreparedStatement ps = null;
		try {
			ps = getConnection().prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ps;
	}
	/*
	 * 事务回滚
	 * */
	public static void rollback() {
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 关闭数据库连接对象
	 * */
	public static void close() {
		
			try {
				if(rs!=null)
				rs.close();
				if(s!=null) s.close();
				if(conn!=null) conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
