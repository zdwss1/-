package utils;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Servlet Filter implementation class CharacterEncodingFilter
 */
@WebFilter(
		urlPatterns = {"/*"},
	    initParams = {
	    		
	    				@WebInitParam(name = "encoding",value="utf-8")
	    		
	    }
		)
public class CharacterEncodingFilter implements Filter {
    protected String encoding;
    public void destory() {}
    /**
     * Default constructor. 
     */


	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
        if(encoding!=null) {
        	//设置请求报文的字符编码
        	request.setCharacterEncoding(encoding);
        }
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		//获取初始化参数
		this.encoding = fConfig.getInitParameter("encoding");
	}

}
