package cn.kgc.agentsystem.enums;

public enum ResultEnum {
    LOGIN_USERCODE_EMPTY(001, "LOGIN_USERCODE_EMPTY"),
    LOGIN_PASSWORD_EMPTY(002, "LOGIN_PASSWORD_EMPTY"),
    LOGIN_INFO_WRONG(003, "LOGIN_INFO_WRONG"),
    UPDATE_FAIL(004, "UPDATE_FAIL"),
    DATA_CAST_ERROR(005, "DATA_CAST_ERROR"),
    LOGIN_USER_ERRER(006, "LOGIN_USER_ERRER");
    int status;
    String msg;

    ResultEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
