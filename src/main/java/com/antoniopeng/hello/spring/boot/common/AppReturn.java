package com.antoniopeng.hello.spring.boot.common;

public class AppReturn {

    private Integer code;
    private String msg;
    private Object data;
    private Integer count;

    @Override
    public String toString() {
        return "AppReturn{" +
                "code=" + code +
                ", msg='" + msg +
                ", data=" + data +
                ", count=" + count +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public AppReturn() {
        super();
    }

    public AppReturn(Integer code, String msg, Integer count, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.count = count;
    }

    /**
     * 响应成功
     * @param msg 响应消息
     * @param count 响应行
     * @param data  响应数据
     * @return AppReturn
     */
    public static AppReturn succeed(String msg, Integer count, Object data) {
        return new AppReturn(200, msg, count, data);
    }

    /**
     * 响应成功
     * @param msg 响应消息
     * @return AppReturn
     */
    public static AppReturn succeed(String msg) {
        return new AppReturn(200, msg, 0, null);
    }

    /**
     * 响应失败
     * @param msg 响应消息
     * @return AppReturn
     */
    public static AppReturn defeated(String msg) {
        return new AppReturn(400, msg, null, 0);
    }

    /**
     * 响应异常
     * @return AppReturn
     */
    public static AppReturn exceptioned() {
        return new AppReturn(500, "服务端响应错误", null, 0);
    }

    /**
     * 自定义响应包
     * @param code 响应码
     * @param msg 响应消息
     * @param count 响应行
     * @param data  响应数据
     * @return
     */
    public static AppReturn build(Integer code, String msg, Integer count, Object data) {
        return new AppReturn(code, msg, count, data);
    }
}