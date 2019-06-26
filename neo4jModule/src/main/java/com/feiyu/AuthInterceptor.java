package com.feiyu;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.feiyu.model.Authorization;
import com.feiyu.model.ResponseBean;
import com.feiyu.model.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import sun.misc.BASE64Decoder;

public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private Authorization authorization;

    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String CONTENT_TYPE = "application/json; charset=utf-8";
    private static final String HEADER_AUTH = "Authorization";
    private static final String HEADER_BASIC = "basic";
    private static final String SPLIT_COLON = ":";
    private static final String STATIC_HEALTH = "/health";

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (null != request && STATIC_HEALTH.equals(request.getRequestURI())) {
            return true;
        }
        StatusCode statusCode = checkHTTPBasicAuthorize(request);
        if (statusCode != StatusCode.SUCCESSFUL) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setCharacterEncoding(CHARSET_UTF8);
            httpResponse.setContentType(CONTENT_TYPE);
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            ObjectMapper mapper = new ObjectMapper();
            ResponseBean responseBean = new ResponseBean(StatusCode.AUTHORIZATION_ERROR.getCode(), null);
            httpResponse.getWriter().write(mapper.writeValueAsString(responseBean));
            return false;
        } else {
            return true;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

    private StatusCode checkHTTPBasicAuthorize(ServletRequest request) {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String auth = httpRequest.getHeader(HEADER_AUTH);
            if ((auth != null) && (auth.length() > 6)) {
                String HeadStr = auth.substring(0, 5).toLowerCase();
                if (HeadStr.compareTo(HEADER_BASIC) == 0) {
                    auth = auth.substring(6, auth.length());
                    String decodedAuth = getFromBASE64(auth);
                    if (decodedAuth != null) {
                        String[] UserArray = decodedAuth.split(SPLIT_COLON);
                        if (UserArray != null && UserArray.length == 2) {
                            if (UserArray[0].compareTo(authorization.getUsername()) == 0
                                    && UserArray[1].compareTo(authorization.getPassword()) == 0) {
                                return StatusCode.SUCCESSFUL;
                            }
                        }
                    }
                }
            }
            return StatusCode.AUTHORIZATION_ERROR;
        } catch (Exception ex) {
            return StatusCode.AUTHORIZATION_ERROR;
        }

    }

    private String getFromBASE64(String s) {
        if (s == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            byte[] b = decoder.decodeBuffer(s);
            return new String(b);
        } catch (Exception e) {
            return null;
        }
    }
}

