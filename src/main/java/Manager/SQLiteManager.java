package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import DTO.WifiDTO;

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
	// public void insertTest() throws SQLException, ClassNotFoundException {
	// 	String sql = "INSERT INTO WIFI (MGR_NO) VALUES (?)";
	//
	// 	Connection conn = getConnect();
	// 	PreparedStatement ps = conn.prepareStatement(sql);
	// 	ps.setString(1, "test");
	// 	ps.execute();
	// 	closeConnect(conn);
	// }

	//WIFI DTO LIST insert
	public void insertWifiInfos(List<WifiDTO> list) throws SQLException, ClassNotFoundException {
		Connection conn = getConnect();
		PreparedStatement ps;

		for(WifiDTO dto : list) {

			String sql = "INSERT INTO WIFI "
				+ "("
				+ "MGR_NO, " //1
				+ "WRDOFC, " //2
				+ "MAIN_NM, " //3
				+ "ADRES1, " //4
				+ "ADRES2, " //5
				+ "INSTL_FLOOR, " //6
				+ "INSTL_TY, " //7
				+ "INSTL_MBY, " //8
				+ "SVC_SE, " //9
				+ "CMCWR, " //10
				+ "CNSTC_YEAR, " //11
				+ "INOUT_DOOR, " //12
				+ "REMARS3, " //13
				+ "LAT, " //14
				+ "LNT, " //15
				+ "WORK_DTTM) " //16
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
				+ ")";

			ps = conn.prepareStatement(sql);
			ps.setString(1, dto.getMGR_NO());
			ps.setString(2, dto.getWRDOFC());
			ps.setString(3, dto.getMAIN_NM());
			ps.setString(4, dto.getADRES1());
			ps.setString(5, dto.getADRES1());
			ps.setString(6, dto.getINSTL_FLOOR());
			ps.setString(7, dto.getINSTL_TY());
			ps.setString(8, dto.getINSTL_MBY());
			ps.setString(9, dto.getSVC_SE());
			ps.setString(10, dto.getCMCWR());
			ps.setString(11, String.valueOf(dto.getCNSTC_YEAR()));
			ps.setString(12, dto.getINOUT_DOOR());
			ps.setString(13, dto.getREMARS3());
			ps.setString(14, dto.getLAT());
			ps.setString(15, dto.getLNT());
			ps.setString(16, dto.getWORK_DTTM());

			ps.execute();
		}

		closeConnect(conn);
	}

	public void deleteWifiInfos() throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM WIFI";
		Connection conn = getConnect();
		Statement stat = conn.createStatement();
		stat.execute(sql);
	}
}
