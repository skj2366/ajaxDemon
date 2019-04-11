package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

public class EncodingFilter implements Filter {

	private String encoding ="";
	public EncodingFilter() {
		System.out.println("난 무조건 첫빠따!");
	}
	public void destroy() {
		System.out.println("난 종료되어야 호출됨!");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		encoding = fConfig.getInitParameter("encoding");
		System.out.println("난 생선된 다음에 호출됨!!!");
	}

}
