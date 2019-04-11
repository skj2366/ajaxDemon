package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import dao.UserDAO;
import db.DBCon;

public class UserDAOImpl implements UserDAO {

	private static final String INSERT_USER = "insert into user_info(ui_num, ui_name, ui_id, ui_pwd, ui_email) values(seq_ui_num.nextval,?,?,?,?)";
	private static final String COMPARE_USER = "select ui_id, ui_pwd from user_info where ui_id=?";
	private String selectUserByUiId = "select * from user_info where ui_id=? and ui_pwd=?";
	@Override
	public int insertUser(Map<String, String> user) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(INSERT_USER);
			ps.setString(1, user.get("uiName"));
			ps.setString(2, user.get("uiId"));
			ps.setString(3, user.get("uiPwd"));
			ps.setString(4, user.get("uiEmail"));
			
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return 0;
	}
	
	@Override
	public boolean compareUser(Map<String, String> user) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(COMPARE_USER);
			ps.setString(1, user.get("uiId"));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> userMap = new HashMap<>();
				userMap.put("ui_id", rs.getString("ui_id"));
				userMap.put("ui_pwd", rs.getString("ui_pwd"));
				if(user.get("uiId").equals(userMap.get("ui_id"))) {
					if(user.get("uiPwd").equals(userMap.get("ui_pwd"))) {
						return true;
					}else {
						return false;
					}
					
				}else {
					return false;
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		
		return false;
	}
	
	@Override
	public Map<String, String> selectUserByUiId(String uiId, String uiPwd) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(selectUserByUiId);
			ps.setString(1, uiId);
			ps.setString(2, uiPwd);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> user = new HashMap<>();
				user.put("uiId", rs.getString("ui_id"));
				user.put("uiName", rs.getString("ui_name"));
				user.put("uiPwd", rs.getString("ui_pwd"));
				user.put("uiEmail", rs.getString("ui_email"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return null;
	}

	public static void main(String[] args) {
//		UserDAO udao = new UserDAOImpl();
//		Map<String,String> test = new HashMap<>();
//		test.put("uiName","도동이");
//		test.put("uiId","asdasd");
//		test.put("uiPwd","asdasd");
//		test.put("uiEmail","asdasd@asdasd.com");
//		
//		
//		System.out.println(udao.insertUser(test));
		
//		UserDAO udao = new UserDAOImpl();
//		Map<String,String> test = new HashMap<>();
//		test.put("uiId", "asdasd");
//		test.put("uiPwd", "asdasd");
//		System.out.println(udao.compareUser(test));
	
//		String uri = "/movuie/list";
//		int idx = uri.lastIndexOf("/");
//		System.out.println(idx);
		
	}

}
