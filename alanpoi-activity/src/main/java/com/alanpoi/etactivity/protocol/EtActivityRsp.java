package com.alanpoi.etactivity.protocol;

import io.netty.buffer.ByteBuf;

public class EtActivityRsp {
    private short code;
    private int version;
    private int length;
    private int erLength;
    private byte[] body;
    private byte[] erMsg;

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public short getCode() {
        return code;
    }

    public void setCode(short code) {
        this.code = code;
    }

    public int getErLength() {
        return erLength;
    }

    public void setErLength(int erLength) {
        this.erLength = erLength;
    }

    public byte[] getErMsg() {
        return erMsg;
    }

    public void setErMsg(byte[] erMsg) {
        this.erMsg = erMsg;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public byte[] toByteArray() {
        ByteBuf buf = ByteBufCache.getByteBuf();
        buf.writeShort(code);
        buf.writeInt(version);
        buf.writeInt(length);
        buf.writeInt(erLength);
        if (body != null && body.length > 0)
            buf.writeBytes(body);
        if (erMsg != null && erMsg.length > 0)
            buf.writeBytes(erMsg);
        return buf.array();
    }
}
