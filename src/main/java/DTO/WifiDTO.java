package DTO;

public class WifiDTO {
	private String MGR_NO; //관리번호
	private String WRDOFC; //자치구
	private String MAIN_NM; //와이파이명
	private String ADRES1; //도로명주소
	private String ADRES2; //상세주소
	private String INSTL_FLOOR; //설치위치(층)
	private String INSTL_TY; //설치유형
	private String INSTL_MBY; //설치기관
	private String SVC_SE; //서비스구분
	private String CMCWR; //망종류
	private int CNSTC_YEAR; //설치년도
	private String INOUT_DOOR; //실내외구분
	private String REMARS3; //wifi 접속환경
	private String LAT; //x좌표
	private String LNT; //y좌표
	private String WORK_DTTM; //작업일자

	public String getMGR_NO() {
		return MGR_NO;
	}

	public void setMGR_NO(String MGR_NO) {
		this.MGR_NO = MGR_NO;
	}

	public String getWRDOFC() {
		return WRDOFC;
	}

	public void setWRDOFC(String WRDOFC) {
		this.WRDOFC = WRDOFC;
	}

	public String getMAIN_NM() {
		return MAIN_NM;
	}

	public void setMAIN_NM(String MAIN_NM) {
		this.MAIN_NM = MAIN_NM;
	}

	public String getADRES1() {
		return ADRES1;
	}

	public void setADRES1(String ADRES1) {
		this.ADRES1 = ADRES1;
	}

	public String getADRES2() {
		return ADRES2;
	}

	public void setADRES2(String ADRES2) {
		this.ADRES2 = ADRES2;
	}

	public String getINSTL_FLOOR() {
		return INSTL_FLOOR;
	}

	public void setINSTL_FLOOR(String INSTL_FLOOR) {
		this.INSTL_FLOOR = INSTL_FLOOR;
	}

	public String getINSTL_TY() {
		return INSTL_TY;
	}

	public void setINSTL_TY(String INSTL_TY) {
		this.INSTL_TY = INSTL_TY;
	}

	public String getINSTL_MBY() {
		return INSTL_MBY;
	}

	public void setINSTL_MBY(String INSTL_MBY) {
		this.INSTL_MBY = INSTL_MBY;
	}

	public String getSVC_SE() {
		return SVC_SE;
	}

	public void setSVC_SE(String SVC_SE) {
		this.SVC_SE = SVC_SE;
	}

	public String getCMCWR() {
		return CMCWR;
	}

	public void setCMCWR(String CMCWR) {
		this.CMCWR = CMCWR;
	}

	public int getCNSTC_YEAR() {
		return CNSTC_YEAR;
	}

	public void setCNSTC_YEAR(int CNSTC_YEAR) {
		this.CNSTC_YEAR = CNSTC_YEAR;
	}

	public String getINOUT_DOOR() {
		return INOUT_DOOR;
	}

	public void setINOUT_DOOR(String INOUT_DOOR) {
		this.INOUT_DOOR = INOUT_DOOR;
	}

	public String getREMARS3() {
		return REMARS3;
	}

	public void setREMARS3(String REMARS3) {
		this.REMARS3 = REMARS3;
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

	public String getWORK_DTTM() {
		return WORK_DTTM;
	}

	public void setWORK_DTTM(String WORK_DTTM) {
		this.WORK_DTTM = WORK_DTTM;
	}
}
