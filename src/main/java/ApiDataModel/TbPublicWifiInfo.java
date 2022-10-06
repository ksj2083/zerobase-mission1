package ApiDataModel;

import java.util.ArrayList;
import java.util.List;


public class TbPublicWifiInfo {
	private int list_total_count;
	private Result RESULT;
	private List<Row> row = new ArrayList<>();

	public int getList_total_count() {
		return list_total_count;
	}

	public void setList_total_count(int list_total_count) {
		this.list_total_count = list_total_count;
	}

	public Result getRESULT() {
		return RESULT;
	}

	public void setRESULT(Result RESULT) {
		this.RESULT = RESULT;
	}

	public List<Row> getRow() {
		return row;
	}

	public void setRow(List<Row> row) {
		this.row = row;
	}
}