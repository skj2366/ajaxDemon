package service.impl;

import java.util.Map;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import service.UserService;

public class UserServiceImpl implements UserService {

	private UserDAO udao = new UserDAOImpl();
	@Override
	public int insertUser(Map<String, String> user) {
		return udao.insertUser(user);
	}

	@Override
	public boolean compareUser(Map<String, String> user) {
		return udao.compareUser(user);
	}
	
	public static void main(String[] args) {
//		UserService udao = new UserServiceImpl();
//		Map<String,String> test = new HashMap<>();
//		test.put("uiName","도동이");
//		test.put("uiId","asdasd");
//		test.put("uiPwd","asdasd");
//		test.put("uiEmail","asdasd@asdasd.com");
//		
//		
//		System.out.println(udao.insertUser(test));
	}

	@Override
	public Map<String, String> login(String uiId, String uiPwd) {
		return udao.selectUserByUiId(uiId,uiPwd);
	}
	
}
