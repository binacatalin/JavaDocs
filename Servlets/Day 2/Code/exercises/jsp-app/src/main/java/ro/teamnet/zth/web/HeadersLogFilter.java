package ro.teamnet.zth.web;

import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by user on 7/13/2016.
 */
public class HeadersLogFilter implements Filter {
    private String path = "/usr/local/tomcat/conf/logs/header.log";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String headerName, headerValue;

        Enumeration<String> e = httpReq.getHeaderNames();

        while(e.hasMoreElements()) {
            headerName = e.nextElement();
            headerValue = httpReq.getHeader(headerName);
            ro.teamnet.zth.file.LogFileWriter.logHeader(headerName, headerValue);
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
