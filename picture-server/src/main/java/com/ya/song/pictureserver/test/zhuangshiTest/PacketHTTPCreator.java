package com.ya.song.pictureserver.test.zhuangshiTest;

public class PacketHTTPCreator extends PacketDecorator {
    public PacketHTTPCreator(IPacketCreator info) {
        super(info);
    }

    @Override
    public String handleContent() {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("http");
        stringBuffer.append(component.handleContent());
        stringBuffer.append("<html>");
        return stringBuffer.toString();
    }
}
