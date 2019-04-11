package dao;

import java.util.List;
import java.util.Map;

public interface Address1DAO {

	public List<Map<String,String>> selectAddrList(Map<String,String> addr);
	public Map<String,String> selectAddr(Map<String,String> addr);
	public int selectTotalAddrCount(Map<String, String> addr);
	public int updateAddr(Map<String,String> addr);
	public int deleteAddr(Map<String,String> addr);
	public List<String> selectAdSido();
	public List<String> selectAdGugun(String adSido);
	
}
