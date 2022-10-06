package DTO;

public class HistoryDTO {
	public int getHIS_NO() {
		return HIS_NO;
	}

	public void setHIS_NO(int HIS_NO) {
		this.HIS_NO = HIS_NO;
	}

	public String getLAT() {
		return LAT;
	}

	public void setLAT(String LAT) {
		this.LAT = LAT;
	}

	public String getLNT() {
		return LNT;
	}

	public void setLNT(String LNT) {
		this.LNT = LNT;
	}

	public String getLKUP_DTTM() {
		return LKUP_DTTM;
	}

	public void setLKUP_DTTM(String LKUP_DTTM) {
		this.LKUP_DTTM = LKUP_DTTM;
	}

	int HIS_NO; //기록번호
	String LAT; //위도
	String LNT; //경도
	String LKUP_DTTM; //조회일자

}
