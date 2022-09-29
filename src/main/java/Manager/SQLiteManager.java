package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteManager {
	private final String jdbc = "org.sqlite.JDBC";
	private final String connString = "jdbc:sqlite:C:\\TOOLS\\db\\SQLite";
	private Connection getConnect() throws SQLException, ClassNotFoundException {
		Connection conn = null;
		try
		{
			Class.forName(jdbc);
			conn = DriverManager.getConnection(connString);
		}
		catch(Exception e)
		{
			closeConnect(conn); //오류 발생시 connection 해제
		}
		return conn;
	}
	private void closeConnect(Connection conn) throws ClassNotFoundException, SQLException {
		if (conn == null)
		{
			return;
		}
		Class.forName(jdbc);
		conn.close();
	}

	public String selectTest() throws SQLException, ClassNotFoundException {
		String sql = "SELECT * FROM WIFI";

		Connection conn = getConnect();
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery(sql);
		String tmp = "";
		while(rs.next()) {
				tmp = rs.getString("MGR_NO");
			}
		closeConnect(conn);
		return tmp;
	}

	public void insertTest() throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO WIFI (MGR_NO) VALUES (?)";

		Connection conn = getConnect();
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, "test");
		ps.execute();
		closeConnect(conn);
	}

	public static void main(String[] args) throws Exception {
		SQLiteManager h = new SQLiteManager();
		h.insertTest();
		System.out.println(h.selectTest());
	}
}
