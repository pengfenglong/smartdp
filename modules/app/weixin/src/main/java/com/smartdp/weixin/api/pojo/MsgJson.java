package com.smartdp.weixin.api.pojo;

/**
 * 群发消息返回json对象
 *
 * @author pengfenglong
 */
public class MsgJson {
    private int ret;
    private String msg;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
