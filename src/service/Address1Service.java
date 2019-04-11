package service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface Address1Service {
	public List<Map<String,String>> selectAddrList(HttpServletRequest request);
	public void selectAddr(HttpServletRequest request);
	public int selectTotalAddrCount();
	public int updateAddr(Map<String,String> addr); // 나
	public Map<String,String> updateAddr2(HttpServletRequest request)throws IOException; // 선생님
	public Map<String,String> deleteAddr(HttpServletRequest request)throws IOException;
	public List<String> selectAdSido();
	public List<String> selectAdGugun(String adSido);
}
