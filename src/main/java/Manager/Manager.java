package Manager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ApiDataModel.*;
import DTO.*;

public class Manager {
	PublicApiManager pm;
	SQLiteManager sm;

	public Manager() {
		pm = new PublicApiManager();
		sm = new SQLiteManager();
	}

	public List<WifiDTO> getNearWifiInfos(String xCoordinate, String yCoordinate, int count)
	{
		List<WifiDTO> result =sm.getNearWifi(xCoordinate, yCoordinate, count);
		return result;
	}

	public List<HistoryDTO> getHistory()
	{
		List<HistoryDTO> result = sm.getHistory();
		return result;
	}

	//모두 삭제 후, api에서 와이파이 정보 받아서 저장하고 저장개수 반환
	public int saveApiData() {
		sm.deleteWifiInfos(); //기존 데이터 삭제 후 진행
		int total = pm.getTotalSize();
		//한번에 천개씩 받아와서 저장
		int startIdx = 1;
		while(startIdx<total)
		{
			int endIdx = Math.min(startIdx+999, total);
			WifiPojo pojo = pm.getWifiList(startIdx,endIdx);
			List<WifiDTO> dtoList = convertPojoToDto(pojo);
			sm.insertWifiInfos(dtoList);
			startIdx = endIdx+1;
		}
		return total;
	}

	public void saveHistory(String lat, String lnt)
	{
		HistoryDTO dto = new HistoryDTO();
		dto.setLAT(lat);
		dto.setLNT(lnt);

		sm.insertHistory(dto);
	}
	private List<WifiDTO> convertPojoToDto(WifiPojo pojo)
	{
		List<WifiDTO> result = new ArrayList<>();
		List<Row> rowList = pojo.getTbPublicWifiInfo().getRow();

		for(Row row : rowList)
		{
			WifiDTO dto = new WifiDTO();
			dto.setMGR_NO(row.getX_SWIFI_MGR_NO());
			dto.setWRDOFC(row.getX_SWIFI_WRDOFC());
			dto.setMAIN_NM(row.getX_SWIFI_MAIN_NM());
			dto.setADRES1(row.getX_SWIFI_ADRES1());
			dto.setADRES2(row.getX_SWIFI_ADRES2());
			dto.setINSTL_FLOOR(row.getX_SWIFI_INSTL_FLOOR());
			dto.setINSTL_TY(row.getX_SWIFI_INSTL_TY());
			dto.setINSTL_MBY(row.getX_SWIFI_INSTL_MBY());
			dto.setSVC_SE(row.getX_SWIFI_SVC_SE());
			dto.setCMCWR(row.getX_SWIFI_CMCWR());
			if(row.getX_SWIFI_CNSTC_YEAR() .equals("") || row.getX_SWIFI_CNSTC_YEAR() == null)
			{
				dto.setCNSTC_YEAR(0);
			}
			else
			{
				dto.setCNSTC_YEAR(Integer.parseInt(row.getX_SWIFI_CNSTC_YEAR()));
			}
			dto.setINOUT_DOOR(row.getX_SWIFI_INOUT_DOOR());
			dto.setREMARS3(row.getX_SWIFI_REMARS3());
			dto.setLAT(row.getLAT());
			dto.setLNT(row.getLNT());
			dto.setWORK_DTTM(row.getWORK_DTTM());

			result.add(dto);
		}

		return result;
	}

}
