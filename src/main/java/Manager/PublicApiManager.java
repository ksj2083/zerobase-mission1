package Manager;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

import com.google.gson.Gson;

import ApiDataModel.WifiPojo;

public class PublicApiManager {
	private final String apiUrl = "http://openapi.seoul.go.kr:8088";
	private final String key = "525555786b6b736a32384871585853";
	private final String requestFileType = "json";
	private final String serviceName = "TbPublicWifiInfo";

	private URL getUrl(int startIdx, int endIdx) throws IOException {
		StringBuilder urlBuilder = new StringBuilder(apiUrl); /*URL*/
		urlBuilder.append("/" +  URLEncoder.encode(key,"UTF-8") ); /*인증키 (sample사용시에는 호출시 제한됩니다.)*/
		urlBuilder.append("/" +  URLEncoder.encode(requestFileType,"UTF-8") ); /*요청파일타입 (xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode(serviceName,"UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
		urlBuilder.append("/" + URLEncoder.encode(String.valueOf(startIdx),"UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
		urlBuilder.append("/" + URLEncoder.encode(String.valueOf(endIdx),"UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
		return new URL(urlBuilder.toString());
	}

	//return : json
	private String request(int startIdx, int endIdx) throws IOException {
		URL url = getUrl(startIdx, endIdx);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/xml");
		//System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다.*/
		BufferedReader rd;

		// 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
		if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();
		return sb.toString();
	}

	private WifiPojo deserialization(String jsonStr)
	{
		Gson gson = new Gson();
		WifiPojo list = gson.fromJson(jsonStr, WifiPojo.class);
		return list;
	}

	//총 데이터 개수 반환
	public int getTotalSize(){
		String jsonSTr = null;
		try {
			jsonSTr = request(1,1);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		WifiPojo result = deserialization(jsonSTr);
		return result.getTbPublicWifiInfo().getList_total_count();
	}

	//결과 반환
	public WifiPojo getWifiList(int startIdx, int endIdx) {
		String jsonStr = null;
		try {
			jsonStr = request(startIdx, endIdx);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return deserialization(jsonStr);
	}

	// public static void main(String[] args) throws Exception {
	// 	PublicApiManager m = new PublicApiManager();
	// 	System.out.println(m.getTotalSize());
	// }
}