package com.ya.song.zuulserver.common.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.ya.song.zuulserver.common.jwt.JwtTokenUtil;
import io.jsonwebtoken.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

@Component
public class AdminTokenFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(AdminTokenFilter.class);
    /**
     * 获取不过滤的url集合
     */
    @Autowired
    private UrlFilter urlFilter;
    @Autowired
    private JwtTokenUtil myJwtTokenUtil;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {//shouldFilter代表这个过滤器是否生效  true代表生效，false代表不生效
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String url = request.getRequestURI();
        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURI()));
        log.info(String.format("%s", urlFilter.getUrls().size()));
        log.info("b>>>" + urlFilter.getUrls().contains(url));
        if (url.contains("login")) {
            return false;
        }else
            return false;
        /*if (url.contains("/picture-server/files")) {
            return false;
        }
        if (urlFilter.getUrls().contains(url)) {
            return false;
        }
        return true;*/
    }

    @Override
    public Object run() {//这个是主要的处理逻辑的地方，我们做权限控制、日志等都是在这里。
        RequestContext ctx = RequestContext.getCurrentContext();

        HttpServletRequest request = ctx.getRequest();
        log.info(String
                .format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        //String token = request.getHeader("token");
        if ("OPTIONS".equals(request.getMethod())) {
            return null;
        }
        String token = null;
        if ("GET".equals(request.getMethod())) {
            token = request.getHeader("token");
            if (request.getHeader("token") == null) {
                token = request.getParameter("token");
            }
        } else {
            token = request.getHeader("token");
        }
        ctx.setSendZuulResponse(true);
        ctx.setResponseStatusCode(200);
        HttpServletResponse response = ctx.getResponse();
        response.setCharacterEncoding("UTF-8");//设置编码
        response.setContentType("application/json");//设置返回类型
        Writer out = null;
        Map<String, Object> oMap = new HashMap<String, Object>();
        if (StringUtils.isBlank(token)) {
            log.warn("token is empty");
//            oMap.put("code", ConstantsCode.OPERATION_ERROR_CODE);
            oMap.put("message", "token 为空，非法访问");
        } else {
            try {
                Claims claims = myJwtTokenUtil.getClaimsFromToken(token);
                oMap.put("code", 10000);
                return null;
            } catch (ExpiredJwtException e) {
                log.error("JWT 令牌过期:" + e.getMessage());
                oMap.put("code", 30000);
                oMap.put("message", "用户登录超时，请重新登录");
            } catch (UnsupportedJwtException e) {
                log.error("JWT 令牌无效:" + e.getMessage());
                oMap.put("code", 20000);
                oMap.put("message", "非法访问，请登录后重新访问");
            } catch (MalformedJwtException e) {
                log.error("JWT 令牌格式错误:" + e.getMessage());
                oMap.put("code", 20000);
                oMap.put("message", "非法访问，请登录后重新访问");
            } catch (SignatureException e) {
                log.error("JWT 令牌签名无效:" + e.getMessage());
                oMap.put("code", 20000);
                oMap.put("message", "非法访问，请登录后重新访问");
            } catch (IllegalArgumentException e) {
                log.error("JWT 令牌参数异常:" + e.getMessage());
                oMap.put("code", 20000);
                oMap.put("message", "非法访问，请登录后重新访问");
            } catch (Exception e) {
                log.error("JWT 令牌错误:" + e.getMessage());
                oMap.put("code", 20000);
                oMap.put("message", "非法访问，请登录后重新访问");
            }
        }
        if (!"10000".equals(oMap.get("code").toString())) {
            ctx.setSendZuulResponse(false);
            try {
                out = response.getWriter();
                out.write(JSONObject.toJSON(oMap).toString());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        log.info("ok");
        return null;
    }
}
