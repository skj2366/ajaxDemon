package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.Address1DAO;
import db.DBCon;

public class Address1DAOImpl implements Address1DAO {

	private static String selectAddrListSql ="select * from (\r\n" + 
			"select ROWNUM as rown, addr.* FROM\r\n" + 
			"(select * from address $where$ order by ad_num) addr\r\n" + 
			"where rownum <=?)\r\n" + 
			"where rown >= ?";
	private static String selectAddrCount = "select count(1) from address $where$";
	private static String selectAddr = "select * from address where 1=1 and ad_num=?";
	private static String updateAddr = "update address set ad_code=?,ad_sido=?,ad_gugun=?,ad_dong=?,ad_lee=?,ad_bunji=?,ad_ho=? where ad_num = ?";
	private static String deleteAddr = "delete from address where ad_num=?";
	private static String selectAdSido = "select distinct ad_sido from address order by ad_sido";
	private static String selectAdGugun = "select distinct ad_gugun from address where ad_sido=? order by ad_gugun";
	
	@Override
	public List<Map<String, String>> selectAddrList(Map<String, String> addr) {
		String adDong = addr.get("ad_dong");
		String sql = selectAddrListSql.replace("$where$","");
		try {
			if(adDong!=null) {
				sql = selectAddrListSql.replace("$where$", " where ad_dong like '%'||?||'%'");
			}
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ps.setString(1,addr.get("lNum"));
			ps.setString(2, addr.get("sNum"));
			if(adDong!=null) {
				ps.setString(1,adDong);
				ps.setString(2,addr.get("lNum"));
				ps.setString(3, addr.get("sNum"));
			}
			ResultSet rs = ps.executeQuery();
			List<Map<String,String>> addrList = new ArrayList<>();
			while(rs.next()) {
				Map<String,String> address = new HashMap<>();
				address.put("ad_num",rs.getString("ad_num"));
				address.put("ad_sido",rs.getString("ad_sido"));
				address.put("ad_gugun",rs.getString("ad_gugun"));
				address.put("ad_dong",rs.getString("ad_dong"));
				address.put("ad_lee",rs.getString("ad_lee"));
				address.put("ad_bunji",rs.getString("ad_bunji"));
				address.put("ad_ho",rs.getString("ad_ho"));
				addrList.add(address);
			}
			return addrList;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return null;
	}
	@Override
	public int selectTotalAddrCount(Map<String,String> addr) {
		String adDong = addr.get("ad_dong");
		String sql = selectAddrCount.replace("$where$", "");
		try {
			if(adDong!=null) {
				sql = selectAddrCount.replace("$where$", " where ad_dong like '%'||?||'%'");
			}
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);

			if(adDong!=null) {
				ps.setString(1,adDong);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return 0;
	}
	@Override
	public Map<String, String> selectAddr(Map<String, String> addr) {

		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(selectAddr);
			ps.setString(1,addr.get("ad_num"));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> address = new HashMap<>();
				address.put("ad_num",rs.getString("ad_num"));
				address.put("ad_code",rs.getString("ad_code"));
				address.put("ad_sido",rs.getString("ad_sido"));
				address.put("ad_gugun",rs.getString("ad_gugun"));
				address.put("ad_dong",rs.getString("ad_dong"));
				address.put("ad_lee",rs.getString("ad_lee"));
				address.put("ad_bunji",rs.getString("ad_bunji"));
				address.put("ad_ho",rs.getString("ad_ho"));
				return address;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return null;
	}
	@Override
	public int updateAddr(Map<String, String> addr) {
//update address set ad_code=?,ad_sido=?,ad_gugun=?,ad_dong=?,ad_lee=?,ad_bunji=?,ad_ho=? where ad_num = ?
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(updateAddr);
			ps.setString(1, addr.get("adCode"));
			ps.setString(2, addr.get("adSido"));
			ps.setString(3, addr.get("adGugun"));
			ps.setString(4, addr.get("adDong"));
			ps.setString(5, addr.get("adLee"));
			ps.setString(6, addr.get("adBunji"));
			ps.setString(7, addr.get("adHo"));
			ps.setString(8, addr.get("adNum"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return 0;
	}
	@Override
	public int deleteAddr(Map<String, String> addr) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(deleteAddr);
			ps.setString(1, addr.get("adNum"));
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return 0;
	}
	@Override
	public List<String> selectAdSido() {

		try(Connection con = DBCon.getCon();
			PreparedStatement ps = con.prepareStatement(selectAdSido);) {
			ResultSet rs =ps.executeQuery();
			List<String> adSidoList = new ArrayList<>();
			while(rs.next()) {
				adSidoList.add(rs.getString("ad_sido"));
			}
			return adSidoList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return null;
	}
	@Override
	public List<String> selectAdGugun(String adSido) {
		try (Connection con = DBCon.getCon(); PreparedStatement ps = con.prepareStatement(selectAdGugun)) {
			ps.setString(1, adSido);
			ResultSet rs = ps.executeQuery();
			List<String> adGugunList = new ArrayList<>();
			while (rs.next()) {
				adGugunList.add(rs.getString("ad_gugun"));
			}
			return adGugunList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return null;
	}

}
