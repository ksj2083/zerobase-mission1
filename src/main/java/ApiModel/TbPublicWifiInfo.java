package ApiModel;

import java.util.ArrayList;
import java.util.List;


public class TbPublicWifiInfo {
	private int list_total_count;
	private RESULT RESULT;
	private List<row> row = new ArrayList<>();

	public int getList_total_count() {
		return list_total_count;
	}

	public void setList_total_count(int list_total_count) {
		this.list_total_count = list_total_count;
	}

	public ApiModel.RESULT getRESULT() {
		return RESULT;
	}

	public void setRESULT(ApiModel.RESULT RESULT) {
		this.RESULT = RESULT;
	}

	public List<ApiModel.row> getRow() {
		return row;
	}

	public void setRow(List<ApiModel.row> row) {
		this.row = row;
	}
}