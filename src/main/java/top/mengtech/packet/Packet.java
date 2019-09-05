package top.mengtech.packet;

import lombok.Data;

public abstract class Packet {

    /**
     * 协议版本
     */
    private Byte version = 1;

    /**
     * 指令，登录请求/登录响应/消息请求/消息响应
     * @return
     */
     public abstract Byte getCommand();

    public Byte getVersion() {
        return version;
    }

    public void setVersion(Byte version) {
        this.version = version;
    }
}

