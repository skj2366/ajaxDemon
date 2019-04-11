package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import service.MovieService;
import service.impl.MovieServiceImpl;
import utils.Command;

public class AJAXMovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MovieService ms = new MovieServiceImpl();
	private Gson gson = new Gson();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = Command.getCmd(request);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
			System.out.println(cmd);
			if("list".equals(cmd)) {
				PrintWriter pw = response.getWriter();
				pw.println(gson.toJson(ms.selectMovieList()));
			}else {
				try {
					int miNum = Integer.parseInt(cmd);
					PrintWriter pw = response.getWriter();
					pw.println(gson.toJson(ms.selectMovieByMiNum(miNum)));
				}catch(Exception e) {
					throw new ServletException("올바른 상세조회 값이 아닙니다.");
				}
								
			}
		//}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
//		String uri = request.getRequestURI();
//		int idx = uri.lastIndexOf("/");
//		if(idx==0) {
//			throw new ServletException("원하는 서비스가 부정확합니다.");
//		}else {
//			String cmd = uri.substring(idx+1);
		String cmd = Command.getCmd(request);
			if("insert".equals(cmd)) {
				HttpSession hs = request.getSession();
				Map<String,String> rMap = new HashMap<>();
				if(hs.getAttribute("user")==null) {
					rMap.put("msg","로그인하세요");
					rMap.put("url", "/");
					Command.printJSON(response, rMap);
					//Command.goResultPage(request, response, "/", "로그인하세요");
//					request.setAttribute("msg", "로그인 해주세요.");
//					request.setAttribute("url", "/");
//					RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
//					rd.forward(request, response);
					return;
				}
//				Map<String,String> movie = new HashMap<>();
//				movie.put("mi_name", request.getParameter("mi_name"));
//				movie.put("mi_year", request.getParameter("mi_year"));
//				movie.put("mi_national", request.getParameter("mi_national"));
//				movie.put("mi_vendor", request.getParameter("mi_vendor"));
//				movie.put("mi_director", request.getParameter("mi_director"));
				Map<String,String> movie = Command.getSingleMap(request);
//				request.setAttribute("msg", "영화등록실패!");
				rMap.put("msg","영화등록실패!");
				rMap.put("url","/views/movie/ajax_list");
				if(ms.insertMovie(movie)==1) {
					rMap.put("msg","영화등록성공!");
//					request.setAttribute("msg", "영화등록성공");
//					msg = "영화등록성공!";
//					url = "/movie/list";
				}
				Command.printJSON(response, rMap);
//				request.setAttribute("url", "/movie/list");
//				RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
//				rd.forward(request, response);//선생님 방식.조금더 간결.
//				Command.goResultPage(request, response, url, msg);
				
//				if(ms.insertMovie(movie)==1) {//내방식
//					request.setAttribute("msg", "정상적으로 입력되었습니다");
//					request.setAttribute("url", "/movie/list");
//					RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
//					rd.forward(request, response);
//				}else {
//					request.setAttribute("msg", "비정상적으로 입력되었습니다");
//					request.setAttribute("url", "/movie/list");
//					RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
//					rd.forward(request, response);
//				}
				
				
			} else if("update".equals(cmd)) {
				HttpSession hs = request.getSession();
				if(hs.getAttribute("user")==null) {
					request.setAttribute("msg", "로그인 해주세요.");
					request.setAttribute("url", "/");
					RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
					rd.forward(request, response);
					return;
				}
			} else if("delete".equals(cmd)) {
				HttpSession hs = request.getSession();
				if(hs.getAttribute("user")==null) {
//					request.setAttribute("msg", "로그인 해주세요.");
//					request.setAttribute("url", "/");
//					RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
//					rd.forward(request, response);
					Command.goResultPage(request, response, "/", "로그인 해주세요.");
					return;
				}
				int miNum = Integer.parseInt(request.getParameter("mi_num"));
//				request.setAttribute("msg", "삭제에 실패하였습니다.");
//				request.setAttribute("url", "/movie/" + miNum);
				Map<String,String> rMap = new HashMap<>();
				rMap.put("msg","삭제에 실패하였습니다.");
				rMap.put("url", "/views/movie/ajax_list");
				if(ms.deleteMovie(miNum)==1) {
					rMap.put("msg","삭제에 성공하였습니다.");
//					request.setAttribute("msg", "삭제에 성공하였습니다.");
//					request.setAttribute("url", "/movie/list");
//					RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
//					rd.forward(request, response);
//					msg = "삭제 성공";
//					url = "/movie/list";
//					Command.goResultPage(request, response, url, msg);
				}
				Command.printJSON(response, rMap);
			}
		//}
		
	}

}
