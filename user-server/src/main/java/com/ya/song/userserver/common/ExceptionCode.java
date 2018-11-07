package com.ya.song.userserver.common;

public enum ExceptionCode {

    PARAM_ERROR(202, "参数异常"),
    SQL_ERROR(203, "数据库异常,可能是sql语句错误"),
    NULL_POINTER_ERROR(204, "空指针异常"),
    LOGIN_TIME_OUT(401,"您的登录已失效,请重新登录"),
    UNDEFINED_ERROR(201, "未定义异常"),
    OTHER_ERROR(500, "其它异常")
            ;

    //异常码
    private Integer code;
    //异常信息
    private String msg;

    private ExceptionCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //根据异常码获取异常信息
    public static String getMsg(Integer code) {
        for (ExceptionCode esnCode : ExceptionCode.values()) {
            if (esnCode.getCode() == code)
                return esnCode.getMsg();
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
