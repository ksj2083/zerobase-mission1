package Manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.WifiDTO;

public class SQLiteManager {
	private final String JDBC = "org.sqlite.JDBC";
	Connection conn;
	private void connect(){
		try
		{
			Class.forName(JDBC);
			String CONNSTRING = "jdbc:sqlite:C:\\TOOLS\\db\\SQLite";
			conn = DriverManager.getConnection(CONNSTRING);
		}
		catch(Exception e)
		{
			disconnect();
			throw new RuntimeException(e);
		}
	}
	private void disconnect(){
		if (conn == null)
		{
			return;
		}
		try {
			Class.forName(JDBC);
			conn.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	//근처와이파이 찾기, 계산은 DB서버에 위임하여 처리
	public List<WifiDTO> getNearWifi(String myLat, String myLnt, int cnt){
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT *,");
		sql.append("ACOS(");
		sql.append("SIN(? * PI() / 180)");
		sql.append("* SIN(LAT * PI() / 180)");
		sql.append("+ COS(? * PI() / 180)");
		sql.append("* COS(LAT * PI() / 180)");
		sql.append("* (");
		sql.append("SIN(? * PI() / 180)");
		sql.append("* SIN(LNT * PI() / 180)");
		sql.append("+ COS(? * PI() / 180)");
		sql.append("* COS(LNT * PI() / 180)");
		sql.append(")");
		sql.append(") * 6371000 AS DISTANCE ");
		sql.append("FROM WIFI ");
		sql.append("ORDER BY DISTANCE ASC ");
		sql.append("LIMIT ?;");

		connect();
		try
		{
			PreparedStatement ps = conn.prepareStatement(sql.toString());
			ps.setString(1, myLat);
			ps.setString(2, myLat);
			ps.setString(3, myLnt);
			ps.setString(4, myLnt);
			ps.setString(5, String.valueOf(cnt));
			ResultSet rs = ps.executeQuery();

			List<WifiDTO> result = new ArrayList<>();
			while (true) {
				if (!rs.next())
					break;
				WifiDTO dto = new WifiDTO();
				dto.setMGR_NO(rs.getString(1));
				dto.setWRDOFC(rs.getString(2));
				dto.setMAIN_NM(rs.getString(3));
				dto.setADRES1(rs.getString(4));
				dto.setADRES2(rs.getString(5));
				dto.setINSTL_FLOOR(rs.getString(6));
				dto.setINSTL_TY(rs.getString(7));
				dto.setINSTL_MBY(rs.getString(8));
				dto.setSVC_SE(rs.getString(9));
				dto.setCMCWR(rs.getString(10));
				dto.setCNSTC_YEAR(rs.getInt(11));
				dto.setINOUT_DOOR(rs.getString(12));
				dto.setREMARS3(rs.getString(13));
				dto.setLAT(rs.getString(14));
				dto.setLNT(rs.getString(15));
				dto.setWORK_DTTM(rs.getString(16));
				result.add(dto);
			}
			disconnect();
			return result;
		}
		catch (Exception e)
		{
			disconnect();
			throw new RuntimeException(e);
		}

	}

	//WIFI DTO LIST insert
	public void insertWifiInfos(List<WifiDTO> list){
		connect();
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
			try {
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
				//API 정보에 위도(LAT), 경도(LNT)가 반대로 되어 있음. DTO로 변환할 때 바로잡기
				ps.setString(14, dto.getLNT());
				ps.setString(15, dto.getLAT());
				ps.setString(16, dto.getWORK_DTTM());

				ps.execute();
			}
			catch (Exception e)
			{
				disconnect();
				throw new RuntimeException(e);
			}
		}
		disconnect();
	}

	public void deleteWifiInfos() {
		String sql = "DELETE FROM WIFI";
		connect();
		Statement stat = null;
		try {
			stat = conn.createStatement();
			stat.execute(sql);
		} catch (Exception e) {
			disconnect();
			throw new RuntimeException(e);
		}
		disconnect();
	}
}
