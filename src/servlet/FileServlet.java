package servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FileService;
import service.impl.FIleServiceImpl;
import utils.Command;


public class FileServlet extends HttpServlet {

	private FileService fs = new FIleServiceImpl();
	private static final long serialVersionUID = 1L;

	public FileServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		fs.parseText(request);
		Map<String,String> rMap = fs.parseText(request);
		Command.printJSON(response, rMap);
		System.out.println(rMap);
	}

}
