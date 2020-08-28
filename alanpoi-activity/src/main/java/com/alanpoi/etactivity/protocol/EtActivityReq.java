package com.alanpoi.etactivity.protocol;

import io.netty.buffer.ByteBuf;

public class EtActivityReq {
    private byte cmd;
    private int etId;
    private int version;
    private int length;
    private byte[] body;

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

    public byte getCmd() {
        return cmd;
    }

    public void setCmd(byte cmd) {
        this.cmd = cmd;
    }

    public int getEtId() {
        return etId;
    }

    public void setEtId(int etId) {
        this.etId = etId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public byte[] toByteArray() {
        ByteBuf buf = ByteBufCache.getByteBuf();
        buf.writeByte(cmd);
        buf.writeInt(etId);
        buf.writeInt(version);
        buf.writeInt(length);
        if (body != null && body.length > 0)
            buf.writeBytes(body);
        return buf.array();
    }
}
