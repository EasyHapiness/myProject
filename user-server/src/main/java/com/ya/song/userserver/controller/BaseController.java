package com.ya.song.userserver.controller;

import com.ya.song.userserver.common.ExceptionCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

//    @Autowired
//    private JwtTokenUtil jwtTokenUtil;

    /**
     * 成功响应code
     */
    private static final int RES_SUCCESS = 200;

    /**
     * 失败响应code
     */
    private static final int RES_FAIL = 201;

    /**
     * 成功响应返回结果
     *
     * @param msg
     * @param object
     * @return
     */
    protected Map<String, Object> getSuccessResult(String msg, Object object) {
        return createJson(true, RES_SUCCESS, msg, object);
    }

    protected Map<String, Object> getSuccessResult(Object object) {
        return createJson(true, RES_SUCCESS, "", object);
    }

    /**
     * 失败返回结果
     *
     * @param msg
     * @param object
     * @return
     */
    protected Map<String, Object> getFailResult(String msg, Object object) {
        return createJson(true, RES_FAIL, msg, object);
    }

    /**
     * 描述：获取失败结果
     * @param errorMsg
     * @return
     */
    protected Map<String, Object> getFailResult(String errorMsg) {
        return createJson(false, RES_FAIL, errorMsg, Collections.EMPTY_MAP);
    }

    /**
     * @param code
     * @param msg
     * @return
     */
    protected Map<String, Object> getFailResult(Integer code,String msg) {
        return createJson(false, code, msg, Collections.EMPTY_MAP);
    }

    /**
     * 异常捕获(枚举)
     * @param exceptionCode
     * @return
     */
    protected Map<String, Object> getFailResult(ExceptionCode exceptionCode) {
        return createJson(false, exceptionCode.getCode(), exceptionCode.getMsg(), Collections.EMPTY_MAP);
    }

    private Map<String, Object> createJson(boolean isOk, int resCode, String errorMsg, Object obj) {
        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("result", isOk ? "ok" : "fail");
        jsonMap.put("rescode", resCode);
        jsonMap.put("msg", errorMsg);
        jsonMap.put("data", obj);
        return jsonMap;
    }

//    protected String getUserIdByToken() {
//        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
//
//        LOGGER.info("================ " + request.getParameter("token"));
//        return jwtTokenUtil.getJwtUserIdFromToken(request.getHeader("token"));
//    }

}
