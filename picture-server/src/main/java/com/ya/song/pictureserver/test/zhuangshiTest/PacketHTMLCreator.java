package com.ya.song.pictureserver.test.zhuangshiTest;

public class PacketHTMLCreator extends PacketDecorator{

    public PacketHTMLCreator(IPacketCreator info) {
        super(info);
    }

    @Override
    public String handleContent() {

        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append("<html>");
        stringBuffer.append(component.handleContent());
        stringBuffer.append("<html>");
        return stringBuffer.toString();
    }
}
