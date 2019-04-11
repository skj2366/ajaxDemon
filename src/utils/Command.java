package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Command {

	private static final String RESULT_PATH = "/views/msg/result";
	private static Gson JSON = new Gson();
	public static String getCmd(HttpServletRequest req) throws ServletException { // 서블릿의 doGet, doPost도 똑같은 Exception을 throws 받고있기때문에 
																				// 서블릿에서 사용할때 try catch를 해주지 않아도 된다.
		String uri = req.getRequestURI();
		String cmd = req.getParameter("cmd");
		if(cmd!=null) {
			return cmd;
		}
		int idx = uri.lastIndexOf("/");
		if(idx==0 || idx==-1) {
			throw new ServletException("올바르지 않은 요청입니다.");
		}
		return uri.substring(idx+1);
	}
	
	public static void goResultPage(HttpServletRequest request, HttpServletResponse response, String url, String msg) throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher(RESULT_PATH);
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		rd.forward(request, response);
	}
	
	public static Map<String,String> getSingleMap(HttpServletRequest request)  {
		Map<String,String> pMap = new HashMap<>();
		Map<String,String[]> map = request.getParameterMap();
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = map.get(key)[0];
			pMap.put(key, value);
		}
		return pMap;
	}
	public static <T> void printJSON(HttpServletResponse response, T obj) throws IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(JSON.toJson(obj));
	}
	
	public static void goPage(HttpServletRequest request,HttpServletResponse response,String url) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	public static Map<String,String> fromJSON(HttpServletRequest request) throws IOException {
		InputStream is = request.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		
		StringBuffer sb = new StringBuffer();
		String line = null;
		while((line=br.readLine())!=null) {
			sb.append(line);
		}
		return JSON.fromJson(sb.toString(), Map.class);
	}
}
