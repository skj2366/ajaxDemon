package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Address1Service;
import service.impl.Address1ServiceImpl;
import utils.Command;

public class AddrServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Address1Service as = new Address1ServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = Command.getCmd(request);
		if("list".equals(cmd)) {
			as.selectAddrList(request);
			Map<String,Object> rMap = new HashMap<>();
			rMap.put("list", request.getAttribute("list"));
			rMap.put("page", request.getAttribute("page"));
			rMap.put("pageCount", request.getAttribute("pageCount"));
			rMap.put("blockCount",request.getAttribute("blockCount"));
			rMap.put("ad_dong", request.getParameter("ad_dong"));
			rMap.put("totalCnt", request.getAttribute("totalCnt"));
			rMap.put("fBlock", request.getAttribute("fBlock"));
			rMap.put("lBlock", request.getAttribute("lBlock"));
			rMap.put("totalPageCnt", request.getAttribute("totalPageCnt"));
			Command.printJSON(response, rMap);
		}else if("view".equals(cmd)) {
			as.selectAddr(request);
			Command.goPage(request, response, "/views/addr2/view");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Map<String,String> param = Command.fromJSON(request);
//		System.out.println(param);
//		int cnt=as.updateAddr(param);
//		Map<String,Object> rMap = new HashMap<>();
//		if(cnt==1) {
//			rMap.put("msg","true");
//			rMap.put("update","true");
//			Command.printJSON(response, rMap);
//		}else {
//			rMap.put("msg","false");
//			rMap.put("update","false");
//			Command.printJSON(response, rMap);
//		}			//나 
		
		String cmd = Command.getCmd(request); // 선생님 
		if("update".equals(cmd)) {
			Map<String,String> rMap = as.updateAddr2(request);
			Command.printJSON(response, rMap);
		}else if("delete".equals(cmd)) {
			Map<String,String> rMap = as.deleteAddr(request);
			Command.printJSON(response, rMap);
		}
	
	}

}